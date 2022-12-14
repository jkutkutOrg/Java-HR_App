package org.jkutkut.hr_app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jkutkut.hr_app.MainApp;
import org.jkutkut.hr_app.db.HRDB;
import org.jkutkut.hr_app.javabean.Employee;
import org.jkutkut.javafx.Controller;

public class AddController extends Controller {
    // ********** Constants and variables **********
    public static final String XML = "view/addEmployee.fxml";

    // ********** UI **********
    private Stage dialogStage;
    private Employee employee;
    private boolean okClicked = false;
    private HRDB db;

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
        txtfFirstName.setText("");
        txtfLastName.setText("");
        txtfEmail.setText("");
        txtfPhone.setText("");
        txtfHireDate.setText("");
        txtfJobId.setText("");
        txtfSalary.setText("");
        txtfCommissionPct.setText("");
        txtfManagerId.setText("");
        txtfDepartmentId.setText("");
    }

    @FXML
    public void handleClear(){
        reset();
    }

    public void handleSave() {
        // TODO check if the input is valid

//        employee.setFirstName(txtfFirstName.getText());
//        employee.setLastName(txtfLastName.getText());
//        employee.setEmail(txtfEmail.getText());
//        employee.setPhone(txtfPhone.getText());
//        employee.setHireDate(txtfHireDate.getText()); // TODO
//        employee.setJobId(txtfJobId.getText());
//        employee.setSalary(Double.parseDouble(txtfSalary.getText()));
//        employee.setCommissionPct(Double.parseDouble(txtfCommissionPct.getText()));
//        employee.setManagerId(Integer.parseInt(txtfManagerId.getText()));
//        employee.setDepartmentId(Integer.parseInt(txtfDepartmentId.getText()));

        int result = db.addEmployee(employee);
        if (result == HRDB.FAILURE) {
            mainApp.error("Error", "Error adding employee", "Not able to add the employee to the database");
            return;
        }
        okClicked = true;
        dialogStage.close();
    }

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
        dialogStage.setMaxWidth(650);
        dialogStage.setMinHeight(450);
        dialogStage.setMinWidth(650);
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
