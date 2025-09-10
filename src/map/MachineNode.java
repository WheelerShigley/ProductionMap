package map;

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
}