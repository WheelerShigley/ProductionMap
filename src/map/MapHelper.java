package map;

import items.Item;
import items.ItemStack;
import items.Items;
import machines.MachineConfiguration;
import machines.Machines;
import recipes.ItemStackWithPreferredRecipeSource;
import recipes.Recipe;
import recipes.Recipes;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class MapHelper {
    private static void printMachineNode(MachineNode node, int depth, final Logger LOGGER) {
        StringBuilder nodeBuilder = new StringBuilder();
        for(int i = 0; i < depth-1; i++) {
            nodeBuilder.append("\t");
        }
        nodeBuilder.append("└ ").append( node.toString() );

        //% (and/or quantity of machines); % need not be shown for manual tasks
        nodeBuilder.append(" [");
        if(node.recipe != null) {
            nodeBuilder.append( node.recipe.machine().toString() );
        }
        if( node.recipe != null && !node.recipe.circuit().equals(MachineConfiguration.None) ) {
            nodeBuilder.append(", ").append( node.recipe.circuit().toString() );
        }
        nodeBuilder.append(']');

        //uptime
        if(
            0.0 <= node.calculated_uptime
            && !node.recipe.machine().equals(Machines.PLAYER)
        ) {
            nodeBuilder.append("@").append( Math.round(10000.0*node.calculated_uptime)/100.0 ).append('%');
        }

        LOGGER.log(Level.INFO, nodeBuilder.toString() );

        //print sources
        for(MachineNode source : node.sources) {
            printMachineNode(source, depth+1, LOGGER);
        }
    }

    public static void printMap(Map map) {
        final Logger LOGGER; {
            LOGGER = Logger.getLogger("map_printer");

            // Remove default handlers
            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            for(Handler handler : handlers) {
                rootLogger.removeHandler(handler);
            }

            // Create custom handler without timestamp
            ConsoleHandler handler = new ConsoleHandler();
            handler.setFormatter(
                new Formatter() {
                    @Override
                    public String format(LogRecord record) {
                        return record.getMessage()+"\n";
                    }
                }
            );
            LOGGER.addHandler(handler);
        }
        printMachineNode(map.getHead(), 0, LOGGER);
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
        for( ItemStack producedItem : recipe.outputs() ) {
            if( producedItem.item().equals(desiredItem) ) {
                produced_quantity = Math.max(produced_quantity, producedItem.quantity() );
            }
        }
        //return quantity per time
        return produced_quantity/recipe.time_seconds();
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
        for( ItemStackWithPreferredRecipeSource sourceItem : node.recipe.inputs() ) {
            double desired_production_rate =
                ( sourceItem.itemStack.quantity()/node.recipe.time_seconds() ) //maximum rate, in "items/second"
                * node.calculated_uptime //desired rate, in "items/second"
            ;
            for(MachineNode source : node.sources) {
                if(0.0 < source.calculated_uptime) {
                    continue;
                }

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

    public static void calculateUptimes(Map map) {
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

            //find common item between precursor and pre-calculated node
            Item commonItem = getFirstItemInCommon( precursor.recipe.getInputsAsItemStacks(), calculatedNode.recipe.outputs() );
            if(commonItem == null) {
                LOGGER.log(Level.WARNING, "No expected-uptime and parent overlap found; cannot calculate uptimes.");
                return;
            }

            //calculate production rates for precursor
            double uptime; {
                double potential_rate = precursor.recipe.time_seconds() / getQuantityOfDesiredIO(precursor.recipe.getInputsAsItemStacks(), commonItem);
                double available_rate = ( calculatedNode.recipe.time_seconds() / calculatedNode.calculated_uptime )  /  getQuantityOfDesiredIO(calculatedNode.recipe.outputs(), commonItem);
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
        Item commonItem = getFirstItemInCommon(calculatedHeadParent.recipe.outputs(), map.getHead().recipe.getInputsAsItemStacks() );
        if(commonItem == null) {
            LOGGER.log(Level.WARNING, "No match for final-product's parents' and final-product's items; cannot calculate uptimes.");
            return;
        }
            //get production rates for head and calculated-parent
        double uptime; {
            double potential_rate = map.getHead().recipe.time_seconds() / getQuantityOfDesiredIO(map.getHead().recipe.getInputsAsItemStacks(), commonItem);
            double available_rate = ( calculatedHeadParent.recipe.time_seconds() / calculatedHeadParent.calculated_uptime )  /  getQuantityOfDesiredIO(calculatedHeadParent.recipe.outputs(), commonItem);
            uptime = potential_rate/available_rate;
        }
        map.getHead().setUptime(uptime);

        //back-propagate from head
        backPropagateUptimes( map.getHead() );
    }

    private static boolean itemStacksAreNothing(List<ItemStack> items) {
        for(ItemStack item : items) {
            if( !item.item().equals(Items.NOTHING) ) {
                return false;
            }
        }
        return true;
    }

    public static void recursivelyGenerateRecipesFromPreferredSources(MachineNode node) {
        //for leaf-nodes, return
        if(
            node.recipe == null
            || node.recipe.machine().equals(Machines.PLAYER)
            || itemStacksAreNothing( node.recipe.getInputsAsItemStacks() )
        ) {
            return;
        }

        if(  node.recipe.outputs().contains( new ItemStack(Items.EMPTY_CELL, 1) )  ) {
            int a = 1;
        }

        //create new sources
        List<MachineNode> sourceNodes = new ArrayList<MachineNode>();
        for(ItemStackWithPreferredRecipeSource inputWithSource : node.recipe.inputs() ) {
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
}
