import graph.NodeGraph;
import graph.export.GraphViz;
import items.Item;
import recipes.Recipe;
import recipes.Recipes;

import java.util.HashMap;

import static graph.NodeGraphs.*;

public class Main {
    /* TODO
     * BUGFIX: ensure preferred-recipes are respected when instantiating multiple (primary) NodeGraphs
     * Refactor for "abstract" NodeGraphs (main + subgraphs)
     * Account for multiple power-types
     * Add README.md output-style [for NodeGraph]
     * Populate Recipes (more)
     * Alternate "best" evaluation criteria/criterion: Infrastructure-cost, building-time, production-time
     */
    private static final dividedNodeGraph GRAPH_DATA = TNT;
    public static void main(String[] args) {
        initializeOptimalRecipes(GRAPH_DATA.forcedRecipes);
        double maximum_monomachine_final_rate = Recipes.optimalRecipes.get(GRAPH_DATA.product).getProductionRate(GRAPH_DATA.product);

        final NodeGraph GRAPH = new NodeGraph(GRAPH_DATA.product, maximum_monomachine_final_rate/(42.0*0.992064));
        printGraphData(GRAPH, GRAPH_DATA);
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
        String output = ""; {
            output += graph.toString() + "\r\n";
            //output += CSAcademy.getGraphWithSubGraphs(graph, subGraphHeads) + "\r\n";
            output += GraphViz.getDot(graph) + "\r\n";
        }

        System.out.println(output);
    }
    private static void printGraphData(NodeGraph graph, dividedNodeGraph graphData) {
        Item finalItem = graph.getFinalProduct();
        double rate = graph.getProduct(finalItem).getProductionRate();
        final NodeGraph GRAPH = new NodeGraph(GRAPH_DATA.product, rate);

        String output = ""; {
            output += GRAPH.toString() + "\r\n";
            //output += CSAcademy.getGraphWithSubGraphs(graph, subGraphHeads) + "\r\n";
            output += GraphViz.getDot(graph, graphData) + "\r\n";
        }

        System.out.println(output);
    }
}