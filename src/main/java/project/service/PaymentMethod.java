package project.service;

public interface PaymentMethod {
    boolean pay(double amount, String description);
    double getAvailableBalance();
}
