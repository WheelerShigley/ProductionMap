package items.minecraft.GTNH;

import items.Item;

import static register.Namespaces.TINKERS_CONSTRUCT;

public class ThinkersConstruct {
    private static Item getTinkersItem(String name) {
        return new Item(TINKERS_CONSTRUCT, name);
    }

    public static final Item IRON_OREBERRY = getTinkersItem( "iron_oreberry");
}
