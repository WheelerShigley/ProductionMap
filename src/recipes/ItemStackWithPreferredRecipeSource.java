package recipes;

import items.Item;
import items.ItemStack;
import items.Items;
import machines.Machines;

public class ItemStackWithPreferredRecipeSource {
    public final ItemStack itemStack;
    public final Recipe preferredRecipeSource;

    public ItemStackWithPreferredRecipeSource(ItemStack itemStack, Recipe preferredSource) {
        if( isValidSource(itemStack.item(), preferredSource) ) {
            this.itemStack = itemStack;
            this.preferredRecipeSource = preferredSource;
        } else {
            this.itemStack = null;
            this.preferredRecipeSource = null;
        }
    }

    private boolean isValidSource(Item item, Recipe source) {
        if(
            source == null || item.equals(Items.NOTHING)
            || source.equals(Recipes.DUMMY) || source.machine().equals(Machines.PLAYER)
        ) {
            return true;
        }

        for( ItemStack output : source.outputs() ) {
            if( output.item().equals(item) ) {
                return true;
            }
        }

        return false;
    }
}
