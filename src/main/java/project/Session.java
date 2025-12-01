package project;

public class Session {
    private static UserAccount currentUser;

    public static void setCurrentUser(UserAccount user) {
        currentUser = user;
    }
    
    public static UserAccount getCurrentUser() {
        return currentUser;
    }

}
