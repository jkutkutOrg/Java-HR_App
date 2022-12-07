package org.jkutkut.db.configuration;

import org.jkutkut.exception.InvalidDataException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class with the logic needed to read, parse and store the data in a configuration file.
 */
public class Configurator {
    protected final HashMap<String, String> config;

    public Configurator(String filename) {
        config = new HashMap<>();
        parse(filename);
    }

    /**
     * Parses the configuration file and stores it into the instance.
     * @param filename The name of the configuration file.
     */
    protected void parse(String filename) {
        if (!validFileName(filename))
            throw new InvalidDataException("Invalid file name.");
        filename = filename.trim();
        File f = new File(filename);
        Scanner s;
        try {
            s = new Scanner(f);
            String line;
            String[] data;
            while (s.hasNext()) {
                line = s.nextLine();
                if (line.startsWith("#") || line.isEmpty())
                    continue;
                data = line.split("=");
                if (data.length != 2)
                    throw new InvalidDataException("Invalid configuration file.");
                data[1] = data[1].trim().replaceAll("^[\"']|[\"']$", "");
                config.put(data[0].trim(), data[1]);
            }
            s.close();
        }
        catch (FileNotFoundException e) {
            throw new InvalidDataException("File does not exist.");
        }
    }

    /**
     * Validates the configuration file.
     * @throws InvalidDataException If the configuration file is not valid.
     */
    protected void validate() throws InvalidDataException {}

    /**
     * Checks if the file name is valid.
     * @param file The name of the file.
     * @return True if the file name is valid, false otherwise.
     */
    protected boolean validFileName(String file) {
        return (file != null && file.trim().endsWith(".env"));
    }

    // *********** GETTERS ***********

    /**
     * @param key The key of the value to get.
     * @return The value associated with the key.
     * @throws InvalidDataException If the key does not exist or the key is invalid.
     */
    public String get(String key) {
        if (key == null)
            throw new InvalidDataException("Key can not be null.");
        if (!config.containsKey(key))
            throw new InvalidDataException("Key does not exist.");
        return config.get(key);
    }
}
