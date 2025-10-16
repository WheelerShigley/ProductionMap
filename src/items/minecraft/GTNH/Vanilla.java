package items.minecraft.GTNH;

import items.Item;

import static register.Namespaces.VANILLA;

public class Vanilla {
    private static Item getVanillaItem(String name) {
        return new Item(VANILLA, name);
    }

    public static final Item CHARCOAL = getVanillaItem("charcoal");
    public static final Item CHISELED_SANDSTONE = getVanillaItem("chiseled_sandstone");
    public static final Item CHISELED_STONE_BRICKS = getVanillaItem("chiseled_stone_bricks");
    public static final Item COBBLESTONE = getVanillaItem("cobblestone");
    public static final Item DIRT = getVanillaItem("dirt");
    public static final Item FLINT = getVanillaItem("flint");
    public static final Item GLOWSTONE_DUST = getVanillaItem("glowstone_dust");
    public static final Item GRAVEL = getVanillaItem("gravel");
    public static final Item LAVA = getVanillaItem("lava");
    public static final Item NETHERRACK = getVanillaItem("netherrack");
    public static final Item OBSIDIAN = getVanillaItem("obsidian");
    public static final Item REDSTONE_DUST = getVanillaItem("redstone_dust");
    public static final Item SAND = getVanillaItem("sand");
    public static final Item SANDSTONE = getVanillaItem("sandstone");
    public static final Item SMOOTH_SANDSTONE = getVanillaItem("smooth_sandstone");
    public static final Item STONE = getVanillaItem("stone");
    public static final Item SUGAR = getVanillaItem("sugar");
    public static final Item SUGAR_CANE = getVanillaItem("sugar_cane");
}
