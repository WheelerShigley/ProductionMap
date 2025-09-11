package recipes;

import items.ItemStack;
import items.Items;
import machines.MachineConfiguration;
import machines.Machines;

import java.util.List;

public class Recipes {
    public static final Recipe AIR_GAS; static {
        AIR_GAS = new Recipe(
                Machines.LOW_VOLTAGE_FLUID_TANK,
                MachineConfiguration.None,
                List.of(
                        new ItemStack(Items.COMPRESSED_AIR, 1)
                ),
                List.of(
                        new ItemStack(Items.EMPTY_CELL, 1),
                        new ItemStack(Items.AIR_GAS, 2)
                ),
                1
        );
    }
    public static final Recipe ANY_WOOD; static {
        ANY_WOOD = new Recipe(
                Machines.CROP_MANAGER_LV,
                MachineConfiguration.None,
                List.of(
                        new ItemStack(Items.NOTHING, 1)
                ),
                List.of(
                        new ItemStack(Items.ANY_WOOD, 1)
                ),
                0.05
        );
    }
    public static final Recipe CIRCUIT_BOARD; static {
        CIRCUIT_BOARD = new Recipe(
            Machines.BASIC_ASSEMBLING_MACHINE,
            MachineConfiguration.ProgrammedCircuitSix,
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
    public static final Recipe COAL_DUST; static {
        COAL_DUST = new Recipe(
                Machines.PLAYER,
                MachineConfiguration.None,
                List.of(
                        new ItemStack(Items.MANUAL, 1)
                ),
                List.of(
                        new ItemStack(Items.COAL_DUST, 1)
                ),
                Double.MIN_VALUE
        );
    }
    public static final Recipe COBBLESTONE; static {
        COBBLESTONE = new Recipe(
                Machines.BASIC_ROCK_BREAKER,
                MachineConfiguration.None,
                List.of(
                        new ItemStack(Items.NOTHING, 1)
                ),
                List.of(
                        new ItemStack(Items.COBBLESTONE, 1)
                ),
                0.8
        );
    }
    public static final Recipe COMPRESSED_AIR; static {
        COMPRESSED_AIR = new Recipe(
                Machines.BASIC_COMPRESSOR,
                MachineConfiguration.None,
                List.of(
                        new ItemStack(Items.EMPTY_CELL, 1)
                ),
                List.of(
                        new ItemStack(Items.COMPRESSED_AIR, 1)
                ),
                15
        );
    }
    public static final Recipe COPPER_FOIL; static {
        COPPER_FOIL = new Recipe(
                Machines.BASIC_BENDING_MACHINE,
                MachineConfiguration.ProgrammedCircuitTen,
                List.of(
                        new ItemStack(Items.COPPER_INGOT, 1)
                ),
                List.of(
                        new ItemStack(Items.COPPER_FOIL, 4)
                ),
                6.3
        );
    }
    public static final Recipe COPPER_INGOT; static {
        COPPER_INGOT = new Recipe(
                Machines.PLAYER,
                MachineConfiguration.None,
                List.of(
                        new ItemStack(Items.MANUAL, 1)
                ),
                List.of(
                        new ItemStack(Items.COPPER_INGOT, 1)
                ),
                Double.MIN_VALUE
        );
    }
    public static final Recipe ELECTRIC_CIRCUITS; static {
        ELECTRIC_CIRCUITS = new Recipe(
                Machines.BASIC_CIRCUIT_ASSEMBLER,
                MachineConfiguration.None,
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
    public static final Recipe EMPTY_CELL_CYCLED; static {
        EMPTY_CELL_CYCLED = new Recipe(
                Machines.CELL_CYCLING,
                MachineConfiguration.None,
                List.of(
                        new ItemStack(Items.COMPRESSED_AIR, 1)
                ),
                List.of(
                        new ItemStack(Items.EMPTY_CELL, 1)
                ),
                1
        );
    }
    public static final Recipe FINE_COPPER_WIRE; static {
        FINE_COPPER_WIRE = new Recipe(
                Machines.BASIC_WIREMILL,
                MachineConfiguration.ProgrammedCircuitThree,
                List.of(
                        new ItemStack(Items.COPPER_INGOT, 1)
                ),
                List.of(
                        new ItemStack(Items.FINE_COPPER_WIRE, 4)
                ),
                5
        );
    }
    public static final Recipe FLINT; static {
        FLINT = new Recipe(
                Machines.BASIC_SIFTER,
                MachineConfiguration.None,
                List.of(
                        new ItemStack(Items.GRAVEL, 1)
                ),
                List.of(
                        new ItemStack(Items.FLINT, 1+0.9+0.8+0.6+0.33+0.25)
                ),
                30
        );
    }
    public static final Recipe FLINT_DUST; static {
        FLINT_DUST = new Recipe(
                Machines.BASIC_MACERATOR,
                MachineConfiguration.None,
                List.of(
                        new ItemStack(Items.FLINT, 2)
                ),
                List.of(
                        new ItemStack(Items.FLINT_DUST, 1)
                ),
                10
        );
    }
    public static final Recipe GLASS_DUST; static {
        GLASS_DUST = new Recipe(
                Machines.BASIC_MIXER,
                MachineConfiguration.ProgrammedCircuitFour,
                List.of(
                        new ItemStack(Items.FLINT_DUST, 1),
                        new ItemStack(Items.QUARTZ_SAND, 16)
                ),
                List.of(
                        new ItemStack(Items.GLASS_DUST, 16)
                ),
                40
        );
    }
    public static final Recipe GLASS_TUBE; static {
        GLASS_TUBE = new Recipe(
                Machines.BASIC_ALLOY_SMELTER,
                MachineConfiguration.MoldBall,
                List.of(
                        new ItemStack(Items.GLASS_DUST, 1)
                ),
                List.of(
                        new ItemStack(Items.GLASS_TUBE, 1)
                ),
                6
        );
    }
    public static final Recipe GRAVEL; static {
        GRAVEL = new Recipe(
                Machines.BASIC_FORGE_HAMMER,
                MachineConfiguration.None,
                List.of(
                        new ItemStack(Items.COBBLESTONE, 1)
                ),
                List.of(
                        new ItemStack(Items.GRAVEL, 1)
                ),
                0.5
        );
    }
    public static final Recipe IRON_DUST; static {
        IRON_DUST = new Recipe(
                Machines.PLAYER,
                MachineConfiguration.None,
                List.of(
                        new ItemStack(Items.MANUAL, 1)
                ),
                List.of(
                        new ItemStack(Items.IRON_DUST, 1)
                ),
                Double.MIN_VALUE
        );
    }
    public static final Recipe LEAD_INGOT; static {
        LEAD_INGOT = new Recipe(
                Machines.PLAYER,
                MachineConfiguration.None,
                List.of(
                        new ItemStack(Items.MANUAL, 1)
                ),
                List.of(
                        new ItemStack(Items.LEAD_INGOT, 1)
                ),
                Double.MIN_VALUE
        );
    }
    public static final Recipe MOLTEN_LEAD; static {
        MOLTEN_LEAD = new Recipe(
                Machines.ADVANCED_FLUID_EXTRACTOR,
                MachineConfiguration.None,
                List.of(
                        new ItemStack(Items.LEAD_INGOT, 1)
                ),
                List.of(
                        new ItemStack(Items.MOLTEN_LEAD, 1)
                ),
                1.2
        );
    }
    public static final Recipe MOLTEN_RED_ALLOY; static {
        MOLTEN_RED_ALLOY = new Recipe(
                Machines.BASIC_FLUID_EXTRACTOR,
                MachineConfiguration.None,
                List.of(
                        new ItemStack(Items.RED_ALLOY_INGOT, 1)
                ),
                List.of(
                        new ItemStack(Items.MOLTEN_RED_ALLOY, 1)
                ),
                1.2
        );
    }
    public static final Recipe ONE_COPPER_WIRE; static {
        ONE_COPPER_WIRE = new Recipe(
                Machines.BASIC_WIREMILL,
                MachineConfiguration.ProgrammedCircuitOne,
                List.of(
                        new ItemStack(Items.COPPER_INGOT, 1)
                ),
                List.of(
                        new ItemStack(Items.ONE_COPPER_WIRE, 2)
                ),
                5
        );
    }
    public static final Recipe ONE_RED_ALLOY_WIRE; static {
        ONE_RED_ALLOY_WIRE = new Recipe(
                Machines.BASIC_WIREMILL,
                MachineConfiguration.ProgrammedCircuitOne,
                List.of(
                        new ItemStack(Items.RED_ALLOY_INGOT, 1)
                ),
                List.of(
                        new ItemStack(Items.ONE_RED_ALLOY_WIRE, 2)
                ),
                5
        );
    }
    public static final Recipe OXYGEN_CELL; static {
        OXYGEN_CELL = new Recipe(
                Machines.BASIC_CENTRIFUGE,
                MachineConfiguration.None,
                List.of(
                        new ItemStack(Items.EMPTY_CELL, 1),
                        new ItemStack(Items.COMPRESSED_AIR, 10)
                ),
                List.of(
                        new ItemStack(Items.OXYGEN_CELL, 1),
                        new ItemStack(Items.NITROGEN_GAS, 3.9)
                ),
                2.8
        );
    }
    public static final Recipe OXYGEN_GAS; static {
        OXYGEN_GAS = new Recipe(
                Machines.LOW_VOLTAGE_FLUID_TANK,
                MachineConfiguration.None,
                List.of(
                        new ItemStack(Items.OXYGEN_CELL, 1)
                ),
                List.of(
                        new ItemStack(Items.EMPTY_CELL, 1),
                        new ItemStack(Items.OXYGEN_GAS, 1)
                ),
                1
        );
    }
    public static final Recipe QUARTZ_SAND; static {
        QUARTZ_SAND = new Recipe(
                Machines.BASIC_MACERATOR,
                MachineConfiguration.None,
                List.of(
                        new ItemStack(Items.SAND, 1)
                ),
                List.of(
                        new ItemStack(Items.QUARTZ_SAND, 1)
                ),
                10
        );
    }
    public static final Recipe REDSTONE_DUST; static {
        REDSTONE_DUST = new Recipe(
                Machines.PLAYER,
                MachineConfiguration.None,
                List.of(
                        new ItemStack(Items.MANUAL, 1)
                ),
                List.of(
                        new ItemStack(Items.REDSTONE_DUST, 1)
                ),
                Double.MIN_VALUE
        );
    }
    public static final Recipe RED_ALLOY_INGOT; static {
        RED_ALLOY_INGOT = new Recipe(
                Machines.BASIC_ALLOY_SMELTER,
                MachineConfiguration.None,
                List.of(
                        new ItemStack(Items.COPPER_INGOT, 1),
                        new ItemStack(Items.REDSTONE_DUST, 4)
                ),
                List.of(
                        new ItemStack(Items.RED_ALLOY_INGOT, 1)
                ),
                2.5
        );
    }
    public static final Recipe REFINED_GLUE; static {
        REFINED_GLUE = new Recipe(
                Machines.BASIC_CENTRIFUGE,
                MachineConfiguration.None,
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
    public static final Recipe RESISTOR; static {
        RESISTOR = new Recipe(
                Machines.BASIC_CIRCUIT_ASSEMBLER,
                MachineConfiguration.ProgrammedCircuitThree,
                List.of(
                        new ItemStack(Items.COAL_DUST, 1),
                        new ItemStack(Items.FINE_COPPER_WIRE, 4),
                        new ItemStack(Items.ONE_COPPER_WIRE, 4),
                        new ItemStack(Items.REFINED_GLUE, 2)
                ),
                List.of(
                        new ItemStack(Items.RESISTOR, 4)
                ),
                16
        );
    }
    public static final Recipe SAND; static {
        SAND = new Recipe(
                Machines.BASIC_FORGE_HAMMER,
                MachineConfiguration.None,
                List.of(
                        new ItemStack(Items.GRAVEL, 1)
                ),
                List.of(
                        new ItemStack(Items.SAND, 1)
                ),
                0.5
        );
    }
    public static final Recipe STEEL_INGOT; static {
        STEEL_INGOT = new Recipe(
            Machines.ELECTRIC_BLAST_FURNACE_MV_NICU,
            MachineConfiguration.ProgrammedCircuitEleven,
            List.of(
                new ItemStack(Items.WROUGHT_IRON_DUST, 1),
                new ItemStack(Items.OXYGEN_GAS, 1)
            ),
            List.of(
                new ItemStack(Items.STEEL_INGOT, 1),
                new ItemStack(Items.ASHES, 1.0/9.0)
            ),
            5
        );
    }
    public static final Recipe STEEL_ROD; static {
        STEEL_ROD = new Recipe(
                Machines.ADVANCED_EXTRUDER,
                MachineConfiguration.ExtrudeShapeRod,
                List.of(
                        new ItemStack(Items.STEEL_INGOT, 1)
                ),
                List.of(
                        new ItemStack(Items.STEEL_ROD, 2)
                ),
                5.6
        );
    }
    public static final Recipe STICKY_RESIN; static {
        STICKY_RESIN = new Recipe(
                Machines.CROP_MANAGER_LV,
                MachineConfiguration.None,
                List.of(
                        new ItemStack(Items.NOTHING, 1)
                ),
                List.of(
                        new ItemStack(Items.STICKY_RESIN, 1)
                ),
                0.05
        );
    }
    public static final Recipe VACUUM_TUBE; static {
        VACUUM_TUBE = new Recipe(
                Machines.BASIC_ASSEMBLING_MACHINE,
                MachineConfiguration.ProgrammedCircuitFive,
                List.of(
                        new ItemStack(Items.GLASS_TUBE, 4),
                        new ItemStack(Items.ONE_COPPER_WIRE, 4),
                        new ItemStack(Items.STEEL_ROD, 4),
                        new ItemStack(Items.MOLTEN_RED_ALLOY, 0.5)
                ),
                List.of(
                        new ItemStack(Items.VACUUM_TUBE, 4)
                ),
                8
        );
    }
    public static final Recipe WOOD_PLANK; static {
        WOOD_PLANK = new Recipe(
            Machines.BASIC_ASSEMBLING_MACHINE,
            MachineConfiguration.ProgrammedCircuitTwo,
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
    public static final Recipe WOOD_PULP; static {
        WOOD_PULP = new Recipe(
            Machines.BASIC_MACERATOR,
            MachineConfiguration.None,
            List.of(
                new ItemStack(Items.ANY_WOOD, 1)
            ),
            List.of(
                new ItemStack(Items.WOOD_PULP, 6)
            ),
            20
        );
    }
    public static final Recipe WROUGHT_IRON_DUST; static {
        WROUGHT_IRON_DUST = new Recipe(
            Machines.BASIC_MACERATOR,
            MachineConfiguration.ProgrammedCircuitEleven,
            List.of(
                new ItemStack(Items.WROUGHT_IRON_INGOT, 1)
            ),
            List.of(
                new ItemStack(Items.WROUGHT_IRON_DUST, 1)
            ),
            2.8
        );
    }
    public static final Recipe WROUGHT_IRON_INGOT; static {
        WROUGHT_IRON_INGOT = new Recipe(
            Machines.BASIC_ARC_FURNACE,
            MachineConfiguration.None,
            List.of(
                new ItemStack(Items.IRON_DUST, 1),
                    new ItemStack(Items.OXYGEN_GAS, 56.0/1000.0)
            ),
            List.of(
                new ItemStack(Items.WROUGHT_IRON_INGOT, 1)
            ),
            2.8
        );
    }
}
