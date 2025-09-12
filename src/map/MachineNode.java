package map;

import items.ItemStack;
import recipes.Recipe;

import java.util.List;

public class MachineNode {
    Recipe recipe;
    List<MachineNode> sources;
    public double calculated_uptime = -1.0f;

    public MachineNode(Recipe recipe) {
        this.recipe = recipe;
        this.sources = List.of();
    }
    public MachineNode(Recipe recipe, List<MachineNode> sources) {
        this.recipe = recipe;
        this.sources = sources;
    }

    public void setUptime(double uptime) {
        this.calculated_uptime = uptime;
    }

    public void setSources(List<MachineNode> sources) {
        this.sources = sources;
    }

    private String ItemStackListToString(List<ItemStack> items) {
        StringBuilder ItemStackListStringBuilder = new StringBuilder();

        ItemStack itemStack;
        for(int index = 0; index < items.size(); index++) {
            itemStack = items.get(index);
            ItemStackListStringBuilder
                .append( Math.round( 10000.0*itemStack.quantity() )/10000.0 ).append(' ')
                .append( itemStack.item().toString() )
            ;
            if( index < items.size()-1 ) {
                ItemStackListStringBuilder.append(" + ");
            }
        }

        return ItemStackListStringBuilder.toString();
    }

    @Override
    public String toString() {
        if(this.recipe == null) {
            return "Recipe is \"null\".";
        }

        StringBuilder nodeStringBuilder = new StringBuilder();

        String inputs  = ItemStackListToString( recipe.getInputsAsItemStacks()  );
        String outputs = ItemStackListToString( recipe.outputs );

        nodeStringBuilder.append(inputs).append(" = ").append(outputs);
        return nodeStringBuilder.toString();
    }
}