package org.jkutkut.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccessDB {
    private final String driver;
    private final String url;

    public AccessDB(String driver, String url) {
        this.driver = driver;
        this.url = url;

        try {
            Class.forName(driver);
            Connection conection = DriverManager.getConnection(url);
        }
        catch (ClassNotFoundException e) {
//            throw new InvalidDataException("");
        }
        catch (SQLException e) {
//            throw new InvalidDataException("");
        }
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url);
    }
}
