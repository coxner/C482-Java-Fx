package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Inventory;
import model.InHouse;
import model.Outsourced;
import model.Error;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static model.Inventory.getAllParts;
/**
 *
 * @author Christian Oxner 009554482
 */
public class AddPart implements Initializable {
    public TextField idTextField;
    public TextField nameTextField;
    public TextField invTextField;
    public TextField machineOrCnTextField;
    public TextField maxTextField;
    public TextField minTextField;
    public Label machineOrCnLbl;
    public RadioButton inHouseRbtn;
    public RadioButton outsourcedRbtn;
    public TextField priceTextField;
    Stage stage;
    Parent scene;

    /**
     * Changes label text if user selects in house
     * @param event user selects in house radio button
     */
    @FXML
    void inHouseRbtn(ActionEvent event) {
        machineOrCnLbl.setText("Machine ID");
    }

    /**
     * Changes label text if user selects outsourced
     * @param event user selects outsourced radio button
     */
    @FXML
    void outsourcedRbtn(ActionEvent event) {machineOrCnLbl.setText("Company Name");}

    /**
     * Adds part that user creates
     * @param actionEvent event user adds a part
     * @throws IOException from the FXMLLLoader
     */
    public void onClickSave(ActionEvent actionEvent) throws IOException {
        try {
            int id = Inventory.getNewPartID();
            String name = nameTextField.getText();
            int inv = Integer.parseInt(invTextField.getText());
            double price = Double.parseDouble(priceTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());
            int min = Integer.parseInt(minTextField.getText());
            int machineId;
            System.out.println(inv);
            String companyName;
            boolean addPart = false;
            if (name.isEmpty()) {
                Error.errorAlert("Name Empty", "Name Cannot be empty", "Enter value in name field");
            } else {
                if (Error.checkMinValue(min, max) && Error.checkInv(min, max, inv)) {
                    if (inHouseRbtn.isSelected()) {
                        try {
                        machineId = Integer.parseInt(machineOrCnTextField.getText());
                        Inventory.addPart(new InHouse(id, name, price, inv, min, max, machineId));
                        addPart = true;
                    } catch (Exception e){
                            Error.errorAlert("Error","Machine Id Invalid", "Enter a valid machine ID");
                        }
                    }
                    if (outsourcedRbtn.isSelected()) {
                        companyName = machineOrCnTextField.getText();
                        Inventory.addPart(new Outsourced(id, name, price, inv, min, max, companyName));
                        addPart = true;
                    }
                    if (addPart) {
                     returnToMain(actionEvent);
                }
                }
            }
        }catch(Exception e) {
            Error.errorAlert("Error", "Error entering part", "Make sure all fields have correct values");
        }
        }

    /**
     * Returns user to main screen when cancel button is used
     * @param actionEvent event that user cancels adding a part
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
     * Method to return to main menu when adding a part occurs
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
        inHouseRbtn.setSelected(true);
    }
}
