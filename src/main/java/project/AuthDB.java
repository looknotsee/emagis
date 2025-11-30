package project;

import java.util.HashMap;
import java.util.Map;

public class AuthDB {
    private final Map<String, UserAccount> users = new HashMap<>();

    public AuthDB() {
        users.put("20240030515", new UserAccount("20240030515", "12345"));
        users.put("20180015000", new UserAccount("20240030515", "67890"));
        users.put("20180015793", new UserAccount("20240030515", "54321"));
        users.put("200920384", new UserAccount("20240030515", "13579"));
    }

    public boolean login(String idnum, String pin) {
        UserAccount user = users.get(idnum);
        if (user == null) {
            return false;
        }
        return user.matchesPin(pin);
    }
}
