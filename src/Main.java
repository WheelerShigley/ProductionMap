import graph.NodeGraph;
import graph.export.CSAcademy;
import graph.export.GraphViz;
import items.Item;
import items.minecraft.GTNH.GregTech;
import recipes.Recipes;

import java.util.ArrayList;
import java.util.List;

public class Main {
    /* TODO
     * Account for multiple power-types
     * Add README.md output-style [for NodeGraph]
     * Populate Recipes (more)
     * Alternate "best" evaluation criteria/criterion: Infrastructure-cost, building-time, production-time
     */
    public static void main(String[] args) {
        Recipes.calculateOptimalRecipes();

        List<Item> subGraphHeads = new ArrayList<>(); {
            subGraphHeads.add(GregTech.HYDROGEN_CELL);
            subGraphHeads.add(GregTech.CHLOROFORM_CELL);
            subGraphHeads.add(GregTech.FLUORINE);
            subGraphHeads.add(GregTech.OXYGEN);
        }

        NodeGraph mainGraph = new NodeGraph(GregTech.POLYTETRAFLUOROETHYLENE_BAR, 1, subGraphHeads);
        printGraphData(mainGraph, subGraphHeads);
    }

    private static void printGraphData(NodeGraph graph) {
        //System.out.println(graph);
        //System.out.println("\r\n");
        //System.out.println( CSAcademy.getGraphData(graph) );
        //System.out.println("\r\n");
        System.out.println( GraphViz.getDot(graph) );
    }

    private static void printGraphData(NodeGraph graph, List<Item> subGraphHeads) {
        //System.out.println(graph);
        //System.out.println("\r\n");
        //System.out.println( CSAcademy.getGraphData(graph) );
        //System.out.println("\r\n");
        System.out.println( GraphViz.getDot(graph, subGraphHeads) );
    }
}