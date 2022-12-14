package org.jkutkut.hr_app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.jkutkut.db.configuration.ConfiguratorDB;
import org.jkutkut.javafx.Controller;

/**
 * Controller for the login menu.
 *
 * @author kiol12 and jkutkut
 */
public class LoginController extends Controller {

    @FXML
    private TextField txtfUser;

    @FXML
    private PasswordField password;

    @FXML
    private Button btnLogin;

    // ********** Methods **********
    @FXML
    private void initialize() {}

    /**
     * Handles the login button.
     * It checks if the user and password are correct.
     */
    @FXML
    private void handleLogin() {
        String user = txtfUser.getText();
        String pass = password.getText();

        ConfiguratorDB configuratorDB = new ConfiguratorDB("../../db/.env", "localhost");
        if (configuratorDB.get("DB_USR").equals(user) && configuratorDB.get("DB_USR_PASSWD").equals(pass)) {
            mainApp.login();
        } else {
            mainApp.error("Login error", "Invalid credentials", "Please, check your credentials");
        }
    }
}
