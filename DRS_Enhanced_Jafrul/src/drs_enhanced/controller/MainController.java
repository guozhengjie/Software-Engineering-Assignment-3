/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drs_enhanced.controller;

/**
 *
 * @author Jafrul Hasan
 */
import drs_enhanced.model.UserModel;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private StackPane contentArea; // Reference to the content area
    @FXML
    private Button disasterReportButton; // Reference to the Disaster Report button
    @FXML
    private Button logoutButton; // Declare the logoutButton here

    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the UserModel
        // userModel = new UserModel();
        System.out.println("MainController initialized.");

        handleDashboard();
    }

    @FXML
    private void handleDashboard() {
        loadPage("/drs_enhanced/view/dashboard.fxml");
    }

    @FXML
    private void handleDisasterReport() {
        loadPage("/drs_enhanced/view/disaster.fxml");
    }

    @FXML
    private void handleAssessment() {
        loadPage("/drs_enhanced/view/assessment.fxml");
    }

    @FXML
    private void handleAction() {
        loadPage("/drs_enhanced/view/action.fxml");
    }

    @FXML
    private void handleDepartment() {
        loadPage("/drs_enhanced/view/department.fxml");
    }

    @FXML
    private void handleTeam() {
        loadPage("/drs_enhanced/view/team.fxml");
    }

    @FXML
    private void logout() {
        try {
            // Load the login FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/drs_enhanced/view/Login.fxml"));
            Parent root = loader.load();

            // Create a new stage for the login scene
            Stage loginStage = new Stage();

            // Get the controller and set the primary stage
            LoginController loginController = loader.getController();
            loginController.setPrimaryStage(loginStage);

            // Set up the login stage
            loginStage.setTitle("Login");
            loginStage.setScene(new Scene(root));
            loginStage.show();

            // Close the current main stage
            Stage currentStage = (Stage) logoutButton.getScene().getWindow();
            currentStage.close(); // Close the current stage or you can hide it if needed
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPage(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            System.out.println("ki pay?" + getClass().getResource(fxmlFile));

            // Clear the content area and set the new page
            contentArea.getChildren().clear();
            contentArea.getChildren().add(root);

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error loading the page: " + fxmlFile);
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
