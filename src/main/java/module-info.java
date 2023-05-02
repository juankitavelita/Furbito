module com.example.trabajofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.trabajofinal to javafx.fxml;
    exports com.example.trabajofinal;
}