package graph;

import items.Item;
import items.ItemStack;
import machines.MachineTypes;
import power.PowerType;
import recipes.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeNode {
    public final Recipe recipe;
    public List<ProductNode> inputs = new ArrayList<>();
    public List<ProductNode> outputs = new ArrayList<>();

    PowerType powerType;
    private double uptime;

    public RecipeNode(Recipe recipe) {
        this.recipe = recipe;
    }

    public boolean addInput(ProductNode input) {
        //ensure output is not already present
        for(ProductNode potentialInput : inputs) {
            if(potentialInput == input) {
                return false;
            }
        }

        inputs.add(input);
        updateUpTime();
        return true;
    }
    public boolean addOutput(ProductNode output) {
        //ensure output is not already present
        for(ProductNode potentialOutput : outputs) {
            if(potentialOutput == output) {
                return false;
            }
        }

        outputs.add(output);
        updateUpTime();
        return true;
    }

    public void generateSurroundings(NodeGraph graph) {
        //for non-existing I/O, create new nodes for them (via recipe)
        this.inputs = new ArrayList<>();
        for(ItemStack missingProduct : recipe.inputs) {
            ProductNode foundConnection = graph.getProduct(missingProduct.item);
            if(foundConnection != null) {
                inputs.add(foundConnection);
                foundConnection.addSink(this);
                continue;
            }

            ProductNode createdProductNode = new ProductNode(missingProduct.item);
            if( graph.addProduct(createdProductNode) ) {
                createdProductNode.addSink(this);
                this.addInput(createdProductNode);
                updateUpTime();
            } else {
                //TODO: Warn
            }
        }
        this.outputs = new ArrayList<>();
        for(ItemStack missingProduct : recipe.outputs) {
            ProductNode foundConnection = graph.getProduct(missingProduct.item);
            if(foundConnection != null) {
                outputs.add(foundConnection);
                foundConnection.addSource(this);
                continue;
            }

            ProductNode createdProductNode = new ProductNode(missingProduct.item);
            if( graph.addProduct(createdProductNode) ) {
                createdProductNode.addSource(this);
                this.addOutput(createdProductNode);
                updateUpTime();
            } else {
                //TODO: Warn
            }
        }
    }

    //TODO
    private void updateUpTime() {
        //TODO: flags for which outputs are the primary one(s)
        //for each output, check for the highest-demanding product; set speed for the highest demand-speed
        double maximum_unmet_demand = 0.0, unmet_demand_rate;
        for(ProductNode output : outputs) {
            unmet_demand_rate = output.getUnmetDemandRate()/this.getProductionRate(output.product);
            maximum_unmet_demand = Math.max(maximum_unmet_demand, unmet_demand_rate);
        }
        setUpTime(getUptime() + maximum_unmet_demand);
    }

    public void setUpTime(Double uptime) {
        this.uptime = uptime;
    }

    public double getUsageRate(Item ofItem) {
        double usage_rate = 0.0;
        for(ItemStack usedItem : recipe.inputs) {
            if( usedItem.item.equals(ofItem) ) {
                usage_rate += usedItem.quantity;
            }
        }
        usage_rate /= this.recipe.time_seconds;
        usage_rate *= uptime;
        return usage_rate;
    }
    public double getProductionRate(Item ofItem) {
        if(this.recipe == null) {
            return 0.0;
        }

        double production_rate = 0.0;
        for(ItemStack producedItem : recipe.outputs) {
            if( producedItem.item.equals(ofItem) ) {
                production_rate += producedItem.quantity;
            }
        }
        production_rate /= this.recipe.time_seconds;
        return production_rate;
    }

    public double getUptime() {
        return uptime;
    }

    @Override
    public String toString() {
        StringBuilder recipeNodeStringBuilder = new StringBuilder();
        recipeNodeStringBuilder.append( recipe.machineType.getName() ).append("(");

        for(int index = 0; index < inputs.size(); index++) {
            double quantity = recipe.inputs.get(index).quantity;
            if(quantity != 1.0) {
                double a = quantity%1.0;
                if(quantity%1.0 == 0.0) {
                    recipeNodeStringBuilder.append( (int)recipe.inputs.get(index).quantity ).append("×");
                } else {
                    recipeNodeStringBuilder.append(
                        Math.round(recipe.inputs.get(index).quantity*1000.0)/1000.0
                    ).append("×");
                }
            }
            recipeNodeStringBuilder.append( recipe.inputs.get(index).item );
            if(index < recipe.inputs.size()-1) {
                recipeNodeStringBuilder.append(" + ");
            }
        }

        recipeNodeStringBuilder.append(") = ");
        for(int index = 0; index < outputs.size(); index++) {
            double quantity = recipe.outputs.get(index).quantity;
            if(quantity != 1.0) {
                if(quantity%1.0 == 0.0) {
                    recipeNodeStringBuilder.append( (int)recipe.outputs.get(index).quantity ).append("×");
                } else {
                    recipeNodeStringBuilder.append(
                        Math.round(recipe.outputs.get(index).quantity*10000.0)/10000.0
                    ).append("×");
                }
            }
            recipeNodeStringBuilder.append( recipe.outputs.get(index).item );
            ;
            if(index < recipe.outputs.size()-1) {
                recipeNodeStringBuilder.append(" + ");
            }
        }

        if( this.recipe.machineType.equals(MachineTypes.PLAYER) ) {
            recipeNodeStringBuilder.append(" (manual)");
            return recipeNodeStringBuilder.toString();
        }

        double uptime_percentage = this.getUptime();
        recipeNodeStringBuilder.append(" @ ");
        if(uptime_percentage%1.0 == 0.0) {
            recipeNodeStringBuilder.append(100*(int)uptime_percentage);
        } else {
            recipeNodeStringBuilder.append( 100.0*Math.round(this.getUptime()*10000.0)/10000.0 );
        }
        recipeNodeStringBuilder.append("%");

        return recipeNodeStringBuilder.toString();
    }
}
