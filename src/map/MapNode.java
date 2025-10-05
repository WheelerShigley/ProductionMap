package map;

import items.ItemStack;
import recipes.Recipe;

import java.util.List;

@Deprecated
public class MapNode {
    Recipe recipe;
    List<MapNode> sources;
    public double calculated_uptime = -1.0f;

    @Deprecated
    public MapNode(Recipe recipe) {
        this.recipe = recipe;
        this.sources = List.of();
    }
    @Deprecated
    public MapNode(Recipe recipe, List<MapNode> sources) {
        this.recipe = recipe;
        this.sources = sources;
    }

    public void setUptime(double uptime) {
        this.calculated_uptime = uptime;
    }

    public void setSources(List<MapNode> sources) {
        this.sources = sources;
    }

    private String ItemStackListToString(List<ItemStack> items) {
        StringBuilder ItemStackListStringBuilder = new StringBuilder();

        ItemStack itemStack;
        for(int index = 0; index < items.size(); index++) {
            itemStack = items.get(index);
            ItemStackListStringBuilder
                .append( Math.round( 10000.0*itemStack.quantity )/10000.0 ).append(' ')
                .append( itemStack.item.toString() )
            ;
            if( index < items.size()-1 ) {
                ItemStackListStringBuilder.append(" + ");
            }
        }

        return ItemStackListStringBuilder.toString();
    }

    public boolean equals(MapNode otherNode) {
        if(otherNode == null) {
            return false;
        }
        return otherNode.recipe == this.recipe;
    }

    @Override
    public String toString() {
        if(this.recipe == null) {
            return "Recipe is \"null\".";
        }

        StringBuilder nodeStringBuilder = new StringBuilder();

        String inputs  = ItemStackListToString(recipe.inputs );
        String outputs = ItemStackListToString(recipe.outputs);

        nodeStringBuilder.append(inputs).append(" = ").append(outputs);
        return nodeStringBuilder.toString();
    }

    public static boolean includes(List<MapNode> nodes, MapNode node) {
        for(MapNode listNode : nodes) {
            if( listNode.equals(node) ) {
                return true;
            }
        }
        return false;
    }
}