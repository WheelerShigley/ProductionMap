package machines;

public class MachineData {
    private final String name;
    private final double pollution_rate;

    private final double speed_modifier;
    private final double power_modifier;

    public MachineData(String name) {
        this.name = name;
        this.pollution_rate = 0.0;

        speed_modifier = 0.0;
        power_modifier = 0.0;
    }
    public MachineData(String name, double pollution_rate) {
        this.name = name;
        this.pollution_rate = pollution_rate;

        speed_modifier = 0.0;
        power_modifier = 0.0;
    }
    public MachineData(String name, double speed_modifier, double power_modifier) {
        this.name = name;
        this.pollution_rate = 0.0;

        this.speed_modifier = speed_modifier;
        this.power_modifier = power_modifier;
    }
    public MachineData(String name, double pollution_rate, double speed_modifier, double power_modifier) {
        this.name = name;
        this.pollution_rate = pollution_rate;

        this.speed_modifier = speed_modifier;
        this.power_modifier = power_modifier;
    }

    public String getName() {
        return this.name;
    }
    public double getPollutionRate() {
        return this.pollution_rate;
    }
    public double getSpeed() {
        return 1.0+speed_modifier;
    }
    public double getPowerPercentage() {
        return 1.0+power_modifier;
    }
}
