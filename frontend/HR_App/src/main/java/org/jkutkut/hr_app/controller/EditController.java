package org.jkutkut.hr_app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jkutkut.hr_app.db.HRDB;
import org.jkutkut.hr_app.javabean.Employee;
import org.jkutkut.hr_app.utils.DateUtil;
import org.jkutkut.hr_app.utils.EmployeePolicy;
import org.jkutkut.javafx.Controller;

/**
 * Controller for the Edit Employee view.
 */
public class EditController extends Controller {
    // ********** Constants and variables **********
    public static final String XML = "view/editEmployee.fxml";

    // ********** UI **********
    private Stage dialogStage;
    private Employee employee;
    private boolean okClicked = false;
    private HRDB db;

    @FXML
    private TextField txtfId;
    @FXML
    private TextField txtfFirstName;
    @FXML
    private TextField txtfLastName;
    @FXML
    private TextField txtfEmail;
    @FXML
    private TextField txtfPhone;
    @FXML
    private TextField txtfHireDate;
    @FXML
    private TextField txtfJobId;
    @FXML
    private TextField txtfSalary;
    @FXML
    private TextField txtfCommissionPct;
    @FXML
    private TextField txtfManagerId;
    @FXML
    private TextField txtfDepartmentId;

    // ********** Methods **********
    public void reset() {
        txtfId.setText(String.format("%d", employee.getId()));
        txtfFirstName.setText(employee.getFirstName());
        txtfLastName.setText(employee.getLastName());
        txtfEmail.setText(employee.getEmail());
        txtfPhone.setText(employee.getPhone());
        txtfHireDate.setText(DateUtil.format(employee.getHireDate()));
        txtfJobId.setText(employee.getJobId());
        txtfSalary.setText(String.format("%.2f", employee.getSalary()));
        txtfCommissionPct.setText(String.format("%.2f", employee.getCommissionPct()));
        txtfManagerId.setText(String.format("%d", employee.getManagerId()));
        txtfDepartmentId.setText(String.format("%d", employee.getDepartmentId()));
    }

    /**
     * Handles the reset button.
     */
    @FXML
    public void handleClear(){
        reset();
    }

    /**
     * Handles the Save button.
     */
    @FXML
    public void handleSave() {
        if (inputValid()) {
            Employee newEmployee = new Employee();
            newEmployee.setFirstName(txtfFirstName.getText());
            newEmployee.setLastName(txtfLastName.getText());
            newEmployee.setEmail(txtfEmail.getText());
            newEmployee.setPhone(txtfPhone.getText());
            newEmployee.setHireDate(DateUtil.parse(txtfHireDate.getText()));
            newEmployee.setJobId(txtfJobId.getText());
            newEmployee.setSalary(Double.parseDouble(txtfSalary.getText()));
            newEmployee.setCommissionPct(Double.parseDouble(txtfCommissionPct.getText()));
            newEmployee.setManagerId(Integer.parseInt(txtfManagerId.getText()));
            newEmployee.setDepartmentId(Integer.parseInt(txtfDepartmentId.getText()));

            int result = db.updateEmployee(employee, newEmployee);
            if (result == HRDB.FAILURE) {
                mainApp.error("Error", "Error adding employee", "Not able to add the employee to the database");
                return;
            }
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Checks if the user input is valid.
     * Alerts the user if the input is not valid.
     * @return true if the input is valid. False otherwise.
     */
    public boolean inputValid() {
        EmployeePolicy policy = new EmployeePolicy();
        String error = policy.test(
            txtfFirstName.getText(),
            txtfLastName.getText(),
            txtfEmail.getText(),
            txtfPhone.getText(),
            txtfHireDate.getText(),
            txtfJobId.getText(),
            txtfSalary.getText(),
            txtfCommissionPct.getText(),
            txtfManagerId.getText(),
            txtfDepartmentId.getText()
        );
        if (error == null || error.isEmpty()) {
            return true;
        } else {
            mainApp.error("Error", "Invalid data", error);
            return false;
        }
    }

    /**
     * Handles the Cancel button.
     */
    public void handleCancel() {
        dialogStage.close();
    }

    /**
     * Holds the execution until the dialog is closed.
     */
    public void showAndWait() {
        dialogStage.showAndWait();
    }

    // ********** Getters and setters **********
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        dialogStage.setMaxHeight(450);
        dialogStage.setMaxWidth(720);
        dialogStage.setMinHeight(450);
        dialogStage.setMinWidth(720);
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setDB(HRDB db) {
        this.db = db;
    }
}
