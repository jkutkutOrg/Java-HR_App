package org.jkutkut.hr_app;

import javafx.scene.layout.AnchorPane;
import org.jkutkut.hr_app.controller.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jkutkut.hr_app.db.HRDB;
import org.jkutkut.javafx.JavafxApp;

import java.io.IOException;

public class MainApp extends JavafxApp {
    // ********** Constants and variables **********
    public static final String APP_NAME = "HR App";
    private static final String LOGO = "file:src/main/resources/org/jkutkut/hr_app/images/logo.png";
    private static final String APP_XML = "view/app.fxml";
    private static final String LOGIN_MENU_XML = "view/login.fxml";
    private static final int MIN_WIDTH = 800;
    private static final int MIN_HEIGHT = 500;

    // ********** UI **********
    private BorderPane rootLayout;

    // ********** Class methods **********
    public static void main(String[] args) {
        launch(args);
    }

    public MainApp() {
        super(APP_NAME, LOGO);
    }

    @Override
    public void start(Stage stage) throws IOException {
        super.start(stage);
        loadLayouts();
        primaryStage.setOnCloseRequest(e -> {
            e.consume();
            exitApplication();
        });
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setScene(new Scene(rootLayout));
        stage.show();
    }

    private void loadLayouts() throws IOException {
        // App body
        FXMLLoader appLoader = new FXMLLoader(MainApp.class.getResource(APP_XML));
        rootLayout = appLoader.load();
        RootController appController = appLoader.getController();
        appController.setMainApp(this);

        // Login menu
        FXMLLoader loginLoader = new FXMLLoader(MainApp.class.getResource(LOGIN_MENU_XML));
        AnchorPane loginMenu = loginLoader.load();
        LoginController controller = loginLoader.getController();
        controller.setMainApp(this);

        // Default menu
        rootLayout.setCenter(loginMenu);
    }

    // ********** App methods **********
    private HRDB db;
    public void login() {
        db = HRDB.createInstance();
        rootLayout.setCenter(null); // TODO
    }
}
