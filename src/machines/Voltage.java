package machines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public enum Voltage {
    None,
    UltraLow,
    Low,
    Medium,
    High,
    Extreme,
    Insane,
    Ludicrous,
    ZPM,
    Ultimate,
    HighlyUltimate,
    ExtremelyUltimate,
    InsanelyUltimate,
    MegaUltimate,
    ExtendedMegaUltimate,
    Maximum
    ;

    @Override
    public String toString() {
        return switch(this) {
            case UltraLow -> "ULV";
            case Low -> "LV";
            case Medium -> "MV";
            case High -> "HV";
            case Extreme -> "EV";
            case Insane -> "IV";
            case Ludicrous -> "LuV";
            case ZPM -> "ZPM";
            case Ultimate -> "UV";
            case HighlyUltimate -> "UHV";
            case ExtremelyUltimate -> "UEV";
            case InsanelyUltimate -> "UIV";
            case MegaUltimate -> "UMV";
            case ExtendedMegaUltimate -> "UXV";
            case Maximum -> "MAX";
            default -> "";
        };
    }

    public String toUnabreviatedString() {
        return switch(this) {
            case UltraLow -> "Ultra Low Voltage";
            case Low -> "Low Voltage";
            case Medium -> "Medium Voltage";
            case High -> "High Voltage";
            case Extreme -> "Extreme Voltage";
            case Insane -> "Insane Voltage";
            case Ludicrous -> "Ludicrous Voltage";
            case ZPM -> "ZPM";
            case Ultimate -> "Ultimate Voltage";
            case HighlyUltimate -> "Highly Ultimate Voltage";
            case ExtremelyUltimate -> "Extremely Ultimate Voltage";
            case InsanelyUltimate -> "Insanely Ultimate Voltage";
            case MegaUltimate -> "Mega Voltage";
            case ExtendedMegaUltimate -> "Extended Mega Voltage";
            case Maximum -> "Maximum Voltage";
            default -> "";
        };
    }

    public int EULimit() {
        return switch(this) {
            case UltraLow ->                1<<3;
            case Low ->                     1<<5;
            case Medium ->                  1<<7;
            case High ->                    1<<9;
            case Extreme ->                 1<<11;
            case Insane ->                  1<<13;
            case Ludicrous ->               1<<15;
            case ZPM ->                     1<<17;
            case Ultimate ->                1<<19;
            case HighlyUltimate ->          1<<21;
            case ExtremelyUltimate ->       1<<23;
            case InsanelyUltimate ->        1<<25;
            case MegaUltimate ->            1<<27;
            case ExtendedMegaUltimate ->    1<<29;
            case Maximum ->                 Integer.MAX_VALUE;
            default ->                      0;
        };
    }

    public static Voltage getVoltage(double eu_per_tick) {
        Voltage minimumVoltage = Voltage.None;
        while(
            minimumVoltage != null
            && minimumVoltage.EULimit() < eu_per_tick
        ) {
            minimumVoltage = getNextVoltage(minimumVoltage);
        }
        return minimumVoltage;
    }

    public static Voltage getNextVoltage(Voltage v) {
        return switch(v) {
            case Low ->                     Voltage.Medium;
            case Medium ->                  Voltage.High;
            case High ->                    Voltage.Extreme;
            case Extreme ->                 Voltage.Insane;
            case Insane ->                  Voltage.Ludicrous;
            case Ludicrous ->               Voltage.ZPM;
            case ZPM ->                     Voltage.Ultimate;
            case Ultimate ->                Voltage.HighlyUltimate;
            case HighlyUltimate ->          Voltage.ExtremelyUltimate;
            case ExtremelyUltimate ->       Voltage.InsanelyUltimate;
            case InsanelyUltimate ->        Voltage.MegaUltimate;
            case MegaUltimate ->            Voltage.ExtendedMegaUltimate;
            case ExtendedMegaUltimate ->    Voltage.Maximum;
            case Maximum ->                 null;
            default ->                      Voltage.Low;
        };
    }

    public static List<Voltage> getVoltagesBetweenInclusive(Voltage first, Voltage second) {
        Voltage smaller =   (first.EULimit() < second.EULimit() ? first : second);
        Voltage larger =    (first.EULimit() < second.EULimit() ? second : first);

        List<Voltage> voltagesBetweenInclusive = new ArrayList<>();
        Voltage current = smaller;
        while( current != null && current.EULimit() <= larger.EULimit() ) {
            voltagesBetweenInclusive.add(current);
            current = getNextVoltage(current);
        }
        return voltagesBetweenInclusive;
    }

    //TODO: getMaximumAveragePower(double eu_limit) [for a power-refactor]

    public static String getAveragePowerConsumptionString(HashMap<Voltage, Double> averagePowerConsumption){
        //print average power consumption
        StringBuilder powerConsumptionMessageBuilder = new StringBuilder();
        powerConsumptionMessageBuilder.append("Average Power Consumption = {");

        int accumulator = 0;
        for(Voltage voltage : averagePowerConsumption.keySet() ) {
            if( voltage.equals(Voltage.None) ) {
                continue;
            }
            powerConsumptionMessageBuilder
                .append(
                    Math.round(
                        100.0*averagePowerConsumption.get(voltage)
                    )/100.0
                )
                .append("A@")
                .append( voltage.toString() )
            ;
            if(accumulator < averagePowerConsumption.size()-2) {
                powerConsumptionMessageBuilder.append(", ");
            }
            accumulator++;
        }
        powerConsumptionMessageBuilder.append('}');

        return powerConsumptionMessageBuilder.toString();
    }

    public static HashMap<Voltage, Double> combinePower(List< HashMap<Voltage, Double> > powers) {
        HashMap<Voltage, Double> combinedPower = new HashMap<>(); {
            for(HashMap<Voltage, Double> branchPowerConsumption : powers) {
                for( Voltage voltage : branchPowerConsumption.keySet() ) {
                    if( combinedPower.containsKey(voltage) ) {
                        combinedPower.replace(
                            voltage,
                            combinedPower.get(voltage) + branchPowerConsumption.get(voltage)
                        );
                    } else {
                        combinedPower.put(voltage, branchPowerConsumption.get(voltage) );
                    }
                }
            }
        }
        return combinedPower;
    }
}
