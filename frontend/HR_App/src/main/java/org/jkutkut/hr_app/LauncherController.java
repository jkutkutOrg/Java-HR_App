package org.jkutkut.hr_app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LauncherController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}