package project.core;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }

    //polymorphism, product can either be canteenitem or bookcentitem
    public void addOrUpdate(Product product, int quantity) {
        for (CartItem ci : items) {
            if (ci.getProduct().getProductID().equals(product.getProductID())) {
                if (quantity <= 0) {
                    items.remove(ci);
                } else {
                    ci.setQuantity(quantity);
                }
                return;
            }
        }

        if (quantity > 0) {
            items.add(new CartItem(product, quantity));
        }
    }

    public void clear() {
        items.clear();
    }

    public double getTotal() {
        double total = 0.00;
        for (CartItem ci : items) {
            total += ci.getSubTotal();
        }
        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
