package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import model.Error;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddProduct implements Initializable {
    public TextField maxTextField;
    public TextField minTextField;
    public TextField idTextField;
    public TextField nameTextField;
    public TextField invTextField;
    public TextField priceTextField;
    public TableColumn productPartIdCol;
    public TableColumn productPartNameCol;
    public TableColumn productPartInvCol;
    public TableColumn productPartPriceCol;
    public TableView addToProductTable;
    public TextField PartQueryTF;
    Stage stage;
    Parent scene;
    public TableView partsTable;
    public TableColumn partIdCol;
    public TableColumn partNameCol;
    public TableColumn partInvCol;
    public TableColumn partCostCol;
    private ObservableList<Part> partsForProduct = FXCollections.observableArrayList();

    /**
     * Method for user to add a part to the project being created
     * @param actionEvent user selects a part to add to the product being created
     */
    public void onAddToProduct(ActionEvent actionEvent) {
        Part addToProduct = (Part) partsTable.getSelectionModel().getSelectedItem();
        if (addToProduct == null) {
            Error.errorAlert("Error", "Select a part","Select a part to add to product");
        } else {
            partsForProduct.add(addToProduct);
            addToProductTable.setItems(partsForProduct);
        }

    }

    /**
     * Returns search results when user uses search text field
     * @param actionEvent user presses search button and results are returned to table view
     */
    public void getSearchResults(ActionEvent actionEvent) {
        String q = PartQueryTF.getText();
        ObservableList<Part> parts = Inventory.lookupPart(q);
        if (parts.size() == 0){
            try {
                int partId = Integer.parseInt(q);
                Part p = Inventory.lookupPart(partId);
                if (p != null) {
                    parts.add(p);
                }
            } catch (NumberFormatException e) {

            }
        }
        partsTable.setItems(parts);
        if (parts.size() == 0 ){
            Error.infoAlert("Error", "No results found", "No results found try search again");
            partsTable.setItems(Inventory.getAllParts());
        }
        PartQueryTF.setText("");
    }

    /**
     * User wants to remove a part that will be added to the product
     * @param actionEvent user uses button to remove a part from the add to product table
     */
    public void onRemoveAssociatedPart(ActionEvent actionEvent) {
        Part partToRemove = (Part) addToProductTable.getSelectionModel().getSelectedItem();
        if (partToRemove == null) {
            Error.errorAlert("Error", "Select a part","Select a part to remove from the product");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove part from the product?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) {
                partsForProduct.remove(partToRemove);
                addToProductTable.setItems(partsForProduct);
            }
        }
    }

    /**
     * RUNTIME ERROR
     * At first was unsure how to save a product with an observable list attached to it. Was unsure if it needed to be added
     * to a constructor or not. Took a while to solve but ended up realizing it was just a simple solution of attaching the
     * list to a product
     * @param actionEvent user uses button to save a product
     * @throws IOException from the FXMLLLoader
     */
    public void onClickSave(ActionEvent actionEvent) throws IOException {
        try {
            int id = Inventory.getNewProductID();
            String name = nameTextField.getText();
            int inv = Integer.parseInt(invTextField.getText());
            double price = Double.parseDouble(priceTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());
            int min = Integer.parseInt(minTextField.getText());
            if (name.isEmpty()) {
                Error.errorAlert("Name Empty", "Name Cannot be empty", "Enter value in name field");
            } else {
                if (Error.checkMinValue(min, max) && Error.checkInv(min, max, inv)) {
                    Product newProduct = new Product(id, name, price, inv, min, max);
                    newProduct.addAssociatedPart(partsForProduct);
                    Inventory.addProduct(newProduct);
                    returnToMain(actionEvent);
                }
            }
        } catch (Exception e) {
            Error.errorAlert("Error", "Error entering product", "Make sure all fields have correct values");
        }
    }
    /**
     * Method to return to main menu when adding a product occurs
     * @param actionEvent event occurs to return user to main menu
     * @throws IOException from the FXMLLLoader
     */
    private void returnToMain(ActionEvent actionEvent) throws IOException {
        this.stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        this.scene = FXMLLoader.load(this.getClass().getResource("/view/main.fxml"));
        this.stage.setScene(new Scene(this.scene));
        this.stage.show();
    }
    /**
     * Returns user to main screen when cancel button is used
     * @param actionEvent event that user cancels adding a product
     * @throws IOException from the FXMLLLoader
     */
    public void onClickCancel(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to return to main menu any data entered will be lost");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            this.stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            this.scene = FXMLLoader.load(this.getClass().getResource("/view/main.fxml"));
            this.stage.setScene(new Scene(this.scene));
            this.stage.show();
        }
    }

    /**
     *
     * @param location
     * The location used to resolve relative paths for the root object, or
     * <tt>null</tt> if the location is not known.
     *
     * @param resources
     * The resources used to localize the root object, or <tt>null</tt> if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        ObservableList<Part> parts = Inventory.getAllParts();
        partsTable.setItems(parts);

        productPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        addToProductTable.setItems(partsForProduct);

    }

}