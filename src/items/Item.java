package items;

public record Item(String namespace, String identifier) {
    public Item(String namespace, String identifier) {
        this.namespace = namespace;
        this.identifier = identifier;
        ItemsRegistry.registerItem(this, false);
    }

    @Override
    public String toString() {
        return this.namespace+':'+this.identifier;
    }
}
