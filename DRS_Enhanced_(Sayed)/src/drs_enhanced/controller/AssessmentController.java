/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drs_enhanced.controller;

import drs_enhanced.Assessment;
import drs_enhanced.model.AssessmentModel;
import java.time.LocalDate;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Sayed
 */
public class AssessmentController {

    @FXML
    private ComboBox<String> disasterIdComboBox; // Disaster ID ComboBox
    @FXML
    private ComboBox<String> departmentIdComboBox; // Department ID ComboBox

    @FXML
    private TextField assessmentIdField; // Auto-generated Assessment ID
    @FXML
    private DatePicker assessmentDatePicker; // Assessment Date Picker
    @FXML
    private TextArea findingsField; // Findings TextArea
    @FXML
    private Spinner<Integer> severityLevelSpinner; // Severity Level Spinner

    private AssessmentModel assessmentModel;
    @FXML
    private ListView<Assessment> assessmentListView;

    @FXML
    public void initialize() {
        assessmentModel = new AssessmentModel(); // Initialize the model
        assessmentIdField.setVisible(false);
        loadDisasters();
        loadDepartments();
        loadAssessments(); // Load existing assessments when the controller initializes
        setupListViewSelection();

        // Initialize severity Spinner with range 1-5
        SpinnerValueFactory<Integer> severityValueFactory
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        severityLevelSpinner.setValueFactory(severityValueFactory);

    }

    private void loadDisasters() {
        List<String> disasters = assessmentModel.getDisasters();
        disasterIdComboBox.getItems().addAll(disasters); // Load into ComboBox
    }

    private void loadDepartments() {
        List<String> departments = assessmentModel.getDepartments();
        departmentIdComboBox.getItems().addAll(departments); // Load into ComboBox
    }

    private void loadAssessments() {
        List<Assessment> assessments = assessmentModel.getAssessments();
        assessmentListView.getItems().clear(); // Clear existing items
        assessmentListView.getItems().addAll(assessments); // Add fetched assessments to the list view
    }

    @FXML
    private void handleSubmit() {
        // Get the current values from the form
        String disasterId = disasterIdComboBox.getSelectionModel().getSelectedItem().split(" - ")[0];
        String departmentId = departmentIdComboBox.getSelectionModel().getSelectedItem().split(" - ")[0];
        String assessmentDate = assessmentDatePicker.getValue().toString();
        String findings = findingsField.getText();
        int severityLevel = severityLevelSpinner.getValue();

        // Check if we are in "edit" mode (i.e., an assessment is selected in the ListView)
        Assessment selectedAssessment = assessmentListView.getSelectionModel().getSelectedItem();

        if (selectedAssessment == null) {
            // Insert operation
            boolean success = assessmentModel.addAssessment(disasterId, departmentId, assessmentDate, findings, severityLevel);

            if (success) {
                showAlert("Assessment successfully added!");
                loadAssessments(); // Refresh the assessments list view
                clearFields();
            } else {
                showAlert("Failed to add assessment. Please try again.");
            }
        } else {
            // Update operation
            int assessmentId = selectedAssessment.getAssessmentId(); // Get the ID of the selected assessment

            boolean success = assessmentModel.updateAssessment(assessmentId, disasterId, departmentId, assessmentDate, findings, severityLevel);

            if (success) {
                showAlert("Assessment successfully updated!");
                loadAssessments(); // Refresh the assessments list view
                clearFields();
            } else {
                showAlert("Failed to update assessment. Please try again.");
            }
        }
    }

    private void clearFields() {
        assessmentIdField.clear();
        disasterIdComboBox.getSelectionModel().clearSelection();
        departmentIdComboBox.getSelectionModel().clearSelection();
        assessmentDatePicker.setValue(null);
        findingsField.clear();
        severityLevelSpinner.getValueFactory().setValue(1); // Reset to default severity level
        assessmentListView.getSelectionModel().clearSelection(); // Deselect the item in the ListView
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Set up ListView selection to populate fields for editing
    private void setupListViewSelection() {
        
    // Add listener for selecting an item from the ListView
    assessmentListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedAssessment) -> {
        if (selectedAssessment != null) {
            populateFieldsForEditing(selectedAssessment); // Populate fields with selected assessment data
        }
    });
    }

   private void populateFieldsForEditing(Assessment assessment) {
    assessmentIdField.setText(String.valueOf(assessment.getAssessmentId()));
    disasterIdComboBox.getSelectionModel().select(assessment.getDisasterId() + " - " + assessmentModel.getDisasterTypeById(assessment.getDisasterId())); // Assuming you have a method to get the disaster name
    departmentIdComboBox.getSelectionModel().select(assessment.getDepartmentId() + " - " + assessmentModel.getDepartmentNameById(assessment.getDepartmentId())); // Assuming you have a method to get the department name
    assessmentDatePicker.setValue(LocalDate.parse(assessment.getAssessmentDate()));
    findingsField.setText(assessment.getFindings());
    severityLevelSpinner.getValueFactory().setValue(assessment.getSeverityLevel());
}
}
