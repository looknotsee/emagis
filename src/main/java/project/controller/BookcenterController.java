package project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class BookcenterController {
    
    @FXML
    private Button minusButton;

    @FXML
    private Button addButton;

    @FXML
    private Spinner<Integer> QtySpinner;

    public void initialize() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99,1, 1);
        QtySpinner.setValueFactory(valueFactory);
        QtySpinner.setEditable(false);
}
    @FXML
    public void onMinusClick() {
        QtySpinner.decrement(1);
    } 

    @FXML
    public void onAddClick() {
        QtySpinner.increment(1);
    }
}
