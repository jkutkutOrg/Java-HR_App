package org.jkutkut.hr_app.db;

import org.jkutkut.db.PostgreSQLDB;
import org.jkutkut.db.SQLQuery;
import org.jkutkut.exception.InvalidDataException;
import org.jkutkut.hr_app.javabean.Employee;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Class with logic to interact with the HR database.
 *
 * @implNote This class should be instantiated by a method, not by calling the constructor.
 */
public class HRDB extends PostgreSQLDB {

    public static final int FAILURE = -1;
    public static final int SUCCESS = 0;

    private static final String ENV_FILE = ".env";
    private static final String[] PATHS = {"../../db/", "../../", "../../db/scripts/"};

    public HRDB(String configurationFilename) {
        super(configurationFilename);
    }

    /**
     * Standard way to create an instance of this class.
     * @return an instance of this class.
     * @throws InvalidDataException if the configuration file needed is not found.
     */
    public static HRDB createInstance() {
        String path = getConfigurationPath();
        if (path == null)
            throw new InvalidDataException("No configuration file found.");
        return new HRDB(path);
    }

    /**
     * Get the path to the configuration file.
     * @return the path to the configuration file or null.
     */
    private static String getConfigurationPath() {
        File f;
        for (String path : PATHS) {
            f = new File(path + ENV_FILE);
            if (f.exists())
                return path + ENV_FILE;
        }
        return null;
    }

    // *********** METHODS ***********

    private static final String EMPLOYEE_TABLE = "EMPLOYEE";

    public ArrayList<Employee> getAllEmployees() {
        return sql2Employees(SQLQuery.get(this, 11, "SELECT * FROM " + Employee.TABLE_NAME));
    }


    public int addEmployee(Employee employee) {
        // TODO: implement
        return FAILURE;
    }

    private ArrayList<Employee> sql2Employees(ArrayList<Object[]> data) {
        ArrayList<Employee> employees = new ArrayList<>();
        for (Object[] row : data) {
            employees.add(new Employee(
                    (int) row[0], // id
                    (String) row[1], // first_name
                    (String) row[2], // last_name
                    (String) row[3], // email
                    (String) row[4], // phone_number
                    (Date) row[5], // hire_date
                    (String) row[6], // job_id
                    (double) row[7], // salary
                    (double) row[8], // commission_pct
                    (int) row[9], // manager_id
                    (int) row[10] // department_id
            ));
        }
        return employees;
    }
}

