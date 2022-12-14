package org.jkutkut.hr_app.javabean;

import javafx.beans.property.*;
import org.jkutkut.hr_app.utils.CustomDate;

import java.sql.Date;

public class Employee {
    private final IntegerProperty id;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty email;
    private final StringProperty phone;
    private final ObjectProperty<CustomDate> hireDate;
    private final StringProperty jobId;
    private final DoubleProperty salary;
    private final DoubleProperty commissionPct;
    private final IntegerProperty managerId;
    private final IntegerProperty departmentId;

    public static final String ID = "ID";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME";
    public static final String EMAIL = "EMAIL";
    public static final String PHONE = "PHONE";
    public static final String HIRE_DATE = "HIRE_DATE";
    public static final String JOB_ID = "JOB_ID";
    public static final String SALARY = "SALARY";
    public static final String COMMISSION_PCT = "COMMISSION_PCT";
    public static final String MANAGER_ID = "MANAGER_ID";
    public static final String DEPARTMENT_ID = "DEPARTMENT_ID";

    public static final String[] ATRIBUTES = {
        ID,
        FIRST_NAME,
        LAST_NAME,
        EMAIL,
        PHONE,
        HIRE_DATE,
        JOB_ID,
        SALARY,
        COMMISSION_PCT,
        MANAGER_ID,
        DEPARTMENT_ID
    };
    public static final String PK = ID;
    public static final String TABLE_NAME = "EMPLOYEE";

    public Employee() {
        this.id = new SimpleIntegerProperty();
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.phone = new SimpleStringProperty();
        this.hireDate = new SimpleObjectProperty<>();
        this.jobId = new SimpleStringProperty();
        this.salary = new SimpleDoubleProperty();
        this.commissionPct = new SimpleDoubleProperty();
        this.managerId = new SimpleIntegerProperty();
        this.departmentId = new SimpleIntegerProperty();
    }

    public Employee(int id, String firstName, String lastName, String email, String phone, CustomDate hireDate,
                    String jobId, double salary, double commissionPct, int managerId, int departmentId) {
        this();
        this.id.set(id);
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.email.set(email);
        this.phone.set(phone);
        this.hireDate.set(hireDate);
        this.jobId.set(jobId);
        this.salary.set(salary);
        this.commissionPct.set(commissionPct);
        this.managerId.set(managerId);
        this.departmentId.set(departmentId);
    }

    // *********** GETTERS ***********
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public Date getHireDate() {
        return hireDate.get();
    }

    public ObjectProperty<CustomDate> hireDateProperty() {
        return hireDate;
    }

    public String getJobId() {
        return jobId.get();
    }

    public StringProperty jobIdProperty() {
        return jobId;
    }

    public double getSalary() {
        return salary.get();
    }

    public DoubleProperty salaryProperty() {
        return salary;
    }

    public double getCommissionPct() {
        return commissionPct.get();
    }

    public DoubleProperty commissionPctProperty() {
        return commissionPct;
    }

    public int getManagerId() {
        return managerId.get();
    }

    public IntegerProperty managerIdProperty() {
        return managerId;
    }

    public int getDepartmentId() {
        return departmentId.get();
    }

    public IntegerProperty departmentIdProperty() {
        return departmentId;
    }

    // *********** SETTERS ***********
    public void setId(int id) {
        this.id.set(id);
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public void setHireDate(CustomDate hireDate) {
        this.hireDate.set(hireDate);
    }

    public void setJobId(String jobId) {
        this.jobId.set(jobId);
    }

    public void setSalary(double salary) {
        this.salary.set(salary);
    }

    public void setCommissionPct(double commissionPct) {
        this.commissionPct.set(commissionPct);
    }

    public void setManagerId(int managerId) {
        this.managerId.set(managerId);
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId.set(departmentId);
    }

    // *********** TOOLS ***********

    @Override
    public String toString() {
        return "Employee " + getId() + " " + getFirstName() + " " + getLastName() + "\n" +
            "  email: " + getEmail() + ", phone: " + getPhone() + "\n" +
            "  hire date: " + getHireDate() + ", job id: " + getJobId() + "\n" +
            "  salary: " + getSalary() + ", commission pct: " + getCommissionPct() + "\n" +
            "  manager id: " + getManagerId() + ", department id: " + getDepartmentId() + "\n";
    }
}
