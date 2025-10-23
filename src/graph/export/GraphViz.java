package graph.export;

import graph.NodeGraph;
import graph.ProductNode;
import graph.RecipeNode;

import java.util.HashMap;
import java.util.List;

public class GraphViz {
    private static class NodesNameMap {
        public final HashMap<ProductNode, String> productIdentifiers;
        public final HashMap<RecipeNode, String>  transformationIdentifiers;

        public NodesNameMap(
            HashMap<ProductNode, String> productIdentifiers,
            HashMap<RecipeNode, String>  transformationIdentifiers
        ) {
            this.productIdentifiers = productIdentifiers;
            this.transformationIdentifiers = transformationIdentifiers;
        }

        public String getName(ProductNode productNode) {
            if( productIdentifiers.containsKey(productNode) ) {
                return productIdentifiers.get(productNode);
            }
            return "";
        }
        public String getName(RecipeNode recipeNode) {
            if( transformationIdentifiers.containsKey(recipeNode) ) {
                return transformationIdentifiers.get(recipeNode);
            }
            return "";
        }
    }

    private static int getIdentifiersLengthForGraphNodes(List<?> list) {
        /* LOGARITHMS:
            log_b(x) = log_y(x)/log_y(b), where
                b is the base (26 in this case),
                x is the input, and
                y is any base (in Java, bases 10 and e are built-in)
            ; so, log_26(n) = ln(n)/ln(26).
         */
        return (int)Math.ceil(
            Math.log( list.size() ) / Math.log(26)
        );
    }
    private static char incrementAlphabeticalLetter(char letter) {
        if(
            ('A' <= letter && letter < 'Z')
            || ('a' <= letter && letter < 'z')
        ) {
            return ++letter;
        }

        return letter;
    }
    private static String incrementAlphabeticalString(String alphabeticalString) {
        char[] alphabeticalCharacterArray = alphabeticalString.toCharArray();

        char current_character;
        for(int index = alphabeticalCharacterArray.length-1; 0 <= index; --index) {
            current_character = alphabeticalCharacterArray[index];
            alphabeticalCharacterArray[index] = incrementAlphabeticalLetter(current_character);
            if(current_character != 'z') {
                return new String(alphabeticalCharacterArray);
            }
        }
        if( alphabeticalCharacterArray[0] == 'z') {
            return "a" + new String(alphabeticalCharacterArray);
        } else {
            return new String(alphabeticalCharacterArray);
        }
    }
    private static NodesNameMap getNodeIdentifiers(NodeGraph graph) {

        final HashMap<ProductNode, String> productIdentifiers = new HashMap<>();
        //products
        int identifier_length = getIdentifiersLengthForGraphNodes(graph.products);
        String identifier = "a";
        for(ProductNode productNode : graph.products) {
            productIdentifiers.put(productNode, "p_"+identifier);
            identifier = incrementAlphabeticalString(identifier);
        }

        final HashMap<RecipeNode, String>  transformationIdentifiers = new HashMap<>();
        //transformers
        identifier_length = getIdentifiersLengthForGraphNodes(graph.transformers);
        identifier = "a";
        for(RecipeNode recipeNode : graph.transformers) {
            transformationIdentifiers.put(recipeNode, "t_"+identifier);
            identifier = incrementAlphabeticalString(identifier);
        }

        return new NodesNameMap(productIdentifiers, transformationIdentifiers);
    }

    public static String getDot(NodeGraph graph) {
        NodesNameMap names = getNodeIdentifiers(graph);

        StringBuilder dotBuilder = new StringBuilder();
        dotBuilder.append("digraph {\r\n");

        /* Nodes */ {
            //product nodes
            boolean isInput, isOutput;
            for(ProductNode product : names.productIdentifiers.keySet() ) {
                isInput = isInput(product);
                isOutput = isOutput(product);

                dotBuilder
                    .append("\t")
                    .append( names.getName(product) )
                    .append(" [")
                        .append("shape=box,")
                        .append("style=\"rounded").append( isInput || isOutput ? ", filled" : "").append("\",")
                        .append("label=\"").append( product.product.getName() ).append("\"")
                        .append( isInput ? ", fillcolor=\"darkslategray2\"" : "")
                        .append( isOutput ? ", fillcolor=\"darkgoldenrod2\"" : "")
                    .append("]\r\n")
                ;
            }
            dotBuilder.append("\r\n");

            //recipes nodes
            for(RecipeNode transformation : names.transformationIdentifiers.keySet() ) {
                dotBuilder
                    .append("\t")
                    .append( names.getName(transformation) )
                    .append(" [shape=box, label=\"").append( transformation.recipe.machineType.getName() ).append("\"]")
                    .append("\r\n")
                ;
            }
            dotBuilder.append("\r\n");
        }

        /* Connections */ {
            HashMap< String, List<String> > existingConnections = new HashMap<>();
            StringBuilder connectionsBuilder = new StringBuilder();

            //product -> transformer
            for(ProductNode productNode : graph.products) {
                connectionsBuilder.setLength(0);
                for(int index = 0; index < productNode.sinks.size(); index++) {
                    connectionsBuilder.append(  names.getName( productNode.sinks.get(index) )  );
                    if(index < productNode.sinks.size()-1 ) {
                        connectionsBuilder.append(',');
                    }

                    dotBuilder
                        .append("\t")
                        .append( names.getName(productNode) )
                        .append(" -> {")
                        .append( connectionsBuilder.toString() )
                        .append("}\r\n")
                    ;
                }
            }
            dotBuilder.append("\r\n");

            //transformer -> product
            for(RecipeNode recipeNode : graph.transformers) {
                connectionsBuilder.setLength(0);
                for(int index = 0; index < recipeNode.outputs.size(); index++) {
                    connectionsBuilder.append(  names.getName( recipeNode.outputs.get(index) )  );
                    if(index < recipeNode.outputs.size()-1 ) {
                        connectionsBuilder.append(',');
                    }
                }

                dotBuilder
                    .append("\t")
                    .append( names.getName(recipeNode) )
                    .append(" -> {")
                    .append( connectionsBuilder.toString() )
                    .append("}\r\n")
                ;
            }
        }

        dotBuilder.append("}\r\n");
        return dotBuilder.toString();
    }

    private static boolean isInput(ProductNode product) {
        return product.sources.isEmpty() && !product.sinks.isEmpty();
    }
    private static boolean isOutput(ProductNode product) {
        return !product.sources.isEmpty() && product.sinks.isEmpty();
    }
}
