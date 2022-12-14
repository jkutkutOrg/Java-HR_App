package org.jkutkut.hr_app;

import javafx.scene.layout.AnchorPane;
import org.jkutkut.hr_app.controller.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jkutkut.hr_app.db.HRDB;
import org.jkutkut.javafx.Controller;
import org.jkutkut.javafx.JavafxApp;

import java.io.IOException;

public class MainApp extends JavafxApp {
    // ********** Constants and variables **********
    public static final String APP_NAME = "HR App";
    private static final String LOGO = "file:src/main/resources/org/jkutkut/hr_app/images/logo.png";
    private static final int MIN_WIDTH = 800;
    private static final int MIN_HEIGHT = 500;

    private HRDB db;

    // ********** UI **********
    private BorderPane rootLayout;
    private RootController rootController;
    private AnchorPane listLayout;
    private ListController listController;

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
        FXMLLoader appLoader = new FXMLLoader(MainApp.class.getResource(RootController.XML));
        rootLayout = appLoader.load();
        rootController = appLoader.getController();
        rootController.setMainApp(this);

        // Login menu
        FXMLLoader loginLoader = new FXMLLoader(MainApp.class.getResource(LoginController.XML));
        AnchorPane loginMenu = loginLoader.load();
        LoginController controller = loginLoader.getController();
        controller.setMainApp(this);

        // List menu
        FXMLLoader listLoader = new FXMLLoader(MainApp.class.getResource(ListController.XML));
        listLayout = listLoader.load();
        listController = listLoader.getController();
        listController.setMainApp(this);

        // Default menu
        rootController.setLoggedMode(false);
        rootLayout.setCenter(loginMenu);
    }

    // ********** App methods **********
    public void login() {
        db = HRDB.createInstance();
        rootController.setLoggedMode(true);
        go2("list");
    }

    public void go2(String layout) {
        Controller controller;
        AnchorPane pane;
        switch (layout) {
            case "list":
                controller = listController;
                pane = listLayout;
                break;
            default:
                error("Layout not found", "Layout " + layout + " not found", "Please, contact the developer");
                return;
        }
        controller.reset();
        rootLayout.setCenter(pane);
    }
}
