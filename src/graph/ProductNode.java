package graph;

import items.Item;
import machines.MachineTypes;
import recipes.Recipe;
import recipes.Recipes;
import recipes.minecraft.GTNH.GregTechRecipes;

import java.util.ArrayList;
import java.util.List;

public class ProductNode {
    public final Item product;
    public double default_demand = 0.0;
    public final List<RecipeNode> sources = new ArrayList<>();
    public final List<RecipeNode> sinks = new ArrayList<>();

    public ProductNode(Item product) {
        this.product = product;
    }

    public boolean addSource(RecipeNode source) {
        //ensure it's not already in the sources (by reference)
        for(RecipeNode existingSource : sources) {
            if(existingSource == source) {
                return false;
            }
        }

        sources.add(source);
        forceUpdateSourceUptimes();
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

    public boolean generateSource(NodeGraph graph) {
        Recipe generatedSource = Recipes.optimalRecipes.get(this.product);
        if(
            generatedSource == null
            || generatedSource.equals(GregTechRecipes.DUMMY)
            || generatedSource.machineType.equals(MachineTypes.DUMMY)
        ) {
            return false;
        }

        //Check if RecipeNode already exists
        for(RecipeNode recipeNode : graph.transformers) {
            if( recipeNode.recipe.equals(generatedSource) ) {
                recipeNode.addOutput(this);
                forceUpdateSourceUptimes();
                return true;
            }
        }

        RecipeNode generatedSourceNode; {
            generatedSourceNode = new RecipeNode(generatedSource);
            generatedSourceNode.addOutput(this);
            generatedSourceNode.setUpTime(
                this.getUnmetDemandRate()/generatedSource.getProductionRate(this.product)
            );
        }

        if( graph.addTransformer(generatedSourceNode) ) {
            this.addSource(generatedSourceNode);
            forceUpdateSourceUptimes();
            return true;
        }
        return false;
    }

    public boolean updateSourceUptimes() {
        double unmet_demand_rate = getUnmetDemandRate();
        if(unmet_demand_rate <= 0.0) {
            return true;
        }

        Recipe bestSourceRecipe = Recipes.optimalRecipes.get(this.product);
        //check for existing source that may meet demand
        for(RecipeNode source : sources) {
            if(source.recipe.equals(bestSourceRecipe) ) {
                source.setUpTime(
                    source.getUptime() + unmet_demand_rate/source.getProductionRate(this.product)
                );
                return true;
            }
        }
        return false;
    }

    public RecipeNode forceUpdateSourceUptimes() {
        if( updateSourceUptimes() ) {
            return null;
        }

        //meet demand
        Recipe bestSourceRecipe = Recipes.optimalRecipes.get(this.product);
        RecipeNode newProducer = new RecipeNode(bestSourceRecipe);
        newProducer.setUpTime(
            getUnmetDemandRate()/newProducer.getProductionRate(this.product)
        );
        this.addSource(newProducer);
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
        return ( default_demand + getDemandRate() ) - getProductionRate();
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
            productNodeStringBuilder
                .append( StringHelper.getNumberString(rate) )
                .append("/second")
            ;
        }

        if(
            MachineTypes.isLeafMachine(
                Recipes.optimalRecipes.get(this.product).machineType
            )
        ) {
            productNodeStringBuilder.append(" (unautomated)");
        }

        return productNodeStringBuilder.toString();
    }
}
