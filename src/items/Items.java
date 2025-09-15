package items;

import machines.Machine;
import register.Registered;

import java.util.ArrayList;
import java.util.List;

import static register.Namespaces.*;

public class Items extends Registered<Item> {
    private static Items instance;
    public static final List<Item> registry = new ArrayList<Item>();
    public Items() {
        super("Items", registry);
        instance = this;
    }

    public static boolean register(Item item) {
        if(instance == null) {
            instance = new Items();
        }
        return instance.registerInstance(item);
    }

    public static final Item MANUAL = new Item(PLAYER,"manual");
    public static final Item NOTHING = new Item(VANILLA,"nothing");

    public static final Item AIR_GAS = new Item(GREGTECH,"air");
    public static final Item ANNEALED_COPPER_INGOT = new Item(GREGTECH,"annealed_copper_ingot");
    public static final Item ANY_WOOD = new Item(VANILLA,"any_log");
    public static final Item ASHES = new Item(GREGTECH,"ashes");
    public static final Item CIRCUIT_BOARD = new Item(GREGTECH,"circuit_board");
    public static final Item COAL_DUST = new Item(GREGTECH,"coal_dust");
    public static final Item COMPRESSED_AIR = new Item(GREGTECH,"compressed_air");
    public static final Item COPPER_FOIL = new Item(GREGTECH,"copper_foil");
    public static final Item COPPER_DUST = new Item(GREGTECH,"copper_dust");
    public static final Item COPPER_INGOT = new Item(GREGTECH,"copper_ingot");
    public static final Item COBBLESTONE = new Item(VANILLA,"cobblestone");
    public static final Item ELECTRIC_CIRCUIT = new Item(INDUSTRIAL_CRAFT_TWO,"electric_circuit");
    public static final Item EMPTY_CELL = new Item(GREGTECH,"empty_cell");
    public static final Item FINE_COPPER_WIRE = new Item(GREGTECH,"fine_copper_wire");
    public static final Item FLINT = new Item(VANILLA,"flint");
    public static final Item FLINT_DUST = new Item(GREGTECH,"flint_dust");
    public static final Item GLASS_DUST = new Item(GREGTECH,"glass_dust");
    public static final Item GLASS_TUBE = new Item(GREGTECH,"glass_tube");
    public static final Item GRAVEL = new Item(VANILLA,"gravel");
    public static final Item IRON_DUST = new Item(GREGTECH,"iron_dust");
    public static final Item LAVA = new Item(VANILLA,"lava");
    public static final Item LEAD_INGOT = new Item(GREGTECH,"lead_ingot");
    public static final Item MAGNESIUM_DUST = new Item(GREGTECH,"magnesium_dust");
    public static final Item MOLTEN_LEAD = new Item(GREGTECH,"molten_lead");
    public static final Item MOLTEN_RED_ALLOY = new Item(GREGTECH,"molten_red_alloy");
    public static final Item MOLTEN_REDSTONE = new Item(GREGTECH,"molten_redstone");
    public static final Item MOLTEN_REDSTONE_ALLOY = new Item(GREGTECH,"molten_redstone_alloy");
    public static final Item NITROGEN_GAS = new Item(GREGTECH,"nitrogen");
    public static final Item OBSIDIAN = new Item(VANILLA,"obsidian");
    public static final Item OBSIDIAN_DUST = new Item(GREGTECH,"obsidian_dust");
    public static final Item ONE_ANNEALED_COPPER_WIRE = new Item(GREGTECH,"1x_annealed_copper_wire");
    public static final Item ONE_RED_ALLOY_WIRE = new Item(GREGTECH,"1x_red_alloy_wire");
    public static final Item ONE_COPPER_WIRE = new Item(GREGTECH,"1x_copper_wire");
    public static final Item OXYGEN_CELL = new Item(GREGTECH,"oxygen_cell");
    public static final Item OXYGEN_GAS = new Item(GREGTECH,"oxygen");
    public static final Item PLANT_BALL = new Item(INDUSTRIAL_CRAFT_TWO,"plant_ball");
    public static final Item QUARTZ_SAND = new Item(GREGTECH,"quartz_sand");
    public static final Item RAW_RUBBER_DUST = new Item(GREGTECH,"raw_rubber_dust");
    public static final Item RAW_SILICON_DUST = new Item(GREGTECH,"raw_silicon_dust");
    public static final Item REDSTONE_ALLOY_DUST = new Item(GREGTECH,"redstone_alloy_dust");
    public static final Item REDSTONE_ALLOY_INGOT = new Item(GREGTECH,"redstone_alloy_ingot");
    public static final Item REDSTONE_DUST = new Item(VANILLA,"redstone_dust");
    public static final Item RED_ALLOY_INGOT = new Item(GREGTECH,"red_alloy_ingot");
    public static final Item REFINED_GLUE = new Item(GREGTECH,"refined_glue");
    public static final Item RESISTOR = new Item(GREGTECH,"resistor");
    public static final Item SAND = new Item(VANILLA,"sand");
    public static final Item STEEL_INGOT = new Item(GREGTECH,"steel_ingot");
    public static final Item STEEL_ROD = new Item(GREGTECH,"steel_rod");
    public static final Item STICKY_RESIN = new Item(INDUSTRIAL_CRAFT_TWO,"sticky_resin");
    public static final Item VACUUM_TUBE = new Item(GREGTECH,"vacuum_tube");
    public static final Item WOOD_PLANK = new Item(GREGTECH,"wood_plank");
    public static final Item WOOD_PULP = new Item(GREGTECH,"wood_pulp");
    public static final Item WROUGHT_IRON_DUST = new Item(GREGTECH,"wrought_iron_dust");
    public static final Item WROUGHT_IRON_INGOT = new Item(GREGTECH,"wrought_iron_ingot");
}
