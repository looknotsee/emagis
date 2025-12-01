package project.core;

public class UserWallet {
    //encap
    private double balance;

    public UserWallet(double initialBal) {
        this.balance = initialBal;
    }

    public double getBal() {
        return balance;
    }

    public void load(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }

        balance += amount;
    }


    public boolean pay(double amount) {
        if (balance < amount) return false;
        if (amount <= 0) return false;
        balance -= amount;
        return true;
    }

    public boolean canAfford(double amount) {
        return amount > 0 && amount <= balance;
    }
}
