package recipes.minecraft.GTNH.thaumcraft;

import items.ItemStack;
import items.minecraft.GTNH.GregTech;
import machines.MachineTypes;
import recipes.Recipe;

public class ThaumcraftRecipes {
    public ThaumcraftRecipes() {
        new AspectsRecipes();
        new CauldronRecipes();
    }

    public static final Recipe THIRSTYTANK_WATER = new Recipe(MachineTypes.THIRSTYTANK, 0.0, new ItemStack(GregTech.WATER, 1.0), 31.0/20.0);
}
