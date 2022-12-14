package org.jkutkut.hr_app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.jkutkut.exception.InvalidDataException;
import org.jkutkut.hr_app.db.HRDB;
import org.jkutkut.hr_app.javabean.Employee;
import org.jkutkut.javafx.Controller;

import java.util.ArrayList;

public class ListController extends Controller {
    // ********** Constants and variables **********
    public static final String XML = "view/list.fxml";

    private HRDB db;

    @FXML
    public ChoiceBox<String> searchMenu;

    @FXML
    public TextField txtfSearch;
    @FXML
    public Button btnSearch;

    @FXML
    private TableView<Employee> table;
    @FXML
    private TableColumn<Employee, String> idColumn;
    @FXML
    private TableColumn<Employee, String> firstNameColumn;
    @FXML
    private TableColumn<Employee, String> lastNameColumn;
    @FXML
    private TableColumn<Employee, String> emailColumn;
    @FXML
    private TableColumn<Employee, String> phoneColumn;
    @FXML
    private TableColumn<Employee, String> hireDateColumn;
    @FXML
    private TableColumn<Employee, String> jobIdColumn;
    @FXML
    private TableColumn<Employee, String> salaryColumn;
    @FXML
    private TableColumn<Employee, String> commissionPctColumn;
    @FXML
    private TableColumn<Employee, String> managerIdColumn;
    @FXML
    private TableColumn<Employee, String> departmentIdColumn;

    // ********** Methods **********
    public void reset() {
        txtfSearch.setText("");
        searchMenu.setValue("ID");
        table.getItems().clear();
        table.setPlaceholder(new Label(""));
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asString());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        hireDateColumn.setCellValueFactory(cellData -> cellData.getValue().hireDateProperty().asString());
        jobIdColumn.setCellValueFactory(cellData -> cellData.getValue().jobIdProperty());
        salaryColumn.setCellValueFactory(cellData -> cellData.getValue().salaryProperty().asString());
        commissionPctColumn.setCellValueFactory(cellData -> cellData.getValue().commissionPctProperty().asString());
        managerIdColumn.setCellValueFactory(cellData -> cellData.getValue().managerIdProperty().asString());
        departmentIdColumn.setCellValueFactory(cellData -> cellData.getValue().departmentIdProperty().asString());
    }

    @FXML
    public void handleSearchAll() {
        ArrayList<Employee> employees = db.getAllEmployees();
        table.getItems().clear();
        table.getItems().addAll(employees);
    }

    @FXML
    public void handleEdit() {
        Employee employee = getSelectedEmployee();
        if (employee == null) {
            mainApp.warn("No employee selected","No employee selected","Please select an employee to modify.");
            return;
        }
        Employee newEmployee = mainApp.editEmployee(employee);
        if (newEmployee == null) // Nothing changed
            return;
        // Update table
        table.getItems().remove(employee);
        table.getItems().add(newEmployee);
        table.refresh();
    }

    @FXML
    public void handleDelete() {
        Employee employee = getSelectedEmployee();
        if (employee != null) {
            if (!mainApp.confirm("Delete employee", "Are you sure you want to delete this employee?", "This action cannot be undone."))
                return;
            int result = db.deleteEmployee(employee);
            if (result == HRDB.SUCCESS) {
                table.getItems().remove(employee);
                mainApp.info("Employee deleted","Employee deleted successfully.",employee.getFirstName() + " is no longer in the database.");
            }
            else {
                mainApp.error("Error deleting employee","Error deleting employee.","The database was not able to delete the employee.");
            }
        }
        else {
            mainApp.warn("No employee selected","No employee selected","Please select an employee to delete.");
        }
    }

    @FXML
    public void handleSearch() {
        int searchBy = searchMenu.getSelectionModel().getSelectedIndex();
        String key = txtfSearch.getText().trim();
        if (key.isEmpty()) {
            mainApp.warn("No search key","No search key","Please enter a search key.");
            return;
        }
        try {
            ArrayList<Employee> employees = db.searchBy(searchBy, key);
            table.getItems().clear();
            if (employees == null) {
                mainApp.warn("No results","No results","No employees were found with the search key.");
                return;
            }
            table.getItems().addAll(employees);
        }
        catch (InvalidDataException e) {
            mainApp.error("Error on database","Not able to obtain the data",e.getMessage());
        }
    }

    // ********** Getters and setters **********
    public void setDB(HRDB db) {
        this.db = db;
    }

    public Employee getSelectedEmployee() {
        return table.getSelectionModel().getSelectedItem();
    }
}
