package recipes;

import graph.evaluations.RecipeComparison;
import items.Item;
import items.ItemStack;
import items.minecraft.GTNH.GregTech;
import machines.MachineTypes;
import recipes.minecraft.GTNH.GTNH;
import recipes.minecraft.GTNH.GregTechRecipes;
import recipes.minecraft.GTNH.thaumcraft.ThaumcraftRecipes;
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
    private static HashMap<Item, Recipe> optimalRecipes = new HashMap<>();

    public static final Recipe DUMMY = new Recipe(MachineTypes.PLAYER, 0.0, new ArrayList<>(), Double.MIN_VALUE);

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
            recipe.equals(GregTechRecipes.COBBLESTONE)
            || recipe.equals(GregTechRecipes.STONE)
            || recipe.equals(ThaumcraftRecipes.THIRSTYTANK_WATER)
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

    public static void calculateOptimalRecipes() {
        //Calculate optimal-recipes for sources first

        //TODO: more options
        optimalRecipes =
            RecipeComparison.getBestRecipes(FASTEST)
            //RecipeComparison.getBestRecipes(POWER_EFFICIENT)
            //RecipeComparison.getBestRecipes(RESOURCE_EFFICIENT)
        ;
    }
    public static Recipe getOptimalRecipe(Item ofItem) {
        return optimalRecipes.getOrDefault(ofItem, Recipes.DUMMY);
    }
    public static Recipe getOptimalRecipe(Item ofItem, HashMap<Item, Recipe> overrides) {
        if( overrides.containsKey(ofItem) ) {
            return overrides.get(ofItem);
        }
        return getOptimalRecipe(ofItem);
    }

    @Deprecated
    public static Recipe getFastestProducingRecipe(Item ofItem) {
        //linear search
        double fastest_production_rate = 0.0, current_production_rate;
        Recipe fastestProducingRecipes = Recipes.DUMMY;
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
