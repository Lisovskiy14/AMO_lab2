module com.example.amo_lab2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.amo_lab2 to javafx.fxml;
    exports com.example.amo_lab2;
}