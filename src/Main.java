import map.Map;
import map.MapHelper;
import map.Maps;

public class Main {
    public static void main(String[] args) {
        final Map ELECTRIC_CIRCUIT = Maps.ELECTRIC_CIRCUIT;
        MapHelper.calculateUptimes(ELECTRIC_CIRCUIT);

        MapHelper.printMap(ELECTRIC_CIRCUIT);
    }
}