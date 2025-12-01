package project.core;

public class UserAccount {
    //encap
    private String name;
    private final String idnum;
    private final String pin;
    private final UserWallet wallet;
    private final ShoppingCart cart;

    public UserAccount(String name, String idnum, String pin, double initialBal) {
        this.name = name;
        this.idnum = idnum;
        this.pin = pin;
        this.wallet = new UserWallet(initialBal);
        this.cart = new ShoppingCart();
    }

    public String getIdNum () {
        return idnum;        
    }

    public UserWallet getWallet() {
        return wallet;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public boolean matchesPin(String inputPin) {
        return this.pin.equals(inputPin);
    }
}
