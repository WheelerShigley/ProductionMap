package recipes;

import items.ItemStack;
import items.Items;
import machines.Circuit;
import machines.Machines;

import java.util.List;

public class Recipes {
    public static final Recipe ElectricCircuits; static {
        ElectricCircuits = new Recipe(
            Machines.BASIC_CIRCUIT_ASSEMBLER,
            Circuit.None,
            List.of(
                new ItemStack(Items.CIRCUIT_BOARD, 1),
                new ItemStack(Items.RESISTOR, 2),
                new ItemStack(Items.ONE_RED_ALLOY_WIRE, 2),
                new ItemStack(Items.VACUUM_TUBE, 2),
                new ItemStack(Items.MOLTEN_LEAD, 2)
            ),
            List.of(
                new ItemStack(Items.ELECTRIC_CIRCUIT, 1)
            ),
            10
        );
    }
    public static final Recipe CircuitBoard; static {
        CircuitBoard = new Recipe(
            Machines.BASIC_ASSEMBLING_MACHINE,
            Circuit.ProgrammedCircuitSix,
            List.of(
                new ItemStack(Items.WOOD_PLANK, 8),
                new ItemStack(Items.COPPER_FOIL, 32),
                new ItemStack(Items.REFINED_GLUE, 4)
            ),
            List.of(
                new ItemStack(Items.CIRCUIT_BOARD, 8)
            ),
            80
        );
    }
    public static final Recipe WoodPlank; static {
        WoodPlank = new Recipe(
            Machines.BASIC_ASSEMBLING_MACHINE,
            Circuit.ProgrammedCircuitTwo,
            List.of(
                new ItemStack(Items.WOOD_PULP, 64),
                new ItemStack(Items.REFINED_GLUE, 1)
            ),
            List.of(
                new ItemStack(Items.WOOD_PLANK, 64)
            ),
            120
        );
    }
    public static final Recipe WoodPulp; static {
        WoodPulp = new Recipe(
            Machines.BASIC_MACERATOR,
            Circuit.None,
            List.of(
                new ItemStack(Items.ANY_WOOD, 1)
            ),
            List.of(
                new ItemStack(Items.WOOD_PULP, 6)
            ),
            20
        );
    }
    public static final Recipe AnyWood; static {
        AnyWood = new Recipe(
            Machines.CROP_MANAGER_LV,
            Circuit.None,
            List.of(
                new ItemStack(Items.AIR, 1)
            ),
            List.of(
                new ItemStack(Items.ANY_WOOD, 1)
            ),
            0.05
        );
    }
    public static final Recipe RefinedGlue; static {
        RefinedGlue = new Recipe(
            Machines.BASIC_CENTRIFUGE,
            Circuit.None,
            List.of(
                new ItemStack(Items.STICKY_RESIN, 1)
            ),
            List.of(
                new ItemStack(Items.RAW_RUBBER_DUST, 3),
                new ItemStack(Items.PLANT_BALL, 0.1),
                new ItemStack(Items.REFINED_GLUE, 100.0/144.0)
            ),
            0.05
        );
    }
    public static final Recipe StickyResin; static {
        StickyResin = new Recipe(
            Machines.CROP_MANAGER_LV,
            Circuit.None,
            List.of(
                new ItemStack(Items.AIR, 1)
            ),
            List.of(
                new ItemStack(Items.STICKY_RESIN, 1)
            ),
            0.05
        );
    }
    public static final Recipe CopperFoil; static {
        CopperFoil = new Recipe(
            Machines.BASIC_BENDING_MACHINE,
            Circuit.ProgrammedBioCircuitTen,
            List.of(
                new ItemStack(Items.COPPER_INGOT, 1)
            ),
            List.of(
                new ItemStack(Items.COPPER_FOIL, 4)
            ),
            6.3
        );
    }
    public static final Recipe CopperIngot; static {
        CopperIngot = new Recipe(
            Machines.PLAYER,
            Circuit.ProgrammedBioCircuitTen,
            List.of(
                new ItemStack(Items.AIR, 1)
            ),
            List.of(
                new ItemStack(Items.COPPER_INGOT, 1)
            ),
            Double.MIN_VALUE
        );
    }
}
