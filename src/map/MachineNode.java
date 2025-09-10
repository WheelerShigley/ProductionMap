package map;

import items.ItemStack;
import recipes.Recipe;

import java.util.List;

public class MachineNode {
    Recipe recipe;
    List<MachineNode> sources;

    public MachineNode(Recipe recipe) {
        this.recipe = recipe;
        this.sources = List.of();
    }
    public MachineNode(Recipe recipe, List<MachineNode> sources) {
        this.recipe = recipe;
        this.sources = sources;
    }

    private String ItemStackListToString(List<ItemStack> items) {
        StringBuilder ItemStackListStringBuilder = new StringBuilder();

        ItemStack itemStack;
        for(int index = 0; index < items.size(); index++) {
            itemStack = items.get(index);
            ItemStackListStringBuilder
                .append( itemStack.quantity() ).append(' ')
                .append( itemStack.item().toString() )
            ;
            if( index < items.size()-1 ) {
                ItemStackListStringBuilder.append(" + ");
            }
        }

        return ItemStackListStringBuilder.toString();
    }

    public String toString() {
        StringBuilder nodeStringBuilder = new StringBuilder();

        String inputs  = ItemStackListToString( recipe.inputs()  );
        String outputs = ItemStackListToString( recipe.outputs() );

        nodeStringBuilder.append(inputs).append(" = ").append(outputs);
        return nodeStringBuilder.toString();
    }
}