package drs_enhanced.controller;

import drs_enhanced.model.UserModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LoginController implements Initializable {

    @FXML
    private Label errorMessage;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    private UserModel userModel;
    private Object disaster;

    private Stage primaryStage;

    // Method to set the primary stage
    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the UserModel
        userModel = new UserModel();
    }

    @FXML
    void handleLoginAction(ActionEvent event) {
        String uname = usernameField.getText();
        String pass = passwordField.getText();

        if (uname.isEmpty() || pass.isEmpty()) {
            //errorMessage.setTextFill(Color.RED);
            //errorMessage.setText("Username or Password is empty.");
            JOptionPane.showMessageDialog(null, "Username or Password is empty.");
        } else {
            // Use the UserModel to authenticate the user
            boolean loginSuccess = userModel.authenticate(uname, pass);
            if (loginSuccess) {

               JOptionPane.showMessageDialog(null, "Login Successful!");
                // Redirect to another page or dashboard after successful login

                try {
                    if (primaryStage != null) {
                        // Create and set the new scene for the dashboard
                        // Load the Dashboard FXML
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/drs_enhanced/view/mainLayout.fxml"));
                        Parent root = loader.load();

                        // Show the Dashboard scene
                        primaryStage.setScene(new Scene(root));
                        primaryStage.setTitle("Disaster Response System");
                        primaryStage.show();
                    } else {
                        System.err.println("Primary stage is not initialized.");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                ///arekta ses
            } else {
                //errorMessage.setTextFill(Color.RED);
                //errorMessage.setText("Invalid Username or Password.");
                JOptionPane.showMessageDialog(null, "Invalid Username or Password.");

            }
        }
    }
}
