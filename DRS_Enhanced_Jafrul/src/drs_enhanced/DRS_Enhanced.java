package drs_enhanced;

import drs_enhanced.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jafrul Hasan
 */
public class DRS_Enhanced extends Application {
   private Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage; // Corrected to use 'stage'

        // Load the FXML file for the login view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/drs_enhanced/view/Login.fxml"));
        Parent root = loader.load();

        // Initialize the LoginController and pass the primaryStage
        LoginController loginController = loader.getController();
        loginController.setPrimaryStage(primaryStage);

        // Set up the scene and show the stage
        primaryStage.setTitle("Disaster Response System - Report");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
