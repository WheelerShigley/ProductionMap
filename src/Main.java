import graph.NodeGraph;
import graph.NodeGraphs;
import graph.export.CSAcademy;
import items.Items;
import map.Maps;
import recipes.Recipes;

public class Main {
    /* TODO
     * Fix Leaf-nodes not printing + Branches not Consolidating
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
        //System.out.println( Maps.ELECTRONIC_CIRCUIT );
        //Recipes.printAllComplexities();
        //System.out.println( CSAcademy.getGraphData(NodeGraphs.electronicCircuitGraph) );
        NodeGraph test = new NodeGraph(Items.OXYGEN_GAS, 1);
        System.out.println(test);
    }
}