package recipes;

import items.ItemStack;
import machines.Circuit;
import machines.Machine;

import java.util.List;

public record Recipe(
    Machine machine,
    Circuit circuit,
    List<ItemStack> inputs,
    List<ItemStack> outputs,
    double time_seconds
) {}