package graph.evaluations;

import items.Item;
import items.ItemStack;
import items.Items;
import items.minecraft.GTNH.GregTech;
import recipes.Recipe;
import recipes.Recipes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public abstract class RecipeComparison implements Comparator<Recipe> {
    @Override
    public int compare(Recipe first, Recipe second) {
        Item item = first.outputs.getFirst().item;
        return compare(first, second, item);
    }
    public abstract int compare(Recipe first, Recipe second, Item item);

    public static HashMap<Item, Recipe> getBestRecipes(RecipeComparison comparator) {
        HashMap<Item, Recipe> bestRecipes = new HashMap<>();
        for(Item item : Items.registry) {
            if( !bestRecipes.containsKey(item) ) {
                bestRecipes.put(
                    item,
                    getBestRecipe(comparator, item)
                );
            }
        }
        return bestRecipes;
    }

    static Recipe getBestRecipe(RecipeComparison comparator, Item ofItem) {
        if( Recipes.optimalRecipes.containsKey(ofItem) ) {
            return Recipes.optimalRecipes.get(ofItem);
        }

        List<Recipe> recipes; {
            recipes = new ArrayList<>();
            for(Recipe recipe : Recipes.registry) {
                if( itemStacksContainItem(recipe.outputs, ofItem) ) {
                    recipes.add(recipe);
                }
            }
        }

        Recipe bestRecipe = Recipes.DUMMY;
        for(Recipe recipe : recipes) {
            if( comparator.compare(bestRecipe, recipe, ofItem) == 1) {
                bestRecipe = recipe;
            }
        }

        return bestRecipe;
    }

    public static boolean itemStacksContainItem(List<ItemStack> stacks, Item item) {
        if(item == null) {
            return false;
        }

        for(ItemStack stack : stacks) {
            if( stack.item.equals(item) ) {
                return true;
            }
        }
        return false;
    }
}
