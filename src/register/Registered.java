package register;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Registered<T extends Identified> {
    private static Logger LOGGER;
    ArrayList<T> Registry = new ArrayList<T>();

    public Registered(String name) {
        LOGGER = Logger.getLogger(name+"Registry");
    }

    public boolean registerInstance(T instance) {
        return registerInstance(instance, false);
    }
    public boolean registerInstance(T instance, boolean enableLogging) {
        StringBuilder loggingMessageBuilder = new StringBuilder();
        loggingMessageBuilder.append('"').append( instance.getNamespace() ).append(':').append( instance.getName() ).append("\" ");

        //validate Namespace:Identifier
        if(instance.getNamespace().length() < 2 && instance.getName().length() < 2) {
            if(enableLogging) {
                loggingMessageBuilder.append("was not registered because its namespace and identifier was too short.");
                LOGGER.log( Level.WARNING, loggingMessageBuilder.toString() );
            }
            return false;
        }
        if(instance.getNamespace().length() < 2) {
            if(enableLogging) {
                loggingMessageBuilder.append("was not registered because its namespace was too short.");
                LOGGER.log( Level.WARNING, loggingMessageBuilder.toString() );
            }
            return false;
        }
        if(instance.getName().length() < 2) {
            if(enableLogging) {
                loggingMessageBuilder.append("was not registered because its identifier was too short.");
                LOGGER.log( Level.WARNING, loggingMessageBuilder.toString() );
            }
            return false;
        }

        //ensure it is not already registered
        for(T registeredInstance : Registry) {
            if(
                   registeredInstance.getNamespace().equalsIgnoreCase( instance.getNamespace() )
                && registeredInstance.getName().equalsIgnoreCase( instance.getName() )
            ) {
                if(enableLogging) {
                    loggingMessageBuilder.append("was not registered because it was a duplicate entry.");
                    LOGGER.log( Level.WARNING, loggingMessageBuilder.toString() );
                }
                return false;
            }
        }

        //register item
        Registry.add(instance);
        if(enableLogging) {
            loggingMessageBuilder.append("was registered.");
            LOGGER.log( Level.INFO, loggingMessageBuilder.toString() );
        }
        return true;
    }
}
