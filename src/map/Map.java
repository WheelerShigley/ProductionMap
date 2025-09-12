package map;

import recipes.Recipe;

public class Map {
    public final MachineNode head;

    public Map(Recipe recipe) {
        this.head = new MachineNode(recipe);
        MapHelper.recursivelyGenerateRecipesFromPreferredSources(head);
    }
    public Map(Recipe recipe, double uptime) {
        this.head = new MachineNode(recipe);
        MapHelper.recursivelyGenerateRecipesFromPreferredSources(head);
        head.setUptime(uptime);
        MapHelper.calculateUptimes(this);
    }
}