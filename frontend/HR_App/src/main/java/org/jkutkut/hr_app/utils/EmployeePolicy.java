package org.jkutkut.hr_app.utils;

import org.jkutkut.inputPolicy.DatePolicy;
import org.jkutkut.inputPolicy.UserPolicy;

import java.util.ArrayList;

public class EmployeePolicy {

    /**
     * Policy to validate the name of a person.
     */
    protected static class NamePolicy extends UserPolicy {
        protected static final String POLICY_NAME = "Name";
        protected static final int MIN_LENGTH = 2;
        protected static final int MAX_LENGTH = Integer.MAX_VALUE;

        protected String getPolicyName() {
            return POLICY_NAME;
        }

        protected int getMinLength() {
            return MIN_LENGTH;
        }

        protected int getMaxLength() {
            return MAX_LENGTH;
        }
    }

    /**
     * Policy to validate the last name of a person.
     */
    private static class LastNamePolicy extends NamePolicy {
        protected static final String POLICY_NAME = "Last Name";

        protected String getPolicyName() {
            return POLICY_NAME;
        }
    }

    private final NamePolicy namePolicy;
    private final LastNamePolicy lastNamePolicy;
    private final DatePolicy hireDatePolicy;

    public EmployeePolicy() {
        this.namePolicy = new NamePolicy();
        this.lastNamePolicy = new LastNamePolicy();
        this.hireDatePolicy = new DatePolicy();
    }

    public String test(String firstName, String lastName, String email, String phone, String hireDate,
                       String jobId, String salary, String commissionPct, String managerId, String departmentId) {
        ArrayList<String> errors = new ArrayList<>();

        errors.add(namePolicy.testAll(firstName));
        errors.add(lastNamePolicy.testAll(lastName));
        if (email == null || email.isEmpty()) {
            errors.add("Email is empty");
        } else if (!email.matches("^[a-z][a-z1-9._-]*@[a-z]+\\.[a-z]{1,3}$")) {
            errors.add("Email is not valid");
        }
        if (phone == null || phone.isEmpty()) {
            errors.add("Phone is empty");
        } else if (!phone.matches("^(\\+\\d{2,3})? ?\\d{3} ?(\\d{3} ?\\d{3}|\\d{2} ?\\d{2} ?\\d{2})$")) {
            errors.add("Phone is not valid");
        }

        errors.add(hireDatePolicy.testAll(hireDate));

        try {
            int jobIdInt = Integer.parseInt(jobId);
            if (jobIdInt < 0) {
                errors.add("Job ID must be a positive number.");
            }
        }
        catch (NumberFormatException e) {
            errors.add("Job ID must be a number greater or equal 0.");
        }
        try {
            double salaryDouble = Double.parseDouble(salary);
            if (salaryDouble < 0) {
                errors.add("Salary must be a positive number.");
            }
        }
        catch (NumberFormatException e) {
            errors.add("Salary must be a number greater or equal 0.");
        }
        try {
            double commissionPctDouble = Double.parseDouble(commissionPct);
            if (commissionPctDouble < 0 || commissionPctDouble >= 100) {
                errors.add("Commission should be a percentage.");
            }
        }
        catch (NumberFormatException e) {
            errors.add("Commission should be a percentage.");
        }

        try {
            int managerIdInt = Integer.parseInt(managerId);
            if (managerIdInt < 0) {
                errors.add("Manager ID must be a positive number.");
            }
        }
        catch (NumberFormatException e) {
            errors.add("Manager ID must be a number greater or equal 0.");
        }

        try {
            int departmentIdInt = Integer.parseInt(departmentId);
            if (departmentIdInt < 0) {
                errors.add("Department ID must be a positive number.");
            }
        }
        catch (NumberFormatException e) {
            errors.add("Department ID must be a number greater or equal 0.");
        }

        return String.join("\n", errors).trim();
    }
}
