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

            //larger subsystems
            MachineNode FIRST_REFINED_GLUE; {
                MachineNode STICKY_RESIN = new MachineNode(Recipes.STICKY_RESIN);
                FIRST_REFINED_GLUE = new MachineNode(
                    Recipes.REFINED_GLUE,
                    List.of(STICKY_RESIN)
                );
            }
            MachineNode SECOND_REFINED_GLUE; {
                MachineNode STICKY_RESIN = new MachineNode(Recipes.STICKY_RESIN);
                SECOND_REFINED_GLUE = new MachineNode(
                    Recipes.REFINED_GLUE,
                    List.of(STICKY_RESIN)
                );
            }
            MachineNode FIRST_RED_ALLOY_INGOT; {
                MachineNode COPPER_INGOT = new MachineNode(Recipes.COPPER_INGOT);
                MachineNode REDSTONE_DUST = new MachineNode(Recipes.REDSTONE_DUST);
                FIRST_RED_ALLOY_INGOT = new MachineNode(
                    Recipes.RED_ALLOY_INGOT,
                    List.of(COPPER_INGOT, REDSTONE_DUST)
                );
            }
            MachineNode SECOND_RED_ALLOY_INGOT; {
                MachineNode COPPER_INGOT = new MachineNode(Recipes.COPPER_INGOT);
                MachineNode REDSTONE_DUST = new MachineNode(Recipes.REDSTONE_DUST);
                SECOND_RED_ALLOY_INGOT = new MachineNode(
                    Recipes.RED_ALLOY_INGOT,
                    List.of(COPPER_INGOT, REDSTONE_DUST)
                );
            }
            MachineNode FIRST_OXYGEN_GAS; {
                MachineNode EMPTY_CELL = new MachineNode(Recipes.EMPTY_CELL_CYCLED);
                MachineNode COMPRESSED_AIR = new MachineNode(
                    Recipes.COMPRESSED_AIR,
                    List.of(EMPTY_CELL)
                );
                MachineNode AIR_GAS = new MachineNode(
                    Recipes.AIR_GAS,
                    List.of(COMPRESSED_AIR)
                );
                MachineNode OXYGEN_CELL = new MachineNode(
                    Recipes.OXYGEN_CELL,
                    List.of(AIR_GAS, EMPTY_CELL)
                );
                FIRST_OXYGEN_GAS = new MachineNode(
                    Recipes.OXYGEN_GAS,
                    List.of(OXYGEN_CELL)
                );
            }
            MachineNode SECOND_OXYGEN_GAS; {
                MachineNode EMPTY_CELL = new MachineNode(Recipes.EMPTY_CELL_CYCLED);
                MachineNode COMPRESSED_AIR = new MachineNode(
                    Recipes.COMPRESSED_AIR,
                    List.of(EMPTY_CELL)
                );
                MachineNode AIR_GAS = new MachineNode(
                    Recipes.AIR_GAS,
                    List.of(COMPRESSED_AIR)
                );
                MachineNode OXYGEN_CELL = new MachineNode(
                    Recipes.OXYGEN_CELL,
                    List.of(AIR_GAS, EMPTY_CELL)
                );
                SECOND_OXYGEN_GAS = new MachineNode(
                    Recipes.OXYGEN_GAS,
                    List.of(OXYGEN_CELL)
                );
            }

            //recipe inputs
            MachineNode CIRCUIT_BOARD; {
                MachineNode ANY_WOOD = new MachineNode(Recipes.ANY_WOOD);
                MachineNode WOOD_PULP = new MachineNode(
                        Recipes.WOOD_PULP,
                        List.of(ANY_WOOD)
                );
                MachineNode WOOD_PLANK = new MachineNode(
                        Recipes.WOOD_PLANK,
                        List.of(WOOD_PULP)
                );
                MachineNode COPPER_INGOT = new MachineNode(Recipes.COPPER_INGOT);
                MachineNode COPPER_FOIL = new MachineNode(
                        Recipes.COPPER_FOIL,
                        List.of(COPPER_INGOT)
                );
                CIRCUIT_BOARD = new MachineNode(
                    Recipes.CIRCUIT_BOARD,
                    List.of(COPPER_FOIL,WOOD_PLANK,FIRST_REFINED_GLUE)
                );
            }
            MachineNode RESISTOR; {
                MachineNode COAL_DUST = new MachineNode(Recipes.COAL_DUST);
                MachineNode FINE_COPPER_WIRE = new MachineNode(Recipes.FINE_COPPER_WIRE);
                MachineNode ONE_COPPER_WIRE = new MachineNode(Recipes.ONE_COPPER_WIRE);
                RESISTOR = new MachineNode(
                    Recipes.RESISTOR,
                    List.of(COAL_DUST,FINE_COPPER_WIRE,ONE_COPPER_WIRE,SECOND_REFINED_GLUE)
                );
            }
            MachineNode ONE_RED_ALLOY_WIRE; {
                ONE_RED_ALLOY_WIRE = new MachineNode(
                    Recipes.ONE_RED_ALLOY_WIRE,
                    List.of(FIRST_RED_ALLOY_INGOT)
                );
            }
            MachineNode VACUUM_TUBE; {
                MachineNode GLASS_TUBE; {
                    MachineNode FIRST_COBBLESTONE = new MachineNode(Recipes.COBBLESTONE);
                    MachineNode SECOND_COBBLESTONE = new MachineNode(Recipes.COBBLESTONE);
                    MachineNode FIRST_GRAVEL = new MachineNode(
                        Recipes.GRAVEL,
                        List.of(FIRST_COBBLESTONE)
                    );
                    MachineNode SECOND_GRAVEL = new MachineNode(
                        Recipes.GRAVEL,
                        List.of(SECOND_COBBLESTONE)
                    );
                    MachineNode SAND = new MachineNode(
                        Recipes.SAND,
                        List.of(FIRST_GRAVEL)
                    );
                    MachineNode QUARTZ_SAND = new MachineNode(
                        Recipes.QUARTZ_SAND,
                        List.of(SAND)
                    );
                    MachineNode FLINT = new MachineNode(
                            Recipes.FLINT,
                            List.of(SECOND_GRAVEL)
                    );
                    MachineNode FLINT_DUST = new MachineNode(
                            Recipes.FLINT_DUST,
                            List.of(FLINT)
                    );
                    MachineNode GLASS_DUST = new MachineNode(
                        Recipes.GLASS_DUST,
                        List.of(QUARTZ_SAND, FLINT_DUST)
                    );
                    GLASS_TUBE = new MachineNode(
                        Recipes.GLASS_TUBE,
                        List.of(GLASS_DUST)
                    );
                }
                MachineNode STEEL_ROD; {
                    MachineNode IRON_DUST = new MachineNode(Recipes.IRON_DUST);
                    MachineNode WROUGHT_IRON_INGOT = new MachineNode(
                        Recipes.WROUGHT_IRON_INGOT,
                        List.of(IRON_DUST, FIRST_OXYGEN_GAS)
                    );
                    MachineNode WROUGHT_IRON_DUST = new MachineNode(
                        Recipes.WROUGHT_IRON_DUST,
                        List.of(WROUGHT_IRON_INGOT)
                    );
                    MachineNode STEEL_INGOT = new MachineNode(
                        Recipes.STEEL_INGOT,
                        List.of(WROUGHT_IRON_DUST, SECOND_OXYGEN_GAS)
                    );
                    STEEL_INGOT.setUptime(1.0); //Expected Limiting Factor
                    STEEL_ROD = new MachineNode(
                        Recipes.STEEL_ROD,
                        List.of(STEEL_INGOT)
                    );
                }
                MachineNode MOLTEN_RED_ALLOY = new MachineNode(
                    Recipes.MOLTEN_RED_ALLOY,
                    List.of(SECOND_RED_ALLOY_INGOT)
                );
                MachineNode ONE_COPPER_WIRE = new MachineNode(Recipes.ONE_COPPER_WIRE);
                VACUUM_TUBE = new MachineNode(
                    Recipes.VACUUM_TUBE,
                    List.of(GLASS_TUBE, ONE_COPPER_WIRE, STEEL_ROD, MOLTEN_RED_ALLOY)
                );
            }
            MachineNode MOLTEN_LEAD; {
                MachineNode LEAD_INGOT = new MachineNode(Recipes.LEAD_INGOT);
                MOLTEN_LEAD = new MachineNode(
                    Recipes.MOLTEN_LEAD,
                    List.of(LEAD_INGOT)
                );
            }

            //recipe (root)
            /*
            head = new MachineNode(
                Recipes.ELECTRIC_CIRCUITS,
                List.of(
                    CIRCUIT_BOARD,
                    RESISTOR,
                    ONE_RED_ALLOY_WIRE,
                    VACUUM_TUBE,
                    MOLTEN_LEAD
                )
            );
            */

            head = new MachineNode(Recipes.ELECTRIC_CIRCUITS);
            MapHelper.recursivelyGenerateRecipesFromPreferredSources(head);
            head.setUptime(2.0);
            //MapHelper.calculateUptimes(head);

        }
    };
}
