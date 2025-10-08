package recipes;

import items.Item;
import items.ItemStack;
import machines.MachineConfiguration;
import machines.MachineType;
import power.PowerType;
import register.Identified;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Recipe extends Identified {
    public MachineType machineType;
    public PowerType powerType; //optional
    public double power_usage_per_tick;
    public final MachineConfiguration configuration; //optional

    public List<ItemStack> inputs; //optional, of single/list
    public final List<ItemStack> outputs; //of single/list
    public final double time_seconds;

    public final int complexity;
    private final static Logger LOGGER = Logger.getLogger("RecipeLogger");

    //MachineConfiguration: 1, inputs: 1, powerType: 1, Lists   (1)
    public Recipe(MachineType machine, MachineConfiguration configuration, PowerType powerType, double power_per_tick, List<ItemStack> inputs, List<ItemStack> outputs, double time_seconds) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                getFirstItem(outputs),
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = configuration;
        this.powerType = powerType;
        this.power_usage_per_tick = power_per_tick;
        this.inputs = inputs;
        this.outputs = outputs;
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }
    //MachineConfiguration: 0, inputs: 1, powerType: 1, Lists   (1)
    public Recipe(MachineType machine, PowerType powerType, double power_per_tick, List<ItemStack> inputs, List<ItemStack> outputs, double time_seconds) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                getFirstItem(outputs),
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = MachineConfiguration.None;
        this.powerType = powerType;
        this.power_usage_per_tick = power_per_tick;
        this.inputs = inputs;
        this.outputs = outputs;
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }
    //MachineConfiguration: 1, inputs: 0, powerType: 1, Lists   (1)
    public Recipe(MachineType machine, MachineConfiguration configuration, PowerType powerType, double power_per_tick, List<ItemStack> outputs, double time_seconds) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                getFirstItem(outputs),
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = configuration;
        this.powerType = powerType;
        this.power_usage_per_tick = power_per_tick;
        this.inputs = new ArrayList<>();
        this.outputs = outputs;
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }
    //MachineConfiguration: 0, inputs: 0, powerType: 1, Lists   (1)
    public Recipe(MachineType machine, PowerType powerType, double power_per_tick, List<ItemStack> outputs, double time_seconds) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                getFirstItem(outputs),
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = MachineConfiguration.None;
        this.powerType = powerType;
        this.power_usage_per_tick = power_per_tick;
        this.inputs = new ArrayList<>();
        this.outputs = outputs;
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }
    //MachineConfiguration: 1, inputs: 1, powerType: 1, Singles (0)
    public Recipe(MachineType machine, MachineConfiguration configuration, PowerType powerType, double power_per_tick, ItemStack input, ItemStack output, double time_seconds) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                output.item,
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = configuration;
        this.powerType = powerType;
        this.power_usage_per_tick = power_per_tick;
        this.inputs = List.of(input);
        this.outputs = List.of(output);
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }
    //MachineConfiguration: 0, inputs: 1, powerType: 1, Singles (0)
    public Recipe(MachineType machine, PowerType powerType, double power_per_tick, ItemStack input, ItemStack output, double time_seconds) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                output.item,
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = MachineConfiguration.None;
        this.powerType = powerType;
        this.power_usage_per_tick = power_per_tick;
        this.inputs = List.of(input);
        this.outputs = List.of(output);
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }
    //MachineConfiguration: 1, inputs: 0, powerType: 1, Singles (0)
    public Recipe(MachineType machine, MachineConfiguration configuration, PowerType powerType, double power_per_tick, ItemStack output, double time_seconds) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                output.item,
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = configuration;
        this.powerType = powerType;
        this.power_usage_per_tick = power_per_tick;
        this.inputs = new ArrayList<>();
        this.outputs = List.of(output);
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }
    //MachineConfiguration: 0, inputs: 0, powerType: 1, Singles (0)
    public Recipe(MachineType machine, PowerType powerType, double power_per_tick, ItemStack output, double time_seconds) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                output.item,
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = MachineConfiguration.None;
        this.powerType = powerType;
        this.power_usage_per_tick = power_per_tick;
        this.inputs = new ArrayList<>();
        this.outputs = List.of(output);
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }

    //MachineConfiguration: 1, inputs: 1, powerType: 0, Lists   (1)
    public Recipe(MachineType machine, MachineConfiguration configuration, double power_per_tick, List<ItemStack> inputs, List<ItemStack> outputs, double time_seconds) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                getFirstItem(outputs),
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = configuration;
        this.powerType = PowerType.EU;
        this.power_usage_per_tick = power_per_tick;
        this.inputs = inputs;
        this.outputs = outputs;
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }
    //MachineConfiguration: 0, inputs: 1, powerType: 0, Lists   (1)
    public Recipe(MachineType machine, double power_per_tick, List<ItemStack> inputs, List<ItemStack> outputs, double time_seconds) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                getFirstItem(outputs),
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = MachineConfiguration.None;
        this.powerType = PowerType.EU;
        this.power_usage_per_tick = power_per_tick;
        this.inputs = inputs;
        this.outputs = outputs;
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }
    //MachineConfiguration: 1, inputs: 0, powerType: 0, Lists   (1)
    public Recipe(MachineType machine, MachineConfiguration configuration, double power_per_tick, List<ItemStack> outputs, double time_seconds) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                getFirstItem(outputs),
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = configuration;
        this.powerType = PowerType.EU;
        this.power_usage_per_tick = power_per_tick;
        this.inputs = new ArrayList<>();
        this.outputs = outputs;
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }
    //MachineConfiguration: 0, inputs: 0, powerType: 0, Lists   (1)
    public Recipe(MachineType machine, double power_per_tick, List<ItemStack> outputs, double time_seconds) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                getFirstItem(outputs),
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = MachineConfiguration.None;
        this.powerType = PowerType.EU;
        this.power_usage_per_tick = power_per_tick;
        this.inputs = new ArrayList<>();
        this.outputs = outputs;
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }
    //MachineConfiguration: 1, inputs: 1, powerType: 0, Singles (0)
    public Recipe(MachineType machine, MachineConfiguration configuration, double power_per_tick, ItemStack input, ItemStack output, double time_seconds) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                output.item,
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = configuration;
        this.powerType = PowerType.EU;
        this.power_usage_per_tick = power_per_tick;
        this.inputs = List.of(input);
        this.outputs = List.of(output);
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }
    //MachineConfiguration: 0, inputs: 1, powerType: 0, Singles (0)
    public Recipe(MachineType machine, double power_per_tick, ItemStack input, ItemStack output, double time_seconds) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                output.item,
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = MachineConfiguration.None;
        this.powerType = PowerType.EU;
        this.power_usage_per_tick = power_per_tick;
        this.inputs = List.of(input);
        this.outputs = List.of(output);
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }
    //MachineConfiguration: 1, inputs: 0, powerType: 0, Singles (0)
    public Recipe(MachineType machine, MachineConfiguration configuration, double power_per_tick, ItemStack output, double time_seconds) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                output.item,
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = configuration;
        this.powerType = PowerType.EU;
        this.power_usage_per_tick = power_per_tick;
        this.inputs = new ArrayList<>();
        this.outputs = List.of(output);
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }
    //MachineConfiguration: 0, inputs: 0, powerType: 0, Singles (0)
    public Recipe(MachineType machine, double power_per_tick, ItemStack output, double time_seconds) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                output.item,
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = MachineConfiguration.None;
        this.powerType = PowerType.EU;
        this.power_usage_per_tick = power_per_tick;
        this.inputs = new ArrayList<>();
        this.outputs = List.of(output);
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }


    //# Setter(s)
    public void setPowerUsageRate(double power_per_tick) {
        if(power_per_tick <= 0.0) {
            power_per_tick = 0.0;
            return;
        }

        double maximum_power_rate = ( this.machineType.getMinimumVoltageForLimit(power_per_tick).EULimit() );
        if(maximum_power_rate < power_per_tick) {
            Recipe.LOGGER.log(
                Level.WARNING,
                "EU-rate for \""+this+"\", "+power_per_tick+" above maximum rate of "+maximum_power_rate+"; truncated."
            );

        }

        this.power_usage_per_tick = Math.min(maximum_power_rate, power_per_tick);
    }

    //# Calculators + Helpers
    private static Item getFirstItem(List<ItemStack> itemStacks) {
        if( itemStacks.isEmpty() ) {
            return null;
        }
        return itemStacks.getFirst().item;
    }

    //Complexity is the number of steps to get from
    //basic inputs to the result of the recipe
    public static int calculateComplexity(Recipe recipe) {
        int depth = 0;
        //TODO
        /*
        for(ItemStack input : recipe.inputs) {
            if(input.preferredRecipeSource != null) {
                depth = Math.max(depth, calculateComplexity(input.preferredRecipeSource)+1 );
            }
        }
        */
        return depth;
    }

    private static int getRegisteredRecipeOccurenceCount(Item item) {
        int counter = 0;
        for(Recipe registeredRecipe : Recipes.registry) {
            if(
                registeredRecipe.outputs != null
                && registeredRecipe.outputs.getFirst().item.equals(item)
            ) {
                counter++;
            }
        }
        return counter;
    }
    public static String getUniqueRecipeName(Item primaryInput, String namespace) {
        if(primaryInput == null) {
            return "";
        }

        int recipe_count = getRegisteredRecipeOccurenceCount(primaryInput);

        StringBuilder nameBuilder = new StringBuilder();
        nameBuilder.append(namespace).append(":").append( primaryInput.toString() );
        if(0 < recipe_count) {
            nameBuilder.append("__").append( Integer.toString(recipe_count) );
        }
        return nameBuilder.toString();
    }

    public double getProductionRate(Item ofItem) {
        double production_rate = 0.0;
        for(ItemStack output : outputs) {
            if( output.item.equals(ofItem) ) {
                production_rate += output.quantity;
            }
        }
        production_rate /= (time_seconds/20.0);

        return production_rate;
    }
}