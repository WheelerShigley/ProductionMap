package map;

import recipes.Recipes;

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
            head = new MachineNode(Recipes.ELECTRIC_CIRCUITS);
            MapHelper.recursivelyGenerateRecipesFromPreferredSources(head);
            head.setUptime(2.0);
            //MapHelper.calculateUptimes(head);

        }
    };
}
