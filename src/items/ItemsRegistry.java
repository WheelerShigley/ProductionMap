package items;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemsRegistry {
    private static final Logger LOGGER = Logger.getLogger("ItemRegistry");
    static ArrayList<Item> ItemRegistry = new ArrayList<Item>();

    public static boolean registerItem(Item item) {
        return registerItem(item, false);
    }
    public static boolean registerItem(Item item, boolean enableLogging) {
        StringBuilder loggingMessageBuilder = new StringBuilder();
        loggingMessageBuilder.append('"').append( item.namespace() ).append(':').append( item.identifier() ).append("\" ");

        //validate Namespace:Identifier
        if(item.namespace().length() < 2 && item.identifier().length() < 2) {
            if(enableLogging) {
                loggingMessageBuilder.append("was not registered because its namespace and identifier was too short.");
                LOGGER.log( Level.WARNING, loggingMessageBuilder.toString() );
            }
            return false;
        }
        if(item.namespace().length() < 2) {
            if(enableLogging) {
                loggingMessageBuilder.append("was not registered because its namespace was too short.");
                LOGGER.log( Level.WARNING, loggingMessageBuilder.toString() );
            }
            return false;
        }
        if(item.identifier().length() < 2) {
            if(enableLogging) {
                loggingMessageBuilder.append("was not registered because its identifier was too short.");
                LOGGER.log( Level.WARNING, loggingMessageBuilder.toString() );
            }
            return false;
        }

        //ensure it is not already registered
        for(Item registeredItem : ItemsRegistry.ItemRegistry) {
            if(
                registeredItem.namespace().equalsIgnoreCase( item.namespace() )
                && registeredItem.identifier().equalsIgnoreCase( item.identifier() )
            ) {
                if(enableLogging) {
                    loggingMessageBuilder.append("was not registered because it was a duplicate entry.");
                    LOGGER.log( Level.WARNING, loggingMessageBuilder.toString() );
                }
                return false;
            }
        }

        //register item
        ItemRegistry.add(item);
        if(enableLogging) {
            loggingMessageBuilder.append("was registered.");
            LOGGER.log( Level.INFO, loggingMessageBuilder.toString() );
        }
        return true;
    }
}