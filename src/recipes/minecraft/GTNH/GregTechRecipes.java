package recipes.minecraft.GTNH;

import items.ItemStack;
import items.Items;
import items.minecraft.GTNH.GregTech;
import items.minecraft.GTNH.IndustrialCraft;
import items.minecraft.GTNH.ThinkersConstruct;
import items.minecraft.GTNH.Vanilla;
import machines.MachineConfiguration;
import machines.MachineTypes;
import recipes.Recipe;

import java.util.ArrayList;
import java.util.List;

import static items.minecraft.GTNH.Vanilla.*;

public class GregTechRecipes {

    /* Misc */
    //If there's a need for more cells, it's almost certain that there simply needs to be more cells in the system;
    //this should always be the best choice for a deficit of empty_cells
    public static final Recipe CYCLED_CELLS = new Recipe(MachineTypes.DUMMY, 0.0, new ItemStack(GregTech.EMPTY_CELL), Double.MIN_VALUE);
    public static final Recipe DUMMY = new Recipe(MachineTypes.PLAYER, 0.0, new ArrayList<>(), Double.MIN_VALUE);
    @Deprecated
    public static final Recipe EMPTY_CELL_CYCLED = new Recipe(MachineTypes.CELL_CYCLING, 0.0, new ItemStack(GregTech.COMPRESSED_AIR), new ItemStack(GregTech.EMPTY_CELL), 1.0);

    /* Crops */
    public static final Recipe ANY_WOOD = new Recipe(MachineTypes.CROP_MANAGER, 16.0, new ItemStack(Items.ANY_WOOD), 1.0);
    public static final Recipe GLOW_FLOWER = new Recipe(MachineTypes.CROP_MANAGER, 16.0, new ItemStack(items.Items.GLOW_FLOWER), 1.0);
    public static final Recipe IRON_OREBERRY = new Recipe(MachineTypes.CROP_MANAGER, 16.0, new ItemStack(ThinkersConstruct.IRON_OREBERRY), 1.0);
    public static final Recipe NETHERRACK_DUST = new Recipe(MachineTypes.CROP_MANAGER, 16.0, new ItemStack(GregTech.NETHERRACK_DUST), 1.0);
    public static final Recipe NETHER_WART = new Recipe(MachineTypes.CROP_MANAGER, 16.0, new ItemStack(Vanilla.NETHER_WART), 1.0);
    public static final Recipe STICKY_RESIN = new Recipe(MachineTypes.CROP_MANAGER, 16.0, new ItemStack(IndustrialCraft.STICKY_RESIN), 1.0);
    public static final Recipe SALTY_ROOT = new Recipe(MachineTypes.CROP_MANAGER, 16.0, new ItemStack(Items.SALTY_ROOT), 1.0);
    public static final Recipe SUGAR_BEET = new Recipe(MachineTypes.CROP_MANAGER, 16.0, new ItemStack(Items.SUGAR_BEET), 1.0);
    public static final Recipe SUGAR_CANE = new Recipe(MachineTypes.CROP_MANAGER, 16.0, new ItemStack(Vanilla.SUGAR_CANE), 1.0);

    /* Fluid Handling */
    public static final Recipe AIR_GAS; static {
        AIR_GAS = new Recipe(
                MachineTypes.FLUID_TANK,
                0.0,
                List.of( new ItemStack(GregTech.COMPRESSED_AIR) ),
                List.of(
                        new ItemStack(GregTech.EMPTY_CELL, 1),
                        new ItemStack(GregTech.AIR_GAS, 2)
                ),
                0.1
        );
    }
    public static final Recipe CANNED_PHOSPHORIC_ACID; static {
        CANNED_PHOSPHORIC_ACID = new Recipe(
                MachineTypes.FLUID_CANNER,
                1.0,
                List.of(
                        new ItemStack(GregTech.EMPTY_CELL),
                        new ItemStack(GregTech.PHOSPHORIC_ACID)
                ),
                List.of(
                        new ItemStack(GregTech.PHOSPHORIC_ACID_CELL)
                ),
                0.1
        );
    }
    public static final Recipe DECELLED_HYDROGEN = new Recipe(MachineTypes.FLUID_TANK, 0.0, new ItemStack(GregTech.HYDROGEN_CELL), new ItemStack(GregTech.HYDROGEN), 0.1);
    public static final Recipe ETHANOL = new Recipe(MachineTypes.FLUID_TANK, 0.0, new ItemStack(GregTech.ETHANOL_CELL), new ItemStack(GregTech.ETHANOL), 0.1);
    public static final Recipe ETHYLENE_CELL = new Recipe(MachineTypes.FLUID_TANK, 0.0, new ItemStack(GregTech.ETHYLENE, 1.0/0.144), new ItemStack(GregTech.ETHYLENE_CELL), 0.1);
    public static final Recipe FISH_OIL_CELL = new Recipe(MachineTypes.FLUID_TANK, 0.0, new ItemStack(GregTech.FISH_OIL, 1.0/0.144), new ItemStack(GregTech.FISH_OIL_CELL), 0.1);
    public static final Recipe OXYGEN_GAS; static {
        OXYGEN_GAS = new Recipe(
                MachineTypes.FLUID_TANK,
                0.0,
                List.of(
                        new ItemStack(GregTech.OXYGEN_CELL)
                ),
                List.of(
                        new ItemStack(GregTech.EMPTY_CELL),
                        new ItemStack(GregTech.OXYGEN)
                ),
                0.1
        );
    }
    public static final Recipe SOLIDIFIED_TIN_BOLT = new Recipe(MachineTypes.FLUID_SOLIDIFIER, MachineConfiguration.MoldBolt, 30.0, new ItemStack(GregTech.MOLTEN_TIN, 0.018), new ItemStack(GregTech.TIN_BOLT), 2.5);
    public static final Recipe SOLIDIFIED_SILVER_BOLT = new Recipe(MachineTypes.FLUID_SOLIDIFIER, MachineConfiguration.MoldBolt, 30.0, new ItemStack(GregTech.MOLTEN_SILVER, 0.018), new ItemStack(GregTech.SILVER_BOLT), 2.5);

    /* Wires */
    public static final Recipe ONE_ANNEALED_COPPER_WIRE = new Recipe(MachineTypes.WIREMILL, MachineConfiguration.ProgrammedCircuitOne, 4.0, new ItemStack(GregTech.ANNEALED_COPPER_INGOT), new ItemStack(GregTech.ONE_ANNEALED_COPPER_WIRE, 2), 5.0);
    public static final Recipe ONE_COPPER_WIRE = new Recipe(MachineTypes.WIREMILL, MachineConfiguration.ProgrammedCircuitOne, 4.0, new ItemStack(GregTech.COPPER_INGOT), new ItemStack(GregTech.ONE_COPPER_WIRE, 2), 5.0);
    public static final Recipe ONE_RED_ALLOY_WIRE = new Recipe(MachineTypes.WIREMILL, MachineConfiguration.ProgrammedCircuitOne, 4.0, new ItemStack(GregTech.RED_ALLOY_INGOT), new ItemStack(GregTech.ONE_RED_ALLOY_WIRE, 2), 5.0);
    public static final Recipe FINE_ANNEALED_COPPER_WIRE = new Recipe(MachineTypes.WIREMILL, MachineConfiguration.ProgrammedCircuitThree, 4.0, new ItemStack(GregTech.ANNEALED_COPPER_INGOT), new ItemStack(GregTech.FINE_ANNEALED_COPPER_WIRE, 8), 5.0);
    public static final Recipe FINE_GOLD_WIRE = new Recipe(MachineTypes.WIREMILL, MachineConfiguration.ProgrammedCircuitThree, 4.0, new ItemStack(GOLD_INGOT), new ItemStack(GregTech.FINE_GOLD_WIRE, 8), 5.0);

