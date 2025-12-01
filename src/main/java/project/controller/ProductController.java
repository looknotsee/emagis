package project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import project.core.Product;
import project.core.ShoppingCart;
import project.core.UserAccount;
import project.service.Session;

public class ProductController {
    
    @FXML
    private Spinner<Integer> QtySpinner;

    private Product product;
    private UserAccount currentUser;

    public void initialize() {
        currentUser = Session.getCurrentUser();

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0, 1);
        QtySpinner.setValueFactory(valueFactory);
         
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @FXML
    private void onAddCartClick() {
        int qty = QtySpinner.getValue();
        ShoppingCart cart = currentUser.getCart();
        cart.addOrUpdate(product, qty);
    }
}

