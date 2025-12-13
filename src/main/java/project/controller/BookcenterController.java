package project.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Pane;
import project.core.App;
import project.core.BookItem;
import project.core.Product;
import project.core.ShoppingCart;
import project.core.UserAccount;
import project.service.Session;   

public class BookcenterController {

    @FXML
    private Pane centerPane;

    // we'll initialize all Spinners under `centerPane` so FXML ids don't have to match field names

    private UserAccount currentUser;
    private ShoppingCart bookcenterCart;

    public void initialize() {
        currentUser = Session.getCurrentUser();
        // the UserAccount stores a general cart; guard against missing session
        if (currentUser != null) {
            bookcenterCart = currentUser.getCart();
        } else {
            // create a temporary cart so controller logic can run safely when no user is signed in
            bookcenterCart = new ShoppingCart();
        }

        // init all spinners under the centerPane: min 0, max 99, start 0, step 1
        if (centerPane != null) {
            initializeSpinners(centerPane);
        }
    }

 
    private int getQty(Spinner<Integer> spinner) {
        return spinner.getValue();
    }

    private void setQty(Spinner<Integer> spinner, int value) {
        if (value < 0) value = 0;
        spinner.getValueFactory().setValue(value);
    }


    @FXML
    private void onMinusClick(ActionEvent event) {
        Node q = findSiblingQuantityNode(event);
        if (q instanceof Spinner) ((Spinner<Integer>) q).decrement(1);
        else if (q instanceof Label) decrementLabel((Label) q);
    }

    @FXML
    private void onAddClick(ActionEvent event) {
        Node q = findSiblingQuantityNode(event);
        if (q instanceof Spinner) ((Spinner<Integer>) q).increment(1);
        else if (q instanceof Label) incrementLabel((Label) q);
    }

  
    @SuppressWarnings("unchecked")
    private Node findSiblingQuantityNode(ActionEvent event) {
        Node src = (Node) event.getSource();
        Parent parent = src.getParent();
        if (parent == null) return null;
        for (Node child : parent.getChildrenUnmodifiable()) {
            if (child instanceof Spinner) return child;
            if (child instanceof Label) return child;
        }
        return null;
    }

    private void incrementLabel(Label l) {
        try {
            int v = Integer.parseInt(l.getText().trim());
            l.setText(String.valueOf(v + 1));
        } catch (NumberFormatException ex) {
            l.setText("1");
        }
    }

    private void decrementLabel(Label l) {
        try {
            int v = Integer.parseInt(l.getText().trim());
            v = Math.max(0, v - 1);
            l.setText(String.valueOf(v));
        } catch (NumberFormatException ex) {
            l.setText("0");
        }
    }

    @FXML
    private void onBackClick(ActionEvent event) throws Exception {
        App.setRoot("home");
    }

    // recursively initialize spinners under a parent node
    @SuppressWarnings("unchecked")
    private void initializeSpinners(Parent root) {
        for (Node child : root.getChildrenUnmodifiable()) {
            if (child instanceof Spinner) {
                Spinner<Integer> s = (Spinner<Integer>) child;
                s.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99, 0, 1));
            } else if (child instanceof Parent) {
                initializeSpinners((Parent) child);
            }
        }
    }

    @FXML
    private void onCheckoutClick() throws Exception {
        // sync spinner values into the BOOKCENTER cart
        bookcenterCart.clear();

        // collect all spinners in visual order; first=ballpen, second=bond
        java.util.List<Spinner<Integer>> spinners = new java.util.ArrayList<>();
        if (centerPane != null) collectSpinners(centerPane, spinners);

        int ballpenQty = spinners.size() > 0 ? spinners.get(0).getValue() : 0;
        int bondQty    = spinners.size() > 1 ? spinners.get(1).getValue() : 0;

        if (ballpenQty > 0) {
            Product ballpen = new BookItem("B1", "Ballpen", 20);   // put real price
            bookcenterCart.add(ballpen, ballpenQty);
        }

        if (bondQty > 0) {
            Product bond = new BookItem("B2", "Bond Paper", 50);   // put real price
            bookcenterCart.add(bond, bondQty);
        }

        // go to checkout screen
        App.setRoot("checkout");   // go to checkout.fxml
    }

    // collect spinners recursively
    private void collectSpinners(Parent root, java.util.List<Spinner<Integer>> out) {
        for (Node child : root.getChildrenUnmodifiable()) {
            if (child instanceof Spinner) out.add((Spinner<Integer>) child);
            else if (child instanceof Parent) collectSpinners((Parent) child, out);
        }
    }
}