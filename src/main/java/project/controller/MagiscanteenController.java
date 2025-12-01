package project.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import project.core.App;

public class MagiscanteenController {

    @FXML
    public void onBackClick() {
        try {
            App.setRoot("home2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
