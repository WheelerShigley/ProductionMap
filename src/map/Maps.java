package map;

import recipes.Recipes;

import java.util.List;

public class Maps {
    public static final Map ELECTRIC_CIRCUIT = new Map() {
        private MachineNode head;
        @Override
        public MachineNode getHead() {
            if(head == null) {
                constructMap();
            }

            return head;
        }

        @Override
        public void constructMap() {
            MachineNode ANY_WOOD = new MachineNode(Recipes.AnyWood);
            MachineNode WOOD_PULP = new MachineNode(
                Recipes.WoodPulp,
                List.of(ANY_WOOD)
            );
            MachineNode WOOD_PLANK = new MachineNode(
                Recipes.WoodPlank,
                List.of(WOOD_PULP)
            );

            MachineNode STICKY_RESIN = new MachineNode(Recipes.StickyResin);
            MachineNode REFINED_GLUE = new MachineNode(
                Recipes.RefinedGlue,
                List.of(STICKY_RESIN)
            );

            MachineNode COPPER_INGOT = new MachineNode(Recipes.CopperIngot);
            MachineNode COPPER_FOIL = new MachineNode(
                Recipes.CopperFoil,
                List.of(COPPER_INGOT)
            );
            MachineNode CIRCUIT_BOARD = new MachineNode(
                Recipes.CircuitBoard,
                List.of(COPPER_FOIL,WOOD_PLANK,REFINED_GLUE)
            );

            head = new MachineNode(
                Recipes.ElectricCircuits,
                List.of(
                    CIRCUIT_BOARD
                )
            );
        }
    };
}
