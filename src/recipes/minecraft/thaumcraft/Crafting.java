package recipes.minecraft.thaumcraft;

import items.ItemStack;
import items.Items;
import items.minecraft.thaumcraft.Aspects;
import machines.MachineType;
import machines.MachineTypes;
import recipes.Recipe;

import java.util.List;

public class Crafting {
    public static final Recipe SALIS_MUNDUS; static {
        SALIS_MUNDUS = new Recipe(
            MachineTypes.CAULDRON,
            0.0,
            List.of(
                new ItemStack(Items.SALIS_MUNDUS, 1),
                new ItemStack(Aspects.AER, 4),
                new ItemStack(Aspects.AQUA, 4),
                new ItemStack(Aspects.IGNIS, 4),
                new ItemStack(Aspects.PRAECANTATIO, 4),
                new ItemStack(Aspects.ORDO, 4),
                new ItemStack(Aspects.PERDITIO, 4),
                new ItemStack(Aspects.TERRA, 4)
            ),
            List.of(
                new ItemStack(Items.SALIS_MUNDUS, 2)
            ),
            5.0 //TODO
        );
    }
}
