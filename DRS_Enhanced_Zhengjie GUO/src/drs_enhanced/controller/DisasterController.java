/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drs_enhanced.controller;

/**
 *
 * @author Jafrul Hasan
 */
import drs_enhanced.Disaster;
import drs_enhanced.model.DisasterModel;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class DisasterController implements Initializable {

    @FXML
    private ComboBox<String> typeComboBox;  // Updated from TextField to ComboBox

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField locationField;

    @FXML
    private DatePicker datePicker;
    ;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private Spinner<Integer> severitySpinner;  // Updated from TextField to Spinner

    @FXML
    private Button submitButton;

    @FXML
    private ListView<String> disasterListView;

    private DisasterModel disasterModel;
    private Stage primaryStage;
    private int selectedDisasterId = -1;  // To track the selected disaster ID for editing

    // Method to set the primary stage
    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        disasterModel = new DisasterModel();
        loadDisasters();
        setupListViewSelection();

        // Initialize disaster type ComboBox with predefined values
        typeComboBox.setItems(FXCollections.observableArrayList(
                "Hurricane", "Fire", "Earthquake", "Flood", "Tornado"
        ));

        // Initialize status ComboBox with options
        statusComboBox.setItems(FXCollections.observableArrayList(
                "Pending", "In Progress", "Resolved"
        ));

        // Initialize severity Spinner with range 1-5
        SpinnerValueFactory<Integer> severityValueFactory
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        severitySpinner.setValueFactory(severityValueFactory);

        // Initialize DatePicker to today's date
        datePicker.setValue(LocalDate.now());
    }

    @FXML
    private void handleSubmitAction(ActionEvent event) {
        String type = typeComboBox.getValue();  // Updated to get value from ComboBox
        String description = descriptionField.getText();
        String location = locationField.getText();
        LocalDate dateReported = datePicker.getValue();
        String status = statusComboBox.getValue();
        int severity = severitySpinner.getValue();  // Updated to get value from Spinner

        // Add the disaster to the model
        boolean success;

        if (selectedDisasterId == -1) {
            // If no disaster is selected, perform an insert operation
            success = disasterModel.addDisaster(type, description, location, dateReported, status, severity);
            if (success) {
                JOptionPane.showMessageDialog(null, "Disaster reported Successfully!");

                loadDisasters();
                clearFields();
            } else {
                // Show an error message (could be in an alert)
                System.out.println("Failed to add disaster.");
            }
        } else {
            // If a disaster is selected, update the existing disaster
            success = disasterModel.updateDisaster(selectedDisasterId, type, description, location, dateReported, status, severity);
            if (success) {
                JOptionPane.showMessageDialog(null, "Disaster updated Successfully!");

                loadDisasters();
                clearFields();
            } else {
                // Show an error message (could be in an alert)
                System.out.println("Failed to add disaster.");
            }
        }
        //boolean success = disasterModel.addDisaster(type, description, location, dateReported, status, severity);

    }

    // Load all disasters into the ListView
    private void loadDisasters() {
        List<String> disasters = disasterModel.getAllDisasters();
        disasterListView.getItems().setAll(disasters);
    }

    // Clear input fields after submission
    private void clearFields() {
        typeComboBox.setValue(null);  // Clear ComboBox
        descriptionField.clear();
        locationField.clear();
        statusComboBox.setValue(null);  // Clear ComboBox
        severitySpinner.getValueFactory().setValue(1);  // Reset Spinner to 1
        selectedDisasterId = -1;  // Reset the selected disaster ID

    }

    // Set up ListView selection to populate fields for editing
    private void setupListViewSelection() {
        disasterListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Extract disaster ID from selected item
                int disasterId = extractDisasterId(newSelection);
                populateFieldsForEditing(disasterId);
            }
        });
    }

    // Extract disaster ID from the selected ListView item
    private int extractDisasterId(String selectedItem) {
        String[] parts = selectedItem.split(",")[0].split(":");
        return Integer.parseInt(parts[1].trim());
    }

    // Populate fields for editing based on the selected disaster
    private void populateFieldsForEditing(int disasterId) {
        Disaster disaster = disasterModel.getDisasterById(disasterId);
        if (disaster != null) {
            selectedDisasterId = disaster.getId();
            typeComboBox.setValue(disaster.getType());
            descriptionField.setText(disaster.getDescription());
            locationField.setText(disaster.getLocation());
            datePicker.setValue(disaster.getDateReported());
            statusComboBox.setValue(disaster.getStatus());
            severitySpinner.getValueFactory().setValue(disaster.getSeverity());
        }
    }
}
