package recipes.minecraft.GTNH.thaumcraft;

import items.ItemStack;
import items.minecraft.GTNH.Thaumcraft;
import items.minecraft.GTNH.Vanilla;
import machines.MachineTypes;
import recipes.Recipe;

import java.util.List;

public class CauldronRecipes {
    public static final Recipe SALIS_MUNDUS; static {
        SALIS_MUNDUS = new Recipe(
            MachineTypes.CAULDRON,
            0.0,
            List.of(
                new ItemStack(Thaumcraft.SALIS_MUNDUS, 1),
                new ItemStack(Thaumcraft.AER, 4),
                new ItemStack(Thaumcraft.AQUA, 4),
                new ItemStack(Thaumcraft.IGNIS, 4),
                new ItemStack(Thaumcraft.PRAECANTATIO, 4),
                new ItemStack(Thaumcraft.ORDO, 4),
                new ItemStack(Thaumcraft.PERDITIO, 4),
                new ItemStack(Thaumcraft.TERRA, 4)
            ),
            List.of(
                new ItemStack(Thaumcraft.SALIS_MUNDUS, 2)
            ),
            5.0 //TODO
        );
    }
    public static final Recipe THAUMIUM_INGOT; static {
        THAUMIUM_INGOT = new Recipe(
            MachineTypes.CAULDRON,
            0.0,
            List.of(
                new ItemStack(Vanilla.IRON_INGOT),
                new ItemStack(Thaumcraft.PRAECANTATIO, 4)
            ),
            List.of(
                new ItemStack(Thaumcraft.THAUMIUM_INGOT)
            ),
            5.0 //TODO
        );
    }
}
