package org.jkutkut.db;

import org.jkutkut.exception.InvalidDataException;

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
            Connection connection = DriverManager.getConnection(url);
            connection.close();
        }
        catch (ClassNotFoundException e) {
            throw new InvalidDataException("The class to access the DB is not valid.");
        }
        catch (SQLException e) {
            throw new InvalidDataException("There was an error opening the connection: " + e.getMessage());
        }
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url);
    }
}
