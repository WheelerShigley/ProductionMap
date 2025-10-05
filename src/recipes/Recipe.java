package recipes;

import items.Item;
import items.ItemStack;
import machines.MachineConfiguration;
import machines.MachineType;
import register.Identified;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Recipe extends Identified {
    public MachineType machineType;
    public double eu_per_tick;
    public final MachineConfiguration configuration; //optional

    public List<ItemStack> inputs; //optional
    public final List<ItemStack> outputs;
    public final double time_seconds;

    public final int complexity;
    public boolean isConsolidated; //TODO: rework

    private final static Logger LOGGER = Logger.getLogger("RecipeLogger");

    public Recipe(
        MachineType machine, MachineConfiguration configuration, double eu_per_tick,
        List<ItemStack> inputs, List<ItemStack> outputs, double time_seconds
    ) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                getFirstItem(outputs),
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = configuration;
        this.eu_per_tick = eu_per_tick;
        this.inputs = inputs;
        this.outputs = outputs;
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }
    public Recipe(
        MachineType machine, double eu_per_tick,
        List<ItemStack> inputs, List<ItemStack> outputs, double time_seconds
    ) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                getFirstItem(outputs),
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = MachineConfiguration.None;
        this.eu_per_tick = eu_per_tick;
        this.inputs = inputs;
        this.outputs = outputs;
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }
    public Recipe(
        MachineType machine, MachineConfiguration configuration, double eu_per_tick,
        List<ItemStack> outputs, double time_seconds
    ) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                getFirstItem(outputs),
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = configuration;
        this.eu_per_tick = eu_per_tick;
        this.inputs = new ArrayList<>();
        this.outputs = outputs;
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }
    public Recipe(
        MachineType machine, double eu_per_tick,
        List<ItemStack> outputs, double time_seconds
    ) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                getFirstItem(outputs),
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = MachineConfiguration.None;
        this.eu_per_tick = eu_per_tick;
        this.inputs = new ArrayList<>();
        this.outputs = outputs;
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }
    public Recipe(
        MachineType machine, MachineConfiguration configuration, double eu_per_tick,
        ItemStack input, ItemStack output, double time_seconds
    ) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                output.item,
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = configuration;
        this.eu_per_tick = eu_per_tick;
        this.inputs = List.of(input);
        this.outputs = List.of(output);
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }
    public Recipe(
        MachineType machine, double eu_per_tick,
        ItemStack input, ItemStack output, double time_seconds
    ) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                output.item,
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = MachineConfiguration.None;
        this.eu_per_tick = eu_per_tick;
        this.inputs = List.of(input);
        this.outputs = List.of(output);
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }
    public Recipe(
        MachineType machine, MachineConfiguration configuration, double eu_per_tick,
        ItemStack output, double time_seconds
    ) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                output.item,
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = configuration;
        this.eu_per_tick = eu_per_tick;
        this.inputs = new ArrayList<>();
        this.outputs = List.of(output);
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }
    public Recipe(
        MachineType machine, double eu_per_tick,
        ItemStack output, double time_seconds
    ) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                output.item,
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.configuration = MachineConfiguration.None;
        this.eu_per_tick = eu_per_tick;
        this.inputs = new ArrayList<>();
        this.outputs = List.of(output);
        this.time_seconds = time_seconds;

        this.complexity = calculateComplexity(this);
        Recipes.register(this);
    }

    //# Setter(s)
    public void setEUPerTick(double eu_per_tick) {
        if(eu_per_tick <= 0.0) {
            eu_per_tick = 0.0;
            return;
        }

        double maximum_EU_rate = ( this.machineType.getMinimumVoltageForLimit(eu_per_tick).EULimit() );
        if(maximum_EU_rate < eu_per_tick) {
            Recipe.LOGGER.log(
                Level.WARNING,
                "EU-rate for \""+this+"\", "+eu_per_tick+" above maximum rate of "+maximum_EU_rate+"; truncated."
            );

        }

        this.eu_per_tick = Math.min(maximum_EU_rate, eu_per_tick);
    }

    //# Getter(s)
    public int getTimeInTicks() {
        return (int)(20.0*this.time_seconds);
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
        production_rate /= getTimeInTicks();

        return production_rate;
    }
}