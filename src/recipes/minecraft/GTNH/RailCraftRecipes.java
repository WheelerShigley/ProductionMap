package recipes.minecraft.GTNH;

import items.ItemStack;
import items.minecraft.GTNH.GregTech;
import machines.MachineTypes;
import recipes.Recipe;

public class RailCraftRecipes {
    public static final Recipe WATERTANK_WATER = new Recipe(MachineTypes.WATERTANK, 0.0, new ItemStack(GregTech.WATER, 0.8/1000.0), 1.0);
}
