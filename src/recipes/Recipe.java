package recipes;

import items.ItemStack;
import machines.Machine;

import java.util.List;

public record Recipe(
    Machine machine,
    List<ItemStack> inputs,
    List<ItemStack> outputs,
    float time_seconds
) {}