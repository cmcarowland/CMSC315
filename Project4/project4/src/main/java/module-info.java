module com.project4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens com.project4 to javafx.fxml;
    exports com.project4;
}
