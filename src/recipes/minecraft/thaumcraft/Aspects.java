package recipes.minecraft.thaumcraft;

import items.ItemStack;
import items.Items;
import recipes.Recipe;

import java.util.List;

import static items.minecraft.thaumcraft.Aspects.*;
import static machines.MachineTypes.*;

public class Aspects {
    private static Recipe getAlchemicalFurnaceRecipe(ItemStack item, ItemStack aspect) {
        return getAlchemicalFurnaceRecipe(List.of(item), List.of(aspect));
    }
    private static Recipe getAlchemicalFurnaceRecipe(ItemStack item, List<ItemStack> aspects) {
        return getAlchemicalFurnaceRecipe(List.of(item), aspects);
    }
    private static Recipe getAlchemicalFurnaceRecipe(List<ItemStack> item, List<ItemStack> aspects) {
        return new Recipe(ALCHEMICAL_FURNACE, 0.0, item, aspects, 5.0);
    }

    public static final Recipe CHISELED_STONE_BRICKS = getAlchemicalFurnaceRecipe(new ItemStack(Items.CHISELED_STONE_BRICKS), new ItemStack(items.minecraft.thaumcraft.Aspects.ORDO));
    public static final Recipe SUGAR_CANE; static {
        SUGAR_CANE = getAlchemicalFurnaceRecipe(
            new ItemStack(Items.SUGAR_CANE),
            List.of(
                new ItemStack(AER), new ItemStack(AQUA), new ItemStack(HERBA)
            )
        );
    }
    public static final Recipe NETHER_COBBLE_SLAB = getAlchemicalFurnaceRecipe(new ItemStack(Items.NETHER_COBBLESTONE_SLAB), new ItemStack(IGNIS));
    public static final Recipe CHISELED_SANDSTONE; static {
        CHISELED_SANDSTONE = getAlchemicalFurnaceRecipe(
            new ItemStack(Items.CHISELED_SANDSTONE),
            List.of(
                new ItemStack(PERDITIO, 3),
                new ItemStack(TERRA, 2),
                new ItemStack(PRAECANTATIO, 1)
            )
        );
    }
}
