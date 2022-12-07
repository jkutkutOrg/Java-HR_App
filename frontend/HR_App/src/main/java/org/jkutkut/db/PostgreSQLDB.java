package org.jkutkut.db;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgreSQLDB extends AccessDB {

    private static final String DRIVER = "org.postgresql.Driver";

    private final String ip;
    private final String port;
    private final String user;
    private final String pass;


    public PostgreSQLDB(String ip, String port, String user, String pass) {
        super(DRIVER);

        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pass = pass;

        setUrl(getUrl());
        System.out.println(getUrl());
    }

    private String getUrl() {
        final String POSTGRESQL_URL_BEGIN = "jdbc:postgresql://";
        final String POSTGRESQL_URL_END = "/postgres";

        return String.format(
            "%s%s:%s%s",
            POSTGRESQL_URL_BEGIN,
            ip,
            port,
            POSTGRESQL_URL_END
        );
    }

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return super.getConnection(user, pass);
    }
}
