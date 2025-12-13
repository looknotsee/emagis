package project.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import project.core.App;
import project.core.CanteenItem;
import project.core.Product;
import project.core.ShoppingCart;
import project.service.Session;

public class MagiscanteenController {

    private final CanteenItem porkAdobo = new CanteenItem("C1", "Pork Adobo", 70.0);
    private final CanteenItem chickenCurry = new CanteenItem("C2", "Chicken Curry", 70.0);
    private final CanteenItem humba = new CanteenItem("C3", "Humba", 80.0);
    private final CanteenItem porkSisig = new CanteenItem("C4", "Pork Sisig", 60.0);
    private final CanteenItem friedChicken = new CanteenItem("C5", "Fried Chicken", 70.0);
    private final CanteenItem ginisangMonggo = new CanteenItem("C6", "Ginisang Monggo", 50.0);
    private final CanteenItem porkchop = new CanteenItem("C7", "Porkchop", 70.0);
    private final CanteenItem karekare = new CanteenItem("C8", "Karekare", 80.0);
    

    @FXML private Label qtyPorkAdobo;
    @FXML private Label qtyChickenCurry;
    @FXML private Label qtyHumba;
    @FXML private Label qtyPorkSisig;
    @FXML private Label qtyFriedChicken;
    @FXML private Label qtyGinisangMonggo;
    @FXML private Label qtyPorkchop;
    @FXML private Label qtyKarekare;

    @FXML private Label pricePorkAdobo;
    @FXML private Label priceChickenCurry;
    @FXML private Label priceHumba;
    @FXML private Label pricePorkSisig;
    @FXML private Label priceFriedChicken;
    @FXML private Label priceGinisangMonggo;
    @FXML private Label pricePorkchop;
    @FXML private Label priceKarekare;

    @FXML private Button checkoutButton;


    @FXML
    public void initialize() {
        if (qtyPorkAdobo != null) qtyPorkAdobo.setUserData(porkAdobo);
        else System.err.println("Warning: qtyPorkAdobo not injected (null)");

        if (qtyChickenCurry != null) qtyChickenCurry.setUserData(chickenCurry);
        else System.err.println("Warning: qtyChickenCurry not injected (null)");

        if (qtyHumba != null) qtyHumba.setUserData(humba);
        else System.err.println("Warning: qtyHumba not injected (null)");

        if (qtyPorkSisig != null) qtyPorkSisig.setUserData(porkSisig);
        else System.err.println("Warning: qtyPorkSisig not injected (null)");

        if (qtyFriedChicken != null) qtyFriedChicken.setUserData(friedChicken);
        else System.err.println("Warning: qtyFriedChicken not injected (null)");

        if (qtyGinisangMonggo != null) qtyGinisangMonggo.setUserData(ginisangMonggo);
        else System.err.println("Warning: qtyGinisangMonggo not injected (null)");

        if (qtyPorkchop != null) qtyPorkchop.setUserData(porkchop);
        else System.err.println("Warning: qtyPorkchop not injected (null)");

        if (qtyKarekare != null) qtyKarekare.setUserData(karekare);
        else System.err.println("Warning: qtyKarekare not injected (null)");

        
        if (pricePorkAdobo != null) pricePorkAdobo.setText(String.format("₱ %.2f", porkAdobo.getProductPrice()));
        if (priceChickenCurry != null) priceChickenCurry.setText(String.format("₱ %.2f", chickenCurry.getProductPrice()));
        if (priceHumba != null) priceHumba.setText(String.format("₱ %.2f", humba.getProductPrice()));
        if (pricePorkSisig != null) pricePorkSisig.setText(String.format("₱ %.2f", porkSisig.getProductPrice()));
        if (priceFriedChicken != null) priceFriedChicken.setText(String.format("₱ %.2f", friedChicken.getProductPrice()));
        if (priceGinisangMonggo != null) priceGinisangMonggo.setText(String.format("₱ %.2f", ginisangMonggo.getProductPrice()));
        if (pricePorkchop != null) pricePorkchop.setText(String.format("₱ %.2f", porkchop.getProductPrice()));
        if (priceKarekare != null) priceKarekare.setText(String.format("₱ %.2f", karekare.getProductPrice()));
    }

    @FXML
    public void onCheckoutClick() {
        try {
            App.setRoot("checkout");
            System.out.println("went to checkout");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onBackClick() {
        try {
            App.setRoot("home2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onAddClick(ActionEvent event) {
        changeQuantity(event, +1);
    }

    @FXML
    private void onMinusClick(ActionEvent event) {
        changeQuantity(event, -1);
    }

    private final ShoppingCart cart = Session.getCart();

    private void changeQuantity(ActionEvent event, int delta) {
        // Which button was clicked?
        Button clickedButton = (Button) event.getSource();

        // HBox: [-] [StackPane(circle+label)] [+]
        HBox parentBox = (HBox) clickedButton.getParent();

        // Get the StackPane in the middle, then its Label
        StackPane stack = (StackPane) parentBox.getChildren().get(1);
        Label qtyLabel = (Label) stack.getChildren().get(1);

        // Parse current quantity
        int current;
        try {
            current = Integer.parseInt(qtyLabel.getText());
        } catch (NumberFormatException e) {
            current = 0;
        }

        // Compute new quantity (don’t go below 0)
        int next = current + delta;
        if (next < 0) next = 0;

        // If nothing changed, no need to touch cart
        if (next == current) return;

        // Update the label
        qtyLabel.setText(String.valueOf(next));

        // Update the cart model
        Product product = (Product) qtyLabel.getUserData();
        if (product != null) {
            int actualDelta = next - current;   // +1, -1, etc.
            cart.add(product, actualDelta);
            }
        }
        
    }

