package graph;

import items.Item;
import machines.MachineType;
import machines.MachineTypes;
import recipes.Recipe;
import recipes.Recipes;

import java.util.ArrayList;
import java.util.List;

public class NodeGraph {
    private final Item finalProduct;
    public final List<ProductNode> products = new ArrayList<>();
    public final List<RecipeNode> transformers = new ArrayList<>();

    public NodeGraph(Item finalProduct, double rate) {
        this.finalProduct = finalProduct;

        //create original recipe-node and product-node
        ProductNode ultimateSink = new ProductNode(finalProduct);

        RecipeNode ultimateSource = new RecipeNode(
            Recipes.getFastestProducingRecipe(finalProduct)
        );
        ultimateSource.setUpTime(
            rate/ultimateSource.recipe.getProductionRate(finalProduct)
        );

        ultimateSource.addOutput(ultimateSink);
        ultimateSink.addSource(ultimateSource);
        this.addProduct(ultimateSink);
        this.addTransformer(ultimateSource);

        //back-calculate production-map
        constructIncompleteNodeGraph();
    }

    private List<RecipeNode> getUnsourcedRecipeNodes() {
        List<RecipeNode> unsourcedRecipeNodes = new ArrayList<>();
        for(RecipeNode potentiallyUnsourcedRecipeNode : transformers) {
            if(
                potentiallyUnsourcedRecipeNode.inputs.isEmpty()
                && !Recipes.isLeafRecipe(potentiallyUnsourcedRecipeNode.recipe)
            ) {
                unsourcedRecipeNodes.add(potentiallyUnsourcedRecipeNode);
            }
        }
        return unsourcedRecipeNodes;
    }
    private List<ProductNode> getUnsourcedProductNodes() {
        List<ProductNode> unsourcedProductNode = new ArrayList<>();
        for(ProductNode potentiallyUnsourcedProductNode : products) {
            if(
                potentiallyUnsourcedProductNode.getSourceCount() < 1
                && Recipes.optimalRecipes.containsKey(potentiallyUnsourcedProductNode.product)
                /*&& !MachineTypes.isLeafMachine(
                    Recipes.optimalRecipes.get(potentiallyUnsourcedProductNode.product).machineType
                )*/
            ) {
                unsourcedProductNode.add(potentiallyUnsourcedProductNode);
            }
        }
        return unsourcedProductNode;
    }
    private void constructIncompleteNodeGraph() {
        //temporary, for testing
        /*
        for(RecipeNode transformer : transformers) {
            transformer.generateSurroundings(this);
        }
         */
        final int MAXIMUM_DEPTH = 50;
        int current_depth = 0;

        List<ProductNode> unsourcedProductNodes = getUnsourcedProductNodes();
        List<RecipeNode>  unsourcedRecipesNodes = getUnsourcedRecipeNodes() ;
        while(
            ( !unsourcedProductNodes.isEmpty() || !unsourcedRecipesNodes.isEmpty() )
            && current_depth < MAXIMUM_DEPTH
        ) {
            for(ProductNode unsourcedProducedNode : unsourcedProductNodes) {
                unsourcedProducedNode.generateSource(this);
            }
            for(RecipeNode unsourcedRecipeNode : unsourcedRecipesNodes) {
                unsourcedRecipeNode.generateSurroundings(this);
            }

            unsourcedProductNodes = getUnsourcedProductNodes();
            unsourcedRecipesNodes = getUnsourcedRecipeNodes() ;
            current_depth++;
        }
    }

    public boolean addProduct(ProductNode product) {
        //ensure it does not already exist in transformations
        for(ProductNode existingProduct : products) {
            if(existingProduct == product) {
                return false;
            }
        }

        products.add(product);
        return true;
    }
    public boolean addTransformer(RecipeNode transformer) {
        //ensure it does not already exist in transformations
        for(RecipeNode existingTransformer : transformers) {
            if(existingTransformer == transformer) {
                return false;
            }
        }

        transformers.add(transformer);
        return true;
    }

    public ProductNode getProduct(Item ofItem) {
        //linear search
        for(ProductNode product : products) {
            if( product.product.equals(ofItem) ) {
                return product;
            }
        }
        return null;
    }
    public RecipeNode getTransformer(Recipe ofRecipe) {
        //linear search
        for(RecipeNode transformation : transformers) {
            if( transformation.recipe.equals(ofRecipe) ) {
                return transformation;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder nodeGraphStringBuilder = new StringBuilder();
        nodeGraphStringBuilder.append("NodeGraph(").append( finalProduct.getName() ).append(") {\r\n");

        int counter = 0;
        nodeGraphStringBuilder.append("\tProducts:\r\n");
        for(ProductNode product : products) {
            nodeGraphStringBuilder.append("\t\t").append(counter++).append(" -> ").append( product.toString() ).append("\r\n");
        }
        nodeGraphStringBuilder.append("\r\n");

        counter = 0;
        nodeGraphStringBuilder.append("\tTransformers:\r\n");
        for(RecipeNode transformer : transformers) {
            nodeGraphStringBuilder.append("\t\t").append(counter++).append(" -> ").append( transformer.toString() ).append("\r\n");
        }
        nodeGraphStringBuilder.append("\r\n");

        nodeGraphStringBuilder.append("}");
        return nodeGraphStringBuilder.toString();
    }
}
