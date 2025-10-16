package graph.evaluations;

import graph.NodeGraph;
import graph.ProductNode;
import items.Item;
import items.ItemStack;
import items.minecraft.GTNH.GregTech;
import recipes.Recipe;
import recipes.Recipes;

import java.util.List;

public class ResourceEfficient extends RecipeComparison {
    @Override
    public int compare(Recipe first, Recipe second, Item item) {
        //input validation
        if(
            item == null
            || (first == null && second == null)
            || ( first.outputs.isEmpty() || second.outputs.isEmpty() )
        ) {
            return 0;
        }
        if(first == null) {
            return Double.compare( 0.0, second.getProductionRate(item) );
        }
        if(second == null) {
            return Double.compare( 0.0, first.getProductionRate(item) );
        }

        //if this is a loop, skip (TODO: ensure this is robust against recipe-chained-loops)
        if(
            ( itemStacksContainItem(second.inputs, item) && itemStacksContainItem(first.outputs, item) )
            || ( itemStacksContainItem(first.inputs, item) && itemStacksContainItem(second.outputs, item) )
        ) {
            return 0;
        }

        //ensure sources are complete first (unless the source is a loop)
        for(ItemStack stack : first.inputs) {
            if( !Recipes.optimalRecipes.containsKey(stack.item) ) {
                Recipes.optimalRecipes.put(
                    item,
                    getBestRecipe(this, stack.item)
                );
            }
        }
        for(ItemStack stack : second.inputs) {
            if( !Recipes.optimalRecipes.containsKey(stack.item) ) {
                Recipes.optimalRecipes.put(
                    item,
                    getBestRecipe(this, stack.item)
                );
            }
        }

        //sum all sources of leaf-node's resources (accounting for uptime)
        double first_resource_count = 0.0; {
            List<ProductNode> firstSources = new NodeGraph(item, first, 1.0).getLeafProductNodes();
            for(ProductNode firstSource : firstSources) {
                first_resource_count += firstSource.getDemandRate();
            }
        }
        double second_resource_count = 0.0; {
            List<ProductNode> secondSources = new NodeGraph(item, first, 1.0).getLeafProductNodes();
            for(ProductNode secondSource : secondSources) {
                second_resource_count += secondSource.getDemandRate();
            }
        }

        if(first_resource_count == second_resource_count) {
            //TODO: option?
            return RecipeComparisons.FASTEST.compare(first, second, item);
        }

        return Double.compare(first_resource_count, second_resource_count);
    }

    private double getCounts(List<ItemStack> stacks) {
        double count = 0.0;
        for(ItemStack stack : stacks) {
            count += stack.quantity;
        }
        return count;
    }
}
