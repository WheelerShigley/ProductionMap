package graph.evaluations;

import items.Item;
import recipes.Recipe;

public class PowerEfficient extends RecipeComparison {
    @Override
    public int compare(Recipe first, Recipe second, Item item) {
        if(item == null) {
            return 0;
        }

        double first_energy_per_cycle = first.power_usage_per_tick * first.time_seconds*20.0;
        double second_energy_per_cycle = second.power_usage_per_tick * second.time_seconds*20.0;

        double first_efficiency = first.getItemOutputCount(item) / first_energy_per_cycle;
        double second_efficiency = second.getItemOutputCount(item) / second_energy_per_cycle;
        return Double.compare(first_efficiency, second_efficiency);
    }
}
