import graph.NodeGraph;
import graph.NodeGraphs;
import graph.export.CSAcademy;
import items.Items;

public class Main {
    /* TODO
     * Account for multiple power-types
     * Add README.md output-style [for NodeGraph]
     * Populate Recipes (more)
     * Alternate "best" evaluation criteria/criterion: Infrastructure-cost, building-time, production-time
     */
    public static void main(String[] args) {
        NodeGraph graph = new NodeGraph(Items.LIGHT_CONCRETE, 1);
        //Recipes.printAllComplexities();
        System.out.println(graph);
        //System.out.println( CSAcademy.getGraphData(graph) );
    }
}