package org.jkutkut.hr_app.db;

import org.jkutkut.db.PostgreSQLDB;
import org.jkutkut.exception.InvalidDataException;

import java.io.File;

public class HRDB extends PostgreSQLDB {

    private static final String ENV_FILE = ".env";
    private static final String[] PATHS = {"../../db/", "../../", "../../db/scripts/"};

    public HRDB(String configurationFilename) {
        super(configurationFilename);
    }

    public static HRDB createInstance() {
        String path = getConfigurationPath();
        if (path == null)
            throw new InvalidDataException("No configuration file found.");
        return new HRDB(path);
    }

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

