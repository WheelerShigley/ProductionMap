package recipes;

import items.ItemStack;
import items.Items;
import machines.Machines;

import java.util.Arrays;

public class Recipes {
    public static final Recipe TEST = new Recipe(
        Machines.TEST,
        Arrays.asList( new ItemStack(Items.AIR, 1) ),
        Arrays.asList( new ItemStack(Items.AIR, 1) ),
        1
    );
    public static final Recipe ElectricCircuits = new Recipe(
        Machines.TEST,
        Arrays.asList(
            new ItemStack(Items.CIRCUIT_BOARD, 1),
            new ItemStack(Items.RESISTOR, 2),
            new ItemStack(Items.ONE_RED_ALLOY_WIRE, 2),
            new ItemStack(Items.VACUUM_TUBE, 2),
            new ItemStack(Items.MOLTEN_LEAD, 2)
        ),
        Arrays.asList( new ItemStack(Items.ELECTRIC_CIRCUIT, 1) ),
        10
    );
}
