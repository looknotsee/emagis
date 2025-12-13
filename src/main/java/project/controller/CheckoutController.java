package project.controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import project.core.App;
import project.core.CartItem;
import project.core.Product;
import project.core.ShoppingCart;
import project.service.CheckoutService;
import project.service.Session;

public class CheckoutController {

    @FXML private TableView<CartItem> cartTable;
    @FXML private TableColumn<CartItem, String> colItem;
    @FXML private TableColumn<CartItem, Double> colPrice;
    @FXML private TableColumn<CartItem, CartItem> colQty;
    @FXML private TableColumn<CartItem, Double> colSubtotal;
    @FXML private Label lblTotal;

    private final ShoppingCart cart = Session.getCart();
    private final ObservableList<CartItem> tableData = FXCollections.observableArrayList();
    private final CheckoutService checkoutService = new CheckoutService();

    @FXML
    public void initialize() {

        tableData.setAll(cart.getItems());
        cartTable.setItems(tableData);

        colItem.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getProduct().getProductName()
                )
        );

        colPrice.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleDoubleProperty(
                        cell.getValue().getProduct().getProductPrice()
                ).asObject()
        );

        colSubtotal.setCellValueFactory(cell ->
                new javafx.beans.property.SimpleDoubleProperty(
                        cell.getValue().getSubTotal()
                ).asObject()
        );

        // IMPORTANT FIX:
        colQty.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));

        colQty.setCellFactory(col -> new TableCell<CartItem, CartItem>() {

            private final Button minusBtn = new Button("-");
            private final Button plusBtn = new Button("+");
            private final Label qtyLabel = new Label();

            {
                minusBtn.setOnAction(e -> updateQuantity(-1));
                plusBtn.setOnAction(e -> updateQuantity(+1));
            }

            private void updateQuantity(int delta) {
                CartItem item = getTableView().getItems().get(getIndex());
                Product product = item.getProduct();

                int oldQty = item.getQuantity();
                int newQty = Math.max(0, oldQty + delta);

                int actualDelta = newQty - oldQty;

                if (actualDelta != 0) {
                    cart.add(product, actualDelta);
                    refreshTable();
                }
            }

            @Override
            protected void updateItem(CartItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    return;
                }

                qtyLabel.setText(String.valueOf(item.getQuantity()));

                HBox box = new HBox(5, minusBtn, qtyLabel, plusBtn);
                box.setStyle("-fx-alignment: center;");

                setGraphic(box);
            }
        });

        updateTotal();
    }

    private void refreshTable() {
        tableData.setAll(cart.getItems());
        cartTable.refresh();
        updateTotal();
    }

    private void updateTotal() {
        lblTotal.setText(String.format("₱ %.2f", cart.getTotal()));
    }

    @FXML
    private void onBackClick() {
        try {
            App.setRoot("magiscanteen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onConfirmClick() {
        // perform checkout using CheckoutService which interacts with the user's wallet
        var user = Session.getCurrentUser();
        if (user == null) {
            javafx.scene.control.Alert a = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR, "No user logged in.");
            a.showAndWait();
            return;
        }

        boolean ok = checkoutService.checkout(user);
        javafx.scene.control.Alert a = new javafx.scene.control.Alert(ok ? javafx.scene.control.Alert.AlertType.INFORMATION : javafx.scene.control.Alert.AlertType.ERROR,
                ok ? "Purchase successful." : "Purchase failed (insufficient balance or empty cart).");
        a.showAndWait();

        if (ok) {
            // refresh the view (cart cleared by service)
            refreshTable();
            try {
                App.setRoot("home2");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
