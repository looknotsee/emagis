package project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {
    
    @FXML
    private Label balanceLabel;

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

}
