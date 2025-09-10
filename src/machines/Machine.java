package machines;

public record Machine(String namespace, String identifier) {
    public Machine(String namespace, String identifier) {
        this.namespace = namespace;
        this.identifier = identifier;
        MachinesRegistry.registerMachine(this, false);
    }

    @Override
    public String toString() {
        return this.namespace+':'+this.identifier;
    }
}