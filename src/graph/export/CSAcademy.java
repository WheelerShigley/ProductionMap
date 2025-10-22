package graph.export;

import graph.NodeGraph;
import graph.ProductNode;
import graph.RecipeNode;

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
}
