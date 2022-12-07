package org.jkutkut.hr_app.db;

import org.jkutkut.db.AccessDB;
import org.jkutkut.db.PostgreSQLDB;
import org.jkutkut.db.SQLQuery;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class HRDB extends PostgreSQLDB {

    private static final String ENV_FILE = ".env";
    private static final String[] PATHS  = {"../bd/"};

    public HRDB() {
        super("localhost", "", "", "");
    }


}

