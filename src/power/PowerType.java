package power;

import java.util.HashMap;

class Pair<T, K> {
    private final T first;
    private final K second;

    public Pair(T first, K second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }
    public K getSecond() {
        return second;
    }
}

public enum PowerType {
    Steam,
    EU,
    RF
    ;

    private static HashMap< Pair<PowerType, PowerType>, Double > powerConversionMap; static {
        powerConversionMap = new HashMap<>();

        powerConversionMap.put( new Pair<>(Steam, EU), 0.5 );
        //TODO: RF
    }
    public double getConversionFactor(PowerType from, PowerType to) {
        for(Pair<PowerType, PowerType> powerConversion : powerConversionMap.keySet() ) {
            if(
                powerConversion.getFirst().equals(from)
                && powerConversion.getSecond().equals(to)
            ) {
                return powerConversionMap.get(powerConversion);
            }

            if(
                powerConversion.getFirst().equals(to)
                && powerConversion.getSecond().equals(from)
            ) {
                return 1.0/powerConversionMap.get(powerConversion);
            }
        }

        return 0.0;
    }
}
