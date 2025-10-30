package recipes;

import graph.evaluations.RecipeComparison;
import items.Item;
import items.ItemStack;
import recipes.minecraft.GTNH.GTNH;
import recipes.minecraft.GTNH.GregTechRecipes;
import register.Registered;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static graph.evaluations.RecipeComparisons.*;
import static items.minecraft.GTNH.Vanilla.*;

public class Recipes extends Registered<Recipe> {
    private static Recipes instance;
    public static final List<Recipe> registry = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger("Recipes");
    public static HashMap<Item, Recipe> optimalRecipes = new HashMap<>();

    public Recipes() {
        super("Recipes", registry);
        instance = this;
    }

    public static boolean register(Recipe recipe) {
        if(instance == null) {
            instance = new Recipes();
            new GTNH(); //TODO: make clear this is where the chosen recipes are sourced
        }

        boolean wasSuccessfullyRegistered = instance.registerInstance(recipe);
        /*if(wasSuccessfullyRegistered) {
            //TODO: make this run only when it's done adding recipes
            optimalRecipes = new HashMap<>();
            calculateOptimalRecipes();
        }*/
        return wasSuccessfullyRegistered;
    }
    public static boolean isLeafRecipe(Recipe recipe) {
        return
            recipe.equals(COBBLESTONE)
        ;
    }

    public static void printAllComplexities() {
        StringBuilder complexitiesMapListBuilder = new StringBuilder();
        for(Recipe recipe : registry) {
            complexitiesMapListBuilder
                .append('"').append( recipe.toString() ).append("\": ")
                .append( recipe.complexity )
                .append("\r\n")
            ;
        }
        LOGGER.log(Level.INFO, complexitiesMapListBuilder.toString() );
    }

    private static ItemStack getItemStackOfSpecificItem(List<ItemStack> itemStacks, Item itemType) {
        ItemStack result = new ItemStack(itemType, 0);
        for(ItemStack itemStack : itemStacks) {
            if( itemStack.item.equals(itemType) ) {
                result = new ItemStack(itemType, result.quantity+itemStack.quantity);
            }
        }
        return result;
    }
    public static void calculateOptimalRecipes() {
        //Calculate optimal-recipes for sources first

        //TODO: more options
        optimalRecipes =
            RecipeComparison.getBestRecipes(FASTEST)
            //RecipeComparison.getBestRecipes(POWER_EFFICIENT)
            //RecipeComparison.getBestRecipes(RESOURCE_EFFICIENT)
        ;
    }

    public static Recipe getFastestProducingRecipe(Item ofItem) {
        //linear search
        double fastest_production_rate = 0.0, current_production_rate;
        Recipe fastestProducingRecipes = GregTechRecipes.DUMMY;
        for(Recipe potentiallyFastestProducingRecipe : Recipes.registry) {
            current_production_rate = potentiallyFastestProducingRecipe.getProductionRate(ofItem);
            if(fastest_production_rate < current_production_rate) {
                fastest_production_rate = current_production_rate;
                fastestProducingRecipes = potentiallyFastestProducingRecipe;
            }
        }

        return fastestProducingRecipes;
    }
}
