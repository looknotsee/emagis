package project.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import project.core.App;
import project.core.UserAccount;
import project.core.UserWallet;
import project.service.Session;

public class HomeController {
    
    @FXML
    private Label balanceLabel;

    @FXML
    private Button canteenButton;

    @FXML
    private Button BCButton;

    @FXML
    private Button profileButton;


    private UserWallet wallet;

    private UserAccount currentUser;

    public void setWallet(UserWallet wallet) {
        this.wallet = wallet;
        updateBalLabel();
    }

    public void updateBalLabel() {
        if (wallet != null) {
            balanceLabel.setText("₱ " + wallet.getBal());
        }
    }

    public void initialize() {
        currentUser = Session.getCurrentUser();
        if (currentUser != null) {
            this.wallet = currentUser.getWallet();
            updateBalLabel();
        }
    }

    public void refreshBal() {
        updateBalLabel();
    }

    @FXML
    public void onCanteenClick() {
        try {
            App.setRoot("magiscanteen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onBookClick() {
        try {
            App.setRoot("bookcenter");
            System.out.println("went to book");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
