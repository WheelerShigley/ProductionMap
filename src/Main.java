import graph.NodeGraph;
import graph.export.GraphViz;
import items.Item;
import items.minecraft.GTNH.GregTech;
import recipes.Recipe;
import recipes.Recipes;

import java.util.HashMap;
import java.util.List;

import static graph.NodeGraphs.*;

public class Main {
    /* TODO
     * BUGFIX: ensure preferred-recipes are respected when instantiating multiple (primary) NodeGraphs    
     * Account for multiple power-types
     * Add README.md output-style [for NodeGraph]
     * Populate Recipes (more)
     * Alternate "best" evaluation criteria/criterion: Infrastructure-cost, building-time, production-time
     */
    private static final dividedNodeGraph GRAPH_DATA = ELECTRONIC_CIRCUIT;
    public static void main(String[] args) {
        initializeOptimalRecipes(GRAPH_DATA.forcedRecipes);

        final NodeGraph GRAPH = new NodeGraph(GRAPH_DATA.product, 1, GRAPH_DATA.subGraphHeads);
        printGraphData(GRAPH, GRAPH_DATA.subGraphHeads);
    }

    private static void initializeOptimalRecipes() {
        initializeOptimalRecipes( new HashMap<Item, Recipe>() );
    }
    private static void initializeOptimalRecipes(HashMap<Item, Recipe> forcedRecipes) {
        Recipes.calculateOptimalRecipes();

        //custom changes
        for( Item forcedSourcedItem : forcedRecipes.keySet() ) {
            Recipes.optimalRecipes.replace(forcedSourcedItem, forcedRecipes.get(forcedSourcedItem) );
        }

        //HashMap<Item, Recipe> recipes = Recipes.optimalRecipes;
        //Recipe si = Recipes.optimalRecipes.get(GregTech.RAW_SILICON_DUST);
    }

    private static void printGraphData(NodeGraph graph) {
        System.out.println( GraphViz.getDot(graph) );
    }
    private static void printGraphData(NodeGraph graph, List<Item> subGraphHeads) {
        System.out.println( GraphViz.getDot(graph, subGraphHeads) );
    }
}