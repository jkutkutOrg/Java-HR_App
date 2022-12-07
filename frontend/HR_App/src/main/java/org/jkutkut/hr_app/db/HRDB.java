package org.jkutkut.hr_app.db;

import org.jkutkut.db.PostgreSQLDB;
import org.jkutkut.exception.InvalidDataException;

import java.io.File;

/**
 * Class with logic to interact with the HR database.
 *
 * @implNote This class should be instantiated by a method, not by calling the constructor.
 */
public class HRDB extends PostgreSQLDB {

    private static final String ENV_FILE = ".env";
    private static final String[] PATHS = {"../../db/", "../../", "../../db/scripts/"};

    public HRDB(String configurationFilename) {
        super(configurationFilename);
    }

    /**
     * Standard way to create an instance of this class.
     * @return an instance of this class.
     * @throws InvalidDataException if the configuration file needed is not found.
     */
    public static HRDB createInstance() {
        String path = getConfigurationPath();
        if (path == null)
            throw new InvalidDataException("No configuration file found.");
        return new HRDB(path);
    }

    /**
     * Get the path to the configuration file.
     * @return the path to the configuration file or null.
     */
    private static String getConfigurationPath() {
        File f;
        for (String path : PATHS) {
            f = new File(path + ENV_FILE);
            if (f.exists())
                return path + ENV_FILE;
        }
        return null;
    }
}

