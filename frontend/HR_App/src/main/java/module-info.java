module org.jkutkut.hr_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.jkutkut.hr_app to javafx.fxml;
    exports org.jkutkut.hr_app;
    exports org.jkutkut;
    opens org.jkutkut to javafx.fxml;
    exports org.jkutkut.db;
    opens org.jkutkut.db to javafx.fxml;
}