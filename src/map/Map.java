package map;

import items.Item;
import items.ItemStack;
import items.Items;
import machines.Machine;
import machines.MachineConfiguration;
import machines.Machines;
import machines.Voltage;
import recipes.ItemStackWithPreferredRecipeSource;
import recipes.Recipe;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Map {
    private final MachineNode head;
    private final List<MachineNode> consolidatedBranches = new ArrayList<MachineNode>();

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

        consolidateDuplicateBranches(this);
    }

    //# toString() and Helpers
    @Override
    public String toString() {
        final StringBuilder MAP_STRING_BUILDER = new StringBuilder();

        //print sub-systems first
        MAP_STRING_BUILDER.append("# Sub-System Production-Lines\r\n\r\n");
        for(MachineNode subMapHead : this.consolidatedBranches) {
            Map subSystemMap = new Map(subMapHead.recipe);
            subSystemMap.head.setUptime(subMapHead.calculated_uptime);
            calculateUptimes(subSystemMap);

            HashMap<Integer, Boolean> linePositions = new HashMap<Integer, Boolean>();
            linePositions.put(0, false);
            getMapNodeAsString(subSystemMap, null, subSystemMap.getHead(), linePositions, 0, MAP_STRING_BUILDER, false);

            MAP_STRING_BUILDER.append("\r\n");
        }
        MAP_STRING_BUILDER.append("\r\n");

        //print tree (branched node-graph)
        MAP_STRING_BUILDER.append("# Main Production-Line\r\n");
        HashMap<Integer, Boolean> linePositions = new HashMap<Integer, Boolean>();
        linePositions.put(0, true);
        getMapNodeAsString(this, null, this.head, linePositions, 0, MAP_STRING_BUILDER, true);

        //additional data
        MAP_STRING_BUILDER.append("\r\n");
        MAP_STRING_BUILDER.append( getMaximumPowerConsumptionString(this) );
        MAP_STRING_BUILDER.append( getAveragePowerConsumptionString(this) ).append("\r\n");
        MAP_STRING_BUILDER.append( getMaximumPollutionRateString(this) ).append("\r\n");
        MAP_STRING_BUILDER.append( getAveragePollutionRateString(this) ).append("\r\n\r\n");
        MAP_STRING_BUILDER.append( getMachinesCountString(this) );

        return MAP_STRING_BUILDER.toString();
    }

    private static String getAveragePowerConsumptionString(Map map) {
            final StringBuilder AVERAGE_POWER_CONSUMPTION_STRING_BUILDER = new StringBuilder();

        HashMap<Voltage, Double> averagePowerConsumption = new HashMap<>(); {
        List< HashMap<Voltage, Double> > averagePowerConsumptions = new ArrayList<>();
            averagePowerConsumptions.add(  getAveragePowerConsumption( map.getHead() )  );
            for(MachineNode branchHead : map.consolidatedBranches) {
                averagePowerConsumptions.add( getAveragePowerConsumption(branchHead) );
            }

            averagePowerConsumption = Voltage.combinePower(averagePowerConsumptions);
        }
        AVERAGE_POWER_CONSUMPTION_STRING_BUILDER.append("## Average Power Consumption").append("\r\n");
        AVERAGE_POWER_CONSUMPTION_STRING_BUILDER.append( getPowerList(averagePowerConsumption) );

        return AVERAGE_POWER_CONSUMPTION_STRING_BUILDER.toString();
    }
    private static String getMaximumPowerConsumptionString(Map map) {
        final StringBuilder MAXIMUM_POWER_CONSUMPTION_STRING_BUILDER = new StringBuilder();

        HashMap<Voltage, Double> maximumPowerConsumption = new HashMap<>(); {
            List< HashMap<Voltage, Double> > maximumPowerConsumptions = new ArrayList<>();
            maximumPowerConsumptions.add(  getMaximumPowerConsumption( map.getHead() )  );
            for(MachineNode branchHead : map.consolidatedBranches) {
                maximumPowerConsumptions.add( getMaximumPowerConsumption(branchHead) );
            }

            maximumPowerConsumption = Voltage.combinePower(maximumPowerConsumptions);
        }
        MAXIMUM_POWER_CONSUMPTION_STRING_BUILDER.append("## Maximum Power Consumption").append("\r\n");
        MAXIMUM_POWER_CONSUMPTION_STRING_BUILDER.append( getPowerList(maximumPowerConsumption) );

        return MAXIMUM_POWER_CONSUMPTION_STRING_BUILDER.toString();
    }
    private static String getPowerList(HashMap<Voltage, Double> power) {
        final StringBuilder POWER_LIST_BUILDER = new StringBuilder();
        int index = 0;
        boolean isLast;
        for(Voltage voltage : power.keySet() ) {
            if( voltage.equals(Voltage.None) ) {
                continue;
            }

            isLast = power.size()-2 <= index;

            POWER_LIST_BUILDER
                .append(' ')
                .append(  ( isLast ? getAngledPipe(0) : getSplitPipe(0) )  )
                .append(
                    Math.round( 100.0*power.get(voltage) )/100.0
                )
                .append("A ").append(voltage)

                .append(" (")
                .append( Math.round(100.0*power.get(voltage)*voltage.EULimit() )/100.0 )
                .append(" EU/t)")
            ;

            POWER_LIST_BUILDER.append("\r\n");
            index++;
        }
        return POWER_LIST_BUILDER.toString();
    }

    private static String getAveragePollutionRateString(Map map) {
        double rate = getAveragePollutionRate( map.getHead() );
        for(MachineNode branchHead : map.consolidatedBranches) {
            rate += getAveragePollutionRate(branchHead);
        }

        return
            "## Average Pollution Rate\r\n"
            + getAngledPipe(0)+" " + Math.round(100.0*rate)/100.0 +" pollution/second"
        ;
    }
    private static String getMaximumPollutionRateString(Map map) {
        double rate = getMaximumPollutionRate( map.getHead() );
        for(MachineNode branchHead : map.consolidatedBranches) {
            rate += getMaximumPollutionRate(branchHead);
        }

        return
            "## Maximum Pollution Rate\r\n"
            + getAngledPipe(0)+" " + Math.round(100.0*rate)/100.0 +" pollution/second"
        ;
    }

    private static void getMapNodeAsString(
        Map map, MachineNode parent,
        MachineNode node,
        HashMap<Integer, Boolean> depthsWithLines, int depth,
        final StringBuilder STRINGBUILDER,
        boolean includeConsolidations
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
            if(includeConsolidations && node.recipe.isConsolidated) {
                STRINGBUILDER.append( Machines.CONSOLIDATED_BRANCH.toString() );
            } else {
                if( !node.recipe.machine.equals(Machines.PLAYER) ) {
                    STRINGBUILDER.append( (int)Math.ceil(node.calculated_uptime) ).append("× ");
                }
                STRINGBUILDER.append( node.recipe.machine.toString() );
            }
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
            && !node.recipe.machine.equals(Machines.CONSOLIDATED_BRANCH)
        ) {
            double minimum_machine_count = Math.ceil(node.calculated_uptime);
            double rounded_percentage = Math.round(10000.0*node.calculated_uptime/minimum_machine_count)/100.0;
            STRINGBUILDER.append("@").append(rounded_percentage).append('%');
        }

        /*if(node.recipe != null) {
            STRINGBUILDER
                .append(" ( ")
                .append(node.calculated_uptime).append('*').append(node.recipe.eu_per_tick )
                .append('/')
                .append( node.recipe.amperage*node.recipe.machine.voltage.EULimit() )
                .append(" EU/t  = ")
                .append( (node.calculated_uptime*node.recipe.eu_per_tick)/(node.recipe.amperage*node.recipe.machine.voltage.EULimit()) )
                .append("A )")
            ;
        }*/

        STRINGBUILDER.append("\r\n");

        //print sources
        for(MachineNode source : node.sources) {
            getMapNodeAsString(map, node, source, depthsWithLines, depth + 1, STRINGBUILDER, includeConsolidations);
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

    private static String getMachinesCountString(Map map) {
        HashMap<Machine, Integer> countedMachines = new HashMap<>(); {
            //get original map and all consolidated Branches' machines
            List< HashMap<Machine, Integer> > branchesCountedMachines = new ArrayList<>();
            branchesCountedMachines.add(  getMachinesCount( map.getHead() )  );
            for(MachineNode branchHead : map.consolidatedBranches) {
                branchesCountedMachines.add( getMachinesCount(branchHead) );
            }
            //add all machine counts
            for(HashMap<Machine, Integer> branchCountedMachines: branchesCountedMachines) {
                for( Machine branchCountedMachine : branchCountedMachines.keySet() ) {
                    if( countedMachines.containsKey(branchCountedMachine) ) {
                        countedMachines.replace(
                            branchCountedMachine,
                            branchCountedMachines.get(branchCountedMachine) + countedMachines.get(branchCountedMachine)
                        );
                    } else {
                        countedMachines.put(
                            branchCountedMachine,
                            branchCountedMachines.get(branchCountedMachine)
                        );
                    }
                }
            }
        }

        Machine[] sortedCountedMachines = countedMachines.keySet().toArray(new Machine[0]);
        Arrays.sort(
            sortedCountedMachines,
            new Comparator<Machine>() {
                @Override
                public int compare(Machine primary, Machine secondary) {
                    return primary.getName().compareTo( secondary.getName() );
                }

                @Override
                public boolean equals(Object obj) {
                    return false;
                }
            }
        );

        StringBuilder countedMachinesBuilder = new StringBuilder();
        countedMachinesBuilder.append("## Machines\r\n");
        int counter = 0;
        for(Machine countedAndSortedMachine : sortedCountedMachines ) {

            countedMachinesBuilder.append(' ');
            if( counter < countedMachines.size()-1 ) {
                countedMachinesBuilder.append( getSplitPipe(0) );
            } else {
                countedMachinesBuilder.append( getAngledPipe(0) );
            }
            countedMachinesBuilder.append(' ');

            countedMachinesBuilder
                .append( countedMachines.get(countedAndSortedMachine) )
                .append("× ")
                .append( countedAndSortedMachine.toString() ).append(" (").append( countedAndSortedMachine.voltage.toString() ).append(')')
                .append("\r\n")
            ;

            counter++;
        }
        return countedMachinesBuilder.toString();
    }
    private static HashMap<Machine, Integer> getMachinesCount(MachineNode node) {
        HashMap<Machine, Integer> nodeMachines = new HashMap<>();
        if(
            node.recipe.machine.equals(Machines.PLAYER)
            || node.recipe.machine.voltage.equals(Voltage.None)
        ) {
            return nodeMachines;
        }

        nodeMachines.put(
            node.recipe.machine,
            (int)Math.ceil( node.calculated_uptime )
        );
        for(MachineNode source : node.sources) {
            HashMap<Machine, Integer> sourceMachines = getMachinesCount(source);
            for( Machine sourceMachine : sourceMachines.keySet() ) {
                if( nodeMachines.containsKey(sourceMachine) ) {
                    nodeMachines.replace(
                        sourceMachine,
                        nodeMachines.get(sourceMachine) + sourceMachines.get(sourceMachine)
                    );
                } else {
                    nodeMachines.put(
                        sourceMachine,
                        sourceMachines.get(sourceMachine)
                    );
                }
            }
        }
        return nodeMachines;
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
            if(
                   item.item().equals(Items.NOTHING)
                || item.item().equals(Items.MANUAL)
            ) {
                return true;
            }
        }
        return false;
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

    private static void consolidateDuplicateBranches(Map map) {
        //get all nodes in Map
        List<MachineNode> nodes = getAllSourceNodes( map.getHead() );

        //Check each node against all others for matching branches,
        //when a matching branch is found, move them into a set for matching branches.
        List< List<MachineNode> > matchingBranchesHeads = new ArrayList< List<MachineNode> >();
        for(MachineNode primaryNode : nodes) {
            for(MachineNode secondaryNode : nodes) {
                //skip self
                if(primaryNode == secondaryNode) {
                    continue;
                }

                if( areBranchesDuplicates(primaryNode,secondaryNode) ) {
                    incorporateBranchesIntoMatchingList(matchingBranchesHeads, primaryNode, secondaryNode);
                }
            }
        }

        //remove matched sub-branch-sets (these are sub-branches of other matched branches)
        final HashMap<  Integer,  List< List<MachineNode> >  > COMPLEXITY_BRANCH_MAP = new HashMap<>();
        /*Order Branches (and sub-Branches) by their recipe-complexity*/ {
            MachineNode exampleNode;
            int complexity;
            for(List<MachineNode> branch : matchingBranchesHeads) {
                exampleNode = branch.getFirst();
                complexity = exampleNode.recipe.complexity;
                if( COMPLEXITY_BRANCH_MAP.containsKey(complexity) ) {
                    List< List<MachineNode> > modifiedBranchList = new ArrayList<>(
                        COMPLEXITY_BRANCH_MAP.get(complexity)
                    );
                    modifiedBranchList.add(branch);
                    COMPLEXITY_BRANCH_MAP.replace(complexity, modifiedBranchList);
                } else {
                    List< List<MachineNode> > branchAsList = new ArrayList<>();
                    branchAsList.add(branch);
                    COMPLEXITY_BRANCH_MAP.put(complexity, branchAsList );
                }
            }
        }

        List< List<MachineNode> > subBranchesToBeRemoved = new ArrayList<>();
        /*In forward order (toward higher complexity), verify that sub-branches do not exist; if they do, annihilate them.*/ {
            int maximum_complexity = 0;
            for(Integer complexity : COMPLEXITY_BRANCH_MAP.keySet() ) {
                maximum_complexity = Math.max(maximum_complexity, complexity.intValue() );
            }

            for(int complexity = 0; complexity < maximum_complexity; complexity++) {
                if( !COMPLEXITY_BRANCH_MAP.containsKey(complexity) ) {
                    continue;
                }

                //with an example from each potential sub-branch, check if it exists in any higher-complexity branch
                for(List<MachineNode> subBranch : COMPLEXITY_BRANCH_MAP.get(complexity) ) {
                    MachineNode subBranchExampleNode = subBranch.getFirst();
                    List<MachineNode> higherComplexityBranchNodes = getAllNodesInHigherComplexityBranches(COMPLEXITY_BRANCH_MAP, complexity);

                    if( MachineNode.includes(higherComplexityBranchNodes, subBranchExampleNode) ) {
                        for( List<MachineNode> potentiallyRemovedSubBranch : COMPLEXITY_BRANCH_MAP.get(complexity) ) {
                            if( potentiallyRemovedSubBranch.contains(subBranchExampleNode) ) {
                                subBranchesToBeRemoved.add(potentiallyRemovedSubBranch);
                            }
                        }
                    }
                }
            }
            //remove branches marked for culling
            for( Integer complexity : COMPLEXITY_BRANCH_MAP.keySet() ) {
                for( Iterator< List<MachineNode> > mapAtComplexityIterator = COMPLEXITY_BRANCH_MAP.get(complexity).iterator(); mapAtComplexityIterator.hasNext(); ) {
                    List<MachineNode> branch = mapAtComplexityIterator.next();
                    for(List<MachineNode> branchToBeRemoved : subBranchesToBeRemoved) {
                        if(branch == branchToBeRemoved) {
                            mapAtComplexityIterator.remove();
                        }
                    }
                }
            }
        }

        //create new lists of combined sub-branches into a set of branches (consolidated branches)
        for(Integer complexity : COMPLEXITY_BRANCH_MAP.keySet() ) {
            for(List<MachineNode> subBranch : COMPLEXITY_BRANCH_MAP.get(complexity) ) {
                MachineNode newBranch = new MachineNode(subBranch.getFirst().recipe);
                recursivelyGenerateRecipesFromPreferredSources(newBranch);

                double newBranchUptime = 0.0;
                for(MachineNode subBranchNode : subBranch) {
                    newBranchUptime += subBranchNode.calculated_uptime;
                }
                newBranch.setUptime(newBranchUptime);
                backPropagateUptimes(newBranch);

                map.consolidatedBranches.add(newBranch);
            }
        }

        /*remove sub-branches from map via removing sources from matches and using "consolidation" type*/ {
            List<MachineNode> nodesToBeMarkedAsConsolidated = new ArrayList<>(); {
                for( Integer complexity : COMPLEXITY_BRANCH_MAP.keySet() ) {
                    for(List<MachineNode> subBranchHeads : COMPLEXITY_BRANCH_MAP.get(complexity) ) {
                        nodesToBeMarkedAsConsolidated.addAll(subBranchHeads);
                    }
                }
            }

            setBranchesInListToConsolidationType(map.getHead(), nodesToBeMarkedAsConsolidated);
        }

    }
    private static List<MachineNode> getAllSourceNodes(MachineNode node) {
        List<MachineNode> allSourceNodes = new ArrayList<MachineNode>(node.sources);
        for(MachineNode source : node.sources) {
            allSourceNodes.addAll(
                getAllSourceNodes(source)
            );
        }
        return allSourceNodes;
    }
    private static boolean areBranchesDuplicates(MachineNode nodeOne, MachineNode nodeTwo) {
        if( nodeOne.equals(nodeTwo) ) {
            if( nodeOne.sources.isEmpty() && nodeTwo.sources.isEmpty() ) {
                return true;
            }
            if( nodeOne.sources.size() != nodeTwo.sources.size() ) {
                return false;
            }

            boolean areAllSourcesMatching = true;
            for(MachineNode sourceOne : nodeOne.sources) {
                if( !MachineNode.includes(nodeTwo.sources, sourceOne) ) {
                    return false;
                }

                MachineNode matchingSource = null;
                for(MachineNode sourceTwo : nodeTwo.sources) {
                    if( sourceOne.equals(sourceTwo) ) {
                        matchingSource = sourceTwo;
                        break;
                    }
                }
                if(matchingSource == null) {
                    return false;
                }
                return areBranchesDuplicates(sourceOne, matchingSource);
        }

            return true;
        }
        return false;
    }
    private static void incorporateBranchesIntoMatchingList(
        List< List<MachineNode> > matchingBranchesList,
        MachineNode... matchingNodes
    ) {
        MachineNode exampleNode = null;
        for(MachineNode node : matchingNodes) {
            exampleNode = node;
            break;
        }
        if(exampleNode == null) {
            return;
        }

        //find if it belongs to an existing branch-set
        for(List<MachineNode> branch : matchingBranchesList) {
            if( branch.isEmpty() ) {
                continue;
            }
            if( areBranchesDuplicates(branch.getFirst(), exampleNode) ) {
                for(MachineNode node : matchingNodes) {
                    if( !branch.contains(node) ) {
                        branch.add(node);
                    }
                }
                return;
            }
        }

        //if it does not belong to an existing branch-set, create a new one
        matchingBranchesList.add(
            new ArrayList<MachineNode>( Arrays.asList(matchingNodes) )
        );
    }
    private static List<MachineNode> getAllNodesInHigherComplexityBranches(
        final HashMap<  Integer,  List< List<MachineNode> >  > branches,
        int complexity
    ) {
        List<MachineNode> allNodesInHigherComplexityBranches = new ArrayList<>();
        //linear search, because they may be in an arbitrary order
        for(Integer branchComplexity : branches.keySet() ) {
            if( branchComplexity.intValue() <= complexity ) {
                continue;
            }
            for(List<MachineNode> higherComplexityBranch : branches.get(branchComplexity) ) {
                for(MachineNode subNode : higherComplexityBranch) {
                    allNodesInHigherComplexityBranches.add(subNode);
                    allNodesInHigherComplexityBranches.addAll(
                        getAllSourceNodes(subNode)
                    );
                }
            }
        }
        return allNodesInHigherComplexityBranches;
    }
    private static void setBranchesInListToConsolidationType(MachineNode node, List<MachineNode> consolidatedBranchHeads) {
        if(node == null) {
            return;
        }

        for(MachineNode consolidatedBranchHead : consolidatedBranchHeads) {
            if( node.equals(consolidatedBranchHead) ) {
                MachineNode consolidatedNode = new MachineNode(node.recipe);
                consolidatedNode.recipe.isConsolidated = true;
                consolidatedNode.sources = new ArrayList<>();
                node.sources = new ArrayList<>();
                node = consolidatedNode;
                return;
            }
        }
        for(MachineNode source : node.sources) {
            setBranchesInListToConsolidationType(source, consolidatedBranchHeads);
        }
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
            * ( node.recipe.eu_per_tick / node.recipe.machine.voltage.EULimit() )
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
    public static HashMap<Voltage, Double> getMaximumPowerConsumption(MachineNode node) {
        HashMap<Voltage, Double> powerConsumption = new HashMap<Voltage, Double>();
        double maximum_amperage = node.recipe.amperage * Math.ceil(node.calculated_uptime);

        powerConsumption.put(
            node.recipe.machine.voltage,
            maximum_amperage * ( node.recipe.eu_per_tick / node.recipe.machine.voltage.EULimit() )
        );

        //get source's consumption as well
        for(MachineNode source : node.sources) {
            addAmperes(
                powerConsumption,
                getMaximumPowerConsumption(source)
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
    public static double getMaximumPollutionRate(MachineNode node) {
        double pollution_rate = node.recipe.machine.pollution * Math.ceil(node.calculated_uptime);

        for(MachineNode source : node.sources) {
            pollution_rate += getMaximumPollutionRate(source);
        }

        return pollution_rate;
    }
}