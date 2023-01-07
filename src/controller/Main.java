package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Did not know if moving Javadocs to their own file would affect them. Files can be found under controller, index-files,
 * and model in the C482 root file and not in the src folder. Hope this makes sense
 *
 * FUTURE ENHANCEMENTS
 * We made code that is used to call screens reusable and put it in its own file. Would have the function accept a
 * parameter which would be the FXML file to be called.
 * Would do the same for the search bar feautures just to make the code as less dense and reusable as possible
 *
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(new Scene(root, 1000, 425));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
