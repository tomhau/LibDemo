module com.example.libdatabase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.libdatabase to javafx.fxml;
    exports com.example.libdatabase;
}