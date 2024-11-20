module com.example.projetmaintbionew {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;


    opens com.example.projetmaintbionew to javafx.fxml;
    exports com.example.projetmaintbionew;
}