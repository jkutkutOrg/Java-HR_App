module org.jkutkut.hr_app {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.jkutkut.hr_app to javafx.fxml;
    exports org.jkutkut.hr_app;
}