package org.jkutkut.hr_app.db;

import org.jkutkut.db.PostgreSQLDB;
import org.jkutkut.db.SQLQuery;
import org.jkutkut.exception.InvalidDataException;
import org.jkutkut.hr_app.javabean.Employee;
import org.jkutkut.hr_app.utils.CustomDate;
import org.jkutkut.hr_app.utils.DateUtil;

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

    /**
     * Get all employees from the database.
     * @return an ArrayList with all employees.
     */
    public ArrayList<Employee> getAllEmployees() {
        return sql2Employees(SQLQuery.get(this, 11, "SELECT * FROM " + Employee.TABLE_NAME));
    }

    /**
     * Add an employee to the database.
     * @param employee the employee to be added.
     * @return SUCCESS if the employee was added, FAILURE otherwise.
     */
    public int addEmployee(Employee employee) {
        int id = getNewId();
        String query = String.format(
                "INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                Employee.TABLE_NAME,
                Employee.ID,
                Employee.FIRST_NAME,
                Employee.LAST_NAME,
                Employee.EMAIL,
                Employee.PHONE,
                Employee.HIRE_DATE,
                Employee.JOB_ID,
                Employee.SALARY,
                Employee.COMMISSION_PCT,
                Employee.MANAGER_ID,
                Employee.DEPARTMENT_ID
        );
        int result = SQLQuery.execute(this, query,
                id,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getHireDate(),
                employee.getJobId(),
                employee.getSalary(),
                employee.getCommissionPct(),
                employee.getManagerId(),
                employee.getDepartmentId()
        );
        if (result == 0)
            return SUCCESS;
        return FAILURE;
    }

    /**
     * Deletes the given employee from the database.
     * @param employee the employee to be deleted.
     * @return SUCCESS if the employee was deleted, FAILURE otherwise.
     */
    public int deleteEmployee(Employee employee) {
        String query = String.format(
                "DELETE FROM %s WHERE %s = ?",
                Employee.TABLE_NAME,
                Employee.ID
        );
        int result = SQLQuery.execute(this, query, employee.getId());
        if (result == 1)
            return SUCCESS;
        return FAILURE;
    }

    /**
     * Updates the given employee in the database.
     * @param old the old employee.
     * @param updated the updated employee.
     * @return SUCCESS if the employee was updated, FAILURE otherwise.
     */
    public int updateEmployee(Employee old, Employee updated) {
        String query = String.format(
                "UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
                Employee.TABLE_NAME,
                Employee.FIRST_NAME,
                Employee.LAST_NAME,
                Employee.EMAIL,
                Employee.PHONE,
                Employee.HIRE_DATE,
                Employee.JOB_ID,
                Employee.SALARY,
                Employee.COMMISSION_PCT,
                Employee.MANAGER_ID,
                Employee.DEPARTMENT_ID,
                Employee.ID
        );
        int result = SQLQuery.execute(this, query,
                updated.getFirstName(),
                updated.getLastName(),
                updated.getEmail(),
                updated.getPhone(),
                updated.getHireDate(),
                updated.getJobId(),
                updated.getSalary(),
                updated.getCommissionPct(),
                updated.getManagerId(),
                updated.getDepartmentId(),
                old.getId()
        );
        if (result == 1)
            return SUCCESS;
        return FAILURE;
    }

    /**
     * Obtains the next id to be used in the database.
     * @return the next id to be used in the database.
     */
    private int getNewId() {
        String query = String.format(
                "SELECT MAX(%s) FROM %s",
                Employee.ID,
                Employee.TABLE_NAME
        );
        return (int) SQLQuery.get(this, 1, query).get(0)[0] + 1;
    }

    /**
     * Search for employees in the database.
     * @param keyIndex Attribute to search for.
     * @param value Value to search for.
     * @return an ArrayList of employees.
     */
    public ArrayList<Employee> searchBy(int keyIndex, String value) {
        if (keyIndex < 0 || keyIndex >= Employee.ATTRIBUTES.length)
            throw new InvalidDataException("Invalid key.");
        String key = Employee.ATTRIBUTES[keyIndex];
        Object valueObject;
        try {
            valueObject = switch (keyIndex) {
                case 0, 9, 10 -> // id, manager_id, department_id
                        Integer.parseInt(value); // salary
                case 7, 8 -> // commission_pct
                        Double.parseDouble(value);
                case 5 -> // hire_date
                        DateUtil.parse(value);
                default -> value;
            };
        }
        catch (Exception e) {
            throw new InvalidDataException("Invalid value.");
        }
        String query = String.format(
                "SELECT * FROM %s WHERE %s = ?",
                Employee.TABLE_NAME,
                key
        );
        return sql2Employees(SQLQuery.get(this, 11, query, valueObject));
    }

    /**
     * Converts the result of a SQL query to an ArrayList of employees.
     * @param data the result of a SQL query.
     * @return an ArrayList of employees.
     */
    private ArrayList<Employee> sql2Employees(ArrayList<Object[]> data) {
        ArrayList<Employee> employees = new ArrayList<>();
        for (Object[] row : data) {
            employees.add(new Employee(
                    (int) row[0], // id
                    (String) row[1], // first_name
                    (String) row[2], // last_name
                    (String) row[3], // email
                    (String) row[4], // phone_number
                    CustomDate.fromDate((Date) row[5]), // hire_date
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

