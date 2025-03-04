package com.example.tipcalculatorlab;// TipCalculatorController.java
// Controller that handles calculateButton and tipPercentageSlider events

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipCalculatorController {
    // formatters for currency and percentages
    private static final NumberFormat currency =
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent =
            NumberFormat.getPercentInstance();

    private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default

    // GUI controls defined in FXML and used by the controller's code
    @FXML
    private TextField amountTextField;

    @FXML
    private Label tipPercentageLabel;

    @FXML
    private Slider tipPercentageSlider;

    @FXML
    private TextField tipTextField;

    @FXML
    private TextField totalTextField;

    // calculates and displays the tip and total amounts
    @FXML
    private void calculateTipAndTotal() {
        try {
            BigDecimal billAmount = new BigDecimal(amountTextField.getText());
            BigDecimal tipAmount = billAmount.multiply(tipPercentage).setScale(2, RoundingMode.HALF_UP);
            BigDecimal totalAmount = billAmount.add(tipAmount);

            tipTextField.setText(currency.format(tipAmount));
            totalTextField.setText(currency.format(totalAmount));
        } catch (NumberFormatException e) {
            tipTextField.setText(""); // Clear tip field if input is invalid
            totalTextField.setText(""); // Clear total field if input is invalid
        }
    }

    // called by FXMLLoader to initialize the controller
    public void initialize() {
        currency.setRoundingMode(RoundingMode.HALF_UP);

        // Listener for changes to tipPercentageSlider's value
        tipPercentageSlider.valueProperty().addListener((ov, oldValue, newValue) -> {
            tipPercentage = BigDecimal.valueOf(newValue.intValue()).divide(BigDecimal.valueOf(100));
            tipPercentageLabel.setText(percent.format(tipPercentage));
            calculateTipAndTotal(); // Recalculate when tip percentage changes
        });

        amountTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            calculateTipAndTotal();
        });
    }
}
