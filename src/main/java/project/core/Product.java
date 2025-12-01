package project.core;

public abstract class Product {
    private final String id;
    private final String name;
    private final double price;

    protected Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getProductID() {
        return id;
    }

    public String getProductName() {
        return name;
    }

    public double getProductPrice() {
        return price;
    }

    //abstraction
    public abstract String getCategoryLabel(); 
}
