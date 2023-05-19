package com.example;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.ResourceBundle;

public class DatePickerExampleController implements Initializable {
    @FXML
    private DatePicker datePicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Add event handling logic or access the selected date from the DatePicker control
        datePicker.setOnAction(event -> {
            System.out.println("Selected date: " + datePicker.getValue());
        });
    }
}
