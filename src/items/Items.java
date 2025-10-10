package items;

import items.minecraft.GTNH.GregTech;
import items.minecraft.GTNH.IndustrialCraft;
import items.minecraft.GTNH.Vanilla;
import items.minecraft.GTNH.Thaumcraft;
import register.Registered;

import java.util.ArrayList;
import java.util.List;

import static register.Namespaces.*;

public class Items extends Registered<Item> {
    private static Items instance;
    public static final List<Item> registry = new ArrayList<>();
    public Items() {
        super("Items", registry);
        instance = this;
    }

    public static boolean register(Item item) {
        if(instance == null) {
            instance = new Items();
            new Vanilla();
            new Thaumcraft();
            new IndustrialCraft();
            new GregTech();
        }
        return instance.registerInstance(item);
    }

    public static final Item MANUAL = new Item(PLAYER,"manual");
    public static final Item NOTHING = new Item("custom","nothing");
    public static final Item ANY_WOOD = new Item("custom","any_log");

    //misc (temporary location)
    public static final Item DRIED_DIRT = new Item(BIOMES_O_PLENTY,"dried_dirt");
    public static final Item GLOW_FLOWER = new Item(PAMS_NETHER,"glow_flower");
    public static final Item MARBLE = new Item(CHISEL,"marble");
    public static final Item NETHER_COBBLESTONE = new Item(RAILCRAFT,"nether_cobblestone");
    public static final Item NETHER_COBBLESTONE_SLAB = new Item(RAILCRAFT,"nether_cobblestone_slab");
    public static final Item SALIS_MUNDUS = new Item(THAUMCRAFT,"salis_mundus");
}
