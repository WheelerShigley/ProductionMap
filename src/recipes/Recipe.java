package recipes;

import items.ItemStack;
import machines.MachineConfiguration;
import machines.Machine;

import java.util.List;

public record Recipe(
    Machine machine,
    MachineConfiguration circuit,
    List<ItemStack> inputs,
    List<ItemStack> outputs,
    double time_seconds
) {}