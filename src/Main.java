import map.Map;
import map.MapHelper;
import map.Maps;

public class Main {
    public static void main(String[] args) {
        /* TODO
         * Add Electricity Demands (in Recipes)
         * Calculate System's Average Power Demands
         * Calculate System's [Theoretical] Maximum Power Demand
         * Add Pollution Outputs (in Recipes)
         * Calculate System's Pollution Output
         *
         * Fix branches of output map?
         */
        final Map ELECTRIC_CIRCUIT = Maps.ELECTRIC_CIRCUIT;
        MapHelper.calculateUptimes(ELECTRIC_CIRCUIT);
        MapHelper.printMap(ELECTRIC_CIRCUIT);
    }
}