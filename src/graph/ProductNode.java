package graph;

import items.Item;
import machines.MachineType;
import machines.MachineTypes;
import recipes.Recipe;
import recipes.Recipes;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductNode {
    public final Item product;
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
        updateSourceUptimes();
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
        updateSourceUptimes();
        return true;
    }

    public boolean generateSource(NodeGraph graph) {
        Recipe generatedSource = Recipes.optimalRecipes.get(this.product);
        if(
            generatedSource == null
            || generatedSource.equals(Recipes.DUMMY)
            || generatedSource.machineType.equals(MachineTypes.DUMMY)
        ) {
            return false;
        }

        //Check if RecipeNode already exists
        for(RecipeNode recipeNode : graph.transformers) {
            if( recipeNode.recipe.equals(generatedSource) ) {
                recipeNode.addOutput(this);
                updateSourceUptimes();
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
            updateSourceUptimes();
            return true;
        }
        return false;
    }

    private void updateSourceUptimes() {
        double unmet_demand_rate = getUnmetDemandRate();
        if(unmet_demand_rate <= 0.0) {
            return;
        }
        //adjust uptimes to the lowest interval of 100%s first (utilizing existing machine-time)
        for(RecipeNode source : sources) {
            if(unmet_demand_rate <= 0.0) {
                return;
            }
            //calculate required uptime to meet demand
            double required_uptime = source.getUptime() + unmet_demand_rate/getProductionRate();
            double next_uptime_centainterval = source.getUptime();
            if(next_uptime_centainterval%1.0 != 0.0) {
                next_uptime_centainterval = Math.ceil(next_uptime_centainterval);
            }
            //if available rate needed exceeds the nearest 100%, set the uptime to the nearest 100%; adjust demand
            if(required_uptime < next_uptime_centainterval) {
                source.setUpTime(required_uptime);
                return;
            } else {
                source.setUpTime(next_uptime_centainterval);
                unmet_demand_rate = getUnmetDemandRate();
            }
        }
        //TODO: Make temporal-evaluation for "best"
        //adjust the "best" producer to meet demand; in this case, the evaluation-criterion is merely production-speed
        RecipeNode mostEfficientProducer = null; {
            double mostEfficientProductionRate = 0.0;
            double currentProductionRate;
            for(RecipeNode source : sources) {
                currentProductionRate = source.getProductionRate(this.product);
                if(mostEfficientProductionRate < currentProductionRate) {
                    mostEfficientProductionRate = currentProductionRate;
                    mostEfficientProducer = source;
                }
            }
            if(mostEfficientProducer == null) {
                //no producer is found
                Logger.getLogger("ProductNode<"+this.product.getName()+">").log(Level.INFO, "No sources found.");
                return;
            }
        }
        mostEfficientProducer.setUpTime(
            mostEfficientProducer.getUptime() + unmet_demand_rate/mostEfficientProducer.getProductionRate(this.product)
        );
        return;
    }

    private double getProductionRate() {
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
        double demand_rate = 0.0;
        for(RecipeNode sink : sinks) {
            demand_rate += sink.getUsageRate(product);
        }
        return demand_rate;
    }
    public double getUnmetDemandRate() {
        return getDemandRate() - getProductionRate();
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
            double rate = 20.0*this.getProductionRate();
            if(rate%1.0 == 0.0) {
                productNodeStringBuilder.append( (int)rate );
            } else {
                productNodeStringBuilder.append( Math.round(rate*10000.0)/10000.0 );
            }
            productNodeStringBuilder.append("/second");
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
