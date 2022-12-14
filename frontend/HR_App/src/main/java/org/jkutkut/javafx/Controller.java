package org.jkutkut.javafx;

import org.jkutkut.hr_app.MainApp;

/**
 * General class to control a JavaFX menu.
 *
 * @author kiol12 and jkutkut
 */
abstract public class Controller {
    protected MainApp mainApp;

    /**
     * Resets the UI.
     */
    abstract public void reset();

    // ********** Setters **********
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
