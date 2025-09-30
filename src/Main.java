import map.Maps;
import recipes.Recipes;

public class Main {
    /* TODO
     * Refactor Recipes; Dynamically Generate optimized preferred recipes
     * Redo Pollution Calculations
     * Redo Machines Counting (based on power demands, at voltages)
     *
     * List all inputs, and EU-count
     * Rename MachineNode to GTNHMachineNode (abstract MachineNode)
     *
     * Add to consolidated branches, when one already exists?
     * 3y Using byproducts?
     * 3z ^Prioritized Input-sources
     *
     * Looping? (Closed Loops too)
     *
     * Refactor Map.toString() helper functions to be easier(?) to understand
     */
    public static void main(String[] args) {
        System.out.println( Maps.ELECTRONIC_CIRCUIT );
        //Recipes.printAllComplexities();
    }
}