package project;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController {
    
    @FXML
    private TextField idField;

    @FXML
    private PasswordField pinField;

    @FXML
    private Label errorLabel;

    private AuthDB authDB;

    public LoginController() {
        this.authDB = new AuthDB();
    }

    @FXML
    private void onLoginClick() {
        String id = idField.getText().trim();
        String pin = pinField.getText().trim();

        errorLabel.setVisible(false);

        if (id.isEmpty()) {
            errorLabel.setText("Please fill in Student ID field.");
            errorLabel.setVisible(true);
            return;
        }

        if (pin.isEmpty()) {
            errorLabel.setText("Please fill in PIN field.");
            errorLabel.setVisible(true);
            return;
        }

    UserAccount user = authDB.login(id, pin);

    if (user != null) {
        Session.setCurrentUser(user);
        try {
            App.setRoot("home");
        } catch (IOException e) {
            e.printStackTrace();
            errorLabel.setText("Failed to load into homescreen.");
            errorLabel.setVisible(true);
        }
        
    } else {
        errorLabel.setText("Invalid ID Number or PIN.");
        errorLabel.setVisible(true);
    }
}   
}
