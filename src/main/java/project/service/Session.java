package project.service;

import project.core.UserAccount;

public class Session {
    private static UserAccount currentUser;

    public static void setCurrentUser(UserAccount user) {
        currentUser = user;
    }
    
    public static UserAccount getCurrentUser() {
        return currentUser;
    }

}
