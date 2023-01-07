package controller;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Error;
import model.Inventory;
import model.Part;
import model.Product;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author Christian Oxner 009554482
 */
public class MainController implements Initializable {


    public TextField PartQueryTF;
    public TextField ProdQueryTF;
    Stage stage;
    Parent scene;
    // ID Declaration for parts table
    public TableView partsTable;
    public TableColumn partIdCol;
    public TableColumn partNameCol;
    public TableColumn partInvCol;
    public TableColumn partCostCol;
    // ID Declaration for products table
    public TableView productsTable;
    public TableColumn prodIdCol;
    public TableColumn prodNameCol;
    public TableColumn prodInvCol;
    public TableColumn prodCostCol;
    // Button Declaration for parts
    public Button deletePart;
    public Button modifyPart;
    public Button addAPart;
    // Button Declaration for products
    public Button prodAdd;
    public Button prodModify;
    public Button prodDelete;
    // Declare part to modify
    private static Part partToModify;
    // Declare product to modify
    private static Product prodToModify;

    /**
     * Getter to return the part to be modified
     * @return return the part to be modify
     */
    public static Part getPartToModify() {
        return partToModify;
    }

    /**
     * Getter to return the product to be modified
     * @return returns product to modify
     */
    public static Product getProdToModify() {
        return prodToModify;
    }

    /**
     * Deletes a part from part table
     * @param actionEvent event user deletes a part
     */
    public void onDeletePart(ActionEvent actionEvent) {
        Part partToDelete = (Part) partsTable.getSelectionModel().getSelectedItem();
        if(partsTable.getSelectionModel().isEmpty()) {
            Error.infoAlert("Warning", "No part selected", "Choose a part from the table above");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Are you sure you wish to delete product?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
            Inventory.deletePart(partToDelete);
        }
        }
    }

    /**
     * Selects part user wants to modify and opens the modify part screen
     * @param actionEvent event user selects a part to modify
     * @throws IOException from the FXMLLLoader
     */
    public void onModifyPart(ActionEvent actionEvent) throws IOException {
        partToModify = (Part) partsTable.getSelectionModel().getSelectedItem();
        if (partToModify == null) {
            Error.errorAlert("Error Alert", "No part selected", "Choose a part from the table to modify it");
        } else {
            this.stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            this.scene = FXMLLoader.load(this.getClass().getResource("/view/ModifyPart.fxml"));
            this.stage.setScene(new Scene(this.scene));
            this.stage.show();
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
     * Returns search results when user uses search text field
     * @param actionEvent user presses search button and results are returned to table view
     */
    public void getSearchResultsProd(ActionEvent actionEvent) {
        String q = ProdQueryTF.getText();
        ObservableList<Product> products = Inventory.lookupProduct(q);
        if (products.size() == 0){
            try {
                int prodId = Integer.parseInt(q);
                Product p = Inventory.lookupProduct(prodId);
                if (p != null) {
                    products.add(p);
                }
            } catch (NumberFormatException e) {
            }
        } productsTable.setItems(products);
        if (products.size() == 0 ){
            Error.infoAlert("Error", "No results found", "No results found try search again");
            productsTable.setItems(Inventory.getAllProducts());
        }
        PartQueryTF.setText("");
    }


    /**
     * Loads screen for user to add part
     * @param actionEvent event user wants to add a part
     * @throws IOException from the FXMLLLoader
     */
    public void onAddPart(ActionEvent actionEvent) throws IOException {
        this.stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        this.scene = FXMLLoader.load(this.getClass().getResource("/view/AddPart.fxml"));
        this.stage.setScene(new Scene(this.scene));
        this.stage.show();
    }

    /**
     * Loads screen for user to add a product
     * @param actionEvent event user wants to add a product
     * @throws IOException from the FXMLLLoader
     */
    public void onProdAdd(ActionEvent actionEvent) throws IOException {
        this.stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        this.scene = FXMLLoader.load(this.getClass().getResource("/view/AddProduct.fxml"));
        this.stage.setScene(new Scene(this.scene));
        this.stage.show();
    }

    /**
     * Selects product user wants to modify and opens the modify product screen
     * @param actionEvent on event user wants to modify a product
     * @throws IOException from the FXMLLLoader
     */
    public void onProdModify(ActionEvent actionEvent) throws IOException {
        prodToModify = (Product) productsTable.getSelectionModel().getSelectedItem();
        if (prodToModify == null) {
            Error.errorAlert("Error Alert", "No product selected", "Choose a product from the table to modify it");
        } else {
            this.stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            this.scene = FXMLLoader.load(this.getClass().getResource("/view/ModifyProduct.fxml"));
            this.stage.setScene(new Scene(this.scene));
            this.stage.show();
        }
    }

    /**
     * Deletes a product from the product table
     * @param actionEvent user wants to delete a product
     */
    public void onProdDelete(ActionEvent actionEvent) {
        Product prodToDelete = (Product) productsTable.getSelectionModel().getSelectedItem();
        if(productsTable.getSelectionModel().isEmpty()) {
            Error.infoAlert("Warning", "No part selected", "Choose a part from the table above");
        } else if (prodToDelete.getAssociatedParts().size() > 0) {
            Error.infoAlert("Error", "Product must have no parts", "Product must have no parts associated with it");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Are you sure you wish to delete product?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deleteProduct(prodToDelete);
            }
        }
    }

    /**
     * Exits program but displays alert first warning the user
     * @param actionEvent user wants to exit program
     */
    public void onExitClicked(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to close the program?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    /**
     * Sets values for tables creates observable list parts and places them in the parts table process is repeated for
     * products
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

        prodIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        ObservableList<Product> products = Inventory.getAllProducts();
        productsTable.setItems(products);
    }
}
