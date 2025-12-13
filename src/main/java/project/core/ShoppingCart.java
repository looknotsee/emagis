package project.core;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<String, CartItem> items = new LinkedHashMap<>();

    public void add(Product product, int delta) {
        CartItem existing = items.get(product.getProductID());
        int newQty = (existing == null ? 0 : existing.getQuantity()) + delta;

        if (newQty <= 0) {
            items.remove(product.getProductID());
        } else {
            if (existing == null) {
                items.put(product.getProductID(), new CartItem(product, newQty));
            } else {
                existing.setQuantity(newQty);
            }
        }
    }

    public Collection<CartItem> getItems() {
        return items.values();
    }

    public double getTotal() {
        return items.values().stream()
                .mapToDouble(CartItem::getSubTotal)
                .sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }
}
