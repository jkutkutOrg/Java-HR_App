package org.jkutkut.db;

import org.jkutkut.db.configuration.Configurator;
import org.jkutkut.db.configuration.ConfiguratorDB;

import java.sql.Connection;
import java.sql.SQLException;

import static org.jkutkut.db.configuration.ConfiguratorDB.*;

public class PostgreSQLDB extends AccessDB {

    private static final String DRIVER = "org.postgresql.Driver";
    private final Configurator config;

    public PostgreSQLDB(String configurationFilename) {
        super(DRIVER);
        config = new ConfiguratorDB(configurationFilename);

        setUrl(getUrl());
    }

    private String getUrl() {
        final String POSTGRESQL_URL_BEGIN = "jdbc:postgresql://";
        final String POSTGRESQL_URL_END = "/postgres";

        return String.format(
            "%s%s:%s%s",
            POSTGRESQL_URL_BEGIN,
            getIP(),
            getPort(),
            POSTGRESQL_URL_END
        );
    }

    // ********** GETTERS **********
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return super.getConnection(getUser(), getPassword());
    }

    protected String getUser() {
        return config.get(USER_KEY);
    }

    protected String getPassword() {
        return config.get(PASS_KEY);
    }

    protected String getIP() {
        return config.get(IP_KEY);
    }

    protected String getPort() {
        return config.get(PORT_KEY);
    }
}
