package graph.export;

import graph.*;
import items.Item;

import java.util.ArrayList;
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
    private static NodesNameMap getNodeIdentifiers(NodeGraph graph, String metaName) {

        final HashMap<ProductNode, String> productIdentifiers = new HashMap<>();
        //products
        int identifier_length = getIdentifiersLengthForGraphNodes(graph.products);
        String identifier = "a";
        for(ProductNode productNode : graph.products) {
            productIdentifiers.put(productNode, "p_"+metaName+"_"+identifier);
            identifier = incrementAlphabeticalString(identifier);
        }

        final HashMap<RecipeNode, String>  transformationIdentifiers = new HashMap<>();
        //transformers
        identifier_length = getIdentifiersLengthForGraphNodes(graph.transformers);
        identifier = "a";
        for(RecipeNode recipeNode : graph.transformers) {
            transformationIdentifiers.put(recipeNode, "t_"+metaName+"_"+identifier);
            identifier = incrementAlphabeticalString(identifier);
        }

        return new NodesNameMap(productIdentifiers, transformationIdentifiers);
    }

    public static String getDot(NodeGraph graph, boolean verbose) {
        return getDot(graph, "digraph", "}\r\n", "", verbose);
    }
    public static String getDot(NodeGraph graph, NodeGraphs.dividedNodeGraph graphData, boolean verbose) {
        HashMap<NodeGraph, String> subGraphs = new HashMap<>();
        List<Item> clusters = graphData.subGraphHeads;
        StringBuilder dotBuilder = new StringBuilder();

        //header for .dot file
        dotBuilder
            .append("\r\n")
            .append("digraph {\r\n")
            .append("\tcompound=true\r\n")
            .append("\r\n")
        ;

        /* subGraphs */ {
            //create subGraphs
            for(Item cluster : clusters) {
                double default_demand = 0.0;
                if( graph.getProduct(cluster) != null) {
                    default_demand = graph.getProduct(cluster).getDemandRate();
                }
                NodeGraph clusterGraph; {
                    List<Item> exclusions; {
                        exclusions = new ArrayList<>(clusters);
                        exclusions.remove(cluster);
                    }

                    clusterGraph = new NodeGraph(cluster, default_demand, exclusions);
                }
                subGraphs.put(clusterGraph, cluster.getName() );
            }

            //Correct "main" graph to only include its subgraph
            NodeGraph mainGraph; {
                Item finalItem = graph.getFinalProduct();
                double finalRate = graph.getProduct(finalItem).getProductionRate();
                mainGraph = new NodeGraph(finalItem, finalRate, clusters);
            }
            subGraphs.put(mainGraph, "main");

            //TODO: populate subGraph's final-product's demand(s)
            for( NodeGraph rateGraph : subGraphs.keySet() ) {
                if( rateGraph.equals(mainGraph) ) {
                    continue;
                }

                ProductNode mainOutput = rateGraph.getProduct( rateGraph.getFinalProduct() );
                double demand = mainOutput.default_demand;
                for( NodeGraph subGraph : subGraphs.keySet() ) {
                    if( rateGraph.equals(subGraph) ) {
                        continue;
                    }
                    if( subGraph.getProduct(mainOutput.product) != null ) {
                        demand += subGraph.getProduct(mainOutput.product).getDemandRate();
                    }
                }
                //reinitialize with new demand
                rateGraph.initialize(rateGraph.getFinalProduct(), demand);
            }

            //append subGraphs data
            for( NodeGraph subGraph : subGraphs.keySet() ) {
                String name = subGraphs.get(subGraph);
                dotBuilder
                    .append("\t")
                    .append(
                        getDot(
                            subGraph,
                            "subgraph cluster_"+name,
                            "}\r\n",
                            name,
                            verbose
                        )
                        .replace("\r\n", "\r\n\t")
                    )
                    .append("\r\n")
                ;
            }
        }

        /*Inter-subGraph connections*/ {
            HashMap< Item, List<String> > inputAndOutputsGraphVizNodeNames = new HashMap<>(); {
                String nodeName; NodesNameMap graphNames;
                for( NodeGraph subGraph : subGraphs.keySet() ) {
                    graphNames = getNodeIdentifiers( subGraph, subGraphs.get(subGraph) );
                    for(ProductNode product: subGraph.products) {
                        nodeName = graphNames.getName(product);
                        if( isInput(product) || isOutput(product) ) {
                            if( inputAndOutputsGraphVizNodeNames.containsKey(product.product) ) {
                                List<String> newNames = new ArrayList<>( inputAndOutputsGraphVizNodeNames.get(product.product) );
                                newNames.add(nodeName);

                                inputAndOutputsGraphVizNodeNames.replace(product.product, newNames);
                            } else {
                                inputAndOutputsGraphVizNodeNames.put( product.product, List.of(nodeName) );
                            }
                        }
                    }
                }
            }
            //TODO: make this less of a mess (?)
            for( Item potentialAnalogousItems : inputAndOutputsGraphVizNodeNames.keySet() ) {
                String[] names = inputAndOutputsGraphVizNodeNames.get(potentialAnalogousItems).toArray(new String[0]);
                if( names.length <= 1 ) {
                    continue;
                }

                String appendedNames; {
                    appendedNames = "{";
                    for(int index = 0; index < names.length; index++) {
                        appendedNames += names[index];
                        if(index < names.length-1) {
                            appendedNames += ", ";
                        }
                    }
                    appendedNames += "}";
                }
                dotBuilder
                    .append("\t")
                    .append(appendedNames)
                    .append(" -> ")
                    .append(appendedNames)
                    .append(" [style=invis]\r\n")
                ;
            }
            dotBuilder.append("\r\n");
        }

        dotBuilder.append("}\r\n");
        return dotBuilder.toString();
    }
    private static String getDot(NodeGraph graph, String prefix, String postfix, String nodeNamePrefix) {
        return getDot(graph, prefix, postfix, nodeNamePrefix, false);
    }
    private static String getDot(NodeGraph graph, String prefix, String postfix, String nodeNamePrefix, boolean verbose) {
        NodesNameMap names = getNodeIdentifiers(graph, nodeNamePrefix);

        StringBuilder dotBuilder = new StringBuilder();
        dotBuilder.append(prefix).append(" {\r\n");

        /* Nodes */ {
            /* Product Nodes */ {
                boolean isInput, isOutput;
                for(ProductNode product : names.productIdentifiers.keySet() ) {
                    isInput = isInput(product);
                    isOutput = isOutput(product);

                    String color; {
                        color = "ivory2";
                        if(isInput) {
                            color = "lightskyblue";
                        }
                        if(isOutput) {
                            color = "lightgoldenrod1";
                        }

                        //primary output
                        if( graph.getFinalProduct().equals(product.product) ) {
                            color = "gold";
                        }
                    }
                    dotBuilder
                        .append("\t")
                        .append( names.getName(product) )
                        .append(" [")
                            .append("shape=box, ")
                            .append("style=\"rounded,filled\"")
                            .append("fillcolor=\"").append(color).append("\", ")
                            .append("label=\"").append( product.product.getName() ).append("\"")
                        .append("]\r\n")
                    ;
                }
                dotBuilder.append("\r\n");
            }
            /* Recipe Nodes (Records) */ {
                for(RecipeNode transformation : names.transformationIdentifiers.keySet() ) {
                    String color; {
                        color = "lavenderblush";
                        if( isInput(transformation) ) {
                            color = "lavenderblush2";
                        }
                    }
                    dotBuilder
                        .append("\t")
                        .append( names.getName(transformation) )
                        .append(" [")
                            .append("shape=record, ")
                            .append("style=\"filled\", ")
                            .append("fillcolor=\"").append(color).append("\", ")
                            .append("label=\"").append( getRecipeNodeAsMachineRecord(transformation) ).append("\"")
                        .append("]")
                        .append("\r\n")
                    ;
                }
                dotBuilder.append("\r\n");
            }
        }

        /* Connections */ {
            //product -> transformer
            for(ProductNode productNode : graph.products) {
                for(int index = 0; index < productNode.sinks.size(); index++) {
                    RecipeNode sink = productNode.sinks.get(index);
                    String amountString = StringHelper.getNumberString(
                        sink.recipe.getItemInputCount(productNode.product)
                    );
                    dotBuilder
                        .append("\t")
                        .append( names.getName(productNode) ).append(" -> ").append(  names.getName(sink)  )
                    ;
                    if(verbose) {
                        dotBuilder.append(" [label=\"").append(amountString).append("\"]");
                    }
                    dotBuilder.append("\r\n");
                }
            }
            dotBuilder.append("\r\n");

            //transformer -> product
            for(RecipeNode recipeNode : graph.transformers) {
                for(int index = 0; index < recipeNode.outputs.size(); index++) {
                    ProductNode source = recipeNode.outputs.get(index);
                    String amountString = StringHelper.getNumberString(
                        recipeNode.recipe.getItemOutputCount(source.product)
                    );
                    dotBuilder
                        .append("\t")
                        .append( names.getName(recipeNode) ).append(" -> ").append( names.getName(source) )
                    ;
                    if(verbose) {
                        dotBuilder.append(" [label=\"").append(amountString).append("\"]");
                    }
                    dotBuilder.append("\r\n")
                    ;
                }
            }
        }

        dotBuilder.append(postfix);
        return dotBuilder.toString();
    }

    private static boolean isInput(ProductNode product) {
        return product.sources.isEmpty() && !product.sinks.isEmpty();
    }
    private static boolean isInput(RecipeNode transformer) {
        return transformer.inputs.isEmpty();
    }
    private static boolean isOutput(ProductNode product) {
        return !product.sources.isEmpty() && product.sinks.isEmpty();
    }

    private static String getRecipeNodeAsMachineRecord(RecipeNode recipe) {
        /* Formatting
        "{ {u| |w}|NAME|{x| |z} }", where
            u is the minimum voltage,
            w is the average up-time (%),
            x is the machine-configuration,
            z is the minimum (integer) machine-count
         */

        StringBuilder recordBuilder = new StringBuilder();
        recordBuilder.append("{ ");

        //top
        double single_machine_power_consumption = recipe.recipe.power_usage_per_tick;
        String minimumVoltageAbbreviatedName = recipe.recipe.machineType.getMinimumVoltageForLimit(single_machine_power_consumption).toString();
        int minimum_count; {
            minimum_count = (int)Math.ceil( recipe.getUptime() );
            minimum_count = Math.max(minimum_count, 1);
        }
        recordBuilder
            .append("{")
        ;
        if(single_machine_power_consumption != 0.0) {
            recordBuilder.append(single_machine_power_consumption).append(" EU/t (").append(minimumVoltageAbbreviatedName).append(")");
        }
        recordBuilder
            .append("|")
                //.append("")
            .append("|")
                .append( StringHelper.getNumberString( 100.0*recipe.getUptime()/minimum_count ) ).append("%")
            .append("}")
        ;

        //middle
        recordBuilder
            .append("|")
            .append( recipe.recipe.machineType.getName() )
            .append("|")
        ;

        //bottom
        recordBuilder
            .append("{")
                .append( recipe.recipe.configuration.toString() )
            .append("|")
                .append( StringHelper.getNumberString(recipe.recipe.time_seconds) ).append('s')
            .append("|")
                .append("×").append(minimum_count)
            .append("}")
        ;

        recordBuilder.append(" }");
        return recordBuilder.toString();
    }
}
