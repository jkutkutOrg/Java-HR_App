package org.jkutkut.hr_app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import org.jkutkut.hr_app.MainApp;
import org.jkutkut.javafx.Controller;

/**
 * Controller for the root layout. It handles the menu bar.
 *
 * @author kiol12 and jkutkut
 */
public class RootController extends Controller {
    // ********** Constants and variables **********
    public static final String XML = "view/app.fxml";

    @FXML
    private MenuBar menuBar;

    // ********** Methods **********
    public void reset() {}

    /**
     * Changes the elements to show on the menu based on the logged status of the user.
     * @param logged True if the user is logged, false otherwise.
     */
    public void setLoggedMode(boolean logged) {
        if (logged) {
            menuBar.getMenus().get(0).setVisible(true);
        }
        else {
            menuBar.getMenus().get(0).setVisible(false);
        }
    }

    @FXML
    private void handleAbout() {
        mainApp.info(MainApp.APP_NAME, "About", "Authors: Kiol12 and Jkutkut. Based on the JavaFX tutorial by SWTESTACADEMY.");
    }

    @FXML
    private void handleList() {
        mainApp.go2("list");
    }

    @FXML
    private void handleAdd() {
        mainApp.go2("add");
    }
}
