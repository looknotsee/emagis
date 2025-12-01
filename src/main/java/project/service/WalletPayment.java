package project.service;
import project.core.UserWallet;

public class WalletPayment implements PaymentMethod {
    
    private final UserWallet wallet;

    public WalletPayment(UserWallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public boolean pay(double amount, String description) {
        return wallet.pay(amount);
    }

    @Override
    public double getAvailableBalance() {
        return wallet.getBal();
    }
}
