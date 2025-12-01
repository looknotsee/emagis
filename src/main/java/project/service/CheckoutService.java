package project.service;
import project.core.ShoppingCart;
import project.core.UserAccount;

public class CheckoutService {
    public boolean checkout(UserAccount user) {
        ShoppingCart cart = user.getCart();
        if (cart.isEmpty()) {
            return false;
        }

        double total = cart.getTotal();
        PaymentMethod payment = new WalletPayment(user.getWallet());

        if (payment.getAvailableBalance() < total) {
            return false;
        }

        boolean success = payment.pay(total, "Canteen/Bookcenter purchase");
        if (success) {
            cart.clear();
        }
        return success;
    }
}
