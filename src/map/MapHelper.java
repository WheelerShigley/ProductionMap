package map;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MapHelper {
    public static void printMap(Map map) {
        Logger LOGGER = Logger.getLogger("map_printer");

        LOGGER.log(Level.INFO, map.getHead().toString() );
    }
}
