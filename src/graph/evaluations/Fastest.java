package graph.evaluations;

import items.Item;
import recipes.Recipe;

public class Fastest extends RecipeComparison {
    @Override
    public int compare(Recipe first, Recipe second, Item item) {
        if(item == null) {
            return 0;
        }

        double first_rate = first.getProductionRate(item);
        double second_rate = second.getProductionRate(item);
        return Double.compare(first_rate, second_rate);
    }
}
