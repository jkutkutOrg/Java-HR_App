package org.jkutkut.db.configuration;

import org.jkutkut.exception.InvalidDataException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Configurator {
    protected final HashMap<String, String> config;

    public Configurator(String filename) {
        config = new HashMap<>();
        parse(filename);
    }

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

    protected void validate() throws InvalidDataException {}

    protected boolean validFileName(String file) {
        return (file != null && file.trim().endsWith(".env"));
    }

    // *********** GETTERS ***********
    public String get(String key) {
        return config.get(key);
    }
}
