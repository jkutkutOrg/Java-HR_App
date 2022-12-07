package org.jkutkut.hr_app.db;

import org.jkutkut.db.AccessDB;
import org.jkutkut.db.SQLQuery;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class HRDB extends AccessDB {

    private static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";
    private static final String PORT = ""; // TODO fix this
    private static final String USER = ""; // TODO fix this
    private static final String PASS = ""; // TODO fix this
    private static final String POSTGRESQL_URL = "jdbc:postgresql://localhost:" + PORT + "/postgres";


    public HRDB() {
        super(POSTGRESQL_DRIVER, POSTGRESQL_URL);
    }

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return super.getConnection(USER, PASS);
    }
}

