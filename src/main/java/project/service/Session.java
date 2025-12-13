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
        return CART;
    }

}
