package org.jkutkut.javafx;

import org.jkutkut.hr_app.MainApp;

abstract public class Controller {
    protected MainApp mainApp;

    // ********** Setters **********
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
