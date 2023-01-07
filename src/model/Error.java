package model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Error {
    /**
     * Error alert used to display to the user
     * @param alertTitle
     * @param alertHeader
     * @param alertContent
     */
    public static void errorAlert(String alertTitle, String alertHeader, String alertContent){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(alertTitle);
        alert.setHeaderText(alertHeader);
        alert.setContentText(alertContent);
        alert.showAndWait();
    }


    /**
     * Method used to check the min value of input and throws an error if it is incorrect
     * @param min
     * @param max
     * @return returns a true or false value passed later into forms
     */
    public static boolean checkMinValue(int min, int max){
        boolean checkMin = true;
        if (min <= 0 || min >= max) {
            checkMin = false;
            errorAlert("Invalid Min","Min Value invalid", "Min value must be greater than 0 and less than max");
        }
        return checkMin;
    }

    /**
     * Method used to check inventory of input and throws an error if it is incorrect
     * @param min
     * @param max
     * @param inv
     * @return returns a true or false values and is passed later into forms
     */
    public static boolean checkInv(int min, int max, int inv) {
        boolean checkInv = true;
        if (inv > max || inv < min) {
            checkInv = false;
            errorAlert("Invalid Inventory", "Inventory value is invalid", "Enter a inventory value less than max and greater than min");
        }
        return checkInv;
    }

    /**
     * Error info used to display to user
     * @param alertTitle
     * @param alertHeader
     * @param alertContent
     */
    public static void infoAlert(String alertTitle, String alertHeader, String alertContent){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(alertTitle);
        alert.setHeaderText(alertHeader);
        alert.setContentText(alertContent);
        alert.showAndWait();
    }
}
