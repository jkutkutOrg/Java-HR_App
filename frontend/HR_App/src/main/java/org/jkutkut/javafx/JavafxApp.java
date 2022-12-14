package org.jkutkut.javafx;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Optional;

abstract public class JavafxApp extends Application {

    private final String appName;
    private final String logo;

    // ********** UI **********
    protected Stage stage;

    public JavafxApp(String appName, String logo) {
        this.appName = appName;
        this.logo = logo;
    }

    // ********** Class methods **********
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        setTitle(appName);
        this.stage.setOnCloseRequest(e -> {
            e.consume();
            exitApplication();
        });
        this.stage.getIcons().add(new Image(logo));
    }

    // ********** UX Methods **********

    /**
     * Changes the title of the application.
     * @param title The new title.
     */
    protected void setTitle(String title) {
        if (stage == null)
            return;
        if (title == null || title.isEmpty() || title.equals(appName))
            stage.setTitle(appName);
        else
            stage.setTitle(appName + " - " + title);
    }

    /**
     * Standard method to show a message dialog to the user.
     *
     * @param window The window to be shown on top of.
     * @param type   The type of the dialog.
     * @param title  The title of the dialog.
     * @param header The header of the dialog.
     * @param msg    The content of the dialog.
     * @return The button pressed by the user.
     */
    private static Optional<ButtonType> showAlert(Window window, Alert.AlertType type, String title, String header, String msg) {
        Alert alert = new Alert(type);
        alert.initOwner(window);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(msg);
        return alert.showAndWait();
    }

    /**
     * Standard method to show a message dialog to the user.
     * @param type The type of the dialog.
     * @param title The title of the dialog.
     * @param header The header of the dialog.
     * @param content The content of the dialog.
     */
    protected void showAlert(Alert.AlertType type, String title, String header, String content) {
        showAlert(stage, type, title, header, content);
    }

    /**
     * Standard method to show a message dialog to the user.
     * @param title The title of the dialog.
     * @param header The header of the dialog.
     * @param content The content of the dialog.
     */
    public void info(String title, String header, String content) {
        showAlert(Alert.AlertType.INFORMATION, title, header, content);
    }

    /**
     * Shows an error message to the user.
     * @param title The title of the dialog.
     * @param header The header of the dialog.
     * @param msg The content of the dialog.
     */
    public void warn(String title, String header, String msg) {
        showAlert(Alert.AlertType.WARNING, title, header, msg);
    }

    /**
     * Shows an information message to the user.
     * @param title The title of the dialog.
     * @param header The header of the dialog.
     * @param msg The content of the dialog.
     */
    public void error(String title, String header, String msg) {
        showAlert(Alert.AlertType.ERROR, title, header, msg);
    }

    /**
     * Confirms something with the user.
     * @param title The title of the dialog.
     * @param header The header of the dialog.
     * @param msg The content of the dialog.
     * @return True if the user confirmed. False otherwise.
     */
    public boolean confirm(String title, String header, String msg) {
        Optional<ButtonType> result = showAlert(stage, Alert.AlertType.CONFIRMATION, title, header, msg);
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    /**
     * Exits the application.
     */
    @FXML
    public void exitApplication() {
        boolean exit = confirm(appName, "Exit", "Are you sure you want to exit?");
        if (exit)
            System.exit(0);
    }
}
