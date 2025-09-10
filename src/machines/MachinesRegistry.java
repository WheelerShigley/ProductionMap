package machines;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MachinesRegistry {
    private static final Logger LOGGER = Logger.getLogger("ItemRegistry");
    static ArrayList<Machine> MachineRegistry = new ArrayList<Machine>();

    public static boolean registerMachine(Machine machine) {
        return registerMachine(machine, false);
    }
    public static boolean registerMachine(Machine machine, boolean enableLogging) {
        StringBuilder loggingMessageBuilder = new StringBuilder();
        loggingMessageBuilder.append('"').append( machine.namespace() ).append(':').append( machine.identifier() ).append("\" ");

        //validate Namespace:Identifier
        if(machine.namespace().length() < 2 && machine.identifier().length() < 2) {
            if(enableLogging) {
                loggingMessageBuilder.append("was not registered because its namespace and identifier was too short.");
                LOGGER.log( Level.WARNING, loggingMessageBuilder.toString() );
            }
            return false;
        }
        if(machine.namespace().length() < 2) {
            if(enableLogging) {
                loggingMessageBuilder.append("was not registered because its namespace was too short.");
                LOGGER.log( Level.WARNING, loggingMessageBuilder.toString() );
            }
            return false;
        }
        if(machine.identifier().length() < 2) {
            if(enableLogging) {
                loggingMessageBuilder.append("was not registered because its identifier was too short.");
                LOGGER.log( Level.WARNING, loggingMessageBuilder.toString() );
            }
            return false;
        }

        //ensure it is not already registered
        for(Machine registeredMachine : MachinesRegistry.MachineRegistry) {
            if(
                registeredMachine.namespace().equalsIgnoreCase( machine.namespace() )
                && registeredMachine.identifier().equalsIgnoreCase( machine.identifier() )
            ) {
                if(enableLogging) {
                    loggingMessageBuilder.append("was not registered because it was a duplicate entry.");
                    LOGGER.log( Level.WARNING, loggingMessageBuilder.toString() );
                }
                return false;
            }
        }

        //register item
        MachineRegistry.add(machine);
        if(enableLogging) {
            loggingMessageBuilder.append("was registered.");
            LOGGER.log( Level.INFO, loggingMessageBuilder.toString() );
        }
        return true;
    }
}