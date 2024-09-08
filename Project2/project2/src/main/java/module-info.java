module com.project2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens com.project2 to javafx.fxml;
    exports com.project2;
}
