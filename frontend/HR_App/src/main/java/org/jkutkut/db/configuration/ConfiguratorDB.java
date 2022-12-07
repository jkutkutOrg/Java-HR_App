package org.jkutkut.db.configuration;

import org.jkutkut.exception.InvalidDataException;

/**
 * Class that contains the configuration needed to connect to a database.
 */
public class ConfiguratorDB extends Configurator {
    public static final String IP_KEY = "DB_IP";
    public static final String PORT_KEY = "DB_PORT";
    public static final String USER_KEY = "DB_USR";
    public static final String PASS_KEY = "DB_USR_PASSWD";

    private static final String[] REQUIRED_KEYS = {USER_KEY, PASS_KEY, IP_KEY, PORT_KEY};

    public ConfiguratorDB(String filename, String ip) {
        super(filename);
        config.put(IP_KEY, ip);
        this.validate();
    }

    public ConfiguratorDB(String filename) {
        this(filename, "localhost");
    }

    /**
     * Validates the configuration file.
     * @throws InvalidDataException If any of the required keys is missing or invalid.
     */
    @Override
    protected void validate() throws InvalidDataException {
        for (String key : REQUIRED_KEYS) {
            if (!config.containsKey(key))
                throw new InvalidDataException("KeyMissing: " + key);
            if (config.get(key) == null)
                throw new InvalidDataException("KeyNull: " + key);
        }
    }
}
