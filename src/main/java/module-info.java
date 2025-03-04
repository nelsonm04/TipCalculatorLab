module com.example.tipcalculatorlab {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tipcalculatorlab to javafx.fxml;
    exports com.example.tipcalculatorlab;
}