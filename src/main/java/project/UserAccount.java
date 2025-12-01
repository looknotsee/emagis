package project;

public class UserAccount {
    //encap
    private final String idnum;
    private final String pin;
    private final UserWallet wallet;

    public UserAccount(String idnum, String pin, double initialBal) {
        this.idnum = idnum;
        this.pin = pin;
        this.wallet = new UserWallet(initialBal);
    }

    public String getIdNum () {
        return idnum;        
    }

    public UserWallet getWallet() {
        return wallet;
    }

    public boolean matchesPin(String inputPin) {
        return this.pin.equals(inputPin);
    }
}
