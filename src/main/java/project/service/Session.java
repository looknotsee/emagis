package project.service;

import project.core.ShoppingCart;
import project.core.UserAccount;

public class Session {
    private static UserAccount currentUser;
    private static final ShoppingCart CART = new ShoppingCart();

    public static void setCurrentUser(UserAccount user) {
        currentUser = user;
    }
    
    public static UserAccount getCurrentUser() {
        return currentUser;
    }

    public static ShoppingCart getCart() {
        // If a user is logged in, use their personal cart so controllers and services
        // operate on the same cart instance. Otherwise fall back to the shared guest cart.
        if (currentUser != null) return currentUser.getCart();
        return CART;
    }

}
