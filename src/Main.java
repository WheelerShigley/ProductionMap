import graph.NodeGraph;
import graph.NodeGraphs;
import graph.export.CSAcademy;
import items.Items;
import recipes.Recipe;
import recipes.Recipes;

import java.util.List;

public class Main {
    /* TODO
     * Account for multiple power-types
     * Add README.md output-style [for NodeGraph]
     * Populate Recipes (more)
     * Alternate "best" evaluation criteria/criterion: Infrastructure-cost, building-time, production-time
     */
    public static void main(String[] args) {
        NodeGraph graph = new NodeGraph(Items.SALIS_MUNDUS, 1.0/60.0);
        //Recipes.printAllComplexities();
        System.out.println(graph);
        System.out.println( CSAcademy.getGraphData(graph) );
    }
}