    /* Centrifuge */
    public static final Recipe CENTRIFUGED_AIR; static {
        CENTRIFUGED_AIR = new Recipe(
                MachineTypes.CENTRIFUGE,
                8.0,
                List.of(
                        new ItemStack(GregTech.AIR_GAS, 10),
                        new ItemStack(GregTech.EMPTY_CELL)
                ),
                List.of(
                        new ItemStack(GregTech.OXYGEN_CELL, 1),
                        new ItemStack(GregTech.NITROGEN_GAS, 3.9)
                ),
                80.0
        );
    }
    public static final Recipe CENTRIFUGED_DRIED_DIRT; static {
        CENTRIFUGED_DRIED_DIRT = new Recipe(
                MachineTypes.CENTRIFUGE,
                30.0,
                List.of( new ItemStack(items.Items.DRIED_DIRT) ),
                List.of(
                        new ItemStack(GregTech.SMALL_PILE_OF_CLAY_DUST, 0.9),
                        new ItemStack(Vanilla.SAND, 0.5),
                        new ItemStack(GregTech.ASHES, 2.77/100.0)
                ),
                5.0
        );
    }
    public static final Recipe CENTRIFUGED_GLOWSTONE_DUST; static {
        CENTRIFUGED_GLOWSTONE_DUST = new Recipe(
            MachineTypes.CENTRIFUGE,
            80.0,
            List.of(
                new ItemStack(Vanilla.GLOWSTONE_DUST, 2)
            ),
            List.of(
                new ItemStack(Vanilla.REDSTONE_DUST, 1),
                new ItemStack(GregTech.GOLD_DUST, 1)
            ),
            48.8
        );
    }
    public static final Recipe CENTRIFUGED_MARBLE_DUST; static {
        CENTRIFUGED_MARBLE_DUST = new Recipe(
                MachineTypes.CENTRIFUGE,
                10.0,
                List.of(
                        new ItemStack(GregTech.MARBLE_DUST, 8)
                ),
                List.of(
                        new ItemStack(GregTech.MAGNESIUM_DUST, 1),
                        new ItemStack(GregTech.CALCITE_DUST, 7)
                ),
                32.0
        );
    }
    public static final Recipe CENTRIFUGED_STONE_DUST; static {
        CENTRIFUGED_STONE_DUST = new Recipe(
                MachineTypes.CENTRIFUGE,
                30.0,
                List.of(
                        new ItemStack(GregTech.STONE_DUST, 36)
                ),
                List.of(
                        new ItemStack(GregTech.QUARTZITE_DUST, 9),
                        new ItemStack(GregTech.POTASSIUM_FELDSPAR_DUST, 9),
                        new ItemStack(GregTech.MARBLE_DUST, 8),
                        new ItemStack(GregTech.BIOTITE_DUST, 4),
                        new ItemStack(GregTech.METAL_MIXTURE_DUST, 3),
                        new ItemStack(GregTech.SODALITE_DUST, 2)
                ),
                432.0
        );
    }

    /* Electrolysis */
    public static final Recipe ELECTROLYZED_BIOTITE_DUST; static {
        ELECTROLYZED_BIOTITE_DUST = new Recipe(
                MachineTypes.CENTRIFUGE,
                120.0,
                List.of(
                        new ItemStack(GregTech.BIOTITE_DUST, 44),
                        new ItemStack(GregTech.EMPTY_CELL, 11)
                ),
                List.of(
                        new ItemStack(GregTech.POTASSIUM_DUST, 2),
                        new ItemStack(GregTech.MAGNESIUM_DUST, 6),
                        new ItemStack(GregTech.ALUMINA_DUST, 15),
                        new ItemStack(GregTech.RAW_SILICON_DUST, 6),
                        new ItemStack(GregTech.OXYGEN_CELL, 11),
                        new ItemStack(GregTech.FLUORINE, 4)
                ),
                14
        );
    }
    public static final Recipe ELECTROLYZED_CHARCOAL_DUST = new Recipe(MachineTypes.ELECTROLYZER, 30.0, new ItemStack(GregTech.CHARCOAL_DUST), new ItemStack(GregTech.CARBON_DUST), 0.6);
    public static final Recipe ELECTROLYZED_MAGNESIA; static {
        ELECTROLYZED_MAGNESIA = new Recipe(
                MachineTypes.ELECTROLYZER,
                30.0,
                List.of(
                        new ItemStack(GregTech.MAGNESIA_DUST,2)
                ),
                List.of(
                        new ItemStack(GregTech.MAGNESIUM_DUST),
                        new ItemStack(GregTech.OXYGEN)
                ),
                2
        );
    }
    public static final Recipe ELECTROLYZED_OBSIDIAN_DUST; static {
        ELECTROLYZED_OBSIDIAN_DUST = new Recipe(
                MachineTypes.ELECTROLYZER,
                90.0,
                List.of(
                        new ItemStack(GregTech.OBSIDIAN_DUST, 12)
                ),
                List.of(
                        new ItemStack(GregTech.MAGNESIUM_DUST, 1),
                        new ItemStack(GregTech.IRON_DUST, 1),
                        new ItemStack(GregTech.RAW_SILICON_DUST, 2),
                        new ItemStack(GregTech.OXYGEN, 8)
                ),
                12.0
        );
    }
    public static final Recipe ELECTROLYZED_PHOSPHORIC_ACID; static {
        ELECTROLYZED_PHOSPHORIC_ACID = new Recipe(
                MachineTypes.CHEMICAL_BATH,
                120.0,
                List.of(
                        new ItemStack(GregTech.PHOSPHORIC_ACID_CELL),
                        new ItemStack(GregTech.EMPTY_CELL, 6)
                ),
                List.of(
                        new ItemStack(GregTech.HYDROGEN_CELL, 3),
                        new ItemStack(GregTech.PHOSPHORUS_DUST),
                        new ItemStack(GregTech.OXYGEN_CELL, 4)
                ),
                27
        );
    }
    public static final Recipe ELECTROLYZED_ROCKSALT; static {
        ELECTROLYZED_ROCKSALT = new Recipe(
                MachineTypes.ELECTROLYZER,
                30.0,
                List.of(
                        new ItemStack(GregTech.ROCK_SALT, 2)
                ),
                List.of(
                        new ItemStack(GregTech.POTASSIUM_DUST),
                        new ItemStack(GregTech.CHLORINE)
                ),
                3.6
        );
    }
    public static final Recipe ELECTROLYZED_SALT; static {
        ELECTROLYZED_SALT = new Recipe(
                MachineTypes.ELECTROLYZER,
                30.0,
                List.of(
                        new ItemStack(GregTech.SALT, 2)
                ),
                List.of(
                        new ItemStack(GregTech.SODIUM_DUST),
                        new ItemStack(GregTech.CHLORINE)
                ),
                16
        );
    }
    public static final Recipe ELECTROLYZED_SUGAR; static {
        ELECTROLYZED_SUGAR = new Recipe(
                MachineTypes.ELECTROLYZER,
                60.0,
                List.of(
                        new ItemStack(Vanilla.SUGAR, 32),
                        new ItemStack(GregTech.EMPTY_CELL, 25)
                ),
                List.of(
                        new ItemStack(GregTech.CARBON_DUST, 2),
                        new ItemStack(GregTech.OXYGEN_CELL, 25),
                        new ItemStack(GregTech.WATER, 5)
                ),
                22.4
        );
    }
    public static final Recipe EXTRACTED_SUGAR_BEET = new Recipe(MachineTypes.EXTRACTOR, 4.0, new ItemStack(Items.SUGAR_BEET), new ItemStack(Vanilla.SUGAR, 8), 6.4);

