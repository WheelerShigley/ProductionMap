package recipes.minecraft.GTNH;

import items.ItemStack;
import items.minecraft.GTNH.GregTech;
import items.minecraft.GTNH.IndustrialCraft;
import machines.MachineTypes;
import recipes.Recipe;

import java.util.List;

public class IndustrialCraftRecipes {
    public static final Recipe ELECTRONIC_CIRCUIT; static {
        ELECTRONIC_CIRCUIT = new Recipe(
                MachineTypes.ASSEMBLER,
                16.0,
                List.of(
                        new ItemStack(GregTech.CIRCUIT_BOARD),
                        new ItemStack(GregTech.RESISTOR, 2),
                        new ItemStack(GregTech.ONE_RED_ALLOY_WIRE, 2),
                        new ItemStack(GregTech.VACUUM_TUBE, 2),
                        new ItemStack(GregTech.MOLTEN_LEAD, 2*0.144)
                ),
                List.of(
                        new ItemStack(IndustrialCraft.ELECTRONIC_CIRCUIT)
                ),
                10.0
        );
    }
}
