import graph.NodeGraph;
import graph.export.GraphViz;
import graph.export.Local;
import items.Item;
import recipes.Recipe;
import recipes.Recipes;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import static graph.NodeGraphs.*;

public class Main {
    private static final Logger LOGGER = Logger.getLogger("Main");

    /* TODO
     * BUGFIX: ensure preferred-recipes are respected when instantiating multiple (primary) NodeGraphs
     * Refactor for "abstract" NodeGraphs (main + subgraphs)
     * Account for multiple power-types
     * Add README.md output-style [for NodeGraph]
     * Populate Recipes (more)
     * Alternate "best" evaluation criteria/criterion: Infrastructure-cost, building-time, production-time
     */
    private static final dividedNodeGraph GRAPH_DATA = CETANE_BOOSTED_DIESEL;
    public static final boolean VERBOSE_PRINTING = true;
    public static void main(String[] args) {
        initializeOptimalRecipes();

        NodeGraph GRAPH; {
            double maximum_monomachine_final_rate = Recipes.getOptimalRecipe(GRAPH_DATA.product).getProductionRate(GRAPH_DATA.product);
            GRAPH = new NodeGraph(GRAPH_DATA.product, maximum_monomachine_final_rate, GRAPH_DATA.forcedRecipes);
            GRAPH = new NodeGraph(GRAPH_DATA.product, maximum_monomachine_final_rate/GRAPH.getHighestUptime(), GRAPH_DATA.forcedRecipes);
        }

        //printGraphData(GRAPH, VERBOSE_PRINTING);
        printGraphData(GRAPH, GRAPH_DATA, VERBOSE_PRINTING);
    }

    private static void initializeOptimalRecipes() {
        Recipes.calculateOptimalRecipes();
    }

    private static void printGraphData(NodeGraph graph, boolean verbose) {
        String output = ""; {
            output += graph.toString() + "\r\n";
            //output += CSAcademy.getGraphWithSubGraphs(graph, subGraphHeads) + "\r\n";
        }

        String outputName = graph.getFinalProduct().getName() + ".dot";
        Local.writeToFile( outputName, GraphViz.getDot(graph, verbose) );

        LOGGER.log(Level.INFO, output);
    }
    private static void printGraphData(NodeGraph graph, dividedNodeGraph graphData, boolean verbose) {
        Item finalItem = graph.getFinalProduct();
        double rate = graph.getProduct(finalItem).getProductionRate();

        String output = ""; {
            output += graph+"\r\n";
            //output += CSAcademy.getGraphWithSubGraphs(graph, subGraphHeads) + "\r\n";
        }

        String outputName = graph.getFinalProduct().getName() + ".dot";
        Local.writeToFile( outputName, GraphViz.getDot(graph, graphData, verbose) );

        LOGGER.log(Level.INFO, output);
    }
}