module org.jkutkut.hr_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.jkutkut.hr_app to javafx.fxml;
    exports org.jkutkut.hr_app;
    opens org.jkutkut.exception to javafx.fxml;
    exports org.jkutkut.exception;
    opens org.jkutkut.db to javafx.fxml;
    exports org.jkutkut.db;
}