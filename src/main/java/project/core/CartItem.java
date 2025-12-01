package project.core;

public class CartItem {
    
    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative.");
        this.product = product;
        this.quantity = quantity;
    }


public Product getProduct() {
    return product;
}

public int getQuantity() {
    return quantity;
}

public void setQuantity(int quantity) {
    if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative.");
    this.quantity = quantity;
}

public double getSubTotal() {
    return product.getProductPrice() * quantity;
}
}