package recipes;

import graph.evaluations.RecipeComparison;
import items.Item;
import items.ItemStack;
import items.Items;
import machines.MachineConfiguration;
import machines.MachineTypes;
import recipes.minecraft.GTNH.thaumcraft.Aspects;
import recipes.minecraft.GTNH.thaumcraft.Cauldron;
import register.Registered;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static graph.evaluations.RecipeComparisons.FASTEST;

public class Recipes extends Registered<Recipe> {
    private static Recipes instance;
    public static final List<Recipe> registry = new ArrayList<Recipe>();
    private static final Logger LOGGER = Logger.getLogger("Recipes");
    public static HashMap<Item, Recipe> optimalRecipes = new HashMap<>();

    public Recipes() {
        super("Recipes", registry);
        instance = this;
    }

    public static boolean register(Recipe recipe) {
        if(instance == null) {
            instance = new Recipes();
            //TODO: redo recipes organization
            new Cauldron();
            new Aspects();
        }

        boolean wasSuccessfullyRegistered = instance.registerInstance(recipe);
        if(wasSuccessfullyRegistered) {
            //TODO: make this run only when it's done adding recipes
            optimalRecipes = new HashMap<>();
            calculateOptimalRecipes();
        }
        return wasSuccessfullyRegistered;
    }
    public static boolean isLeafRecipe(Recipe recipe) {
        return
            recipe.equals(COBBLESTONE)
        ;
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

    private static ItemStack getItemStackOfSpecificItem(List<ItemStack> itemStacks, Item itemType) {
        ItemStack result = new ItemStack(itemType, 0);
        for(ItemStack itemStack : itemStacks) {
            if( itemStack.item.equals(itemType) ) {
                result = new ItemStack(itemType, result.quantity+itemStack.quantity);
            }
        }
        return result;
    }
    public static void calculateOptimalRecipes() {
        //TODO: more options
        optimalRecipes = RecipeComparison.getBestRecipes(FASTEST);
    }

    public static Recipe getFastestProducingRecipe(Item ofItem) {
        //linear search
        double fastest_production_rate = 0.0, current_production_rate;
        Recipe fastestProducingRecipes = Recipes.DUMMY;
        for(Recipe potentiallyFastestProducingRecipe : Recipes.registry) {
            current_production_rate = potentiallyFastestProducingRecipe.getProductionRate(ofItem);
            if(fastest_production_rate < current_production_rate) {
                fastest_production_rate = current_production_rate;
                fastestProducingRecipes = potentiallyFastestProducingRecipe;
            }
        }

        return fastestProducingRecipes;
    }

    //nothing
    public static final Recipe DUMMY = new Recipe(MachineTypes.PLAYER, 0.0, new ArrayList<>(), Double.MIN_VALUE);

    //of nothing
    //TODO: abstract and rework CropManager
    public static final Recipe ANY_WOOD = new Recipe(MachineTypes.CROP_MANAGER, 16.0, new ItemStack(Items.ANY_WOOD), 1.0);
    public static final Recipe COBBLESTONE = new Recipe(MachineTypes.ROCK_BREAKER, 30.0, new ItemStack(Items.COBBLESTONE), 0.8);
    public static final Recipe DRIED_DIRT = new Recipe(MachineTypes.ELECTRIC_FURNACE, 4.0, new ItemStack(Items.DIRT), new ItemStack(Items.DRIED_DIRT), 6.4);
    public static final Recipe EMPTY_CELL_CYCLED = new Recipe(MachineTypes.CELL_CYCLING, 0.0, new ItemStack(Items.COMPRESSED_AIR), new ItemStack(Items.EMPTY_CELL), 1.0);
    public static final Recipe GLOW_FLOWER = new Recipe(MachineTypes.CROP_MANAGER, 16.0, new ItemStack(Items.GLOW_FLOWER), 1.0);
    public static final Recipe NETHERRACK = new Recipe(MachineTypes.CROP_MANAGER, 16.0, new ItemStack(Items.NETHERRACK), 1.0);
    public static final Recipe STONE = new Recipe(MachineTypes.ROCK_BREAKER, 30.0, new ItemStack(Items.STONE), 0.8);
    public static final Recipe STICKY_RESIN = new Recipe(MachineTypes.CROP_MANAGER, 16.0, new ItemStack(Items.STICKY_RESIN), 1.0);
    public static final Recipe SUGAR_CANE = new Recipe(MachineTypes.CROP_MANAGER, 16.0, new ItemStack(Items.SUGAR_CANE), 1.0);
    public static final Recipe WATERTANK_WATER = new Recipe(MachineTypes.WATERTANK, 0.0, new ItemStack(Items.WATER, 0.8/1000.0), 1.0);
    public static final Recipe THIRSTYTANK_WATER = new Recipe(MachineTypes.THIRSTYTANK, 0.0, new ItemStack(Items.WATER, 1.0), 31.0/20.0);

    //of^2 nothing
    //TODO: abstract crafting tasks
    public static final Recipe CENTRIFUGED_DRIED_DIRT; static {
        CENTRIFUGED_DRIED_DIRT = new Recipe(
            MachineTypes.CENTRIFUGE,
            30.0,
            List.of( new ItemStack(Items.DRIED_DIRT) ),
            List.of(
                new ItemStack(Items.SMALL_PILE_OF_CLAY_DUST, 0.9),
                new ItemStack(Items.SAND, 0.5),
                new ItemStack(Items.ASHES, 2.77/100.0)
            ),
            5.0
        );
    }
    public static final Recipe CHISELED_STONE_BRICKS = new Recipe(MachineTypes.LASER_ENGRAVER, MachineConfiguration.GlassLens, 16.0, new ItemStack(Items.STONE), new ItemStack(Items.CHISELED_STONE_BRICKS), 2.5);
    public static final Recipe COMPRESSED_AIR = new Recipe(MachineTypes.COMPRESSOR, 2.0, new ItemStack(Items.EMPTY_CELL), new ItemStack(Items.COMPRESSED_AIR), 15.0);
    public static final Recipe COPPER_INGOT = new Recipe(MachineTypes.ELECTRIC_FURNACE, 4.0, new ItemStack(Items.COPPER_DUST), new ItemStack(Items.COPPER_INGOT, 1), 6.4);
    public static final Recipe GRAVEL = new Recipe(MachineTypes.FORGE_HAMMER, 16.0, new ItemStack(Items.COBBLESTONE), new ItemStack(Items.GRAVEL), 0.5);
    public static final Recipe GLOWSTONE_DUST = new Recipe(MachineTypes.FLUID_EXTRACTOR, 2.0, new ItemStack(Items.GLOW_FLOWER, 2), new ItemStack(Items.GLOWSTONE_DUST), 15.0);
    public static final Recipe MOLTEN_LEAD = new Recipe(MachineTypes.FLUID_EXTRACTOR, 34.0, new ItemStack(Items.LEAD_INGOT), new ItemStack(Items.MOLTEN_LEAD, 144.0/1000.0), 1.2);
    public static final Recipe NETHER_COBBLESTONE; static {
        NETHER_COBBLESTONE = new Recipe(
            MachineTypes.AUTO_WORKBENCH,
            16.0,
            List.of(
                new ItemStack(Items.NETHERRACK, 2),
                new ItemStack(Items.COBBLESTONE, 2)
            ),
            List.of( new ItemStack(Items.NETHER_COBBLESTONE, 4) ),
            3.2
        );
    }
    public static final Recipe REFINED_GLUE; static {
        REFINED_GLUE = new Recipe(
            MachineTypes.CENTRIFUGE,
            5.0,
            List.of(
                new ItemStack(Items.STICKY_RESIN)
            ),
            List.of(
                new ItemStack(Items.RAW_RUBBER_DUST, 3),
                new ItemStack(Items.PLANT_BALL, 0.1),
                new ItemStack(Items.REFINED_GLUE, 100.0/144.0)
            ),
            0.05
        );
    }
    public static final Recipe STONE_DUST = new Recipe(MachineTypes.MACERATOR, 4.0, new ItemStack(Items.COBBLESTONE), new ItemStack(Items.STONE_DUST, 1), 4.9);
    public static final Recipe WOOD_PULP = new Recipe(MachineTypes.MACERATOR, 2.0, new ItemStack(Items.ANY_WOOD), new ItemStack(Items.WOOD_PULP, 6), 20.0);

    //of^3 nothing
    public static final Recipe AIR_GAS; static {
        AIR_GAS = new Recipe(
            MachineTypes.FLUID_TANK,
            0.0,
            List.of( new ItemStack(Items.COMPRESSED_AIR) ),
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
                2.0,
                List.of( new ItemStack(Items.GLOWSTONE_DUST, 2) ),
                List.of(
                    new ItemStack(Items.REDSTONE_DUST, 1),
                    new ItemStack(Items.GOLD_DUST, 1)
                ),
                48.8
        );
    }
    public static final Recipe CENTRIFUGED_STONE_DUST; static {
        CENTRIFUGED_STONE_DUST = new Recipe(
            MachineTypes.CENTRIFUGE,
            30.0,
            List.of(
                new ItemStack(Items.STONE_DUST, 36)
            ),
            List.of(
                new ItemStack(Items.QUARTZITE_DUST, 9),
                new ItemStack(Items.POTASSIUM_FELDSPAR_DUST, 9),
                new ItemStack(Items.MARBLE_DUST, 8),
                new ItemStack(Items.BIOTITE_DUST, 4),
                new ItemStack(Items.METAL_MIXTURE_DUST, 3),
                new ItemStack(Items.SODALITE_DUST, 2)
            ),
            432.0
        );
    }
    public static final Recipe CLAY_DUST = new Recipe(MachineTypes.AUTO_WORKBENCH, 0.0, new ItemStack(Items.SMALL_PILE_OF_CLAY_DUST, 4), new ItemStack(Items.CLAY_DUST), 0.1); //TODO: verify
    public static final Recipe COPPER_FOIL = new Recipe(MachineTypes.BENDING_MACHINE, MachineConfiguration.ProgrammedCircuitTen, 24.0, new ItemStack(Items.COPPER_INGOT), new ItemStack(Items.COPPER_FOIL, 4), 6.3);
    public static final Recipe FINE_COPPER_WIRE = new Recipe(MachineTypes.WIREMILL, MachineConfiguration.ProgrammedCircuitThree, 4.0, new ItemStack(Items.COPPER_INGOT), new ItemStack(Items.FINE_COPPER_WIRE, 4), 5.0);
    public static final Recipe FLINT = new Recipe(MachineTypes.SIFTER, 16.0, new ItemStack(Items.GRAVEL), new ItemStack(Items.FLINT, 1+0.9+0.8+0.6+0.33+0.25), 30.0);
    public static final Recipe EXTRACTED_GLOWSTONE; static {
        EXTRACTED_GLOWSTONE = new Recipe(
            MachineTypes.CENTRIFUGE,
            80.0,
            List.of( new ItemStack(Items.GLOWSTONE_DUST, 2) ),
            List.of(
                new ItemStack(Items.REDSTONE_DUST, 1),
                new ItemStack(Items.GOLD_DUST, 1)
            ),
            48.8
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
    public static final Recipe ONE_COPPER_WIRE = new Recipe(MachineTypes.WIREMILL, MachineConfiguration.ProgrammedCircuitOne, 4.0, new ItemStack(Items.COPPER_INGOT), new ItemStack(Items.ONE_COPPER_WIRE, 2), 5.0);
    public static final Recipe SAND = new Recipe(MachineTypes.FORGE_HAMMER, 16.0, new ItemStack(Items.GRAVEL), new ItemStack(Items.SAND), 0.5);
    public static final Recipe WOOD_PLANK; static {
        WOOD_PLANK = new Recipe(
            MachineTypes.ASSEMBLER,
            MachineConfiguration.ProgrammedCircuitTwo,
            30.0,
            List.of(
                new ItemStack(Items.WOOD_PULP, 64),
                new ItemStack(Items.REFINED_GLUE, 1)
            ),
            List.of(
                new ItemStack(Items.WOOD_PLANK, 64)
            ),
            120.0
        );
    }

    //of^4 nothing
    public static final Recipe CIRCUIT_BOARD; static {
        CIRCUIT_BOARD = new Recipe(
            MachineTypes.ASSEMBLER,
            MachineConfiguration.ProgrammedCircuitSix,
            8.0,
            List.of(
                new ItemStack(Items.WOOD_PLANK, 8),
                new ItemStack(Items.COPPER_FOIL, 32),
                new ItemStack(Items.REFINED_GLUE, 4)
            ),
            List.of(
                new ItemStack(Items.CIRCUIT_BOARD, 8)
            ),
            80.0
        );
    }
    public static final Recipe CENTRIFUGED_MARBLE_DUST; static {
        CENTRIFUGED_MARBLE_DUST = new Recipe(
            MachineTypes.CENTRIFUGE,
            10.0,
            List.of(
                new ItemStack(Items.MARBLE_DUST, 8)
            ),
            List.of(
                new ItemStack(Items.MAGNESIUM_DUST, 1),
                new ItemStack(Items.CALCITE_DUST, 7)
            ),
            32.0
        );
    }
    public static final Recipe FLINT_DUST = new Recipe(MachineTypes.MACERATOR, 2.0, new ItemStack(Items.FLINT, 2), new ItemStack(Items.FLINT_DUST), 10.0);
    public static final Recipe LIGHT_CONCRETE = new Recipe(MachineTypes.FLUID_SOLIDIFIER, MachineConfiguration.MoldBlock, 4.0, new ItemStack(Items.WET_CONCRETE, 144.0/1000.0), new ItemStack(Items.LIGHT_CONCRETE), 0.6  );
    public static final Recipe MARBLE = new Recipe(MachineTypes.COMPRESSOR, 2.0, new ItemStack(Items.MARBLE_DUST, 9), new ItemStack(Items.MARBLE), 15);
    public static final Recipe MOLTEN_REDSTONE = new Recipe(MachineTypes.FLUID_EXTRACTOR, 30.0, new ItemStack(Items.REDSTONE_DUST), new ItemStack(Items.MOLTEN_REDSTONE, 144.0/1000.0), 1.2);
    public static final Recipe SANDSTONE = new Recipe(MachineTypes.AUTO_WORKBENCH, 16.0, new ItemStack(Items.SAND, 4), new ItemStack(Items.SANDSTONE), 3.2);
    public static final Recipe RED_ALLOY_INGOT; static {
        RED_ALLOY_INGOT = new Recipe(
            MachineTypes.ALLOY_SMELTER,
            16.0,
            List.of(
                new ItemStack(Items.REDSTONE_DUST, 4),
                new ItemStack(Items.COPPER_INGOT)
            ),
            List.of(
                    new ItemStack(Items.RED_ALLOY_INGOT, 1)
            ),
            2.5
        );
    }
    public static final Recipe REDSTONE_DUST_OBSIDIAN = new Recipe(MachineTypes.ROCK_BREAKER, MachineConfiguration.ProgrammedCircuitOne, 30.0, new ItemStack(Items.REDSTONE_DUST), new ItemStack(Items.OBSIDIAN), 6.4);
    public static final Recipe OXYGEN_CELL; static {
        OXYGEN_CELL = new Recipe(
            MachineTypes.CENTRIFUGE,
            8.0,
            List.of(
                new ItemStack(Items.AIR_GAS, 10),
                new ItemStack(Items.EMPTY_CELL)
            ),
            List.of(
                new ItemStack(Items.OXYGEN_CELL, 1),
                new ItemStack(Items.NITROGEN_GAS, 3.9)
            ),
            80.0
        );
    }
    public static final Recipe QUARTZ_SAND = new Recipe(MachineTypes.MACERATOR, 8.0, new ItemStack(Items.SAND), new ItemStack(Items.QUARTZ_SAND), 10.0);
    public static final Recipe RESISTOR; static {
        RESISTOR = new Recipe(
            MachineTypes.CIRCUIT_ASSEMBLER,
            MachineConfiguration.ProgrammedCircuitThree,
            16.0,
            List.of(
                new ItemStack(Items.COAL_DUST),
                new ItemStack(Items.FINE_COPPER_WIRE, 4),
                new ItemStack(Items.ONE_COPPER_WIRE, 4),
                new ItemStack(Items.REFINED_GLUE, 2*0.144)
            ),
            List.of(
                new ItemStack(Items.RESISTOR, 4)
            ),
            16.0
        );
    }

    //of^5 nothing
    public static final Recipe CALCITE_DUST; static {
        CALCITE_DUST = new Recipe(
                MachineTypes.MIXER,
                MachineConfiguration.ProgrammedCircuitTwo,
                16.0,
                List.of(
                        new ItemStack(Items.CALCITE_DUST, 2),
                        new ItemStack(Items.CLAY_DUST, 1),
                        new ItemStack(Items.STONE_DUST, 1),
                        new ItemStack(Items.QUARTZ_SAND, 1),
                        new ItemStack(Items.WATER, 2)
                ),
                List.of(
                        new ItemStack(Items.WET_CONCRETE, 2.304)
                ),
                5.0
        );
    }
    public static final Recipe GLASS_DUST; static {
        GLASS_DUST = new Recipe(
            MachineTypes.MIXER,
            MachineConfiguration.ProgrammedCircuitFour,
            8.0,
            List.of(
                new ItemStack(Items.FLINT_DUST),
                new ItemStack(Items.QUARTZ_SAND, 16)
            ),
            List.of(
                new ItemStack(Items.GLASS_DUST, 16)
            ),
            40.0
        );
    }
    public static final Recipe MOLTEN_RED_ALLOY = new Recipe(MachineTypes.FLUID_EXTRACTOR, 30.0, new ItemStack(Items.RED_ALLOY_INGOT), new ItemStack(Items.MOLTEN_RED_ALLOY), 1.2);
    public static final Recipe OBSIDIAN_DUST = new Recipe(MachineTypes.MACERATOR, 4.0, new ItemStack(Items.OBSIDIAN), new ItemStack(Items.OBSIDIAN_DUST, 12), 9.0*1.1);
    public static final Recipe ONE_RED_ALLOY_WIRE = new Recipe(MachineTypes.WIREMILL, MachineConfiguration.ProgrammedCircuitOne, 4.0, new ItemStack(Items.RED_ALLOY_INGOT), new ItemStack(Items.ONE_RED_ALLOY_WIRE, 2), 5.0);
    public static final Recipe OXYGEN_GAS; static {
        OXYGEN_GAS = new Recipe(
            MachineTypes.FLUID_TANK,
            0.0,
            List.of(
                new ItemStack(Items.OXYGEN_CELL)
            ),
            List.of(
                new ItemStack(Items.EMPTY_CELL),
                new ItemStack(Items.OXYGEN_GAS)
            ),
            0.1
        );
    }
    public static final Recipe SMOOTH_SANDSTONE = new Recipe(MachineTypes.ASSEMBLER, MachineConfiguration.ProgrammedCircuitTwentyThree, 16.0, new ItemStack(Items.SANDSTONE), new ItemStack(Items.SMOOTH_SANDSTONE), 2.5);

    //of^6 nothing
    public static final Recipe ANNEALED_COPPER_INGOT; static {
        ANNEALED_COPPER_INGOT = new Recipe(
            MachineTypes.MIXER,
            MachineConfiguration.ProgrammedCircuitFour,
            3.0*30.0,
            List.of(
                new ItemStack(Items.COPPER_INGOT),
                new ItemStack(Items.OXYGEN_GAS, 63.0/1000.0)
            ),
            List.of(
                new ItemStack(Items.ANNEALED_COPPER_INGOT, 1)
            ),
            3.15
        );
    }
    public static final Recipe ELECTROLYZED_OBSIDIAN_DUST; static {
        ELECTROLYZED_OBSIDIAN_DUST = new Recipe(
            MachineTypes.ELECTROLYZER,
            90.0,
            List.of(
                new ItemStack(Items.OBSIDIAN_DUST, 12)
            ),
            List.of(
                new ItemStack(Items.MAGNESIUM_DUST, 1),
                new ItemStack(Items.IRON_DUST, 1),
                new ItemStack(Items.RAW_SILICON_DUST, 2),
                new ItemStack(Items.OXYGEN_GAS, 8)
            ),
            12.0
        );
    }
    public static final Recipe GLASS_TUBE = new Recipe(MachineTypes.ALLOY_SMELTER, MachineConfiguration.MoldBall, 16.0, new ItemStack(Items.GLASS_DUST), new ItemStack(Items.GLASS_TUBE), 6.0);
    public static final Recipe IRON_STEEL_INGOT; static {
        IRON_STEEL_INGOT = new Recipe(
            MachineTypes.ELECTRIC_BLAST_FURNACE,
            MachineConfiguration.ProgrammedCircuitEleven,
            120.0,
            List.of(
                new ItemStack(Items.IRON_DUST),
                new ItemStack(Items.OXYGEN_GAS)
            ),
            List.of(
                new ItemStack(Items.STEEL_INGOT, 1),
                new ItemStack(Items.ASHES, 1.0/9.0)
            ),
            25.0
        );
    }
    public static final Recipe CHISELED_SANDSTONE = new Recipe(MachineTypes.LASER_ENGRAVER, MachineConfiguration.GlassLens, 16.0, new ItemStack(Items.SMOOTH_SANDSTONE), new ItemStack(Items.CHISELED_SANDSTONE), 2.5);
    public static final Recipe WROUGHT_IRON_INGOT; static {
        WROUGHT_IRON_INGOT = new Recipe(
            MachineTypes.ARC_FURNACE,
            90.0,
            List.of(
                new ItemStack(Items.IRON_DUST),
                new ItemStack(Items.OXYGEN_GAS, 56.0/1000.0)
            ),
            List.of(
                new ItemStack(Items.WROUGHT_IRON_INGOT, 1)
            ),
            2.8
        );
    }

    //of^7 nothing
    public static final Recipe REDSTONE_ALLOY_DUST; static {
            REDSTONE_ALLOY_DUST = new Recipe(
                MachineTypes.MIXER,
                MachineConfiguration.ProgrammedCircuitTwentyTwo,
                8.0,
                List.of(
                    new ItemStack(Items.REDSTONE_DUST),
                    new ItemStack(Items.RAW_SILICON_DUST),
                    new ItemStack(Items.COAL_DUST)
                ),
                List.of(
                    new ItemStack(Items.REDSTONE_ALLOY_DUST, 3)
                ),
                5
            );
    }
    public static final Recipe ONE_ANNEALED_COPPER_WIRE = new Recipe(MachineTypes.WIREMILL, MachineConfiguration.ProgrammedCircuitOne, 4.0, new ItemStack(Items.ANNEALED_COPPER_INGOT), new ItemStack(Items.ONE_ANNEALED_COPPER_WIRE, 2), 5.0);
    public static final Recipe WROUGHT_IRON_DUST = new Recipe(MachineTypes.MACERATOR, MachineConfiguration.ProgrammedCircuitEleven, 4.0, new ItemStack(Items.WROUGHT_IRON_INGOT), new ItemStack(Items.WROUGHT_IRON_DUST), 2.8);

    //of^8 nothing
    public static final Recipe REDSTONE_ALLOY_INGOT; static {
        REDSTONE_ALLOY_INGOT = new Recipe(
            MachineTypes.ELECTRIC_BLAST_FURNACE,
            MachineConfiguration.ProgrammedCircuitEleven,
            120.0,
            List.of(
                new ItemStack(Items.REDSTONE_ALLOY_DUST),
                new ItemStack(Items.OXYGEN_GAS)
            ),
            List.of(
                new ItemStack(Items.REDSTONE_ALLOY_INGOT)
            ),
            40.0
        );
    }
    public static final Recipe WROUGHT_STEEL_INGOT; static {
        WROUGHT_STEEL_INGOT = new Recipe(
            MachineTypes.ELECTRIC_BLAST_FURNACE,
            MachineConfiguration.ProgrammedCircuitEleven,
            120.0,
            List.of(
                new ItemStack(Items.WROUGHT_IRON_DUST),
                new ItemStack(Items.OXYGEN_GAS)
            ),
            List.of(
                new ItemStack(Items.STEEL_INGOT, 1),
                new ItemStack(Items.ASHES, 1.0/9.0)
            ),
            5.0
        );
    }

    //of^9 nothing
    public static final Recipe EXTRUDER_STEEL_ROD = new Recipe(MachineTypes.EXTRUDER, MachineConfiguration.ExtrudeShapeRod, 90.0, new ItemStack(Items.STEEL_INGOT), new ItemStack(Items.STEEL_ROD, 2), 5.6);
    public static final Recipe LATHE_STEEL_ROD; static {
        LATHE_STEEL_ROD = new Recipe(
            MachineTypes.LATHE,
            16.0,
            List.of(
                new ItemStack(Items.STEEL_INGOT)
            ),
            List.of(
                new ItemStack(Items.STEEL_ROD, 1),
                new ItemStack(Items.SMALL_PILE_OF_STEEL_DUST, 2)
            ),
            14.0
        );
    }
    public static final Recipe MOLTEN_REDSTONE_ALLOY = new Recipe(MachineTypes.FLUID_EXTRACTOR, 36.0, new ItemStack(Items.REDSTONE_ALLOY_INGOT), new ItemStack(Items.MOLTEN_REDSTONE_ALLOY), 1.2);

    //of^10 nothing
    public static final Recipe SMALL_PILED_STEEL_DUST = new Recipe(MachineTypes.AUTO_WORKBENCH, 16.0, new ItemStack(Items.SMALL_PILE_OF_STEEL_DUST, 4), new ItemStack(Items.STEEL_DUST, 1), 3.2);
    public static final Recipe STEEL_STEEL_INGOT; static {
        STEEL_STEEL_INGOT = new Recipe(
            MachineTypes.ELECTRIC_BLAST_FURNACE,
            MachineConfiguration.ProgrammedCircuitOne,
            120.0,
            List.of(
                new ItemStack(Items.STEEL_DUST),
                new ItemStack(Items.OXYGEN_GAS)
            ),
            List.of(
                new ItemStack(Items.STEEL_INGOT)
            ),
            50.0
        );
    }
    public static final Recipe MOLTEN_RED_ALLOY_VACUUM_TUBE; static {
        MOLTEN_RED_ALLOY_VACUUM_TUBE = new Recipe(
            MachineTypes.ASSEMBLER,
            MachineConfiguration.ProgrammedCircuitFive,
            8.0,
            List.of(
                new ItemStack(Items.GLASS_TUBE, 4),
                new ItemStack(Items.ONE_COPPER_WIRE, 4),
                new ItemStack(Items.STEEL_ROD, 4),
                new ItemStack(Items.MOLTEN_RED_ALLOY, 0.5)
            ),
            List.of(
                new ItemStack(Items.VACUUM_TUBE, 4)
            ),
            8.0
        );
    }
    public static final Recipe MOLTEN_REDSTONE_ALLOY_VACUUM_TUBE; static {
        MOLTEN_REDSTONE_ALLOY_VACUUM_TUBE = new Recipe(
            MachineTypes.ASSEMBLER,
            MachineConfiguration.ProgrammedCircuitFive,
            8.0,
            List.of(
                new ItemStack(Items.GLASS_TUBE, 4),
                new ItemStack(Items.ONE_COPPER_WIRE, 4),
                new ItemStack(Items.STEEL_ROD, 4),
                new ItemStack(Items.MOLTEN_REDSTONE_ALLOY, 0.5)
            ),
            List.of(
                new ItemStack(Items.VACUUM_TUBE, 8)
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
                new ItemStack(Items.GLASS_TUBE, 2),
                new ItemStack(Items.ONE_COPPER_WIRE, 4),
                new ItemStack(Items.STEEL_ROD, 4),
                new ItemStack(Items.MOLTEN_REDSTONE, 1)
            ),
            List.of(
                new ItemStack(Items.VACUUM_TUBE, 2)
            ),
            8.0
        );
    }

    //of^11 nothing
    public static final Recipe ELECTRONIC_CIRCUIT; static {
        ELECTRONIC_CIRCUIT = new Recipe(
            MachineTypes.ASSEMBLER,
            16.0,
            List.of(
                new ItemStack(Items.CIRCUIT_BOARD, 1),
                new ItemStack(Items.RESISTOR, 2),
                new ItemStack(Items.ONE_RED_ALLOY_WIRE, 2),
                new ItemStack(Items.VACUUM_TUBE, 2),
                new ItemStack(Items.MOLTEN_LEAD, 2*0.144)
            ),
            List.of(
                new ItemStack(Items.ELECTRIC_CIRCUIT, 1)
            ),
            10.0
        );
    }
}
