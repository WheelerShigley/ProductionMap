package map;

import recipes.Recipes;

public class Maps {
    Map ElectricCircuit = new Map() {
        private MachineNode head = new MachineNode(Recipes.ElectricCircuits);
        @Override
        public MachineNode getHead() {
            return head;
        }

        @Override
        public void constructMap() {

        }
    };
}
