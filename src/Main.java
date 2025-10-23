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
        //Recipes.printAllComplexities();

        List<Item> subGraphHeads = new ArrayList<>(); {
            subGraphHeads.add(GregTech.HYDROGEN_CELL);
            subGraphHeads.add(GregTech.CHLOROFORM_CELL);
            subGraphHeads.add(GregTech.FLUORINE);
            subGraphHeads.add(GregTech.OXYGEN);
        }

        NodeGraph mainGraph = new NodeGraph(GregTech.POLYTETRAFLUOROETHYLENE_BAR, 1.0/60.0, subGraphHeads);
        printGraphData(mainGraph);

        /*for(Item subGraphHead : subGraphHeads) {
            List<Item> exclusionsWithoutCurrentHead; {
                exclusionsWithoutCurrentHead = new ArrayList<>(subGraphHeads);
                exclusionsWithoutCurrentHead.remove(subGraphHead);
            }
            NodeGraph subGraph = new NodeGraph(
                subGraphHead,
                mainGraph.getProduct(subGraphHead).getDemandRate(),
                exclusionsWithoutCurrentHead
            );

            System.out.println("\r\n");
            printGraphData(subGraph, "\t");
        }*/
    }

    private static void printGraphData(NodeGraph graph) {
        //System.out.println(graph);
        //System.out.println("\r\n");
        //System.out.println( CSAcademy.getGraphData(graph) );
        //System.out.println("\r\n");
        System.out.println( GraphViz.getDot(graph) );
    }
    private static void printGraphData(NodeGraph graph, String prefix) {
        String graphData = prefix + graph.toString();
        graphData = graphData.replace("\r\n", "\r\n"+prefix);
        System.out.println(graphData);

        System.out.println("\r\n");

        String csAcademyGraphData = prefix + CSAcademy.getGraphData(graph);
        csAcademyGraphData = csAcademyGraphData.replace("\r\n", "\r\n"+prefix);
        System.out.println(csAcademyGraphData);
    }
}