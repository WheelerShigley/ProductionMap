package machines;

public class Machine {
    public final MachineType machineType;
    public final Voltage voltage;
    public final String name;

    public Machine(MachineType type, double eu_per_tick) {
        this.machineType = type;
        voltage = type.getMinimumVoltageForLimit(eu_per_tick);
        name = type.getName(eu_per_tick);
    }

    public boolean equals(Machine other) {
        return
            other.machineType.equals(this.machineType)
            && other.voltage.equals(this.voltage)
        ;
    }
}
