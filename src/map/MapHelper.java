package map;

import java.util.logging.*;

public class MapHelper {
    private static void printMachineNode(MachineNode node, int depth, final Logger LOGGER) {
        StringBuilder nodeBuilder = new StringBuilder();
        for(int i = 0; i < depth-1; i++) {
            nodeBuilder.append("\t");
        }
        nodeBuilder.append("└ ").append( node.toString() );

        LOGGER.log(Level.INFO, nodeBuilder.toString() );

        //print sources
        for(MachineNode source : node.sources) {
            printMachineNode(source, depth+1, LOGGER);
        }
    }

    public static void printMap(Map map) {
        final Logger LOGGER; {
            LOGGER = Logger.getLogger("map_printer");

            // Remove default handlers
            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            for(Handler handler : handlers) {
                rootLogger.removeHandler(handler);
            }

            // Create custom handler without timestamp
            ConsoleHandler handler = new ConsoleHandler();
            handler.setFormatter(
                new Formatter() {
                    @Override
                    public String format(LogRecord record) {
                        return record.getMessage()+"\n";
                    }
                }
            );
            LOGGER.addHandler(handler);
        }

        LOGGER.log(Level.INFO, map.getHead().toString() );

        for(MachineNode source : map.getHead().sources) {
            printMachineNode(source, 1, LOGGER);
        }
    }
}
