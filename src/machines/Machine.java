package machines;

import register.Identified;

public class Machine extends Identified {
    public final Voltage voltage;
    public Machine(String namespace, String name, Voltage voltage) {
        super(namespace, name);
        this.voltage = voltage;
    }
}