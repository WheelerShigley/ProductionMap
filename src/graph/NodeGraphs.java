package graph;

import items.Item;
import items.ItemStack;
import items.minecraft.GTNH.GregTech;
import items.minecraft.GTNH.IndustrialCraft;
import items.minecraft.GTNH.Vanilla;
import recipes.Recipe;
import recipes.Recipes;

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

    public static final dividedNodeGraph ELECTRONIC_CIRCUIT; static {
        final List<Item> CIRCUIT_SUB_GRAPHS; {
            CIRCUIT_SUB_GRAPHS = new ArrayList<>();

            CIRCUIT_SUB_GRAPHS.add(GregTech.CIRCUIT_BOARD);
            CIRCUIT_SUB_GRAPHS.add(GregTech.RESISTOR);
            CIRCUIT_SUB_GRAPHS.add(GregTech.ONE_RED_ALLOY_WIRE);
            CIRCUIT_SUB_GRAPHS.add(GregTech.STEEL_INGOT);
            CIRCUIT_SUB_GRAPHS.add(GregTech.VACUUM_TUBE);
            CIRCUIT_SUB_GRAPHS.add(GregTech.MOLTEN_LEAD);
        }
        final HashMap<Item, Recipe> CIRCUIT_LOOP_RECIPES; {
            CIRCUIT_LOOP_RECIPES = new HashMap<>();

            CIRCUIT_LOOP_RECIPES.put(Vanilla.SAND, Recipes.SAND);
            CIRCUIT_LOOP_RECIPES.put(GregTech.RAW_SILICON_DUST, Recipes.PERFECT_RAW_SILICON_DUST);
            CIRCUIT_LOOP_RECIPES.put(GregTech.MAGNESIUM_DUST, Recipes.ELECTROLYZED_MAGNESIA);
        }

        ELECTRONIC_CIRCUIT = new dividedNodeGraph(IndustrialCraft.ELECTRONIC_CIRCUIT, CIRCUIT_SUB_GRAPHS, CIRCUIT_LOOP_RECIPES);
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
}
