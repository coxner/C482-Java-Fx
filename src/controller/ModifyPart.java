package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Error;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPart implements Initializable {
    public RadioButton inHouseRbtn;
    public RadioButton outsourcedRbtn;
    public Label machineOrCnLbl;
    public TextField minTextField;
    public TextField idTextField;
    public TextField nameTextField;
    public TextField invTextField;
    public TextField machineOrCnTextField;
    public TextField maxTextField;
    public TextField priceTextField;
    Stage stage;
    Parent scene;
    private Part partToModify;
    int partId;

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
    void outsourcedRbtn(ActionEvent event) {
        machineOrCnLbl.setText("Company Name");
    }

    /**
     * RUNTIME ERROR
     * Runtime error that occurred was an invocation target exception kept being thrown. Could not figure out the problem
     * until I realized when calling updatePart() that the id of the part did not match the index. Part id was = 1 and
     * index was = 0. Solved by creating an index variable and passing that into the update part instead of having Part
     * Ids begin from 0.
     * @param actionEvent event user clicks save after modifying a part
     * @throws IOException from the FXMLLLoader
     *
     */
    public void onClickSave(ActionEvent actionEvent) throws IOException {
        try{
            int index = Inventory.getAllParts().indexOf(partToModify);
            int inv = Integer.parseInt(invTextField.getText());
            int min = Integer.parseInt(minTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());
            boolean addPart = false;
                if (Error.checkMinValue(min, max) && Error.checkInv(min, max, inv)) {
                    int id = Integer.parseInt(idTextField.getText());
                    String name = nameTextField.getText();
                    Double price = Double.parseDouble(priceTextField.getText());
                    if (inHouseRbtn.isSelected()) {
                            int machineId = Integer.parseInt(machineOrCnTextField.getText());
                            InHouse toModify = new InHouse(id, name, price, inv, min, max, machineId);
                            Inventory.updatePart(index, toModify);
                            addPart = true;
                    }
                    else {
                        String companyName = machineOrCnTextField.getText();
                        Outsourced toModify = new Outsourced(id, name, price, inv, min, max, companyName);
                        Inventory.updatePart(index, toModify);
                        addPart = true;
                    }
                }
                if (addPart) {
                    returnToMain(actionEvent);
                }
                } catch(Exception e) {
            Error.errorAlert("Error", "Error entering part", "Make sure all fields have correct values");}
            }

    /**
     * Returns user to main screen when cancel button is used
     * @param actionEvent event that user cancels adding a part
     * @throws IOException from the FXMLLLoader
     */
    public void onClickCancel(ActionEvent actionEvent) throws IOException {
        this.stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        this.scene = FXMLLoader.load(this.getClass().getResource("/view/main.fxml"));
        this.stage.setScene(new Scene(this.scene));
        this.stage.show();
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
     * Added the partToModify variables in initalize so that when screen loads values are added in, and it is established
     * of part if of InHouse or outsourced. This allows the onClickSave to pull in values from text fields
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
        partToModify = MainController.getPartToModify();
        idTextField.setText(String.valueOf(partToModify.getId()));
        nameTextField.setText(String.valueOf(partToModify.getName()));
        invTextField.setText(String.valueOf(partToModify.getStock()));
        priceTextField.setText(String.valueOf(partToModify.getPrice()));
        minTextField.setText(String.valueOf(partToModify.getMin()));
        maxTextField.setText(String.valueOf(partToModify.getMax()));
        if (partToModify instanceof InHouse) {
            InHouse inHouseMod = (InHouse) partToModify;
            inHouseRbtn.setSelected(true);
            machineOrCnLbl.setText("Machine Id");
            machineOrCnTextField.setText(Integer.toString(inHouseMod.getMachineId()));
        }
        if (partToModify instanceof Outsourced) {
            Outsourced outsourcedMod = (Outsourced) partToModify;
            outsourcedRbtn.setSelected(true);
            machineOrCnLbl.setText("Company Name");
            machineOrCnLbl.setText(outsourcedMod.getCompanyName());
        }
}
}
