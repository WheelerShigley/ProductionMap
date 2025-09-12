package map;

import machines.Voltage;
import recipes.Recipe;

import java.util.HashMap;

import static map.MapHelper.recursivelyGetAveragePowerConsumption;

public class Map {
    public final MachineNode head;
    HashMap<Voltage, Double> averagePowerConsumption;

    public Map(Recipe recipe) {
        this.head = new MachineNode(recipe);
        MapHelper.recursivelyGenerateRecipesFromPreferredSources(head);
    }
    public Map(Recipe recipe, double uptime) {
        this.head = new MachineNode(recipe);
        MapHelper.recursivelyGenerateRecipesFromPreferredSources(head);
        head.setUptime(uptime);
        MapHelper.calculateUptimes(this);
        averagePowerConsumption = recursivelyGetAveragePowerConsumption(head);
    }

    public MachineNode getHead() {
        return head;
    }
}