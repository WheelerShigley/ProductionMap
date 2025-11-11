package graph.export;

import graph.NodeGraph;
import graph.ProductNode;
import graph.RecipeNode;
import items.Item;

import java.util.ArrayList;
import java.util.List;

public class CSAcademy {
    public static String getGraphData(NodeGraph graph) {
        StringBuilder markUpBuilder = new StringBuilder();
        //list all products
        for(ProductNode product : graph.products) {
            markUpBuilder.append('"').append( product.product.getName() ).append("\"\r\n");
        }
        //list all transformations
        for(RecipeNode transformation : graph.transformers) {
            markUpBuilder.append('"').append( transformation.toString() ).append("\"\r\n");
        }
        //list all connections
        for(ProductNode product : graph.products) {
            for(RecipeNode input : product.sources) {
                markUpBuilder
                    .append('"').append( input.toString() ).append("\" \"")
                    .append( product.product.getName() ).append("\"\r\n");
            }
            for(RecipeNode output : product.sinks) {
                markUpBuilder
                    .append('"').append( product.product.getName() ).append("\" \"")
                    .append( output.toString() ).append("\"\r\n")
                ;
            }
        }
        /*
        for(RecipeNode transformation : graph.transformers) {
            for(ProductNode output : transformation.outputs) {
                markUpBuilder
                    .append('"').append( transformation.toString() ).append("\" \"")
                    .append( output.product.getName() ).append("\"\r\n")
                ;
            }
        }
         */
        return markUpBuilder.toString();
    }

    public static String getGraphWithSubGraphs(NodeGraph graph, List<Item> subGraphHeads) {
        StringBuilder graphString = new StringBuilder();
        graphString.append( getGraphData(graph) ).append("\r\n");

        for(Item subGraphHead : subGraphHeads) {
            ProductNode subGraphHeadNode = graph.getProduct(subGraphHead);
            if(subGraphHeadNode == null) {
                continue;
            }
            List<Item> exclusionsWithoutCurrentHead; {
                exclusionsWithoutCurrentHead = new ArrayList<>(subGraphHeads);
                exclusionsWithoutCurrentHead.remove(subGraphHead);
            }
            NodeGraph subGraph = new NodeGraph(
                subGraphHead,
                subGraphHeadNode.getDemandRate(),
                graph.transformerOverrides,
                exclusionsWithoutCurrentHead
            );

            graphString.append( getGraphData(subGraph) ).append("\r\n");
        }

        return graphString.toString();
    }
}
