package map;

import items.Item;
import items.ItemStack;
import items.Items;
import machines.MachineConfiguration;
import machines.Machines;
import machines.Voltage;
import recipes.ItemStackWithPreferredRecipeSource;
import recipes.Recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Map {
    private final MachineNode head;

    @Deprecated
    public Map(Recipe recipe) {
        this.head = new MachineNode(recipe);
        recursivelyGenerateRecipesFromPreferredSources(head);
    }
    public Map(Recipe recipe, double uptime) {
        this.head = new MachineNode(recipe);
        recursivelyGenerateRecipesFromPreferredSources(head);
        head.setUptime(uptime);
        calculateUptimes(this);
    }

    //# toString() and Helpers
    @Override
    public String toString() {
        final StringBuilder MAP_STRING_BUILDER = new StringBuilder();

        //print tree (branched node-graph)
        HashMap<Integer, Boolean> linePositions = new HashMap<Integer, Boolean>();
        linePositions.put(0, true);
        getMapNodeAsString(this, null, this.head, linePositions, 0, MAP_STRING_BUILDER);

        //additional data
        MAP_STRING_BUILDER.append("\r\n");
        MAP_STRING_BUILDER.append( getAveragePowerConsumptionString(this) ).append("\r\n");
        MAP_STRING_BUILDER.append( getAveragePollutionRateString(this) );

        return MAP_STRING_BUILDER.toString();
    }
    private static String getAveragePowerConsumptionString(Map map) {
        final StringBuilder AVERAGE_POWER_CONSUMPTION_STRING_BUILDER = new StringBuilder();

        HashMap<Voltage, Double> averagePowerConsumption = getAveragePowerConsumption( map.getHead() );
        AVERAGE_POWER_CONSUMPTION_STRING_BUILDER.append("Average Power Consumption = { ");
        int index = 0;
        for(Voltage voltage : averagePowerConsumption.keySet() ) {
            if( voltage.equals(Voltage.None) ) {
                continue;
            }

            AVERAGE_POWER_CONSUMPTION_STRING_BUILDER
                .append(
                    Math.round( 100.0*averagePowerConsumption.get(voltage) )/100.0
                ).append("A ")
                .append( voltage.toString() )
            ;

            if(index < averagePowerConsumption.size()-2) {
                AVERAGE_POWER_CONSUMPTION_STRING_BUILDER.append(", ");
            }
            index++;
        }
        AVERAGE_POWER_CONSUMPTION_STRING_BUILDER.append('}');

        return AVERAGE_POWER_CONSUMPTION_STRING_BUILDER.toString();
    }
    private static String getAveragePollutionRateString(Map map) {
        double rate = getAveragePollutionRate( map.getHead() );

        return "Average Pollution Rate = " + Math.round(100.0*rate)/100.0 +" pollution/second.";
    }

    private static void getMapNodeAsString(
        Map map, MachineNode parent,
        MachineNode node,
        HashMap<Integer, Boolean> depthsWithLines, int depth,
        final StringBuilder STRINGBUILDER
    ) {
        //add existing pipes (unfinished branches)
        for(int index = 0; index < depth-1; index++) {
            if( depthsWithLines.containsKey(index) && depthsWithLines.get(index) ) {
                STRINGBUILDER.append( getPipe(index) );
            } else {
                STRINGBUILDER.append(' ');
            }
            STRINGBUILDER.append("\t");
        }

        //head node needs not be derived from another recipe
        MachineNode lastSourceOfParent = getLastSource(parent);
        if(  !node.equals( map.getHead() )  ) {
            boolean isLastInLine = lastSourceOfParent != null && lastSourceOfParent.equals(node);
            if( depthsWithLines.containsKey(depth-1) ) {
                depthsWithLines.replace(depth-1, !isLastInLine);
            } else {
                depthsWithLines.put(depth-1, !isLastInLine);
            }
            STRINGBUILDER
                .append( isLastInLine ? getAngledPipe(depth-1) : getSplitPipe(depth-1) )
                .append(' ')
            ;
        }
        STRINGBUILDER.append(node);

        //% (and/or quantity of machines); % need not be shown for manual tasks
        STRINGBUILDER.append(" [");
        if(node.recipe != null) {
            STRINGBUILDER.append( node.recipe.machine.toString() );
        }
        if( node.recipe != null && !node.recipe.circuit.equals(MachineConfiguration.None) ) {
            STRINGBUILDER.append(", ").append( node.recipe.circuit.toString() );
        }
        STRINGBUILDER.append(']');

        //uptime
        if(
            0.0 <= node.calculated_uptime
            && node.recipe != null
            && !node.recipe.machine.equals(Machines.PLAYER)
        ) {
            STRINGBUILDER.append("@").append( Math.round(10000.0*node.calculated_uptime)/100.0 ).append('%');
        }

        STRINGBUILDER.append("\r\n");

        //print sources
        for(MachineNode source : node.sources) {
            getMapNodeAsString(map, node, source, depthsWithLines, depth + 1, STRINGBUILDER);
        }
    }
    private static MachineNode getLastSource(MachineNode node) {
        if(node == null) {
            return null;
        }
        if( node.sources.isEmpty() ) {
            return null;
        }

        MachineNode lastSource = null;
        for(MachineNode source : node.sources) {
            lastSource = source;
        }
        return lastSource;
    }
    private static char getPipe(int depth) {
        return switch(depth%4) {
            //case 0, 1 -> '│';
            case 2, 3 ->   '║';
            default ->  '│';
        };
    }
    private static char getSplitPipe(int depth) {
        return switch(depth%4) {
            //case 0 -> '├';
            case 1 ->   '╞';
            case 2 ->   '╟';
            case 3 ->   '╠';
            default ->  '├';
        };
    }
    private static char getAngledPipe(int depth) {
        return switch(depth%4) {
            //case 0 -> '└';
            case 1 ->   '╘';
            case 2 ->   '╙';
            case 3 ->   '╚';
            default ->  '└';
        };
    }

    //# GENERATORS
    private static void recursivelyGenerateRecipesFromPreferredSources(MachineNode node) {
        //for leaf-nodes, return
        if(
                node.recipe == null
                        || node.recipe.machine.equals(Machines.PLAYER)
                        || node.recipe.machine.equals(Machines.CELL_CYCLING)
                        || itemStacksAreNothing( node.recipe.getInputsAsItemStacks() )
        ) {
            return;
        }

        //create new sources
        List<MachineNode> sourceNodes = new ArrayList<MachineNode>();
        for(ItemStackWithPreferredRecipeSource inputWithSource : node.recipe.inputs ) {
            sourceNodes.add(
                    new MachineNode(inputWithSource.preferredRecipeSource)
            );
        }
        node.sources = sourceNodes;

        //generate sources for each source
        for(MachineNode sourceNode : sourceNodes) {
            recursivelyGenerateRecipesFromPreferredSources(sourceNode);
        }
    }
    private static boolean itemStacksAreNothing(List<ItemStack> items) {
        for(ItemStack item : items) {
            if( !item.item().equals(Items.NOTHING) ) {
                return false;
            }
        }
        return true;
    }

    private static void calculateUptimes(Map map) {
        Logger LOGGER = Logger.getLogger("uptime_calculation");

        //discover expected limited factor (one line should be set to an expected uptime)
        MachineNode limitingFactor = discoverPrecalculatedUptimeMachine( map.getHead() );
        if(limitingFactor == null) {
            LOGGER.log(Level.WARNING, "No expected-uptime found; cannot calculate uptimes.");
            return;
        }

        //back-propagate uptimes
        backPropagateUptimes(limitingFactor);

        //recursively forward-to-back-propagate uptimes, until reaching the head
        MachineNode precursor = discoverParentOfPrecalculatedUptimeMachine( map.getHead() );
        if(precursor == null) {
            LOGGER.log(Level.WARNING, "No expected-uptime-parent found; final-machine is the limiting factor, or cannot calculate uptimes.");
            return;
        }
        while(  precursor != null  &&  !precursor.equals( map.getHead() )  ) {
            //find precursor
            MachineNode calculatedNode = discoverPrecalculatedUptimeMachine( map.getHead() );
            if(calculatedNode == null) {
                continue;
            }

            //find common item between precursor and pre-calculated node
            Item commonItem = getFirstItemInCommon( precursor.recipe.getInputsAsItemStacks(), calculatedNode.recipe.outputs );
            if(commonItem == null) {
                LOGGER.log(Level.WARNING, "No expected-uptime and parent overlap found; cannot calculate uptimes.");
                return;
            }

            //calculate production rates for precursor
            double uptime; {
                double potential_rate = precursor.recipe.time_seconds / getQuantityOfDesiredIO(precursor.recipe.getInputsAsItemStacks(), commonItem);
                double available_rate = ( calculatedNode.recipe.time_seconds / calculatedNode.calculated_uptime )  /  getQuantityOfDesiredIO(calculatedNode.recipe.outputs, commonItem);
                uptime = potential_rate/available_rate;
            }
            precursor.setUptime(uptime);

            //back-propagate precursor
            backPropagateUptimes(precursor);
            precursor = discoverParentOfPrecalculatedUptimeMachine( map.getHead() );
        }

        //calculate head's rate
        //find input [of head] which has a rate
        MachineNode calculatedHeadParent = null;
        for(MachineNode sourceNode : map.getHead().sources) {
            if(0.0 < sourceNode.calculated_uptime) {
                calculatedHeadParent = sourceNode;
                break;
            }
        }
        if(calculatedHeadParent == null) {
            LOGGER.log(Level.WARNING, "No match for final-product's parents' rates; cannot calculate uptimes.");
            return;
        }
        //find common item for parent and head
        Item commonItem = getFirstItemInCommon(calculatedHeadParent.recipe.outputs, map.getHead().recipe.getInputsAsItemStacks() );
        if(commonItem == null) {
            LOGGER.log(Level.WARNING, "No match for final-product's parents' and final-product's items; cannot calculate uptimes.");
            return;
        }
        //get production rates for head and calculated-parent
        double uptime; {
            double potential_rate = map.getHead().recipe.time_seconds / getQuantityOfDesiredIO(map.getHead().recipe.getInputsAsItemStacks(), commonItem);
            double available_rate = ( calculatedHeadParent.recipe.time_seconds / calculatedHeadParent.calculated_uptime )  /  getQuantityOfDesiredIO(calculatedHeadParent.recipe.outputs, commonItem);
            uptime = potential_rate/available_rate;
        }
        map.getHead().setUptime(uptime);

        //back-propagate from head
        backPropagateUptimes( map.getHead() );
    }
    private static MachineNode discoverPrecalculatedUptimeMachine(MachineNode node) {
        if(0.0 <= node.calculated_uptime) {
            return node;
        }

        //if this is a leaf-node, return nothing
        if( node.sources.isEmpty() ) {
            return null;
        }

        //search all sources
        for(MachineNode source : node.sources) {
            MachineNode result = discoverPrecalculatedUptimeMachine(source);
            if(result != null) {
                return result;
            }
        }

        //if nothing is found, return nothing
        return null;
    }
    private static MachineNode discoverParentOfPrecalculatedUptimeMachine(MachineNode node) {
        if(0.0 <= node.calculated_uptime) {
            return null;
        }

        //if this is a leaf-node, return nothing
        if( node.sources.isEmpty() ) {
            return null;
        }

        //search all sources
        for(MachineNode source : node.sources) {
            if(0.0 <= source.calculated_uptime) {
                return node;
            }
            MachineNode checkedNode = discoverParentOfPrecalculatedUptimeMachine(source);
            if(checkedNode != null) {
                return checkedNode;
            }
        }

        //if nothing is found, return nothing
        return null;
    }
    private static double getRateOfDesiredOutput(Recipe recipe, Item desiredItem) {
        if(recipe == null) {
            return 0.0;
        }

        //find produced quantity of desired output
        double produced_quantity = 0.0;
        for( ItemStack producedItem : recipe.outputs ) {
            if( producedItem.item().equals(desiredItem) ) {
                produced_quantity = Math.max(produced_quantity, producedItem.quantity() );
            }
        }
        //return quantity per time
        return produced_quantity/recipe.time_seconds;
    }
    private static double getQuantityOfDesiredIO(List<ItemStack> list, Item desiredItem) {
        //return quantity of first instance of item
        for(ItemStack producedItem : list) {
            if( producedItem.item().equals(desiredItem) ) {
                return producedItem.quantity();
            }
        }
        return 0.0;
    }
    private static void backPropagateUptimes(MachineNode node) {
        //ensure current node has a calculated uptime
        if(node.calculated_uptime < 0.0) {
            return;
        }

        //calculate expected rate of each source, back-propagating base on their times; then, back-propagate the sources
        //Note: This assumes that only one of any given input produces any given desired item
        for( ItemStackWithPreferredRecipeSource sourceItem : node.recipe.inputs ) {
            double desired_production_rate =
                    ( sourceItem.itemStack.quantity()/node.recipe.time_seconds ) //maximum rate, in "items/second"
                            * node.calculated_uptime //desired rate, in "items/second"
                    ;
            for(MachineNode source : node.sources) {
                double maximum_production_rate = getRateOfDesiredOutput( source.recipe, sourceItem.itemStack.item() );
                if(maximum_production_rate <= 0.0) {
                    continue;
                }

                double source_uptime = desired_production_rate/maximum_production_rate;
                source.setUptime(source_uptime);
                //when the uptime is set, backpropagate their production
                backPropagateUptimes(source);
            }
        }
    }
    private static Item getFirstItemInCommon(List<ItemStack> firstList, List<ItemStack> secondList) {
        //linear search
        for(ItemStack firstItem : firstList) {
            for(ItemStack secondItem : secondList) {
                if(  firstItem.item().equals( secondItem.item() )  ) {
                    return firstItem.item();
                }
            }
        }
        return null;
    }

    //# GETTERS
    public MachineNode getHead() {
        return head;
    }

    public static HashMap<Voltage, Double> getAveragePowerConsumption(MachineNode node) {
        HashMap<Voltage, Double> powerConsumption = new HashMap<Voltage, Double>();

        powerConsumption.put(
            node.recipe.machine.voltage,
            node.recipe.amperage * node.calculated_uptime
        );

        //get source's consumption as well
        for(MachineNode source : node.sources) {
            addAmperes(
                powerConsumption,
                getAveragePowerConsumption(source)
            );
        }

        return powerConsumption;
    }
    private static void addAmperes(HashMap<Voltage, Double> first, HashMap<Voltage, Double> second) {
        for( Voltage addedVoltage : second.keySet() ) {
            if( first.containsKey(addedVoltage) ) {
                first.replace(
                    addedVoltage,
                    first.get(addedVoltage)+second.get(addedVoltage)
                );
            } else {
                first.put(
                    addedVoltage,
                    second.get(addedVoltage)
                );
            }
        }
    }

    public static double getAveragePollutionRate(MachineNode node) {
        double pollution_rate = node.recipe.machine.pollution * node.calculated_uptime;

        for(MachineNode source : node.sources) {
            pollution_rate += getAveragePollutionRate(source);
        }

        return pollution_rate;
    }
}