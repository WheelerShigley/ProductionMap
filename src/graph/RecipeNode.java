package graph;

import items.Item;
import items.ItemStack;
import power.PowerType;
import recipes.Recipe;
import recipes.Recipes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class RecipeNode {
    public final Recipe recipe;
    public List<ProductNode> inputs = new ArrayList<>();
    public List<ProductNode> outputs = new ArrayList<>();

    PowerType powerType;
    double power_rate;
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
        if(this.recipe.equals(Recipes.REDSTONE_ALLOY_DUST)) {
            int a = 0;
        }
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

    }

    public void setUpTime(Double uptime) {
        this.uptime = uptime;
    }

    public double getUsageRate(Item ofItem) {
        double usage_rate = 0.0;
        for(ItemStack usedItem : recipe.inputs) {
            if( usedItem.item.equals(ofItem) ) {
                usage_rate += usedItem.quantity / ( (double)recipe.getTimeInTicks() );
            }
        }
        return usage_rate;
    }
    public double getProductionRate(Item ofItem) {
        double production_rate = 0.0;
        for(ItemStack producedItem : recipe.outputs) {
            if( producedItem.item.equals(ofItem) ) {
                production_rate += producedItem.quantity / ( (double)recipe.getTimeInTicks() );
            }
        }
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
            recipeNodeStringBuilder
                .append( recipe.inputs.get(index).quantity ).append("*")
                .append( recipe.inputs.get(index).item )
            ;
            if(index < recipe.inputs.size()-1) {
                recipeNodeStringBuilder.append(" + ");
            }
        }
        if( inputs.isEmpty() ) {
            recipeNodeStringBuilder.append("[Nothing]");
        }

        recipeNodeStringBuilder.append(" = ");
        for(int index = 0; index < outputs.size(); index++) {
            recipeNodeStringBuilder
                .append( recipe.outputs.get(index).quantity ).append("*")
                .append( recipe.outputs.get(index).item )
            ;
            if(index < recipe.outputs.size()-1) {
                recipeNodeStringBuilder.append(" + ");
            }
        }

        recipeNodeStringBuilder.append(");");
        return recipeNodeStringBuilder.toString();
    }
}
