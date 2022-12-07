package org.jkutkut.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract public class AccessDB {
    private final String driver;
    private String url;

    /**
     * Constructor 1
     *
     * @implNote Do not forget to add the url with setUrl() method.
     *
     * @param driver The driver to use.
     */
    public AccessDB(String driver) {
        this.driver = driver;
    }

    /**
     * Constructor 2
     * @param driver The driver to use.
     * @param url The url to connect to.
     */
    public AccessDB(String driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    // SETTERS
    protected void setUrl(String url) {
        this.url = url;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url);
    }

    public Connection getConnection(String usr, String pass) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, usr, pass);
    }
}
