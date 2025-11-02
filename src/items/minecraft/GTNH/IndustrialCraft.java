package items.minecraft.GTNH;

import items.Item;

import static register.Namespaces.INDUSTRIAL_CRAFT_TWO;

public class IndustrialCraft {
    private static Item getIndustrialCraftItem(String name) {
        return new Item(INDUSTRIAL_CRAFT_TWO, name);
    }

    public static final Item ELECTRONIC_CIRCUIT = getIndustrialCraftItem("electronic_circuit");
    public static final Item PLANT_BALL = getIndustrialCraftItem("plant_ball");
    public static final Item STICKY_RESIN = getIndustrialCraftItem("sticky_resin");
    public static final Item WATER_CELL = getIndustrialCraftItem("water_cell");
}
