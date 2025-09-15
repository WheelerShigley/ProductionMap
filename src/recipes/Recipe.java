package recipes;

import items.ItemStack;
import machines.MachineConfiguration;
import machines.Machine;
import register.Identified;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    public Machine machine;
    public final double amperage;
    public final MachineConfiguration circuit;
    public List<ItemStackWithPreferredRecipeSource> inputs;
    public final List<ItemStack> outputs;
    public final double time_seconds;
    public final int complexity;

    public Recipe(
        Machine machine,
        List<ItemStackWithPreferredRecipeSource> inputs,
        List<ItemStack> outputs,
        double time_seconds
    ) {
        this.machine = machine;
        amperage = 1.0;
        circuit = MachineConfiguration.None;
        this.inputs = inputs;
        this.outputs = outputs;
        this.time_seconds = time_seconds;
        this.complexity = calculateComplexity(this);
    }
    public Recipe(
            Machine machine,
            MachineConfiguration circuit,
            List<ItemStackWithPreferredRecipeSource> inputs,
            List<ItemStack> outputs,
            double time_seconds
    ) {
        this.machine = machine;
        amperage = 1.0;
        this.circuit = circuit;
        this.inputs = inputs;
        this.outputs = outputs;
        this.time_seconds = time_seconds;
        this.complexity = calculateComplexity(this);
    }
    public Recipe(
            Machine machine,
            double amperage,
            List<ItemStackWithPreferredRecipeSource> inputs,
            List<ItemStack> outputs,
            double time_seconds
    ) {
        this.machine = machine;
        this.amperage = amperage;
        this.circuit = MachineConfiguration.None;
        this.inputs = inputs;
        this.outputs = outputs;
        this.time_seconds = time_seconds;
        this.complexity = calculateComplexity(this);
    }
    public Recipe(
            Machine machine,
            double amperage,
            MachineConfiguration configuration,
            List<ItemStackWithPreferredRecipeSource> inputs,
            List<ItemStack> outputs,
            double time_seconds
    ) {
        this.machine = machine;
        this.amperage = amperage;
        this.circuit = configuration;
        this.inputs = inputs;
        this.outputs = outputs;
        this.time_seconds = time_seconds;
        this.complexity = calculateComplexity(this);
    }

    public List<ItemStack> getInputsAsItemStacks() {
        List<ItemStack> inputsList = new ArrayList<>();
        for(ItemStackWithPreferredRecipeSource source : inputs) {
            inputsList.add(source.itemStack);
        }
        return inputsList;
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
}