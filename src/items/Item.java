package items;

record Item(String namespace, String identifier) {
    Item(String namespace, String identifier) {
        this.namespace = namespace;
        this.identifier = identifier;
        ItemsRegistry.registerItem(this, true);
    }

    @Override
    public String toString() {
        return this.namespace+':'+this.identifier;
    }
}
