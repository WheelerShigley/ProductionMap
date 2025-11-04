package graph.export;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Local {
    public static final String DEFAULT_NAME = "test";
    public static final String DEFAULT_EXTENSION = ".dot";

    private static final Logger LOGGER = Logger.getLogger("Local");

    public static void writeToFile(String characters) {
        writeToFile(DEFAULT_NAME+DEFAULT_EXTENSION, characters);
    }
    public static void writeToFile(String name, String characters) {
        String absolutePath; {
            File currentDirFile = new File("out\\graphs\\" + name);
            absolutePath = currentDirFile.getAbsolutePath();
        }

        //ensure file exists
        File outputFile;
        try {
            outputFile = new File(absolutePath);
            if( outputFile.createNewFile() ) {
                LOGGER.log(Level.INFO, "File \""+name+"\" created.");
            } else {
                LOGGER.log(Level.WARNING, "File \""+name+"\" was overridden.");
            }
        } catch(IOException ioException) {
            LOGGER.log(Level.SEVERE, "A File-Error Occurred.");
            ioException.printStackTrace();
            return;
        }

        //write to file
        try {
            Files.writeString(Path.of(absolutePath), characters);
        } catch(IOException ioException) {
            LOGGER.log(Level.SEVERE, "Failed to write to file \""+name+"\".");
            ioException.printStackTrace();
            return;
        }
    }
}
