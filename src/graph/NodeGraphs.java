package graph;

import items.Item;
import items.minecraft.GTNH.GregTech;
import items.minecraft.GTNH.IndustrialCraft;
import items.minecraft.GTNH.Vanilla;
import recipes.Recipe;
import recipes.minecraft.GTNH.GregTechRecipes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NodeGraphs {
    public static class dividedNodeGraph {
        public final Item product;
        public final List<Item> subGraphHeads;
        public final HashMap<Item, Recipe> forcedRecipes;

        public dividedNodeGraph(Item product) {
            this.product = product;
            this.subGraphHeads = new ArrayList<Item>();
            this.forcedRecipes = new HashMap<Item, Recipe>();
        }
        public dividedNodeGraph(Item product, List<Item> subGraphHeads) {
            this.product = product;
            this.subGraphHeads = subGraphHeads;
            this.forcedRecipes = new HashMap<Item, Recipe>();
        }
        public dividedNodeGraph(Item product, List<Item> subGraphHeads, HashMap<Item, Recipe> forcedRecipes) {
            this.product = product;
            this.subGraphHeads = subGraphHeads;
            this.forcedRecipes = forcedRecipes;
        }
    }

    public static final dividedNodeGraph CETANE_BOOSTED_DIESEL; static {
        final List<Item> CBD_SUB_GRAPHS; {
            CBD_SUB_GRAPHS = new ArrayList<>();

            CBD_SUB_GRAPHS.add(GregTech.ACETIC_ACID_CELL);
            CBD_SUB_GRAPHS.add(GregTech.ETHANOL);
            CBD_SUB_GRAPHS.add(GregTech.FISH_OIL_CELL);
            CBD_SUB_GRAPHS.add(GregTech.HYDROGEN_CELL);
            CBD_SUB_GRAPHS.add(GregTech.NITRIC_ACID);
            CBD_SUB_GRAPHS.add(GregTech.NITROGEN_GAS);
            CBD_SUB_GRAPHS.add(GregTech.OXYGEN_CELL);
            CBD_SUB_GRAPHS.add(GregTech.SULFURIC_ACID);
            CBD_SUB_GRAPHS.add(GregTech.TINY_PILE_OF_SODIUM_HYDROXIDE_DUST);
        }
        final HashMap<Item, Recipe> CBD_RECIPES; {
            CBD_RECIPES = new HashMap<>();

            CBD_RECIPES.put(GregTech.ETHYLENE, GregTechRecipes.WOOD_GAS_ETHYLENE);
            CBD_RECIPES.put(GregTech.HYDROGEN_CELL, GregTechRecipes.ELECTROLYZED_PHOSPHORIC_ACID);
            CBD_RECIPES.put(GregTech.SALT, GregTechRecipes.BATHED_SALTY_ROOT);
        }

        CETANE_BOOSTED_DIESEL = new dividedNodeGraph(GregTech.CETANE_BOOSTED_DIESEL, CBD_SUB_GRAPHS, CBD_RECIPES);
    }
    public static final dividedNodeGraph ELECTRONIC_CIRCUIT; static {
        final List<Item> CIRCUIT_SUB_GRAPHS; {
            CIRCUIT_SUB_GRAPHS = new ArrayList<>();

            CIRCUIT_SUB_GRAPHS.add(GregTech.CIRCUIT_BOARD);
            CIRCUIT_SUB_GRAPHS.add(GregTech.RESISTOR);
            CIRCUIT_SUB_GRAPHS.add(GregTech.ONE_RED_ALLOY_WIRE);
                CIRCUIT_SUB_GRAPHS.add(Vanilla.REDSTONE_DUST);
            CIRCUIT_SUB_GRAPHS.add(GregTech.STEEL_ROD);
            CIRCUIT_SUB_GRAPHS.add(GregTech.VACUUM_TUBE);
                CIRCUIT_SUB_GRAPHS.add(GregTech.MOLTEN_REDSTONE_ALLOY);
                CIRCUIT_SUB_GRAPHS.add(GregTech.GLASS_TUBE);
                CIRCUIT_SUB_GRAPHS.add(GregTech.OXYGEN);
            CIRCUIT_SUB_GRAPHS.add(GregTech.MOLTEN_LEAD);
        }
        final HashMap<Item, Recipe> CIRCUIT_LOOP_RECIPES; {
            CIRCUIT_LOOP_RECIPES = new HashMap<>();

            CIRCUIT_LOOP_RECIPES.put(Vanilla.SAND, GregTechRecipes.SAND);
            CIRCUIT_LOOP_RECIPES.put(GregTech.RAW_SILICON_DUST, GregTechRecipes.PERFECT_RAW_SILICON_DUST);
            CIRCUIT_LOOP_RECIPES.put(GregTech.MAGNESIUM_DUST, GregTechRecipes.ELECTROLYZED_MAGNESIA);
        }

        ELECTRONIC_CIRCUIT = new dividedNodeGraph(IndustrialCraft.ELECTRONIC_CIRCUIT, CIRCUIT_SUB_GRAPHS, CIRCUIT_LOOP_RECIPES);
    }
    public static final dividedNodeGraph GOOD_INTEGRATED_CIRCUIT_MV; static {
        final List<Item> CIRCUIT_SUB_GRAPHS; {
            CIRCUIT_SUB_GRAPHS = new ArrayList<>();

            CIRCUIT_SUB_GRAPHS.add(GregTech.GOOD_CIRCUIT_BOARD);
            CIRCUIT_SUB_GRAPHS.add(GregTech.PHENOLIC_CIRCUIT_BOARD);
            CIRCUIT_SUB_GRAPHS.add(GregTech.INTEGRATED_LOGIC_CIRCUIT);
            CIRCUIT_SUB_GRAPHS.add(GregTech.INTEGRATED_LOGIC_CIRCUIT_LV);
            CIRCUIT_SUB_GRAPHS.add(GregTech.WAFER);
            CIRCUIT_SUB_GRAPHS.add(GregTech.SILICON_SOLAR_GRADE_DUST);
            CIRCUIT_SUB_GRAPHS.add(GregTech.RESISTOR);
            CIRCUIT_SUB_GRAPHS.add(GregTech.DIODE);
            CIRCUIT_SUB_GRAPHS.add(GregTech.MOLTEN_POLYETHYLENE);
            CIRCUIT_SUB_GRAPHS.add(GregTech.TIN_BOLT);
            CIRCUIT_SUB_GRAPHS.add(GregTech.MOLTEN_LEAD);
        }
        final HashMap<Item, Recipe> CIRCUIT_LOOP_RECIPES; {
            CIRCUIT_LOOP_RECIPES = new HashMap<>();

            CIRCUIT_LOOP_RECIPES.put(Vanilla.SAND, GregTechRecipes.SAND);
            CIRCUIT_LOOP_RECIPES.put(GregTech.SILICON_SOLAR_GRADE_DUST, GregTechRecipes.SILICON_SOLAR_GRADE_DUST);
            CIRCUIT_LOOP_RECIPES.put(GregTech.RAW_SILICON_DUST, GregTechRecipes.PERFECT_RAW_SILICON_DUST);
            CIRCUIT_LOOP_RECIPES.put(GregTech.MAGNESIUM_DUST, GregTechRecipes.ELECTROLYZED_MAGNESIA);
            CIRCUIT_LOOP_RECIPES.put(GregTech.HYDROCHLORIC_ACID, GregTechRecipes.HYDROCHLORIC_ACID);
        }

        GOOD_INTEGRATED_CIRCUIT_MV = new dividedNodeGraph(GregTech.GOOD_INTEGRATED_CIRCUIT_MV, CIRCUIT_SUB_GRAPHS, CIRCUIT_LOOP_RECIPES);
    }
    public static final dividedNodeGraph INTEGRATED_LOGIC_CIRCUIT_LV; static {
        final List<Item> CIRCUIT_SUB_GRAPHS; {
            CIRCUIT_SUB_GRAPHS = new ArrayList<>();

            CIRCUIT_SUB_GRAPHS.add(GregTech.CIRCUIT_BOARD);
            CIRCUIT_SUB_GRAPHS.add(GregTech.INTEGRATED_LOGIC_CIRCUIT);
                CIRCUIT_SUB_GRAPHS.add(GregTech.WAFER);
                //CIRCUIT_SUB_GRAPHS.add(GregTech.SILICON_SOLAR_GRADE_DUST);
            CIRCUIT_SUB_GRAPHS.add(GregTech.RESISTOR);
            CIRCUIT_SUB_GRAPHS.add(GregTech.DIODE);
                CIRCUIT_SUB_GRAPHS.add(GregTech.MOLTEN_POLYETHYLENE);
            CIRCUIT_SUB_GRAPHS.add(GregTech.FINE_COPPER_WIRE);
            CIRCUIT_SUB_GRAPHS.add(GregTech.TIN_BOLT);
            CIRCUIT_SUB_GRAPHS.add(GregTech.MOLTEN_LEAD);
        }
        final HashMap<Item, Recipe> CIRCUIT_LOOP_RECIPES; {
            CIRCUIT_LOOP_RECIPES = new HashMap<>();

            CIRCUIT_LOOP_RECIPES.put(Vanilla.SAND, GregTechRecipes.SAND);
            CIRCUIT_LOOP_RECIPES.put(GregTech.SILICON_SOLAR_GRADE_DUST, GregTechRecipes.SILICON_SOLAR_GRADE_DUST);
            CIRCUIT_LOOP_RECIPES.put(GregTech.RAW_SILICON_DUST, GregTechRecipes.PERFECT_RAW_SILICON_DUST);
            CIRCUIT_LOOP_RECIPES.put(GregTech.MAGNESIUM_DUST, GregTechRecipes.ELECTROLYZED_MAGNESIA);
        }

        INTEGRATED_LOGIC_CIRCUIT_LV = new dividedNodeGraph(GregTech.INTEGRATED_LOGIC_CIRCUIT_LV, CIRCUIT_SUB_GRAPHS, CIRCUIT_LOOP_RECIPES);
    }
    public static final dividedNodeGraph LIGHT_CONCRETE; static {
        LIGHT_CONCRETE = new dividedNodeGraph(GregTech.LIGHT_CONCRETE);
    }
    public static final dividedNodeGraph POLYTETRAFLUOROETHYLENE_BAR; static {
        final List<Item> ptfeSubGraphs; {
            ptfeSubGraphs = new ArrayList<>();

            ptfeSubGraphs.add(GregTech.HYDROGEN_CELL);
            ptfeSubGraphs.add(GregTech.CHLOROFORM_CELL);
            ptfeSubGraphs.add(GregTech.CHLORINE);
            ptfeSubGraphs.add(GregTech.FLUORINE);
            ptfeSubGraphs.add(GregTech.OXYGEN);
        }

        POLYTETRAFLUOROETHYLENE_BAR = new dividedNodeGraph(GregTech.POLYTETRAFLUOROETHYLENE_BAR, ptfeSubGraphs);
    }
    public static final dividedNodeGraph TNT; static {
        final List<Item> tntSubGraphs; {
            tntSubGraphs = new ArrayList<>();

            tntSubGraphs.add(GregTech.SULFURIC_ACID);
                tntSubGraphs.add(GregTech.OXYGEN);
                tntSubGraphs.add(GregTech.OXYGEN_CELL);
            tntSubGraphs.add(GregTech.GELLED_TOLUENE);
        }

        TNT = new dividedNodeGraph(Vanilla.TNT, tntSubGraphs);
    }
    public static final dividedNodeGraph VACUUM_TUBE; static {
        final List<Item> SUB_GRAPHS; {
            SUB_GRAPHS = new ArrayList<>();
            SUB_GRAPHS.add(GregTech.ONE_COPPER_WIRE);
            SUB_GRAPHS.add(GregTech.GLASS_TUBE);
            SUB_GRAPHS.add(GregTech.MOLTEN_REDSTONE_ALLOY);
            SUB_GRAPHS.add(GregTech.RAW_SILICON_DUST);
            SUB_GRAPHS.add(GregTech.STEEL_ROD);
        }

        final HashMap<Item, Recipe> RECIPES; {
            RECIPES = new HashMap<>();

            RECIPES.put(GregTech.VACUUM_TUBE, GregTechRecipes.MOLTEN_REDSTONE_ALLOY_VACUUM_TUBE);
            RECIPES.put(GregTech.RAW_SILICON_DUST, GregTechRecipes.PERFECT_RAW_SILICON_DUST);
        }

        VACUUM_TUBE = new dividedNodeGraph(GregTech.VACUUM_TUBE, SUB_GRAPHS, RECIPES);
    }
    public static final dividedNodeGraph WAFER; static {
        final List<Item> SUB_GRAPHS = new ArrayList<>();
        final HashMap<Item, Recipe> RECIPES; {
            RECIPES = new HashMap<>();

            RECIPES.put(GregTech.RAW_SILICON_DUST, GregTechRecipes.PERFECT_RAW_SILICON_DUST);
            RECIPES.put(GregTech.SILICON_SOLAR_GRADE_DUST, GregTechRecipes.SILICON_SOLAR_GRADE_DUST);
        }

        WAFER = new dividedNodeGraph(GregTech.WAFER, SUB_GRAPHS, RECIPES);
    }
}
