package recipes;

import items.ItemStack;
import items.Items;
import machines.Machines;

import java.util.Arrays;

public class Recipes {
    public static final Recipe TEST = new Recipe(
        Machines.TEST,
        Arrays.asList( new ItemStack(Items.TEST, 1) ),
        Arrays.asList( new ItemStack(Items.TEST, 1) )
    );
}
