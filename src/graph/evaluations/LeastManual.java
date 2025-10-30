package graph.evaluations;

import graph.NodeGraph;
import graph.ProductNode;
import graph.RecipeNode;
import items.Item;
import items.ItemStack;
import machines.MachineTypes;
import recipes.Recipe;
import recipes.Recipes;

import java.util.ArrayList;
import java.util.List;

public class LeastManual extends RecipeComparison {
    @Override
    public int compare(Recipe first, Recipe second, Item item) {
        if(item == null) {
            return 0;
        }

        double first_manual_count = getManualCount(first, item);
        double second_manual_count = getManualCount(second, item);

        // when either recipes lack the desired output, they should be unconsidered, in comparison
        if( first.getProductionRate(item) <= 0.0 ) {
            first_manual_count = Double.MAX_VALUE;
        }
        if( second.getProductionRate(item) <= 0.0 ) {
            second_manual_count = Double.MAX_VALUE;
        }

        int comparison = Double.compare(first_manual_count, second_manual_count);
        if(comparison == 0) {
            //when they are both equally-manual, prioritize the faster one (this was a subjective choice)
            return RecipeComparisons.FASTEST.compare(first, second, item);
        } else {
            return comparison;
        }
    }

    //In the usage of this function, it can be assumed that recipe.outputs contains at least one entry of prioritizedOutput
    private double getManualCount(Recipe recipe, Item prioritizedOutput) {
        for(ItemStack input : recipe.inputs) {
            //ensure each input has their lowest_manual-count_recipe(s) recorded
            if( !Recipes.optimalRecipes.containsKey(input) ) {
                Recipes.optimalRecipes.put(
                    input.item,
                    RecipeComparison.getBestRecipe(this, input.item)
                );
            }
        }

        List<ItemStack> unsourcedProducts = getUnsourcedProducts(recipe, prioritizedOutput);
        double manually_collected_source = 0.0;
        for(ItemStack unsourcedProduct : unsourcedProducts) {
            manually_collected_source += unsourcedProduct.quantity;
        }

        return manually_collected_source;
    }
    
    private List<ItemStack> getUnsourcedProducts(Recipe recipe, Item item) {
        List<ItemStack> unsourcedProducts = new ArrayList<>();

        NodeGraph productionGraph = new NodeGraph(item, recipe);
        for(ProductNode product : productionGraph.products) {
            if( productSourcesAreEmptyOrOnlyManual(product.sources) ) {
                unsourcedProducts.add(
                    new ItemStack(
                        product.product,
                        recipe.getItemInputCount(product.product) / recipe.getItemOutputCount(item)
                    )
                );
            }
        }

        return unsourcedProducts;
    }

    private boolean productSourcesAreEmptyOrOnlyManual(List<RecipeNode> sources) {
        for(RecipeNode source : sources) {
            if(
                source.recipe != null
                && !source.recipe.machineType.equals(MachineTypes.PLAYER)
            ) {
                return false;
            }
        }
        return true;
    }
}
