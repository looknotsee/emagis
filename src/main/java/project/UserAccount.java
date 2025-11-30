package project;

public class UserAccount {
    
    private final String idnum;
    private final String pin;

    public UserAccount(String idnum, String pin) {
        this.idnum = idnum;
        this.pin = pin;
    }

    public String getIdNum () {
        return idnum;        
    }

    public boolean matchesPin(String inputPin) {
        return this.pin.equals(inputPin);
    }
}
