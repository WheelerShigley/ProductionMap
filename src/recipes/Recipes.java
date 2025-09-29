package recipes;

import items.ItemStack;
import items.Items;
import machines.MachineConfiguration;
import machines.MachineTypes;
import register.Registered;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Recipes extends Registered<Recipe> {
    private static Recipes instance;
    public static final List<Recipe> registry = new ArrayList<Recipe>();
    private static final Logger LOGGER = Logger.getLogger("Recipes");
    public Recipes() {
        super("Recipes", registry);
        instance = this;
    }

    public static boolean register(Recipe recipe) {
        if(instance == null) {
            instance = new Recipes();
        }
        return instance.registerInstance(recipe);
    }

    public static void printAllComplexities() {
        StringBuilder complexitiesMapListBuilder = new StringBuilder();
        for(Recipe recipe : registry) {
            complexitiesMapListBuilder
                .append('"').append( recipe.toString() ).append("\": ")
                .append( recipe.complexity )
                .append("\r\n")
            ;
        }
        LOGGER.log(Level.INFO, complexitiesMapListBuilder.toString() );
    }

    //nothing
    public static final Recipe DUMMY; static {
        DUMMY = new Recipe(
            MachineTypes.PLAYER,
            MachineConfiguration.None,
            List.of(),
            List.of(),
            Double.MIN_VALUE
        );
    }

    //of nothing
    public static final Recipe ANY_WOOD; static {
        ANY_WOOD = new Recipe(
            MachineTypes.CROP_MANAGER,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.NOTHING, 1),
                    Recipes.DUMMY
                )
            ),
            List.of(
                new ItemStack(Items.ANY_WOOD, 1)
            ),
            1
        );
        ANY_WOOD.setEUPerTick(16);
    }
    public static final Recipe COAL_DUST; static {
        COAL_DUST = new Recipe(
            MachineTypes.PLAYER,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.MANUAL, 1),
                    Recipes.DUMMY
                )
            ),
            List.of(
                new ItemStack(Items.COAL_DUST, 1)
            ),
            Double.MIN_VALUE
        );
    }
    public static final Recipe COBBLESTONE; static {
        COBBLESTONE = new Recipe(
            MachineTypes.ROCK_BREAKER,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.NOTHING, 1),
                    Recipes.DUMMY
                )
            ),
            List.of(
                new ItemStack(Items.COBBLESTONE, 1)
            ),
            0.8
        );
        COBBLESTONE.setEUPerTick(30);
    }
    public static final Recipe COPPER_DUST; static {
        COPPER_DUST = new Recipe(
            MachineTypes.PLAYER,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.MANUAL, 1),
                    Recipes.DUMMY
                )
            ),
            List.of(
                new ItemStack(Items.COPPER_DUST, 1)
            ),
            Double.MIN_VALUE
        );
    }
    public static final Recipe EMPTY_CELL_CYCLED; static {
        EMPTY_CELL_CYCLED = new Recipe(
            MachineTypes.CELL_CYCLING,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.COMPRESSED_AIR, 1),
                    Recipes.DUMMY
                )
            ),
            List.of(
                new ItemStack(Items.EMPTY_CELL, 1)
            ),
            1
        );
    }
    public static final Recipe GLOW_FLOWER; static {
        GLOW_FLOWER = new Recipe(
            MachineTypes.CROP_MANAGER,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.NOTHING, 1),
                    Recipes.DUMMY
                )
            ),
            List.of(
                new ItemStack(Items.GLOW_FLOWER, 1)
            ),
            1
        );
        GLOW_FLOWER.setEUPerTick(16);
    }
    public static final Recipe IRON_DUST; static {
        IRON_DUST = new Recipe(
            MachineTypes.PLAYER,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.MANUAL, 1),
                    Recipes.DUMMY
                )
            ),
            List.of(
                new ItemStack(Items.IRON_DUST, 1)
            ),
            Double.MIN_VALUE
        );
    }
    public static final Recipe LAVA; static {
        LAVA = new Recipe(
            MachineTypes.PLAYER,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.MANUAL, 1),
                    Recipes.DUMMY
                )
            ),
            List.of(
                new ItemStack(Items.LAVA, 1)
            ),
            Double.MIN_VALUE
        );
    }
    public static final Recipe LEAD_INGOT; static {
        LEAD_INGOT = new Recipe(
            MachineTypes.PLAYER,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.MANUAL, 1),
                    Recipes.DUMMY
                )
            ),
            List.of(
                new ItemStack(Items.LEAD_INGOT, 1)
            ),
            Double.MIN_VALUE
        );
    }
    public static final Recipe REDSTONE_DUST; static {
        REDSTONE_DUST = new Recipe(
            MachineTypes.PLAYER,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.MANUAL, 1),
                    Recipes.DUMMY
                )
            ),
            List.of(
                new ItemStack(Items.REDSTONE_DUST, 1)
            ),
            Double.MIN_VALUE
        );
    }
    public static final Recipe STICKY_RESIN; static {
        STICKY_RESIN = new Recipe(
            MachineTypes.CROP_MANAGER,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.NOTHING, 1),
                    Recipes.DUMMY
                )
            ),
            List.of(
                new ItemStack(Items.STICKY_RESIN, 1)
            ),
            1
        );
        STICKY_RESIN.setEUPerTick(16);
    }

    //of^2 nothing
    public static final Recipe COMPRESSED_AIR; static {
        COMPRESSED_AIR = new Recipe(
            MachineTypes.COMPRESSOR,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.EMPTY_CELL, 1),
                    Recipes.EMPTY_CELL_CYCLED
                )
            ),
            List.of(
                new ItemStack(Items.COMPRESSED_AIR, 1)
            ),
            15
        );
        COMPRESSED_AIR.setEUPerTick(2);
    }
    public static final Recipe COPPER_INGOT; static {
        COPPER_INGOT = new Recipe(
                MachineTypes.ELECTRIC_FURNACE,
                MachineConfiguration.None,
                List.of(
                        new ItemStackWithPreferredRecipeSource(
                                new ItemStack(Items.COPPER_DUST, 1),
                                Recipes.COPPER_DUST
                        )
                ),
                List.of(
                        new ItemStack(Items.COPPER_INGOT, 1)
                ),
                6.4
        );
    }
    public static final Recipe GRAVEL; static {
        GRAVEL = new Recipe(
            MachineTypes.FORGE_HAMMER,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.COBBLESTONE, 1),
                    Recipes.COBBLESTONE
                )
            ),
            List.of(
                new ItemStack(Items.GRAVEL, 1)
            ),
            0.5
        );
        GRAVEL.setEUPerTick(16);
    }
    public static final Recipe GLOWSTONE_DUST; static {
        GLOWSTONE_DUST = new Recipe(
            MachineTypes.FLUID_EXTRACTOR,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.GLOW_FLOWER, 2),
                    Recipes.GLOW_FLOWER
                )
            ),
            List.of(
                new ItemStack(Items.GLOWSTONE_DUST, 1)
            ),
            15
        );
        GLOWSTONE_DUST.setEUPerTick(2);
    }
    public static final Recipe MOLTEN_LEAD; static {
        MOLTEN_LEAD = new Recipe(
            MachineTypes.FLUID_EXTRACTOR,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.LEAD_INGOT, 1),
                    Recipes.LEAD_INGOT
                )
            ),
            List.of(
                new ItemStack(Items.MOLTEN_LEAD, 1)
            ),
            1.2
        );
        MOLTEN_LEAD.setEUPerTick(34);
    }
    public static final Recipe REFINED_GLUE; static {
        REFINED_GLUE = new Recipe(
            MachineTypes.CENTRIFUGE,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.STICKY_RESIN, 1),
                    Recipes.STICKY_RESIN
                )
            ),
            List.of(
                new ItemStack(Items.RAW_RUBBER_DUST, 3),
                new ItemStack(Items.PLANT_BALL, 0.1),
                new ItemStack(Items.REFINED_GLUE, 100.0/144.0)
            ),
            0.05
        );
        REFINED_GLUE.setEUPerTick(5);
    }
    public static final Recipe WOOD_PULP; static {
        WOOD_PULP = new Recipe(
            MachineTypes.MACERATOR,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.ANY_WOOD, 1),
                    Recipes.ANY_WOOD
                )
            ),
            List.of(
                new ItemStack(Items.WOOD_PULP, 6)
            ),
            20
        );
        WOOD_PULP.setEUPerTick(2);
    }

    //of^3 nothing
    public static final Recipe AIR_GAS; static {
        AIR_GAS = new Recipe(
            MachineTypes.FLUID_TANK,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.COMPRESSED_AIR, 1),
                    Recipes.COMPRESSED_AIR
                )
            ),
            List.of(
                new ItemStack(Items.EMPTY_CELL, 1),
                new ItemStack(Items.AIR_GAS, 2)
            ),
            0.1
        );
    }
    public static final Recipe CENTRIFUGED_GLOWSTONE_DUST; static {
        CENTRIFUGED_GLOWSTONE_DUST = new Recipe(
                MachineTypes.CENTRIFUGE,
                MachineConfiguration.None,
                List.of(
                        new ItemStackWithPreferredRecipeSource(
                                new ItemStack(Items.GLOWSTONE_DUST, 2),
                                Recipes.GLOWSTONE_DUST
                        )
                ),
                List.of(
                        new ItemStack(Items.REDSTONE_DUST, 1),
                        new ItemStack(Items.GOLD_DUST, 1)
                ),
                48.8
        );
        CENTRIFUGED_GLOWSTONE_DUST.setEUPerTick(2);
    }
    public static final Recipe COPPER_FOIL; static {
        COPPER_FOIL = new Recipe(
                MachineTypes.BENDING_MACHINE,
                MachineConfiguration.ProgrammedCircuitTen,
                List.of(
                        new ItemStackWithPreferredRecipeSource(
                                new ItemStack(Items.COPPER_INGOT, 1),
                                Recipes.COPPER_INGOT
                        )
                ),
                List.of(
                        new ItemStack(Items.COPPER_FOIL, 4)
                ),
                6.3
        );
        COPPER_FOIL.setEUPerTick(24);
    }
    public static final Recipe FINE_COPPER_WIRE; static {
        FINE_COPPER_WIRE = new Recipe(
                MachineTypes.WIREMILL,
                MachineConfiguration.ProgrammedCircuitThree,
                List.of(
                        new ItemStackWithPreferredRecipeSource(
                                new ItemStack(Items.COPPER_INGOT, 1),
                                Recipes.COPPER_INGOT
                        )
                ),
                List.of(
                        new ItemStack(Items.FINE_COPPER_WIRE, 4)
                ),
                5
        );
        FINE_COPPER_WIRE.setEUPerTick(4);
    }
    public static final Recipe FLINT; static {
        FLINT = new Recipe(
            MachineTypes.SIFTER,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.GRAVEL, 1),
                    Recipes.GRAVEL
                )
            ),
            List.of(
                new ItemStack(Items.FLINT, 1+0.9+0.8+0.6+0.33+0.25)
            ),
            30
        );
        FLINT.setEUPerTick(16);
    }
    public static final Recipe EXTRACTED_GLOWSTONE; static {
        EXTRACTED_GLOWSTONE = new Recipe(
            MachineTypes.CENTRIFUGE,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.GLOWSTONE_DUST, 2),
                    Recipes.GLOWSTONE_DUST
                )
            ),
            List.of(
                new ItemStack(Items.REDSTONE_DUST, 1),
                new ItemStack(Items.GOLD_DUST, 1)
            ),
            48.8
        );
        EXTRACTED_GLOWSTONE.setEUPerTick(80);
    }
    public static final Recipe ONE_COPPER_WIRE; static {
        ONE_COPPER_WIRE = new Recipe(
                MachineTypes.WIREMILL,
                MachineConfiguration.ProgrammedCircuitOne,
                List.of(
                        new ItemStackWithPreferredRecipeSource(
                                new ItemStack(Items.COPPER_INGOT, 1),
                                Recipes.COPPER_INGOT
                        )
                ),
                List.of(
                        new ItemStack(Items.ONE_COPPER_WIRE, 2)
                ),
                5
        );
        ONE_COPPER_WIRE.setEUPerTick(4);
    }
    public static final Recipe SAND; static {
        SAND = new Recipe(
            MachineTypes.FORGE_HAMMER,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.GRAVEL, 1),
                    Recipes.GRAVEL
                )
            ),
            List.of(
                new ItemStack(Items.SAND, 1)
            ),
            0.5
        );
        SAND.setEUPerTick(16);
    }
    public static final Recipe WOOD_PLANK; static {
        WOOD_PLANK = new Recipe(
            MachineTypes.ASSEMBLER,
            MachineConfiguration.ProgrammedCircuitTwo,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.WOOD_PULP, 64),
                    Recipes.WOOD_PULP
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.REFINED_GLUE, 1),
                    Recipes.REFINED_GLUE
                )
            ),
            List.of(
                new ItemStack(Items.WOOD_PLANK, 64)
            ),
            120
        );
        WOOD_PLANK.setEUPerTick(30);
    }

    //of^4 nothing
    public static final Recipe CIRCUIT_BOARD; static {
        CIRCUIT_BOARD = new Recipe(
            MachineTypes.ASSEMBLER,
            MachineConfiguration.ProgrammedCircuitSix,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.WOOD_PLANK, 8),
                    Recipes.WOOD_PLANK
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.COPPER_FOIL, 32),
                    Recipes.COPPER_FOIL
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.REFINED_GLUE, 4),
                    Recipes.REFINED_GLUE
                )
            ),
            List.of(
                new ItemStack(Items.CIRCUIT_BOARD, 8)
            ),
            80
        );
        CIRCUIT_BOARD.setEUPerTick(8);
    }
    public static final Recipe FLINT_DUST; static {
        FLINT_DUST = new Recipe(
            MachineTypes.MACERATOR,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.FLINT, 2),
                    Recipes.FLINT
                )
            ),
            List.of(
                new ItemStack(Items.FLINT_DUST, 1)
            ),
            10
        );
        FLINT_DUST.setEUPerTick(2);
    }
    public static final Recipe MOLTEN_REDSTONE; static {
        MOLTEN_REDSTONE = new Recipe(
                MachineTypes.FLUID_EXTRACTOR,
                MachineConfiguration.None,
                List.of(
                        new ItemStackWithPreferredRecipeSource(
                                new ItemStack(Items.REDSTONE_DUST, 1),
                                Recipes.CENTRIFUGED_GLOWSTONE_DUST
                        )
                ),
                List.of(
                        new ItemStack(Items.MOLTEN_REDSTONE, 1)
                ),
                1.2
        );
        MOLTEN_REDSTONE.setEUPerTick(30);
    }
    public static final Recipe RED_ALLOY_INGOT; static {
        RED_ALLOY_INGOT = new Recipe(
                MachineTypes.ALLOY_SMELTER,
                MachineConfiguration.None,
                List.of(
                        new ItemStackWithPreferredRecipeSource(
                                new ItemStack(Items.COPPER_INGOT, 1),
                                Recipes.COPPER_INGOT
                        ),
                        new ItemStackWithPreferredRecipeSource(
                                new ItemStack(Items.REDSTONE_DUST, 4),
                                Recipes.CENTRIFUGED_GLOWSTONE_DUST
                        )
                ),
                List.of(
                        new ItemStack(Items.RED_ALLOY_INGOT, 1)
                ),
                2.5
        );
        RED_ALLOY_INGOT.setEUPerTick(16);
    }
    public static final Recipe REDSTONE_DUST_OBSIDIAN; static {
        REDSTONE_DUST_OBSIDIAN = new Recipe(
            MachineTypes.ROCK_BREAKER,
            MachineConfiguration.ProgrammedCircuitOne,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.REDSTONE_DUST, 1),
                    Recipes.CENTRIFUGED_GLOWSTONE_DUST
                )
            ),
            List.of(
                    new ItemStack(Items.OBSIDIAN, 1)
            ),
            6.4
        );
        REDSTONE_DUST_OBSIDIAN.setEUPerTick(30);
    }
    public static final Recipe OXYGEN_CELL; static {
        OXYGEN_CELL = new Recipe(
            MachineTypes.CENTRIFUGE,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.EMPTY_CELL, 1),
                    Recipes.EMPTY_CELL_CYCLED
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.AIR_GAS, 10),
                    Recipes.AIR_GAS
                )
            ),
            List.of(
                new ItemStack(Items.OXYGEN_CELL, 1),
                new ItemStack(Items.NITROGEN_GAS, 3.9)
            ),
            80
        );
        OXYGEN_CELL.setEUPerTick(8);
    }
    public static final Recipe QUARTZ_SAND; static {
        QUARTZ_SAND = new Recipe(
            MachineTypes.MACERATOR,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.SAND, 1),
                    Recipes.SAND
                )
            ),
            List.of(
                new ItemStack(Items.QUARTZ_SAND, 1)
            ),
            10
        );
        QUARTZ_SAND.setEUPerTick(8);
    }
    public static final Recipe RESISTOR; static {
        RESISTOR = new Recipe(
            MachineTypes.CIRCUIT_ASSEMBLER,
            MachineConfiguration.ProgrammedCircuitThree,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.COAL_DUST, 1),
                    Recipes.COAL_DUST
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.FINE_COPPER_WIRE, 4),
                    Recipes.FINE_COPPER_WIRE
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.ONE_COPPER_WIRE, 4),
                    Recipes.ONE_COPPER_WIRE
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.REFINED_GLUE, 2),
                    Recipes.REFINED_GLUE
                )
            ),
            List.of(
                new ItemStack(Items.RESISTOR, 4)
            ),
            16
        );
        RESISTOR.setEUPerTick(16);
    }

    //of^5 nothing
    public static final Recipe GLASS_DUST; static {
        GLASS_DUST = new Recipe(
            MachineTypes.MIXER,
            MachineConfiguration.ProgrammedCircuitFour,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.FLINT_DUST, 1),
                    Recipes.FLINT_DUST
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.QUARTZ_SAND, 16),
                    Recipes.QUARTZ_SAND
                )
            ),
            List.of(
                new ItemStack(Items.GLASS_DUST, 16)
            ),
            40
        );
        GLASS_DUST.setEUPerTick(8);
    }
    public static final Recipe MOLTEN_RED_ALLOY; static {
        MOLTEN_RED_ALLOY = new Recipe(
                MachineTypes.FLUID_EXTRACTOR,
                MachineConfiguration.None,
                List.of(
                        new ItemStackWithPreferredRecipeSource(
                                new ItemStack(Items.RED_ALLOY_INGOT, 1),
                                Recipes.RED_ALLOY_INGOT
                        )
                ),
                List.of(
                        new ItemStack(Items.MOLTEN_RED_ALLOY, 1)
                ),
                1.2
        );
        MOLTEN_RED_ALLOY.setEUPerTick(30);
    }
    public static final Recipe OBSIDIAN_DUST; static {
        OBSIDIAN_DUST = new Recipe(
            MachineTypes.MACERATOR,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.OBSIDIAN, 1),
                    Recipes.REDSTONE_DUST_OBSIDIAN
                )
            ),
            List.of(
                new ItemStack(Items.OBSIDIAN_DUST, 12)
            ),
            9.0*1.1
        );
        OBSIDIAN_DUST.setEUPerTick(4);
    }
    public static final Recipe ONE_RED_ALLOY_WIRE; static {
        ONE_RED_ALLOY_WIRE = new Recipe(
                MachineTypes.WIREMILL,
                MachineConfiguration.ProgrammedCircuitOne,
                List.of(
                        new ItemStackWithPreferredRecipeSource(
                                new ItemStack(Items.RED_ALLOY_INGOT, 1),
                                Recipes.RED_ALLOY_INGOT
                        )
                ),
                List.of(
                        new ItemStack(Items.ONE_RED_ALLOY_WIRE, 2)
                ),
                5
        );
        ONE_RED_ALLOY_WIRE.setEUPerTick(4);
    }
    public static final Recipe OXYGEN_GAS; static {
        OXYGEN_GAS = new Recipe(
            MachineTypes.FLUID_TANK,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.OXYGEN_CELL, 1),
                    Recipes.OXYGEN_CELL
                )
            ),
            List.of(
                new ItemStack(Items.EMPTY_CELL, 1),
                new ItemStack(Items.OXYGEN_GAS, 1)
            ),
            0.1
        );
    }

    //of^6 nothing
    public static final Recipe ANNEALED_COPPER_INGOT; static {
        ANNEALED_COPPER_INGOT = new Recipe(
            MachineTypes.MIXER,
            3,
            MachineConfiguration.ProgrammedCircuitFour,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.COPPER_DUST, 1),
                    Recipes.COPPER_DUST
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.OXYGEN_GAS, 63.0/1000.0),
                    Recipes.OXYGEN_GAS
                )
            ),
            List.of(
                new ItemStack(Items.ANNEALED_COPPER_INGOT, 1)
            ),
            3.15
        );
        ANNEALED_COPPER_INGOT.setEUPerTick(3*30);
    }
    public static final Recipe ELECTROLYZED_OBSIDIAN_DUST; static {
        ELECTROLYZED_OBSIDIAN_DUST = new Recipe(
            MachineTypes.ELECTROLYZER,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.OBSIDIAN_DUST, 12),
                    Recipes.OBSIDIAN_DUST
                )
            ),
            List.of(
                new ItemStack(Items.MAGNESIUM_DUST, 1),
                new ItemStack(Items.IRON_DUST, 1),
                new ItemStack(Items.RAW_SILICON_DUST, 2),
                new ItemStack(Items.OXYGEN_GAS, 8)
            ),
            12
        );
        ELECTROLYZED_OBSIDIAN_DUST.setEUPerTick(90);
    }
    public static final Recipe GLASS_TUBE; static {
        GLASS_TUBE = new Recipe(
            MachineTypes.ALLOY_SMELTER,
            MachineConfiguration.MoldBall,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.GLASS_DUST, 1),
                    Recipes.GLASS_DUST
                )
            ),
            List.of(
                new ItemStack(Items.GLASS_TUBE, 1)
            ),
            6
        );
        GLASS_TUBE.setEUPerTick(16);
    }
    public static final Recipe IRON_STEEL_INGOT; static {
        IRON_STEEL_INGOT = new Recipe(
            MachineTypes.ELECTRIC_BLAST_FURNACE,
            4,
            MachineConfiguration.ProgrammedCircuitEleven,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.IRON_DUST, 1),
                    Recipes.IRON_DUST
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.OXYGEN_GAS, 1),
                    Recipes.OXYGEN_GAS
                )
            ),
            List.of(
                new ItemStack(Items.STEEL_INGOT, 1),
                new ItemStack(Items.ASHES, 1.0/9.0)
            ),
            25
        );
        IRON_STEEL_INGOT.setEUPerTick(120);
    }
    public static final Recipe WROUGHT_IRON_INGOT; static {
        WROUGHT_IRON_INGOT = new Recipe(
            MachineTypes.ARC_FURNACE,
            3,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.IRON_DUST, 1),
                    Recipes.IRON_DUST
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.OXYGEN_GAS, 56.0/1000.0),
                    Recipes.OXYGEN_GAS
                )
            ),
            List.of(
                new ItemStack(Items.WROUGHT_IRON_INGOT, 1)
            ),
            2.8
        );
        WROUGHT_IRON_INGOT.setEUPerTick(3*30);
    }

    //of^7 nothing
    public static final Recipe REDSTONE_ALLOY_DUST; static {
            REDSTONE_ALLOY_DUST = new Recipe(
                MachineTypes.MIXER,
                MachineConfiguration.ProgrammedCircuitTwentyTwo,
                List.of(
                    new ItemStackWithPreferredRecipeSource(
                        new ItemStack(Items.REDSTONE_DUST, 1),
                        Recipes.CENTRIFUGED_GLOWSTONE_DUST
                    ),
                    new ItemStackWithPreferredRecipeSource(
                        new ItemStack(Items.RAW_SILICON_DUST, 1),
                        Recipes.ELECTROLYZED_OBSIDIAN_DUST
                    ),
                    new ItemStackWithPreferredRecipeSource(
                        new ItemStack(Items.COAL_DUST, 1),
                        Recipes.COAL_DUST
                    )
                ),
                List.of(
                    new ItemStack(Items.REDSTONE_ALLOY_DUST, 3)
                ),
                5
            );
            REDSTONE_ALLOY_DUST.setEUPerTick(8);
    }
    public static final Recipe ONE_ANNEALED_COPPER_WIRE; static {
        ONE_ANNEALED_COPPER_WIRE = new Recipe(
            MachineTypes.WIREMILL,
            MachineConfiguration.ProgrammedCircuitOne,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.ANNEALED_COPPER_INGOT, 1),
                    Recipes.ANNEALED_COPPER_INGOT
                )
            ),
            List.of(
                new ItemStack(Items.ONE_ANNEALED_COPPER_WIRE, 2)
            ),
            5
        );
        ONE_ANNEALED_COPPER_WIRE.setEUPerTick(4);
    }
    public static final Recipe WROUGHT_IRON_DUST; static {
        WROUGHT_IRON_DUST = new Recipe(
            MachineTypes.MACERATOR,
            3,
            MachineConfiguration.ProgrammedCircuitEleven,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.WROUGHT_IRON_INGOT, 1),
                    Recipes.WROUGHT_IRON_INGOT
                )
            ),
            List.of(
                new ItemStack(Items.WROUGHT_IRON_DUST, 1)
            ),
            2.8
        );
        WROUGHT_IRON_DUST.setEUPerTick(4);
    }

    //of^8 nothing
    public static final Recipe REDSTONE_ALLOY_INGOT; static {
        REDSTONE_ALLOY_INGOT = new Recipe(
            MachineTypes.ELECTRIC_BLAST_FURNACE,
            4,
            MachineConfiguration.ProgrammedCircuitEleven,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.REDSTONE_ALLOY_DUST, 1),
                    Recipes.REDSTONE_ALLOY_DUST
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.OXYGEN_GAS, 1),
                    Recipes.OXYGEN_GAS
                )
            ),
            List.of(
                new ItemStack(Items.REDSTONE_ALLOY_INGOT, 1)
            ),
            40
        );
        REDSTONE_ALLOY_INGOT.setEUPerTick(120);
    }
    public static final Recipe WROUGHT_STEEL_INGOT; static {
        WROUGHT_STEEL_INGOT = new Recipe(
            MachineTypes.ELECTRIC_BLAST_FURNACE,
            4,
            MachineConfiguration.ProgrammedCircuitEleven,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.WROUGHT_IRON_DUST, 1),
                    Recipes.WROUGHT_IRON_DUST
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.OXYGEN_GAS, 1),
                    Recipes.OXYGEN_GAS
                )
            ),
            List.of(
                new ItemStack(Items.STEEL_INGOT, 1),
                new ItemStack(Items.ASHES, 1.0/9.0)
            ),
            5
        );
        WROUGHT_STEEL_INGOT.setEUPerTick(120);
    }

    //of^9 nothing
    public static final Recipe EXTRUDER_STEEL_ROD; static {
        EXTRUDER_STEEL_ROD = new Recipe(
            MachineTypes.EXTRUDER,
            MachineConfiguration.ExtrudeShapeRod,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.STEEL_INGOT, 1),
                    Recipes.WROUGHT_STEEL_INGOT
                )
            ),
            List.of(
                new ItemStack(Items.STEEL_ROD, 2)
            ),
            5.6
        );
        EXTRUDER_STEEL_ROD.setEUPerTick(90);
    }
    public static final Recipe LATHE_STEEL_ROD; static {
        LATHE_STEEL_ROD = new Recipe(
            MachineTypes.LATHE,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.STEEL_INGOT, 1),
                    Recipes.IRON_STEEL_INGOT
                )
            ),
            List.of(
                new ItemStack(Items.STEEL_ROD, 1),
                new ItemStack(Items.SMALL_PILE_OF_STEEL_DUST, 2)
            ),
            14
        );
        LATHE_STEEL_ROD.setEUPerTick(16);
    }
    public static final Recipe MOLTEN_REDSTONE_ALLOY; static {
        MOLTEN_REDSTONE_ALLOY = new Recipe(
                MachineTypes.FLUID_EXTRACTOR,
                List.of(
                        new ItemStackWithPreferredRecipeSource(
                                new ItemStack(Items.REDSTONE_ALLOY_INGOT, 1),
                                Recipes.REDSTONE_ALLOY_INGOT
                        )
                ),
                List.of(
                        new ItemStack(Items.MOLTEN_REDSTONE_ALLOY, 1)
                ),
                1.2
        );
        MOLTEN_REDSTONE_ALLOY.setEUPerTick(36);
    }

    //of^10 nothing
    public static final Recipe SMALL_PILED_STEEL_DUST; static {
        SMALL_PILED_STEEL_DUST = new Recipe(
            MachineTypes.AUTO_WORKBENCH,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.SMALL_PILE_OF_STEEL_DUST, 4),
                    LATHE_STEEL_ROD
                )
            ),
            List.of(
                new ItemStack(Items.STEEL_DUST, 1)
            ),
            3.2
        );
        SMALL_PILED_STEEL_DUST.setEUPerTick(16);
    }
    public static final Recipe STEEL_STEEL_INGOT; static {
        STEEL_STEEL_INGOT = new Recipe(
            MachineTypes.ELECTRIC_BLAST_FURNACE,
            4,
            MachineConfiguration.ProgrammedCircuitOne,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.STEEL_DUST, 1),
                    Recipes.SMALL_PILED_STEEL_DUST
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.OXYGEN_GAS, 1),
                    Recipes.OXYGEN_GAS
                )
            ),
            List.of(
                new ItemStack(Items.STEEL_INGOT, 1)
            ),
            50
        );
        IRON_STEEL_INGOT.setEUPerTick(120);
    }
    public static final Recipe MOLTEN_RED_ALLOY_VACUUM_TUBE; static {
        MOLTEN_RED_ALLOY_VACUUM_TUBE = new Recipe(
            MachineTypes.ASSEMBLER,
            MachineConfiguration.ProgrammedCircuitFive,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.GLASS_TUBE, 4),
                    Recipes.GLASS_TUBE
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.ONE_COPPER_WIRE, 4),
                    Recipes.ONE_COPPER_WIRE
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.STEEL_ROD, 4),
                    Recipes.EXTRUDER_STEEL_ROD
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.MOLTEN_RED_ALLOY, 0.5),
                    Recipes.MOLTEN_RED_ALLOY
                )
            ),
            List.of(
                new ItemStack(Items.VACUUM_TUBE, 4)
            ),
            8
        );
        MOLTEN_RED_ALLOY_VACUUM_TUBE.setEUPerTick(8);
    }
    public static final Recipe MOLTEN_REDSTONE_ALLOY_VACUUM_TUBE; static {
        MOLTEN_REDSTONE_ALLOY_VACUUM_TUBE = new Recipe(
            MachineTypes.ASSEMBLER,
            MachineConfiguration.ProgrammedCircuitFive,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.GLASS_TUBE, 4),
                    Recipes.GLASS_TUBE
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.ONE_COPPER_WIRE, 4),
                    Recipes.ONE_COPPER_WIRE
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.STEEL_ROD, 4),
                    Recipes.EXTRUDER_STEEL_ROD
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.MOLTEN_REDSTONE_ALLOY, 0.5),
                    Recipes.MOLTEN_REDSTONE_ALLOY
                )
            ),
            List.of(
                new ItemStack(Items.VACUUM_TUBE, 8)
            ),
            8
        );
        MOLTEN_REDSTONE_ALLOY_VACUUM_TUBE.setEUPerTick(8);
    }
    public static final Recipe MOLTEN_REDSTONE_VACUUM_TUBE; static {
        MOLTEN_REDSTONE_VACUUM_TUBE = new Recipe(
            MachineTypes.ASSEMBLER,
            MachineConfiguration.ProgrammedCircuitFive,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.GLASS_TUBE, 2),
                    Recipes.GLASS_TUBE
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.ONE_COPPER_WIRE, 4),
                    Recipes.ONE_COPPER_WIRE
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.STEEL_ROD, 4),
                    Recipes.LATHE_STEEL_ROD
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.MOLTEN_REDSTONE, 1),
                    Recipes.MOLTEN_REDSTONE
                )
            ),
            List.of(
                new ItemStack(Items.VACUUM_TUBE, 2)
            ),
            8
        );
        MOLTEN_REDSTONE_VACUUM_TUBE.setEUPerTick(8);
    }

    //of^11 nothing
    public static final Recipe ELECTRONIC_CIRCUIT; static {
        ELECTRONIC_CIRCUIT = new Recipe(
            MachineTypes.ASSEMBLER,
            MachineConfiguration.None,
            List.of(
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.CIRCUIT_BOARD, 1),
                    Recipes.CIRCUIT_BOARD
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.RESISTOR, 2),
                    Recipes.RESISTOR
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.ONE_RED_ALLOY_WIRE, 2),
                    Recipes.ONE_RED_ALLOY_WIRE
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.VACUUM_TUBE, 2),
                    Recipes.MOLTEN_REDSTONE_ALLOY_VACUUM_TUBE
                ),
                new ItemStackWithPreferredRecipeSource(
                    new ItemStack(Items.MOLTEN_LEAD, 2),
                    Recipes.MOLTEN_LEAD
                )
            ),
            List.of(
                new ItemStack(Items.ELECTRIC_CIRCUIT, 1)
            ),
            10
        );
        ELECTRONIC_CIRCUIT.setEUPerTick(16);
    }
}
