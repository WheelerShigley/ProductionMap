package recipes;

import items.ItemStack;
import machines.MachineConfiguration;
import machines.Machine;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    public final Machine machine;
    public final double amperage;
    public final MachineConfiguration circuit;
    public final List<ItemStackWithPreferredRecipeSource> inputs;
    public final List<ItemStack> outputs;
    public final double time_seconds;

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
    }

    public List<ItemStack> getInputsAsItemStacks() {
        List<ItemStack> inputsList = new ArrayList<>();
        for(ItemStackWithPreferredRecipeSource source : inputs) {
            inputsList.add(source.itemStack);
        }
        return inputsList;
    }
}