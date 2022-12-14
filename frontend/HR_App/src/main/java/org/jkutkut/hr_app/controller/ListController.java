package org.jkutkut.hr_app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.jkutkut.hr_app.javabean.Employee;
import org.jkutkut.javafx.Controller;

public class ListController extends Controller {
    // ********** Constants and variables **********
    public static final String XML = "view/list.fxml";

    @FXML
    public SplitMenuButton splitMenu;
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

        // TODO change the default value of selected item in search
    }

    // TODO add logic
}
