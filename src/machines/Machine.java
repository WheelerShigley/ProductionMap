package machines;

public record Machine(String namespace, String identifier) {
    Machine(String namespace, String identifier) {
        this.namespace = namespace;
        this.identifier = identifier;
        MachinesRegistry.registerMachine(this, true);
    }

    @Override
    public String toString() {
        return this.namespace+':'+this.identifier;
    }
}