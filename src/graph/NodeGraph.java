package graph;

import items.Item;
import machines.Machine;
import machines.MachineTypes;
import machines.Voltage;
import recipes.Recipe;
import recipes.Recipes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NodeGraph {
    private Item finalProduct;
    public List<ProductNode> products = new ArrayList<>();
    public List<RecipeNode> transformers = new ArrayList<>();
    private final List<Item> exclusions = new ArrayList<>();

    public NodeGraph(Item finalProduct, double items_per_second, Item exclusion) {
        this.exclusions.add(exclusion);
        initialize(finalProduct, items_per_second);
    }
    public NodeGraph(Item finalProduct, double items_per_second, List<Item> exclusions) {
        this.exclusions.addAll(exclusions);
        initialize(finalProduct, items_per_second);
    }
    public NodeGraph(Item finalProduct, double items_per_second) {
        initialize(finalProduct, items_per_second);
    }
    public void initialize(Item finalProduct, double items_per_second) {
        this.finalProduct = finalProduct;
        this.products = new ArrayList<>();
        this.transformers = new ArrayList<>();

        //create original recipe-node and product-node
        ProductNode ultimateSink = new ProductNode(finalProduct);

        RecipeNode ultimateSource = new RecipeNode(
            Recipes.optimalRecipes.get(finalProduct)
        );
        ultimateSource.setUpTime(
            items_per_second/ultimateSource.recipe.getProductionRate(finalProduct)
        );

        ultimateSource.addOutput(ultimateSink);
        ultimateSink.addSource(ultimateSource);
        this.addProduct(ultimateSink);
        this.addTransformer(ultimateSource);

        //back-calculate production-map
        constructNodeGraph();
    }
    @Deprecated
    public NodeGraph(Item finalProduct, Recipe finalRecipe, double items_per_second) {
        this.finalProduct = finalProduct;

        //create original recipe-node and product-node
        ProductNode ultimateSink = new ProductNode(finalProduct);
        RecipeNode ultimateSource = new RecipeNode(finalRecipe);

        ultimateSource.setUpTime(
            items_per_second/ultimateSource.recipe.getProductionRate(finalProduct)
        );

        ultimateSource.addOutput(ultimateSink);
        ultimateSink.addSource(ultimateSource);
        this.addProduct(ultimateSink);
        this.addTransformer(ultimateSource);

        //back-calculate production-map
        constructNodeGraph();
    }

    private List<RecipeNode> getUnsourcedRecipeNodes() {
        List<RecipeNode> unsourcedRecipeNodes = new ArrayList<>();
        for(RecipeNode potentiallyUnsourcedRecipeNode : transformers) {
            if(
                potentiallyUnsourcedRecipeNode.inputs.isEmpty()
                && !Recipes.isLeafRecipe(potentiallyUnsourcedRecipeNode.recipe)
            ) {
                unsourcedRecipeNodes.add(potentiallyUnsourcedRecipeNode);
            }
        }
        return unsourcedRecipeNodes;
    }
    private List<ProductNode> getUnsourcedProductNodes() {
        List<ProductNode> unsourcedProductNode = new ArrayList<>();
        for(ProductNode potentiallyUnsourcedProductNode : products) {
            if(
                0.0 < potentiallyUnsourcedProductNode.getUnmetDemandRate()
                //potentiallyUnsourcedProductNode.getSourceCount() < 1
                && Recipes.optimalRecipes.containsKey(potentiallyUnsourcedProductNode.product)
                /*&& !MachineTypes.isLeafMachine(
                    Recipes.optimalRecipes.get(potentiallyUnsourcedProductNode.product).machineType
                )*/
                && !exclusions.contains(potentiallyUnsourcedProductNode.product)
            ) {
                unsourcedProductNode.add(potentiallyUnsourcedProductNode);
            }
        }
        return unsourcedProductNode;
    }
    private void constructNodeGraph() {
        //temporary, for testing
        /*
        for(RecipeNode transformer : transformers) {
            transformer.generateSurroundings(this);
        }
         */
        final int MAXIMUM_DEPTH = 50;
        int current_depth = 0;

        List<ProductNode> unsourcedProductNodes = getUnsourcedProductNodes();
        List<RecipeNode>  unsourcedRecipesNodes = getUnsourcedRecipeNodes() ;
        while(
            ( !unsourcedProductNodes.isEmpty() || !unsourcedRecipesNodes.isEmpty() )
            && current_depth < MAXIMUM_DEPTH
        ) {
            for(ProductNode unsourcedProducedNode : unsourcedProductNodes) {
                unsourcedProducedNode.generateSource(this);
            }
            for(RecipeNode unsourcedRecipeNode : unsourcedRecipesNodes) {
                unsourcedRecipeNode.generateSurroundings(this);
            }

            unsourcedProductNodes = getUnsourcedProductNodes();
            unsourcedRecipesNodes = getUnsourcedRecipeNodes() ;
            current_depth++;
        }
    }

    public boolean addProduct(ProductNode product) {
        //ensure it does not already exist in transformations
        for(ProductNode existingProduct : products) {
            if(existingProduct == product) {
                return false;
            }
        }

        products.add(product);
        return true;
    }
    public boolean addTransformer(RecipeNode transformer) {
        //ensure it does not already exist in transformations
        for(RecipeNode existingTransformer : transformers) {
            if(existingTransformer == transformer) {
                return false;
            }
        }

        transformers.add(transformer);
        return true;
    }

    public ProductNode getProduct(Item ofItem) {
        //linear search
        for(ProductNode product : products) {
            if( product.product.equals(ofItem) ) {
                return product;
            }
        }
        return null;
    }
    public RecipeNode getTransformer(Recipe ofRecipe) {
        //linear search
        for(RecipeNode transformation : transformers) {
            if( transformation.recipe.equals(ofRecipe) ) {
                return transformation;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder nodeGraphStringBuilder = new StringBuilder();
        nodeGraphStringBuilder.append("NodeGraph(").append( finalProduct.getName() ).append(") {\r\n");

        /*Products*/ {
            int counter = 0;
            nodeGraphStringBuilder.append("\tProducts:\r\n");
            for(ProductNode product : products) {
                nodeGraphStringBuilder.append("\t\t").append(counter++).append(" -> ").append( product.toString() ).append("\r\n");
            }
            nodeGraphStringBuilder.append("\r\n");
        }

        /*Transformers*/ {
            int counter = 0;
            nodeGraphStringBuilder.append("\tTransformers:\r\n");
            for(RecipeNode transformer : transformers) {
                nodeGraphStringBuilder.append("\t\t").append(counter++).append(" -> ").append( transformer.toString() ).append("\r\n");
            }
            nodeGraphStringBuilder.append("\r\n");
        }

        //TODO: simplify below
        /*Machine Counts*/ {
            HashMap<Machine, Integer> machines = getMachineCounts();
            HashMap<Machine, Double> uptimes = getMachineSumUptimes();
            nodeGraphStringBuilder.append("\tMachine Counts:\r\n");
            for( Machine machine : machines.keySet() ) {
                nodeGraphStringBuilder.append("\t\t")
                    .append( machines.get(machine) ).append("×")
                    .append(machine.name)
                    .append(" (").append(machine.voltage).append(" ").append( machine.machineType.getName() ).append(")")
                ;
                Machine uptimeMachine = getMachineMatchInMap(uptimes, machine);
                if( uptimes.containsKey(uptimeMachine) ) {
                    nodeGraphStringBuilder.append(" @ ");
                    double sum_uptime = uptimes.get(uptimeMachine)/machines.get(machine);
                    nodeGraphStringBuilder
                        .append( StringHelper.getNumberString(100.0*sum_uptime) )
                        .append("%")
                    ;
                }
                nodeGraphStringBuilder.append("\r\n");
            }
            nodeGraphStringBuilder.append("\r\n");
            }

        /*Average Power Usage*/ {
            nodeGraphStringBuilder.append("\tAverage Power Usage:\r\n");
            HashMap<Voltage, Double> powerMap = getPowerDemand(false);
            for( Voltage voltage : powerMap.keySet() ) {
                double usage_at_voltage = powerMap.get(voltage);
                if(usage_at_voltage == 0.0 || voltage.equals(Voltage.None) ) {
                    continue;
                }

                nodeGraphStringBuilder
                    .append("\t\t").append(voltage).append(": ")
                    .append( StringHelper.getNumberString(usage_at_voltage) )
                    .append(" EU/t\r\n")
                ;
            }
            //nodeGraphStringBuilder.append("\r\n");
        }
        /*Maximum Power Usage*/ {
            nodeGraphStringBuilder.append("\tMaximum Power Usage:\r\n");
            HashMap<Voltage, Double> powerMap = getPowerDemand(true);
            for( Voltage voltage : powerMap.keySet() ) {
                double usage_at_voltage = powerMap.get(voltage);
                if(usage_at_voltage == 0.0 || voltage.equals(Voltage.None) ) {
                    continue;
                }

                nodeGraphStringBuilder
                    .append("\t\t").append(voltage).append(": ")
                    .append( StringHelper.getNumberString(usage_at_voltage) )
                    .append(" EU/t\r\n")
                ;
            }
            nodeGraphStringBuilder.append("\r\n");
        }

        /*Average Pollution Output*/ {
            nodeGraphStringBuilder.append("\tAverage Pollution Output:\r\n");
            double pollution_rate = getPollutionRate(false);
            nodeGraphStringBuilder
                .append("\t\t")
                .append(
                    StringHelper.getNumberString(pollution_rate)
                )
                .append(" pollution/second\r\n")
            ;
            //nodeGraphStringBuilder.append("\r\n");
        }
        /*Maximum Pollution Output*/ {
            nodeGraphStringBuilder.append("\tMaximum Pollution Output:\r\n");
            double pollution_rate = getPollutionRate(true);
            nodeGraphStringBuilder
                    .append("\t\t")
                    .append(
                            StringHelper.getNumberString(pollution_rate)
                    )
                    .append(" pollution/second\r\n")
            ;
            nodeGraphStringBuilder.append("\r\n");
        }

        nodeGraphStringBuilder.append("}");
        return nodeGraphStringBuilder.toString();
    }

    public Item getFinalProduct() {
        return this.finalProduct;
    }
    private HashMap<Voltage, Double> getPowerDemand(boolean isMaximum) {
        HashMap<Voltage, Double> powerMap = new HashMap<>();
        for(RecipeNode transformer : transformers) {
            //TODO: Account for non-eu power-types
            double power_demand = transformer.recipe.power_usage_per_tick;
            double average_power_demand = power_demand;
            if(isMaximum) {
                average_power_demand *= Math.ceil( transformer.getUptime() );
            } else {
                average_power_demand *= transformer.getUptime();
            }
            Voltage currentVoltage = transformer.recipe.machineType.getMinimumVoltageForLimit(power_demand);
            if( currentVoltage.equals(Voltage.None) ) {
                continue;
            }
            if( powerMap.containsKey(currentVoltage) ) {
                powerMap.replace(
                        currentVoltage,
                        powerMap.get(currentVoltage) + average_power_demand
                );
            } else {
                powerMap.put(currentVoltage, average_power_demand);
            }
        }
        return powerMap;
    }
    private double getPollutionRate(boolean isMaximum) {
        double pollution_rate = 0.0;
        for(RecipeNode transformer : transformers) {
            double current_pollution = transformer.recipe.machineType.getPollution(transformer.recipe.power_usage_per_tick);
            if(!isMaximum) {
                current_pollution *= transformer.getUptime();
            }
            pollution_rate += current_pollution;
        }
        return pollution_rate;
    }

    private Machine getMachineMatchInMap(HashMap<Machine, ?> map, Machine machine) {
        for(Machine currentMachine : map.keySet() ) {
            if( currentMachine.equals(machine) ) {
                return currentMachine;
            }
        }
        return machine;
    }
    private HashMap<Machine, Integer> getMachineCounts() {
        HashMap<Machine, Integer> machineCounts = new HashMap<>();
        Machine currentMachine;
        for(RecipeNode transformer : transformers) {
            currentMachine = new Machine(transformer.recipe.machineType, transformer.recipe.power_usage_per_tick);
            currentMachine = getMachineMatchInMap(machineCounts, currentMachine);
            if( currentMachine.machineType.equals(MachineTypes.PLAYER) ) {
                continue;
            }
            if( machineCounts.containsKey(currentMachine) ) {
                machineCounts.replace(
                    currentMachine,
                    machineCounts.get(currentMachine) + (int)Math.ceil( transformer.getUptime() )
                );
            } else {
                machineCounts.put(  currentMachine,  (int)Math.ceil( transformer.getUptime() )  );
            }
        }
        return machineCounts;
    }
    private HashMap<Machine, Double> getMachineSumUptimes() {
        HashMap<Machine, Double> uptimes = new HashMap<>();
        Machine currentMachine;
        for(RecipeNode transformer : transformers) {
            currentMachine = new Machine(transformer.recipe.machineType, transformer.recipe.power_usage_per_tick);
            currentMachine = getMachineMatchInMap(uptimes, currentMachine);
            if( currentMachine.machineType.equals(MachineTypes.PLAYER) ) {
                continue;
            }
            if( uptimes.containsKey(currentMachine) ) {
                uptimes.replace(
                    currentMachine,
                    uptimes.get(currentMachine) + transformer.getUptime()
                );
            } else {
                uptimes.put(  currentMachine,  transformer.getUptime()  );
            }
        }
        return uptimes;
    }

    public List<ProductNode> getLeafProductNodes() {
        List<ProductNode> leafProductNodes = new ArrayList<>();
        for(ProductNode originalSource : products) {
            if( originalSource.sources.isEmpty() ) {
                leafProductNodes.add(originalSource);
            }
        }
        return leafProductNodes;
    }
}
