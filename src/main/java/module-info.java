module com.vincentgruse.hashcracker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.vincentgruse.hashcracker to javafx.fxml;
    exports com.vincentgruse.hashcracker;
}