    /* All */
    public static final Recipe ACETIC_ACID_CELL; static {
        ACETIC_ACID_CELL = new Recipe(
            MachineTypes.CHEMICAL_REACTOR,
            MachineConfiguration.ProgrammedCircuitNineteen,
            30.0,
            List.of(
                new ItemStack(GregTech.OXYGEN_CELL, 2),
                new ItemStack(GregTech.ETHYLENE, 1.0/0.144)
            ),
            List.of(
                new ItemStack(GregTech.ACETIC_ACID_CELL),
                new ItemStack(GregTech.EMPTY_CELL)
            ),
            5.0
        );
    }
    public static final Recipe AMMONIA; static {
        AMMONIA = new Recipe(
            MachineTypes.CHEMICAL_REACTOR,
            MachineConfiguration.ProgrammedCircuitOne,
            384.0,
            List.of(
                new ItemStack(GregTech.HYDROGEN_CELL, 3),
                new ItemStack(GregTech.NITROGEN_GAS, 1.0/0.144)
            ),
            List.of(
                new ItemStack(GregTech.EMPTY_CELL, 3),
                new ItemStack(GregTech.AMMONIA, 1.0/0.144)
            ),
            16.0
        );
    }
    public static final Recipe ANNEALED_COPPER_INGOT; static {
        ANNEALED_COPPER_INGOT = new Recipe(
                MachineTypes.ARC_FURNACE,
                MachineConfiguration.ProgrammedCircuitFour,
                3.0*30.0,
                List.of(
                        new ItemStack(GregTech.COPPER_INGOT),
                        new ItemStack(GregTech.OXYGEN, 63.0/1000.0)
                ),
                List.of(
                        new ItemStack(GregTech.ANNEALED_COPPER_INGOT, 1)
                ),
                3.15
        );
    }
    public static final Recipe ANNEALED_COPPER_RESISTOR; static {
        ANNEALED_COPPER_RESISTOR = new Recipe(
                MachineTypes.CIRCUIT_ASSEMBLER,
                MachineConfiguration.ProgrammedCircuitFive,
                16.0,
                List.of(
                        new ItemStack(GregTech.CHARCOAL_DUST),
                        new ItemStack(GregTech.FINE_ANNEALED_COPPER_WIRE, 4),
                        new ItemStack(GregTech.ONE_ANNEALED_COPPER_WIRE, 4),
                        new ItemStack(GregTech.REFINED_GLUE, 0.288)
                ),
                List.of(
                        new ItemStack(GregTech.RESISTOR, 8)
                ),
                16.0
        );
    }
    public static final Recipe ANY_FISH = new Recipe(MachineTypes.FISH_TRAP, MachineConfiguration.ProgrammedCircuitFourteen, 0.0,  new ItemStack(Items.ANY_FISH, 8), 5);
    public static final Recipe BATHED_SALTY_ROOT; static {
        BATHED_SALTY_ROOT = new Recipe(
                MachineTypes.CHEMICAL_BATH,
                30.0,
                List.of(
                        new ItemStack(Items.SALTY_ROOT),
                        new ItemStack(GregTech.WATER, 0.1)
                ),
                List.of(
                        new ItemStack(GregTech.SALT, 0.95),
                        new ItemStack(GregTech.ROCK_SALT, 0.8),
                        new ItemStack(GregTech.SALTPETER_DUST, 0.5)
                ),
                5
        );
    }
    public static final Recipe BIO_DIESEL_CELL; static {
        BIO_DIESEL_CELL = new Recipe(
            MachineTypes.CHEMICAL_REACTOR,
            30.0,
            List.of(
                new ItemStack(GregTech.TINY_PILE_OF_SODIUM_HYDROXIDE_DUST),
                new ItemStack(GregTech.FISH_OIL_CELL, 6),
                new ItemStack(GregTech.ETHANOL)
            ),
            List.of(
                new ItemStack(GregTech.BIO_DIESEL_CELL, 6),
                new ItemStack(GregTech.GLYCEROL, 1.0/0.144)
            ),
            30
        );
    }
    public static final Recipe CALCITE_DUST; static {
        CALCITE_DUST = new Recipe(
                MachineTypes.MIXER,
                MachineConfiguration.ProgrammedCircuitTwo,
                16.0,
                List.of(
                        new ItemStack(GregTech.CALCITE_DUST, 2),
                        new ItemStack(GregTech.CLAY_DUST, 1),
                        new ItemStack(GregTech.STONE_DUST, 1),
                        new ItemStack(GregTech.QUARTZ_SAND, 1),
                        new ItemStack(GregTech.WATER, 2)
                ),
                List.of(
                        new ItemStack(GregTech.WET_CONCRETE, 2.304)
                ),
                5.0
        );
    }
    public static final Recipe CETANE_BOOSTED_DIESEL; static {
        CETANE_BOOSTED_DIESEL = new Recipe(
            MachineTypes.MIXER,
            MachineConfiguration.ProgrammedCircuitTwo,
            480.0,
            List.of(
                new ItemStack(GregTech.BIO_DIESEL_CELL, 1),
                new ItemStack(GregTech.TETRANITROMETHANE, 0.04/0.144)
            ),
            List.of(
                new ItemStack(GregTech.EMPTY_CELL, 1),
                new ItemStack(GregTech.CETANE_BOOSTED_DIESEL, 0.9/0.144)
            ),
            1
        );
    }
    public static final Recipe CHARCOAL = new Recipe(MachineTypes.ADVANCED_COKE_OVEN, 0.0, new ItemStack(Items.ANY_WOOD), new ItemStack(Vanilla.CHARCOAL), 1.0);
    public static final Recipe CHARCOAL_DUST = new Recipe(MachineTypes.MACERATOR, 4.0, new ItemStack(Vanilla.CHARCOAL), new ItemStack(GregTech.CHARCOAL_DUST), 1.0);
    public static final Recipe CHARCOAL_WOOD_GAS; static {
        CHARCOAL_WOOD_GAS = new Recipe(
                MachineTypes.COKE_OVEN,
                MachineConfiguration.ProgrammedCircuitFive,
                64.0,
                List.of(
                        new ItemStack(Items.ANY_WOOD, 16)
                ),
                List.of(
                        new ItemStack(Vanilla.CHARCOAL, 16),
                        new ItemStack(GregTech.WOOD_GAS, 1500.0/144.0)
                ),
                32
        );
    }
    public static final Recipe CHARCOAL_WOOD_GAS_FAST; static {
        CHARCOAL_WOOD_GAS_FAST = new Recipe(
                MachineTypes.COKE_OVEN,
                MachineConfiguration.ProgrammedCircuitFive,
                64.0,
                List.of(
                        new ItemStack(Items.ANY_WOOD, 16),
                        new ItemStack(GregTech.NITROGEN_GAS)
                ),
                List.of(
                        new ItemStack(Vanilla.CHARCOAL, 16),
                        new ItemStack(GregTech.WOOD_GAS, 1500.0/144.0)
                ),
                32
        );
    }
    public static final Recipe CHISELED_SANDSTONE = new Recipe(MachineTypes.LASER_ENGRAVER, MachineConfiguration.GlassLens, 16.0, new ItemStack(Vanilla.SMOOTH_SANDSTONE), new ItemStack(Vanilla.CHISELED_SANDSTONE), 2.5);
    public static final Recipe CHISELED_STONE_BRICKS = new Recipe(MachineTypes.LASER_ENGRAVER, MachineConfiguration.GlassLens, 16.0, new ItemStack(Vanilla.STONE), new ItemStack(Vanilla.CHISELED_STONE_BRICKS), 2.5);
    public static final Recipe CLAY_DUST = new Recipe(MachineTypes.AUTO_WORKBENCH, 0.0, new ItemStack(GregTech.SMALL_PILE_OF_CLAY_DUST, 4), new ItemStack(GregTech.CLAY_DUST), 0.1); //TODO: verify
    public static final Recipe COBBLESTONE = new Recipe(MachineTypes.ROCK_BREAKER, 30.0, new ItemStack(Vanilla.COBBLESTONE), 0.8);
    public static final Recipe COMPRESSED_AIR = new Recipe(MachineTypes.COMPRESSOR, 2.0, new ItemStack(GregTech.EMPTY_CELL), new ItemStack(GregTech.COMPRESSED_AIR), 15.0);
    public static final Recipe COPPER_FOIL = new Recipe(MachineTypes.BENDING_MACHINE, MachineConfiguration.ProgrammedCircuitTen, 24.0, new ItemStack(GregTech.COPPER_INGOT), new ItemStack(GregTech.COPPER_FOIL, 4), 6.3);
    public static final Recipe COPPER_INGOT = new Recipe(MachineTypes.ELECTRIC_FURNACE, 4.0, new ItemStack(GregTech.COPPER_DUST), new ItemStack(GregTech.COPPER_INGOT, 1), 6.4);
    public static final Recipe COPPER_RESISTOR; static {
        COPPER_RESISTOR = new Recipe(
                MachineTypes.CIRCUIT_ASSEMBLER,
                MachineConfiguration.ProgrammedCircuitThree,
                16.0,
                List.of(
                        new ItemStack(GregTech.COAL_DUST),
                        new ItemStack(GregTech.FINE_COPPER_WIRE, 4),
                        new ItemStack(GregTech.ONE_COPPER_WIRE, 4),
                        new ItemStack(GregTech.REFINED_GLUE, 2*0.144)
                ),
                List.of(
                        new ItemStack(GregTech.RESISTOR, 4)
                ),
                16.0
        );
    }
    public static final Recipe CHLOROFORM_CELL; static {
        CHLOROFORM_CELL = new Recipe(
                MachineTypes.CHEMICAL_REACTOR,
                MachineConfiguration.ProgrammedCircuitThirteen,
                30.0,
                List.of(
                        new ItemStack(GregTech.METHANE_CELL),
                        new ItemStack(GregTech.CHLORINE, 6)
                ),
                List.of(
                        new ItemStack(GregTech.CHLOROFORM_CELL),
                        new ItemStack(GregTech.HYDROCHLORIC_ACID, 3)
                ),
                4
        );
    }
    public static final Recipe CIRCUIT_BOARD; static {
        CIRCUIT_BOARD = new Recipe(
                MachineTypes.ASSEMBLER,
                MachineConfiguration.ProgrammedCircuitSix,
                8.0,
                List.of(
                        new ItemStack(GregTech.WOOD_PLANK, 8),
                        new ItemStack(GregTech.COPPER_FOIL, 32),
                        new ItemStack(GregTech.REFINED_GLUE, 4)
                ),
                List.of(
                        new ItemStack(GregTech.CIRCUIT_BOARD, 8)
                ),
                80.0
        );
    }
    public static final Recipe DISTILLED_WOOD_GAS; static {
        DISTILLED_WOOD_GAS = new Recipe(
                MachineTypes.DISTILLATION_TOWER,
                MachineConfiguration.ProgrammedCircuitOne,
                256.0,
                List.of(
                        new ItemStack(GregTech.WOOD_GAS, 1000.0/144.0)
                ),
                List.of(
                        new ItemStack(GregTech.METHANE, 130.0/144.0),
                        new ItemStack(GregTech.CARBON_MONOXIDE, 240.0/144.0),
                        new ItemStack(GregTech.HYDROGEN, 120.0/144.0),
                        new ItemStack(GregTech.CARBON_DIOXIDE, 390.0/144.0),
                        new ItemStack(GregTech.ETHYLENE, 120.0/144.0)
                ),
                2.0
        );
    }
    public static final Recipe DISTILLED_WOOD_GAS_SINGLE; static {
        DISTILLED_WOOD_GAS_SINGLE = new Recipe(
                MachineTypes.DISTILLERY,
                MachineConfiguration.ProgrammedCircuitTwo,
                64.0,
                new ItemStack(GregTech.WOOD_GAS, 0.2),
                new ItemStack(GregTech.ETHYLENE, 24.0/1000.0),
                0.8
        );
    }
    public static final Recipe DRIED_DIRT = new Recipe(MachineTypes.ELECTRIC_FURNACE, 4.0, new ItemStack(DIRT), new ItemStack(items.Items.DRIED_DIRT), 6.4);
    public static final Recipe EASY_AUTOMATIC_DIODE; static {
        EASY_AUTOMATIC_DIODE = new Recipe(
                MachineTypes.ASSEMBLER,
                MachineConfiguration.ProgrammedCircuitFour,
                30.0,
                List.of(
                        new ItemStack(GregTech.ONE_ANNEALED_COPPER_WIRE, 4),
                        new ItemStack(GregTech.WAFER),
                        new ItemStack(GregTech.MOLTEN_POLYETHYLENE, 0.144)
                ),
                List.of(
                        new ItemStack(GregTech.DIODE, 4 )
                ),
                30.0
        );
    }
    public static final Recipe ETHANOL_CELL; static {
        ETHANOL_CELL = new Recipe(
            MachineTypes.CHEMICAL_REACTOR,
            MachineConfiguration.ProgrammedCircuitTwentyFour,
            480.0,
            List.of(
                new ItemStack(GregTech.ETHYLENE_CELL),
                new ItemStack(GregTech.STEAM, 2.0/0.144)
            ),
            List.of(
                new ItemStack(GregTech.ETHANOL_CELL)
            ),
            20.0
        );
    }
    public static final Recipe ETHENONE_CELL; static {
        ETHENONE_CELL = new Recipe(
            MachineTypes.CHEMICAL_REACTOR,
            MachineConfiguration.ProgrammedCircuitOne,
            120.0,
            List.of(
                new ItemStack(GregTech.ACETIC_ACID_CELL, 1),
                new ItemStack(GregTech.SULFURIC_ACID, 1.0/0.144)
            ),
            List.of(
                new ItemStack(GregTech.ETHENONE_CELL),
                new ItemStack(GregTech.DILUTED_SULFURIC_ACID, 1.0/0.144)
            ),
            8.0
        );
    }
    public static final Recipe EXTRUDED_TIN_BOLT = new Recipe(MachineTypes.EXTRUDER, MachineConfiguration.ExtrudeShapeBolt, 120.0, new ItemStack(GregTech.TIN_INGOT), new ItemStack(GregTech.TIN_BOLT, 8), 11.8);
    public static final Recipe EXTRUDED_SILVER_BOLT = new Recipe(MachineTypes.EXTRUDER, MachineConfiguration.ExtrudeShapeBolt, 120.0, new ItemStack(GregTech.SILVER_INGOT), new ItemStack(GregTech.SILVER_BOLT, 8), 10.7);
    public static final Recipe EXTRUDER_STEEL_ROD = new Recipe(MachineTypes.EXTRUDER, MachineConfiguration.ExtrudeShapeRod, 90.0, new ItemStack(GregTech.STEEL_INGOT), new ItemStack(GregTech.STEEL_ROD, 2), 5.6);
    public static final Recipe FINE_COPPER_WIRE = new Recipe(MachineTypes.WIREMILL, MachineConfiguration.ProgrammedCircuitThree, 4.0, new ItemStack(GregTech.COPPER_INGOT), new ItemStack(GregTech.FINE_COPPER_WIRE, 4), 5.0);
    public static final Recipe FISH_OIL = new Recipe(MachineTypes.FLUID_EXTRACTOR, 4.0, new ItemStack(Items.ANY_FISH), new ItemStack(GregTech.FISH_OIL, 0.050/0.144), 0.8);
    public static final Recipe FLINT = new Recipe(MachineTypes.SIFTER, 16.0, new ItemStack(Vanilla.GRAVEL), new ItemStack(Vanilla.FLINT, 1+0.9+0.8+0.6+0.33+0.25), 30.0);
    public static final Recipe FLINT_DUST = new Recipe(MachineTypes.MACERATOR, 2.0, new ItemStack(Vanilla.FLINT, 2), new ItemStack(GregTech.FLINT_DUST), 10.0);
    public static final Recipe GALLIUM_ARSENIDE_CRYSTAL; static {
        GALLIUM_ARSENIDE_CRYSTAL = new Recipe(
                MachineTypes.BLAST_FURNACE,
                120.0,
                List.of(
                        new ItemStack(GregTech.GALLIUM_DUST),
                        new ItemStack(GregTech.ARSENIC_DUST)
                ),
                List.of(
                        new ItemStack(GregTech.GALLIUM_ARSENIDE_CRYSTAL)
                ),
                60
        );
    }
    public static final Recipe GELLED_TOLUENE = new Recipe(MachineTypes.FLUID_SOLIDIFIER, MachineConfiguration.MoldBall, 16.0, new ItemStack(GregTech.TOLUENE, 100.0/144.0), new ItemStack(GregTech.GELLED_TOLUENE), 5.0);
    public static final Recipe GLOWSTONE_DUST = new Recipe(MachineTypes.EXTRACTOR, 2.0, new ItemStack(Items.GLOW_FLOWER, 2), new ItemStack(Vanilla.GLOWSTONE_DUST), 15.0);
    public static final Recipe GLASS_DUST; static {
        GLASS_DUST = new Recipe(
                MachineTypes.MIXER,
                MachineConfiguration.ProgrammedCircuitFour,
                8.0,
                List.of(
                        new ItemStack(GregTech.FLINT_DUST),
                        new ItemStack(GregTech.QUARTZ_SAND, 16)
                ),
                List.of(
                        new ItemStack(GregTech.GLASS_DUST, 16)
                ),
                40.0
        );
    }
    public static final Recipe GLASS_TUBE = new Recipe(MachineTypes.ALLOY_SMELTER, MachineConfiguration.MoldBall, 16.0, new ItemStack(GregTech.GLASS_DUST), new ItemStack(GregTech.GLASS_TUBE), 6.0);
    public static final Recipe GOLD_FOIL = new Recipe(MachineTypes.BENDING_MACHINE, MachineConfiguration.ProgrammedCircuitTen, 24, new ItemStack(Vanilla.GOLD_INGOT), new ItemStack(GregTech.GOLD_FOIL, 4), 19.6);
    public static final Recipe HYDROCHLORIC_ACID; static {
        HYDROCHLORIC_ACID = new Recipe(
            MachineTypes.CHEMICAL_REACTOR,
            MachineConfiguration.ProgrammedCircuitOne,
            8,
            List.of(
                new ItemStack(GregTech.HYDROGEN_CELL),
                new ItemStack(GregTech.CHLORINE)
            ),
            List.of(
                new ItemStack(GregTech.EMPTY_CELL),
                new ItemStack(GregTech.HYDROCHLORIC_ACID)
            ),
            3
        );
    }
    public static final Recipe HYDROFLUORIC_ACID; static {
        HYDROFLUORIC_ACID = new Recipe(
                MachineTypes.CHEMICAL_REACTOR,
                MachineConfiguration.ProgrammedCircuitOne,
                1.0,
                List.of(
                        new ItemStack(GregTech.HYDROGEN_CELL),
                        new ItemStack(GregTech.FLUORINE)
                ),
                List.of(
                        new ItemStack(GregTech.EMPTY_CELL),
                        new ItemStack(GregTech.HYDROFLUORIC_ACID)
                ),
                3
        );
    }
    public static final Recipe GRAVEL = new Recipe(MachineTypes.FORGE_HAMMER, 16.0, new ItemStack(Vanilla.COBBLESTONE), new ItemStack(Vanilla.GRAVEL), 0.5);
    public static final Recipe GOOD_CIRCUIT_BOARD; static {
        GOOD_CIRCUIT_BOARD = new Recipe(
            MachineTypes.CHEMICAL_REACTOR,
            30,
            List.of(
                new ItemStack(GregTech.PHENOLIC_CIRCUIT_BOARD),
                new ItemStack(GregTech.GOLD_FOIL, 4),
                new ItemStack(GregTech.IRON_III_CHLORIDE, 0.1)
            ),
            List.of(
                new ItemStack(GregTech.GOOD_CIRCUIT_BOARD)
            ),
            30
        );
    }
    public static final Recipe GOOD_INTEGRATED_CIRCUIT_MV; static {
        GOOD_INTEGRATED_CIRCUIT_MV = new Recipe(
            MachineTypes.CIRCUIT_ASSEMBLER,
            24,
            List.of(
                new ItemStack(GregTech.GOOD_CIRCUIT_BOARD),
                new ItemStack(GregTech.INTEGRATED_LOGIC_CIRCUIT_LV, 2),
                new ItemStack(GregTech.RESISTOR, 4),
                new ItemStack(GregTech.DIODE, 4),
                new ItemStack(GregTech.FINE_GOLD_WIRE, 4),
                new ItemStack(GregTech.SILVER_BOLT, 4),
                new ItemStack(GregTech.MOLTEN_LEAD, 2)
            ),
            List.of(
                new ItemStack(GregTech.GOOD_INTEGRATED_CIRCUIT_MV)
            ),
            10
        );
    }
    public static final Recipe IRON_III_CHLORIDE; static {
        IRON_III_CHLORIDE = new Recipe(
            MachineTypes.CHEMICAL_REACTOR,
            MachineConfiguration.ProgrammedCircuitOne,
            30,
            List.of(
                new ItemStack(GregTech.IRON_DUST),
                new ItemStack(GregTech.EMPTY_CELL, 3),
                new ItemStack(GregTech.HYDROCHLORIC_ACID, 3)
            ),
            List.of(
                new ItemStack(GregTech.HYDROGEN_CELL, 3),
                new ItemStack(GregTech.IRON_III_CHLORIDE)
            ),
            20
        );
    }
    public static final Recipe OREBERRY_IRON_DUST = new Recipe(MachineTypes.MACERATOR, 18, new ItemStack(ThinkersConstruct.IRON_OREBERRY, 9), new ItemStack(GregTech.IRON_DUST), 15);
    public static final Recipe IRON_INGOT = new Recipe(MachineTypes.ELECTRIC_FURNACE, 4, new ItemStack(GregTech.IRON_DUST), new ItemStack(Vanilla.IRON_INGOT), 6.4);
    public static final Recipe INTEGRATED_ELECTRONIC_CIRCUIT_LV; static {
        INTEGRATED_ELECTRONIC_CIRCUIT_LV = new Recipe(
                MachineTypes.CIRCUIT_ASSEMBLER,
                16.0,
                List.of(
                        new ItemStack(GregTech.CIRCUIT_BOARD),
                        new ItemStack(GregTech.INTEGRATED_LOGIC_CIRCUIT),
                        new ItemStack(GregTech.RESISTOR, 2),
                        new ItemStack(GregTech.DIODE, 2),
                        new ItemStack(GregTech.FINE_COPPER_WIRE, 2),
                        new ItemStack(GregTech.TIN_BOLT, 2),
                        new ItemStack(GregTech.MOLTEN_LEAD, 2*0.144)
                ),
                List.of(
                        new ItemStack(GregTech.INTEGRATED_LOGIC_CIRCUIT_LV)
                ),
                10.0
        );
    }
    public static final Recipe INTEGRATED_LOGIC_CIRCUIT; static {
        INTEGRATED_LOGIC_CIRCUIT = new Recipe(
                MachineTypes.CUTTING_MACHINE,
                64.0,
                List.of(
                        new ItemStack(GregTech.INTEGRATED_LOGIC_CIRCUIT_WAFER),
                        new ItemStack(GregTech.WATER, 0.180)
                ),
                List.of(
                        new ItemStack(GregTech.INTEGRATED_LOGIC_CIRCUIT, 8)
                ),
                45
        );
    }
    public static final Recipe INTEGRATED_LOGIC_CIRCUIT_WAFER = new Recipe(MachineTypes.LASER_ENGRAVER, MachineConfiguration.RubyLens, 120.0, new ItemStack(GregTech.WAFER), new ItemStack(GregTech.INTEGRATED_LOGIC_CIRCUIT_WAFER), 60.0);
    public static final Recipe IRON_STEEL_INGOT; static {
        IRON_STEEL_INGOT = new Recipe(
                MachineTypes.ELECTRIC_BLAST_FURNACE,
                MachineConfiguration.ProgrammedCircuitEleven,
                120.0,
                List.of(
                        new ItemStack(GregTech.IRON_DUST),
                        new ItemStack(GregTech.OXYGEN)
                ),
                List.of(
                        new ItemStack(GregTech.STEEL_INGOT, 1),
                        new ItemStack(GregTech.ASHES, 1.0/9.0)
                ),
                25.0
        );
    }
    public static final Recipe LATHE_STEEL_ROD; static {
        LATHE_STEEL_ROD = new Recipe(
                MachineTypes.LATHE,
                16.0,
                List.of(
                        new ItemStack(GregTech.STEEL_INGOT)
                ),
                List.of(
                        new ItemStack(GregTech.STEEL_ROD, 1),
                        new ItemStack(GregTech.SMALL_PILE_OF_STEEL_DUST, 2)
                ),
                14.0
        );
    }
    public static final Recipe LEAD_INGOT = new Recipe(MachineTypes.ELECTRIC_FURNACE, 4.0, new ItemStack(GregTech.LEAD_DUST), new ItemStack(GregTech.LEAD_INGOT), 6.4);
    public static final Recipe LIGHT_CONCRETE = new Recipe(MachineTypes.FLUID_SOLIDIFIER, MachineConfiguration.MoldBlock, 4.0, new ItemStack(GregTech.WET_CONCRETE, 144.0/1000.0), new ItemStack(GregTech.LIGHT_CONCRETE), 0.6  );
    public static final Recipe MOLTEN_LEAD = new Recipe(MachineTypes.FLUID_EXTRACTOR, 34.0, new ItemStack(GregTech.LEAD_INGOT), new ItemStack(GregTech.MOLTEN_LEAD, 144.0/1000.0), 1.2);
    public static final Recipe MOLTEN_POLYETHYLENE; static {
        MOLTEN_POLYETHYLENE = new Recipe(
                MachineTypes.CHEMICAL_REACTOR,
                MachineConfiguration.ProgrammedCircuitOne,
                30.0,
                List.of(
                        new ItemStack(GregTech.OXYGEN_CELL),
                        new ItemStack(GregTech.ETHYLENE, 144.0/1000.0)
                ),
                List.of(
                        new ItemStack(GregTech.EMPTY_CELL),
                        new ItemStack(GregTech.MOLTEN_POLYETHYLENE, 216.0/144.0)
                ),
                8
        );
    }
    public static final Recipe MOLTEN_POLYTETRAFLUOROETHYLENE; static {
        MOLTEN_POLYTETRAFLUOROETHYLENE = new Recipe(
                MachineTypes.CHEMICAL_REACTOR,
                MachineConfiguration.ProgrammedCircuitNine,
                30.0,
                List.of(
                        new ItemStack(GregTech.TETRAFLUOROETHYLENE_CELL),
                        new ItemStack(GregTech.OXYGEN, 7)
                ),
                List.of(
                        new ItemStack(GregTech.EMPTY_CELL),
                        new ItemStack(GregTech.MOLTEN_POLYTETRAFLUOROETHYLENE, 1.5)
                ),
                56.0
        );
    }
    public static final Recipe MOLTEN_RED_ALLOY = new Recipe(MachineTypes.FLUID_EXTRACTOR, 30.0, new ItemStack(GregTech.RED_ALLOY_INGOT), new ItemStack(GregTech.MOLTEN_RED_ALLOY), 1.2);
    public static final Recipe MOLTEN_RED_ALLOY_VACUUM_TUBE; static {
        MOLTEN_RED_ALLOY_VACUUM_TUBE = new Recipe(
                MachineTypes.ASSEMBLER,
                MachineConfiguration.ProgrammedCircuitFive,
                8.0,
                List.of(
                        new ItemStack(GregTech.GLASS_TUBE, 4),
                        new ItemStack(GregTech.ONE_COPPER_WIRE, 4),
                        new ItemStack(GregTech.STEEL_ROD, 4),
                        new ItemStack(GregTech.MOLTEN_RED_ALLOY, 0.5)
                ),
                List.of(
                        new ItemStack(GregTech.VACUUM_TUBE, 4)
                ),
                8.0
        );
    }
    public static final Recipe MOLTEN_REDSTONE = new Recipe(MachineTypes.FLUID_EXTRACTOR, 30.0, new ItemStack(Vanilla.REDSTONE_DUST), new ItemStack(GregTech.MOLTEN_REDSTONE, 144.0/1000.0), 1.2);
    public static final Recipe MOLTEN_REDSTONE_ALLOY = new Recipe(MachineTypes.FLUID_EXTRACTOR, 36.0, new ItemStack(GregTech.REDSTONE_ALLOY_INGOT), new ItemStack(GregTech.MOLTEN_REDSTONE_ALLOY), 1.2);
    public static final Recipe MOLTEN_REDSTONE_ALLOY_VACUUM_TUBE; static {
        MOLTEN_REDSTONE_ALLOY_VACUUM_TUBE = new Recipe(
            MachineTypes.ASSEMBLER,
            MachineConfiguration.ProgrammedCircuitFive,
            8.0,
            List.of(
                new ItemStack(GregTech.GLASS_TUBE, 4),
                new ItemStack(GregTech.ONE_ANNEALED_COPPER_WIRE, 4),
                new ItemStack(GregTech.STEEL_ROD, 4),
                new ItemStack(GregTech.MOLTEN_REDSTONE_ALLOY, 0.072/0.144)
            ),
            List.of(
                new ItemStack(GregTech.VACUUM_TUBE, 16)
            ),
            8.0
        );
    }
    public static final Recipe MOLTEN_REDSTONE_VACUUM_TUBE; static {
        MOLTEN_REDSTONE_VACUUM_TUBE = new Recipe(
                MachineTypes.ASSEMBLER,
                MachineConfiguration.ProgrammedCircuitFive,
                8.0,
                List.of(
                        new ItemStack(GregTech.GLASS_TUBE, 2),
                        new ItemStack(GregTech.ONE_COPPER_WIRE, 4),
                        new ItemStack(GregTech.STEEL_ROD, 4),
                        new ItemStack(GregTech.MOLTEN_REDSTONE, 1)
                ),
                List.of(
                        new ItemStack(GregTech.VACUUM_TUBE, 2)
                ),
                8.0
        );
    }
    public static final Recipe MOLTEN_SILVER = new Recipe(MachineTypes.FLUID_EXTRACTOR, 49.0, new ItemStack(GregTech.SILVER_DUST), new ItemStack(GregTech.MOLTEN_SILVER, 0.144), 1.2);
    public static final Recipe MOLTEN_TIN = new Recipe(MachineTypes.FLUID_EXTRACTOR, 30.0, new ItemStack(GregTech.TIN_DUST), new ItemStack(GregTech.MOLTEN_TIN, 0.144), 1.2);
    public static final Recipe MONOCRYSTALLINE_SILICON_BOULE; static {
        MONOCRYSTALLINE_SILICON_BOULE = new Recipe(
                MachineTypes.CHEMICAL_REACTOR,
                MachineConfiguration.ProgrammedCircuitOne,
                120.0,
                List.of(
                        new ItemStack(GregTech.SILICON_SOLAR_GRADE_DUST, 32),
                        new ItemStack(GregTech.SMALL_GALLIUM_ARSENIDE_CRYSTAL)
                ),
                List.of(
                        new ItemStack(GregTech.MONOCRYSTALLINE_SILICON_BOULE)
                ),
                450
        );
    }
    public static final Recipe NETHER_COBBLESTONE; static {
        NETHER_COBBLESTONE = new Recipe(
                MachineTypes.AUTO_WORKBENCH,
                16.0,
                List.of(
                        new ItemStack(Vanilla.NETHERRACK, 2),
                        new ItemStack(Vanilla.COBBLESTONE, 2)
                ),
                List.of( new ItemStack(items.Items.NETHER_COBBLESTONE, 4) ),
                3.2
        );
    }
    public static final Recipe NETHER_COBBLESTONE_SLAB; static {
        NETHER_COBBLESTONE_SLAB = new Recipe(
                MachineTypes.AUTO_WORKBENCH,
                16.0,
                new ItemStack(Items.NETHER_COBBLESTONE, 3),
                new ItemStack(Items.NETHER_COBBLESTONE_SLAB, 6),
                3.2
        );
    }
    public static final Recipe NITROGEN_DIOXIDE_CELL; static {
        NITROGEN_DIOXIDE_CELL = new Recipe(
                MachineTypes.CHEMICAL_REACTOR,
                MachineConfiguration.ProgrammedCircuitEleven,
                30.0,
                List.of(
                        new ItemStack(GregTech.OXYGEN_CELL),
                        new ItemStack(GregTech.NITRIC_OXIDE, 1.0/0.144)
                ),
                List.of(
                        new ItemStack(GregTech.NITROGEN_DIOXIDE_CELL)
                ),
                8.0
        );
    }
    public static final Recipe NITRIC_ACID; static {
        NITRIC_ACID = new Recipe(
            MachineTypes.CHEMICAL_REACTOR,
            30.0,
            List.of(
                new ItemStack(GregTech.NITROGEN_DIOXIDE_CELL, 2),
                new ItemStack(GregTech.OXYGEN_CELL),
                new ItemStack(GregTech.WATER, 1.0/0.144)
            ),
            List.of(
                new ItemStack(GregTech.EMPTY_CELL, 3),
                new ItemStack(GregTech.NITRIC_ACID, 2.0/0.144)
            ),
            12.0
        );
    }
    public static final Recipe NITRIC_OXIDE; static {
        NITRIC_OXIDE = new Recipe(
            MachineTypes.CHEMICAL_REACTOR,
            MachineConfiguration.ProgrammedCircuitTwelve,
            30.0,
            List.of(
                new ItemStack(GregTech.OXYGEN_CELL, 10),
                new ItemStack(GregTech.AMMONIA, 4.0/0.144)
            ),
            List.of(
                new ItemStack(GregTech.EMPTY_CELL, 10),
                new ItemStack(GregTech.NITRIC_OXIDE, 4.0/0.144)
            ),
            16.0
        );
    }
    public static final Recipe OBSIDIAN_DUST = new Recipe(MachineTypes.MACERATOR, 4.0, new ItemStack(Vanilla.OBSIDIAN), new ItemStack(GregTech.OBSIDIAN_DUST, 12), 9.0*1.1);
    public static final Recipe PERFECT_RAW_SILICON_DUST; static {
        PERFECT_RAW_SILICON_DUST = new Recipe(
                MachineTypes.FLUID_SOLIDIFIER,
                MachineConfiguration.MoldIngot,
                8,
                List.of(
                        new ItemStack(GregTech.SILICON_DIOXIDE, 3),
                        new ItemStack(GregTech.MAGNESIUM_DUST, 2)
                ),
                List.of(
                        new ItemStack(GregTech.RAW_SILICON_DUST),
                        new ItemStack(GregTech.MAGNESIA_DUST, 4)
                ),
                5
        );
    }
    public static final Recipe POLYETHYLENE_BAR = new Recipe(MachineTypes.FLUID_SOLIDIFIER, MachineConfiguration.MoldIngot, 8, new ItemStack(GregTech.MOLTEN_POLYETHYLENE), new ItemStack(GregTech.POLYETHYLENE_BAR), 1.6);
    public static final Recipe POLYTETRAFLUOROETHYLENE_BAR = new Recipe(MachineTypes.FLUID_SOLIDIFIER, MachineConfiguration.MoldIngot, 8.0, new ItemStack(GregTech.MOLTEN_POLYTETRAFLUOROETHYLENE, 0.144), new ItemStack(GregTech.POLYTETRAFLUOROETHYLENE_BAR), 1.6);
    public static final Recipe PHENOLIC_CIRCUIT_BOARD; static {
        PHENOLIC_CIRCUIT_BOARD = new Recipe(
            MachineTypes.ASSEMBLER,
            16.0,
            List.of(
                new ItemStack(GregTech.WOOD_PULP, 8),
                new ItemStack(GregTech.REFINED_GLUE, 2*0.144)
            ),
            List.of(
                new ItemStack(GregTech.PHENOLIC_CIRCUIT_BOARD, 8)
            ),
            120
        );
    }
    public static final Recipe PHOSPHORIC_ACID_CELL; static {
        PHOSPHORIC_ACID_CELL = new Recipe(
                MachineTypes.CHEMICAL_REACTOR,
                30.0,
                List.of(
                        new ItemStack(GregTech.PHOSPHOROUS_PENTOXIDE_DUST, 14),
                        new ItemStack(GregTech.WATER, 6)
                ),
                List.of(
                        new ItemStack(GregTech.PHOSPHORIC_ACID, 4)
                ),
                2
        );
    }
    public static final Recipe PHOSPHOROUS_PENTOXIDE_DUST; static {
        PHOSPHOROUS_PENTOXIDE_DUST = new Recipe(
                MachineTypes.CHEMICAL_REACTOR,
                30.0,
                List.of(
                        new ItemStack(GregTech.PHOSPHORUS_DUST, 4),
                        new ItemStack(GregTech.OXYGEN, 10)
                ),
                List.of(
                        new ItemStack(GregTech.PHOSPHOROUS_PENTOXIDE_DUST, 14)
                ),
                2
        );
    }
    public static final Recipe REDSTONE_ALLOY_DUST; static {
        REDSTONE_ALLOY_DUST = new Recipe(
                MachineTypes.MIXER,
                MachineConfiguration.ProgrammedCircuitTwentyTwo,
                8.0,
                List.of(
                        new ItemStack(Vanilla.REDSTONE_DUST),
                        new ItemStack(GregTech.RAW_SILICON_DUST),
                        new ItemStack(GregTech.COAL_DUST)
                ),
                List.of(
                        new ItemStack(GregTech.REDSTONE_ALLOY_DUST, 3)
                ),
                5
        );
    }
    public static final Recipe REDSTONE_ALLOY_INGOT; static {
        REDSTONE_ALLOY_INGOT = new Recipe(
                MachineTypes.ELECTRIC_BLAST_FURNACE,
                MachineConfiguration.ProgrammedCircuitEleven,
                120.0,
                List.of(
                        new ItemStack(GregTech.REDSTONE_ALLOY_DUST),
                        new ItemStack(GregTech.OXYGEN)
                ),
                List.of(
                        new ItemStack(GregTech.REDSTONE_ALLOY_INGOT)
                ),
                40.0
        );
    }
    public static final Recipe REFINED_GLUE; static {
        REFINED_GLUE = new Recipe(
                MachineTypes.CENTRIFUGE,
                5.0,
                List.of(
                        new ItemStack(IndustrialCraft.STICKY_RESIN)
                ),
                List.of(
                        new ItemStack(GregTech.RAW_RUBBER_DUST, 3),
                        new ItemStack(IndustrialCraft.PLANT_BALL, 0.1),
                        new ItemStack(GregTech.REFINED_GLUE, 100.0/144.0)
                ),
                0.05
        );
    }
    public static final Recipe SAND = new Recipe(MachineTypes.FORGE_HAMMER, 16.0, new ItemStack(Vanilla.GRAVEL), new ItemStack(Vanilla.SAND), 0.5);
    public static final Recipe SANDSTONE = new Recipe(MachineTypes.COMPRESSOR, 2.0, new ItemStack(Vanilla.SAND, 4), new ItemStack(Vanilla.SANDSTONE), 15);
    public static final Recipe SILICON_DIOXIDE = new Recipe(MachineTypes.ELECTROLYZER, 25.0, new ItemStack(Vanilla.SAND, 8), new ItemStack(GregTech.SILICON_DIOXIDE), 25);
    public static final Recipe SILICON_SOLAR_GRADE_DUST; static {
        SILICON_SOLAR_GRADE_DUST = new Recipe(
                MachineTypes.CHEMICAL_REACTOR,
                MachineConfiguration.ProgrammedCircuitOne,
                30.0,
                List.of(
                        new ItemStack(GregTech.SODIUM_DUST,4),
                        new ItemStack(GregTech.SILICON_TETRACHLORIDE)
                ),
                List.of(
                        new ItemStack(GregTech.SILICON_SOLAR_GRADE_DUST),
                        new ItemStack(GregTech.SALT, 8)
                ),
                5
        );
    }
    public static final Recipe SILICON_TETRACHLORIDE; static {
        SILICON_TETRACHLORIDE = new Recipe(
                MachineTypes.CHEMICAL_REACTOR,
                MachineConfiguration.ProgrammedCircuitTwo,
                30.0,
                List.of(
                        new ItemStack(GregTech.RAW_SILICON_DUST),
                        new ItemStack(GregTech.CHLORINE, 4)
                ),
                List.of(
                        new ItemStack(GregTech.SILICON_TETRACHLORIDE)
                ),
                20.0
        );
    }
    public static final Recipe SILVER_INGOT = new Recipe(MachineTypes.ELECTRIC_FURNACE, 4.0, new ItemStack(GregTech.SILVER_DUST), new ItemStack(GregTech.SILVER_INGOT), 6.4);
    public static final Recipe SODIUM_HYDROXIDE_DUST; static {
        SODIUM_HYDROXIDE_DUST = new Recipe(
                MachineTypes.CHEMICAL_REACTOR,
                MachineConfiguration.ProgrammedCircuitOne,
                30.0,
                List.of(
                    new ItemStack(GregTech.SODIUM_DUST),
                    new ItemStack(GregTech.WATER,1.0/0.144)
                ),
                List.of(
                    new ItemStack(GregTech.SODIUM_HYDROXIDE_DUST, 3),
                    new ItemStack(GregTech.HYDROGEN, 1.0/0.144)
                ),
                10
        );
    }
    public static final Recipe STONE_DUST = new Recipe(MachineTypes.MACERATOR, 4.0, new ItemStack(Vanilla.COBBLESTONE), new ItemStack(GregTech.STONE_DUST, 1), 4.9);
    public static final Recipe TETRAFLUOROETHYLENE_CELL; static {
        TETRAFLUOROETHYLENE_CELL = new Recipe(
                MachineTypes.CHEMICAL_REACTOR,
                MachineConfiguration.ProgrammedCircuitEleven,
                120.0,
                List.of(
                        new ItemStack(GregTech.CHLOROFORM_CELL,2 ),
                        new ItemStack(GregTech.HYDROFLUORIC_ACID,4)
                ),
                List.of(
                        new ItemStack(GregTech.TETRAFLUOROETHYLENE_CELL),
                        new ItemStack(GregTech.EMPTY_CELL),
                        new ItemStack(GregTech.HYDROCHLORIC_ACID, 6)
                ),
                24
        );
    }
    public static final Recipe TETRANITROMETHANE; static {
        TETRANITROMETHANE = new Recipe(
            MachineTypes.CHEMICAL_REACTOR,
            MachineConfiguration.ProgrammedCircuitTwelve,
            120.0,
            List.of(
                new ItemStack(GregTech.ETHENONE_CELL),
                new ItemStack(GregTech.NITRIC_ACID, 8.0/0.144)
            ),
            List.of(
                new ItemStack(GregTech.EMPTY_CELL),
                new ItemStack(GregTech.TETRANITROMETHANE, 2.0/0.144)
            ),
            24.0
        );
    }
    public static final Recipe QUARTZ_SAND = new Recipe(MachineTypes.MACERATOR, 8.0, new ItemStack(Vanilla.SAND), new ItemStack(GregTech.QUARTZ_SAND), 10.0);
    public static final Recipe RED_ALLOY_INGOT; static {
        RED_ALLOY_INGOT = new Recipe(
                MachineTypes.ALLOY_SMELTER,
                16.0,
                List.of(
                        new ItemStack(Vanilla.REDSTONE_DUST, 4),
                        new ItemStack(GregTech.COPPER_INGOT)
                ),
                List.of(
                        new ItemStack(GregTech.RED_ALLOY_INGOT, 1)
                ),
                2.5
        );
    }
    public static final Recipe REDSTONE_DUST_OBSIDIAN = new Recipe(MachineTypes.ROCK_BREAKER, MachineConfiguration.ProgrammedCircuitOne, 30.0, new ItemStack(Vanilla.REDSTONE_DUST), new ItemStack(Vanilla.OBSIDIAN), 6.4);
    public static final Recipe SIMPLE_METHANE; static {
        SIMPLE_METHANE = new Recipe(
                MachineTypes.CHEMICAL_REACTOR,
                30.0,
                List.of(
                        new ItemStack(GregTech.CARBON_DUST),
                        new ItemStack(GregTech.EMPTY_CELL),
                        new ItemStack(GregTech.HYDROGEN, 4)
                ),
                List.of(
                        new ItemStack(GregTech.METHANE_CELL)
                ),
                10
        );
    }
    public static final Recipe SMALL_GALLIUM_ARSENIDE_CRYSTAL = new Recipe(MachineTypes.FORGE_HAMMER, 4, new ItemStack(GregTech.GALLIUM_ARSENIDE_CRYSTAL), new ItemStack(GregTech.SMALL_GALLIUM_ARSENIDE_CRYSTAL), 2.5);
    public static final Recipe SMALL_PILED_STEEL_DUST = new Recipe(MachineTypes.AUTO_WORKBENCH, 16.0, new ItemStack(GregTech.SMALL_PILE_OF_STEEL_DUST, 4), new ItemStack(GregTech.STEEL_DUST, 1), 3.2);
    public static final Recipe SMOOTH_SANDSTONE = new Recipe(MachineTypes.ASSEMBLER, MachineConfiguration.ProgrammedCircuitTwentyThree, 16.0, new ItemStack(Vanilla.SANDSTONE), new ItemStack(Vanilla.SMOOTH_SANDSTONE), 2.5);
    public static final Recipe STEAM = new Recipe(MachineTypes.FLUID_HEATER, MachineConfiguration.ProgrammedCircuitOne, 30.0, new ItemStack(GregTech.WATER, 0.006/0.144), new ItemStack(GregTech.STEAM, 0.960/0.144), 1.5);
    public static final Recipe STEEL_STEEL_INGOT; static {
        STEEL_STEEL_INGOT = new Recipe(
                MachineTypes.ELECTRIC_BLAST_FURNACE,
                MachineConfiguration.ProgrammedCircuitOne,
                120.0,
                List.of(
                        new ItemStack(GregTech.STEEL_DUST),
                        new ItemStack(GregTech.OXYGEN)
                ),
                List.of(
                        new ItemStack(GregTech.STEEL_INGOT)
                ),
                50.0
        );
    }
    public static final Recipe STONE = new Recipe(MachineTypes.ROCK_BREAKER, 30.0, new ItemStack(Vanilla.STONE), 0.8);
    public static final Recipe SULFUR_DIOXIDE; static {
        SULFUR_DIOXIDE = new Recipe(
            MachineTypes.CHEMICAL_REACTOR,
            MachineConfiguration.ProgrammedCircuitThree,
            8.0,
            List.of(
                new ItemStack(GregTech.OXYGEN, 2),
                new ItemStack(GregTech.SULFUR_DUST)
            ),
            List.of(
                new ItemStack(GregTech.SULFUR_DIOXIDE, 1000.0/144.0)
            ),
            3
        );
    }
    public static final Recipe SULFUR_TRIOXIDE; static {
        SULFUR_TRIOXIDE = new Recipe(
            MachineTypes.CHEMICAL_REACTOR,
            MachineConfiguration.ProgrammedCircuitOne,
            8.0,
            List.of(
                    new ItemStack(GregTech.OXYGEN_CELL),
                    new ItemStack(GregTech.SULFUR_DIOXIDE, 1000.0/144.0)
            ),
            List.of(
                    new ItemStack(GregTech.EMPTY_CELL),
                    new ItemStack(GregTech.SULFUR_TRIOXIDE, 1000.0/144.0)
            ),
            10
        );
    }
    public static final Recipe SULFURIC_ACID; static {
        SULFURIC_ACID = new Recipe(
                MachineTypes.CHEMICAL_REACTOR,
                MachineConfiguration.ProgrammedCircuitOne,
                8.0,
                List.of(
                    new ItemStack(IndustrialCraft.WATER_CELL),
                    new ItemStack(GregTech.SULFUR_TRIOXIDE, 1000.0/144.0)
                ),
                List.of(
                    new ItemStack(GregTech.EMPTY_CELL),
                    new ItemStack(GregTech.SULFURIC_ACID, 1000.0/144.0)
                ),
                16
        );
    }
    public static final Recipe TIN_INGOT = new Recipe(MachineTypes.ELECTRIC_FURNACE, 4.0, new ItemStack(GregTech.TIN_DUST), new ItemStack(GregTech.TIN_INGOT), 6.5);
    public static final Recipe TINY_PILE_OF_SODIUM_HYDROXIDE_DUST = new Recipe(MachineTypes.AUTO_WORKBENCH, 16.0, new ItemStack(GregTech.SODIUM_HYDROXIDE_DUST), new ItemStack(GregTech.TINY_PILE_OF_SODIUM_HYDROXIDE_DUST, 9), 3.2);
    public static final Recipe TNT; static {
        TNT = new Recipe(
            MachineTypes.CHEMICAL_REACTOR,
            MachineConfiguration.ProgrammedCircuitOne,
            24.0,
            List.of(
                new ItemStack(GregTech.GELLED_TOLUENE, 4),
                new ItemStack(GregTech.SULFURIC_ACID, 250.0/144.0)
            ),
            List.of(
                new ItemStack(Vanilla.TNT)
            ),
            10
        );
    }
    public static final Recipe WAFER; static {
        WAFER = new Recipe(
                MachineTypes.CHEMICAL_REACTOR,
                30.0,
                List.of(
                        new ItemStack(GregTech.MONOCRYSTALLINE_SILICON_BOULE),
                        new ItemStack(GregTech.WATER, 0.037)
                ),
                List.of(
                        new ItemStack(GregTech.WAFER, 16),
                        new ItemStack(GregTech.SILICON_SOLAR_GRADE_DUST, 4)
                ),
                10
        );
    }
    public static final Recipe WATER_CELL = new Recipe(MachineTypes.WATERTANK, 0.0, new ItemStack(GregTech.WATER, 1000.0/144.0), new ItemStack(IndustrialCraft.WATER_CELL), 0.1);
    public static final Recipe WOOD_PLANK; static {
        WOOD_PLANK = new Recipe(
                MachineTypes.ASSEMBLER,
                MachineConfiguration.ProgrammedCircuitTwo,
                30.0,
                List.of(
                        new ItemStack(GregTech.WOOD_PULP, 64),
                        new ItemStack(GregTech.REFINED_GLUE, 1)
                ),
                List.of(
                        new ItemStack(GregTech.WOOD_PLANK, 64)
                ),
                120.0
        );
    }
    public static final Recipe WOOD_PULP = new Recipe(MachineTypes.MACERATOR, 2.0, new ItemStack(Items.ANY_WOOD), new ItemStack(GregTech.WOOD_PULP, 6.8), 20.0);
    public static final Recipe WOOD_GAS_ETHYLENE = new Recipe(MachineTypes.DISTILLERY, MachineConfiguration.ProgrammedCircuitTwo, 64.0, new ItemStack(GregTech.WOOD_GAS, 0.200/0.144), new ItemStack(GregTech.ETHYLENE, 0.024/0.144), 0.8);
    public static final Recipe WOOD_TAR; static {
        WOOD_TAR = new Recipe(
            MachineTypes.FLUID_EXTRACTOR,
            MachineConfiguration.ProgrammedCircuitTwo,
            16.0,
            List.of(
                new ItemStack(Vanilla.CHARCOAL)
            ),
            List.of(
                new ItemStack(GregTech.ASHES),
                new ItemStack(GregTech.WOOD_TAR, 0.1)
            ),
            1.5
        );
    }
    public static final Recipe WOOD_TAR_TOLUENE = new Recipe(MachineTypes.DISTILLERY, MachineConfiguration.ProgrammedCircuitFour, 64.0, new ItemStack(GregTech.WOOD_TAR, 200.0/144.0), new ItemStack(GregTech.TOLUENE, 20.0/144.0), 0.8);
    public static final Recipe WROUGHT_IRON_DUST = new Recipe(MachineTypes.MACERATOR, MachineConfiguration.ProgrammedCircuitEleven, 4.0, new ItemStack(GregTech.WROUGHT_IRON_INGOT), new ItemStack(GregTech.WROUGHT_IRON_DUST), 2.8);
    public static final Recipe WROUGHT_IRON_INGOT; static {
        WROUGHT_IRON_INGOT = new Recipe(
                MachineTypes.ARC_FURNACE,
                90.0,
                List.of(
                        new ItemStack(GregTech.IRON_DUST),
                        new ItemStack(GregTech.OXYGEN, 56.0/1000.0)
                ),
                List.of(
                        new ItemStack(GregTech.WROUGHT_IRON_INGOT, 1)
                ),
                2.8
        );
    }
    public static final Recipe WROUGHT_STEEL_INGOT; static {
        WROUGHT_STEEL_INGOT = new Recipe(
                MachineTypes.ELECTRIC_BLAST_FURNACE,
                MachineConfiguration.ProgrammedCircuitEleven,
                120.0,
                List.of(
                        new ItemStack(GregTech.WROUGHT_IRON_DUST),
                        new ItemStack(GregTech.OXYGEN)
                ),
                List.of(
                        new ItemStack(GregTech.STEEL_INGOT, 1),
                        new ItemStack(GregTech.ASHES, 1.0/9.0)
                ),
                5.0
        );
    }
}
