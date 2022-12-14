package org.jkutkut.hr_app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.jkutkut.hr_app.MainApp;
import org.jkutkut.javafx.Controller;

public class RootController extends Controller {

    @FXML
    private void handleAbout() {
        mainApp.info(MainApp.APP_NAME, "About", "Author: Jkutkut");
    }

    @FXML
    private void handleExit() {
        mainApp.exitApplication();
    }


}
