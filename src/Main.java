import graph.NodeGraphs;

public class Main {
    /* TODO
     * Remove Map-system (deprecated by NodeGraph)
     * Account for multiple power-types
     * Add README.md output-style [for NodeGraph]
     * Populate Recipes (more)
     * Alternate "best" evaluation criteria/criterion: Infrastructure-cost, building-time, production-time
     */
    public static void main(String[] args) {
        //Recipes.printAllComplexities();
        //System.out.println( CSAcademy.getGraphData(NodeGraphs.electronicCircuitGraph) );
        System.out.println(NodeGraphs.electronicCircuitGraph);
    }
}