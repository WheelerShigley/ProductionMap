package graph;

import items.Item;
import items.Items;
import items.minecraft.GTNH.GregTech;
import items.minecraft.GTNH.IndustrialCraft;
import machines.MachineTypes;
import recipes.Recipe;
import recipes.Recipes;
import recipes.minecraft.GTNH.GregTechRecipes;

import java.lang.reflect.GenericSignatureFormatError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductNode {
    public final Item product;
    public double default_demand = 0.0;
    public final List<RecipeNode> sources = new ArrayList<>();
    public final List<RecipeNode> sinks = new ArrayList<>();

    public ProductNode(Item product) {
        this.product = product;
    }

    public boolean addSource(RecipeNode source, HashMap<Item, Recipe> overrides) {
        //ensure it's not already in the sources (by reference)
        for(RecipeNode existingSource : sources) {
            if(existingSource == source) {
                return false;
            }
        }

        sources.add(source);
        forceUpdateSourceUptimes(overrides);
        return true;
    }
    public boolean addSink(RecipeNode sink) {
        //ensure it's not already in the sources (by reference)
        for(RecipeNode existingSink : sinks) {
            if(existingSink == sink) {
                return false;
            }
        }

        sinks.add(sink);
        return true;
    }

    public boolean generateSource(NodeGraph graph, HashMap<Item, Recipe> overrides) {
        Recipe bestSourceRecipe = Recipes.getOptimalRecipe(this.product, overrides);

        if(
            bestSourceRecipe == null
            || bestSourceRecipe.equals(Recipes.DUMMY)
            || bestSourceRecipe.machineType.equals(MachineTypes.DUMMY)
        ) {
            return false;
        }

        //Check if RecipeNode already exists
        for(RecipeNode recipeNode : graph.transformers) {
            if( recipeNode.recipe.equals(bestSourceRecipe) ) {
                recipeNode.addOutput(this, overrides);
                forceUpdateSourceUptimes(overrides);
                return true;
            }
        }

        RecipeNode generatedSourceNode; {
            double unmet_demand_rate = this.getUnmetDemandRate();
            double production_rate = bestSourceRecipe.getProductionRate(this.product);
            if(0.0 < production_rate) {
                unmet_demand_rate /= production_rate;
            } else {
                unmet_demand_rate = 0.0;
            }

            generatedSourceNode = new RecipeNode(bestSourceRecipe);
            generatedSourceNode.addOutput(this, overrides);
            generatedSourceNode.setUpTime(unmet_demand_rate, overrides);
        }

        if( graph.addTransformer(generatedSourceNode) ) {
            this.addSource(generatedSourceNode, overrides);
            forceUpdateSourceUptimes(overrides);
            return true;
        }
        return false;
    }

    public boolean updateSourceUptimes(HashMap<Item, Recipe> overrides) {
        if( getUnmetDemandRate() <= 0.0) {
            return true;
        }

        double demand_rate = getDemandRate();
        Recipe bestSourceRecipe = Recipes.getOptimalRecipe(this.product, overrides);
        double production_rate;
        //check for existing source that may meet demand
        for(RecipeNode source : sources) {
            production_rate = source.getProductionRate(this.product);
            if(0.0 < production_rate) {
                demand_rate /= production_rate;
            } else {
                demand_rate = 0.0;
            }
            if( source.recipe.equals(bestSourceRecipe) ) {
                source.setUpTime(demand_rate, overrides);
                return true;
            }
        }
        return false;
    }

    public RecipeNode forceUpdateSourceUptimes(HashMap<Item, Recipe> overrides) {
        if( updateSourceUptimes(overrides) ) {
            return null;
        }

        //meet demand
        RecipeNode newProducer = new RecipeNode( Recipes.getOptimalRecipe(this.product, overrides) );
        newProducer.setUpTime(
            getUnmetDemandRate()/newProducer.getProductionRate(this.product),
            overrides
        );
        this.addSource(newProducer, overrides);
        return newProducer;
    }

    public double getProductionRate() {
        double production_rate = 0.0;
        for(RecipeNode source : sources) {
            production_rate += source.getUptime()*source.getProductionRate(product);
        }
        return production_rate;
    }
    public double getAvailableProductionRate() {
        return getProductionRate() - getDemandRate();
    }
    public double getDemandRate() {
        double demand_rate = default_demand;
        for(RecipeNode sink : sinks) {
            demand_rate += sink.getUsageRate(product);
        }
        return demand_rate;
    }
    public double getUnmetDemandRate() {
        double unmet_demand_rate = getDemandRate();
        if(unmet_demand_rate <= 0.0) {
            unmet_demand_rate += default_demand;
        }
        unmet_demand_rate -= getProductionRate();

        if(unmet_demand_rate < 0.0) {
            unmet_demand_rate = 0.0;
        }
        return unmet_demand_rate;
    }
    public int getSourceCount() {
        return sources.size();
    }

    @Override
    public String toString() {
        StringBuilder productNodeStringBuilder = new StringBuilder();
        productNodeStringBuilder.append(this.product);
        if( sources.isEmpty() ) {
            productNodeStringBuilder.append(" (no source)");
        } else {
            productNodeStringBuilder.append(" @ ");
            double rate = this.getProductionRate();
            if(rate == 0.0) {
                rate = default_demand;
            }
            productNodeStringBuilder
                .append( StringHelper.getNumberString(rate) )
                .append("/second")
            ;
        }

        /*
        if(
            MachineTypes.isLeafMachine(
                Recipes.getOptimalRecipe(this.product).machineType
            )
        ) {
            productNodeStringBuilder.append(" (unautomated)");
        }
         */

        return productNodeStringBuilder.toString();
    }
}
