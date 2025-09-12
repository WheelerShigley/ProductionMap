package recipes;

import items.ItemStack;
import machines.MachineConfiguration;
import machines.Machine;

import java.util.ArrayList;
import java.util.List;

public record Recipe(
    Machine machine,
    MachineConfiguration circuit,
    List<ItemStackWithPreferredRecipeSource> inputs,
    List<ItemStack> outputs,
    double time_seconds
) {
    public List<ItemStack> getInputsAsItemStacks() {
        List<ItemStack> inputsList = new ArrayList<>();
        for(ItemStackWithPreferredRecipeSource source : inputs) {
            inputsList.add(source.itemStack);
        }
        return inputsList;
    }
}