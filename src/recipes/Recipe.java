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
    public final double amperage;
    public double eu_per_tick;
    public final MachineConfiguration circuit;
    public List<ItemStackWithPreferredRecipeSource> inputs;
    public final List<ItemStack> outputs;
    public final double time_seconds;
    public final int complexity;
    public boolean isConsolidated;

    private final static Logger LOGGER = Logger.getLogger("RecipeLogger");

    public Recipe(
        MachineType machine,
        List<ItemStackWithPreferredRecipeSource> inputs,
        List<ItemStack> outputs,
        double time_seconds
    ) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                getFirstItem(outputs),
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        amperage = 1.0;
        this.eu_per_tick = amperage*machine.getMinimumVoltage().EULimit();
        circuit = MachineConfiguration.None;
        this.inputs = inputs;
        this.outputs = outputs;
        this.time_seconds = time_seconds;
        this.complexity = calculateComplexity(this);

        Recipes.register(this);
    }
    public Recipe(
        MachineType machine,
        MachineConfiguration circuit,
        List<ItemStackWithPreferredRecipeSource> inputs,
        List<ItemStack> outputs,
        double time_seconds
    ) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                getFirstItem(outputs),
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        amperage = 1.0;
        this.eu_per_tick = amperage*machine.getMinimumVoltage().EULimit();
        this.circuit = circuit;
        this.inputs = inputs;
        this.outputs = outputs;
        this.time_seconds = time_seconds;
        this.complexity = calculateComplexity(this);

        Recipes.register(this);
    }
    public Recipe(
            MachineType machine,
            double amperage,
            List<ItemStackWithPreferredRecipeSource> inputs,
            List<ItemStack> outputs,
            double time_seconds
    ) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                getFirstItem(outputs),
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.amperage = amperage;
        this.eu_per_tick = amperage*machine.getMinimumVoltage().EULimit();
        this.circuit = MachineConfiguration.None;
        this.inputs = inputs;
        this.outputs = outputs;
        this.time_seconds = time_seconds;
        this.complexity = calculateComplexity(this);

        Recipes.register(this);
    }
    public Recipe(
            MachineType machine,
            double amperage,
            MachineConfiguration configuration,
            List<ItemStackWithPreferredRecipeSource> inputs,
            List<ItemStack> outputs,
            double time_seconds
    ) {
        super(
            machine.getNamespace(),
            getUniqueRecipeName(
                getFirstItem(outputs),
                machine.getNamespace()
            )
        );

        this.machineType = machine;
        this.amperage = amperage;
        this.eu_per_tick = amperage*machine.getMinimumVoltage().EULimit();
        this.circuit = configuration;
        this.inputs = inputs;
        this.outputs = outputs;
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

        double maximum_EU_rate = amperage*( (double)this.machineType.getMinimumVoltageForLimit(eu_per_tick).EULimit() );
        if(maximum_EU_rate < eu_per_tick) {
            Recipe.LOGGER.log(
                Level.WARNING,
                "EU-rate for \""+this+"\", "+eu_per_tick+" above maximum rate of "+maximum_EU_rate+"; truncated."
            );

        }

        this.eu_per_tick = Math.min(maximum_EU_rate, eu_per_tick);
    }

    //# Calculators + Helpers
    public List<ItemStack> getInputsAsItemStacks() {
        List<ItemStack> inputsList = new ArrayList<>();
        for(ItemStackWithPreferredRecipeSource source : inputs) {
            inputsList.add(source.itemStack);
        }
        return inputsList;
    }
    private static Item getFirstItem(List<ItemStack> itemStacks) {
        if( itemStacks.isEmpty() ) {
            return null;
        }
        return itemStacks.getFirst().item();
    }

    //Complexity is the number of steps to get from
    //basic inputs to the result of the recipe
    public static int calculateComplexity(Recipe recipe) {
        int depth = 0;
        for(ItemStackWithPreferredRecipeSource input : recipe.inputs) {
            if(input.preferredRecipeSource != null) {
                depth = Math.max(depth, calculateComplexity(input.preferredRecipeSource)+1 );
            }
        }
        return depth;
    }

    private static int getRegisteredRecipeOccuranceCount(Item item) {
        int counter = 0;
        for(Recipe registeredRecipe : Recipes.registry) {
            if(
                registeredRecipe.outputs != null
                && registeredRecipe.outputs.getFirst().item().equals(item)
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

        int recipe_count = getRegisteredRecipeOccuranceCount(primaryInput);

        StringBuilder nameBuilder = new StringBuilder();
        nameBuilder.append(namespace).append(":").append( primaryInput.toString() );
        if(0 < recipe_count) {
            nameBuilder.append("__").append( Integer.toString(recipe_count) );
        }
        return nameBuilder.toString();
    }
}