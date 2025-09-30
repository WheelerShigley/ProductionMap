package machines;

import register.Identified;

import java.util.HashMap;
import java.util.List;

public class MachineType extends Identified {
    private final HashMap<Voltage, List<MachineData> > data;
    private final String name;

    public MachineType(String namespace, String name) {
        super(namespace, name);

        this.data = new HashMap<>();
        this.data.put(
            Voltage.None,
            List.of(new MachineData(name) )
        );
        this.name = name;

        MachineTypes.register(this);
    }
    public MachineType(String namespace, String name, HashMap<Voltage, List<MachineData> > data) {
        super(namespace, name);

        this.data = data;
        this.name = name;

        MachineTypes.register(this);
    }

    public String getName() {
        return this.name;
    }

    public Voltage getMinimumVoltage() {
        Voltage minimum = Voltage.None;
        for(Voltage voltage : data.keySet()) {
            minimum = (voltage.EULimit() < minimum.EULimit() ? voltage : minimum);
        }
        return minimum;
    }

    public Voltage getMinimumVoltageForLimit(double eu_limit) {
        Voltage minimumVoltage = null;
        for( Voltage voltage : data.keySet() ) {
            if( eu_limit <= voltage.EULimit() ) {
                if(minimumVoltage == null) {
                    minimumVoltage = voltage;
                } else {
                    minimumVoltage = (minimumVoltage.EULimit() < voltage.EULimit()) ? minimumVoltage : voltage;
                }
            }
        }
        return (minimumVoltage == null ? Voltage.None : minimumVoltage);
    }

    public String getName(double eu_limit) {
        Voltage minimumVoltage = getMinimumVoltageForLimit(eu_limit);
        if(minimumVoltage == null) {
            //TODO: warn
            return "! No such Machine !";
        }

        if( data.get(minimumVoltage) == null ) {
            return "";
        }

        StringBuilder namesListBuilder = new StringBuilder();
        int amount_of_machine_options_at_voltage = data.get(minimumVoltage).size();
        for(int index = 0; index < amount_of_machine_options_at_voltage; index++) {
            namesListBuilder.append( data.get(minimumVoltage).get(index).getName() );
            if(index <= amount_of_machine_options_at_voltage-2) {
                namesListBuilder.append(" | ");
            }
        }

        if(1 < amount_of_machine_options_at_voltage) {
            return "["+ namesListBuilder.toString() + "]";
        } else {
            return namesListBuilder.toString();
        }
    }

    //TODO
    public double getPollution(double eu_limit) {
        return 0.0;

        /*
        Voltage minimumVoltage = getMinimumVoltageForLimit(eu_limit);
        if(minimumVoltage == null) {
            //TODO: warn
            return 0.0;
        }

        StringBuilder pollutionListBuilder = new StringBuilder();
        int amount_of_machine_options_at_voltage = data.get(minimumVoltage).size();
        for(int index = 0; index < amount_of_machine_options_at_voltage; index++) {
            pollutionListBuilder
                .append( data.get(minimumVoltage).get(index).getName() )
                .append("@").append( data.get(minimumVoltage).get(index).getPollutionRate() )
            ;
            if(index <= amount_of_machine_options_at_voltage-2) {
                pollutionListBuilder.append(" | ");
            }
        }

        if(1 < amount_of_machine_options_at_voltage) {
            return "["+ pollutionListBuilder.toString() + "]";
        } else {
            return pollutionListBuilder.toString();
        }
         */
    }
}
