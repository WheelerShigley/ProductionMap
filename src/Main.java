import graph.NodeGraph;
import graph.export.CSAcademy;
import items.minecraft.GTNH.GregTech;

public class Main {
    /* TODO
     * Account for multiple power-types
     * Add README.md output-style [for NodeGraph]
     * Populate Recipes (more)
     * Alternate "best" evaluation criteria/criterion: Infrastructure-cost, building-time, production-time
     */
    public static void main(String[] args) {
        NodeGraph graph = new NodeGraph(GregTech.OXYGEN_CELL, 1.0/60.0);
        //Recipes.printAllComplexities();
        System.out.println(graph);
        System.out.println("\r\n");
        System.out.println( CSAcademy.getGraphData(graph) );
    }
}