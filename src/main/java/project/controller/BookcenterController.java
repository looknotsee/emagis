package project.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import project.core.App;

public class BookcenterController {

    @FXML
    private Button minusButton;

    @FXML
    private Button addButton;

        @FXML
        private Spinner<Integer> QtyLabel;

    public void initialize() {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99, 1, 1);
            QtyLabel.setValueFactory(valueFactory);
            QtyLabel.setEditable(false);
    }

    @FXML
    public void onMinusClick() {
            QtyLabel.decrement(1);
    }

    @FXML
    public void onAddClick() {
            QtyLabel.increment(1);
    }

        @FXML
        public void onBackClick() {
            try {
                App.setRoot("home");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
