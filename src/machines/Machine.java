package machines;

import register.Identified;

public class Machine extends Identified {
    public final Voltage voltage;
    public final double pollution;

    public Machine(String namespace, String name) {
        super(namespace, name);
        this.voltage = Voltage.None;
        this.pollution = 0.0;
    }
    public Machine(String namespace, String name, Voltage voltage) {
        super(namespace, name);
        this.voltage = voltage;
        this.pollution = 0.0;
    }
    public Machine(String namespace, String name, double pollution) {
        super(namespace, name);
        this.voltage = Voltage.None;
        this.pollution = pollution;
    }
    public Machine(String namespace, String name, Voltage voltage, double pollution) {
        super(namespace, name);
        this.voltage = voltage;
        this.pollution = pollution;
    }
}