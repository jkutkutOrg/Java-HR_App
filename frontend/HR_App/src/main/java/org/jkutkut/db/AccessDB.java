package org.jkutkut.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is used to connect to a database.
 */
abstract public class AccessDB {
    private final String driver;
    private String url;

    /**
     * @implNote Do not forget to add the url with setUrl() method.
     *
     * @param driver The driver to use.
     */
    public AccessDB(String driver) {
        this.driver = driver;
    }

    /**
     * @param driver The driver to use.
     * @param url The url to connect to.
     */
    public AccessDB(String driver, String url) {
        this(driver);
        this.url = url;
    }

    // ************ SETTERS ************
    protected void setUrl(String url) {
        this.url = url;
    }

    // ************ GETTERS ************

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url);
    }

    public Connection getConnection(String usr, String pass) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, usr, pass);
    }
}
