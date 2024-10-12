/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drs_enhanced.controller;

import drs_enhanced.Action;
import drs_enhanced.Assessment;
import drs_enhanced.model.ActionModel;
import drs_enhanced.model.AssessmentModel;
import drs_enhanced.model.DisasterModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.collections.FXCollections;
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
 * @author Zhengjie GUO
 */
public class ActionController {

    @FXML
    private TextField actionIdField;
    @FXML
    private ComboBox<String> disasterIdComboBox;
    @FXML
    private ComboBox<String> teamIdComboBox; // Changed name here too
    @FXML
    private TextField actionTypeField;
    @FXML
    private TextArea actionDescriptionField;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private ListView<Action> actionListView;

    private ActionModel actionModel = new ActionModel();

    @FXML
    public void initialize() {
        loadDisasters();
        loadTeams();
        // Initialize status ComboBox with options
        statusComboBox.getItems().addAll("Pending", "In Progress", "Resolved");
        loadActions();
        setupListViewSelection();
    }

    private void loadDisasters() {
        List<String> disasterIds = actionModel.getDisasters();
        disasterIdComboBox.getItems().addAll(disasterIds);
    }

    private void loadTeams() {
        List<String> teamIds = actionModel.getAllTeams();
        teamIdComboBox.getItems().addAll(teamIds); // Corrected ComboBox ID here
    }

    private void loadActions() { // Use a more appropriate method name if it loads actions
        List<Action> actions = actionModel.getActions();
        actionListView.getItems().clear(); // Clear existing items
        actionListView.getItems().addAll(actions);

    }

    @FXML
    private void handleSubmitAction() {
        // Get the current values from the form
        String disasterId = disasterIdComboBox.getSelectionModel().getSelectedItem().split(" - ")[0];  // Get disaster ID
        String teamId = teamIdComboBox.getSelectionModel().getSelectedItem().split(" - ")[0];  // Get team ID
        String actionType = actionTypeField.getText();
        String actionDescription = actionDescriptionField.getText();
        String startTime = startDatePicker.getValue().toString();
        String endTime = endDatePicker.getValue().toString();
        String status = statusComboBox.getSelectionModel().getSelectedItem();

        // Check if we are in "edit" mode (i.e., an action is selected in the ListView)
        Action selectedAction = actionListView.getSelectionModel().getSelectedItem();

        if (selectedAction == null) {
            // Insert operation: If no action is selected, add a new action
            boolean success = actionModel.addAction(disasterId, teamId, actionType, actionDescription, startTime, endTime, status);

            if (success) {
                showAlert("Action successfully added!");
                loadActions();  // Refresh the actions list view
                clearFields();
            } else {
                showAlert("Failed to add action. Please try again.");
            }
        } else {
            // Update operation: If an action is selected, update the existing one
            int actionId = selectedAction.getActionId();  // Get the ID of the selected action

            boolean success = actionModel.updateAction(actionId, disasterId, teamId, actionType, actionDescription, startTime, endTime, status);

            if (success) {
                showAlert("Action successfully updated!");
                loadActions();  // Refresh the actions list view
                clearFields();
            } else {
                showAlert("Failed to update action. Please try again.");
            }
        }
    }

    private void clearFields() {
        actionIdField.clear();
        disasterIdComboBox.setValue(null);
        teamIdComboBox.setValue(null);
        actionTypeField.clear();
        actionDescriptionField.clear();
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        statusComboBox.setValue(null);
    }

// Show alert dialog for feedback
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Set up ListView selection to populate fields for editing
    private void setupListViewSelection() {

        // Add listener for selecting an item from the ListView
        actionListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedAction) -> {
            if (selectedAction != null) {
                populateFieldsForEditing(selectedAction); // Populate fields with selected assessment data
            }
        });
    }

    private void populateFieldsForEditing(Action action) {
        actionIdField.setText(String.valueOf(action.getActionId())); // Set the Action ID (auto-generated, not editable)
        disasterIdComboBox.getSelectionModel().select(action.getDisasterId() + " - " + actionModel.getDisasterTypeById(action.getDisasterId())); // Assuming you have a method to get the disaster name
        teamIdComboBox.getSelectionModel().select(action.getTeamId() + " - " + actionModel.getTeamNameById(action.getTeamId())); // Assuming you have a method to get the team name
        actionTypeField.setText(action.getActionType()); // Set the action type
        actionDescriptionField.setText(action.getActionDescription()); // Set the action description
        String startTimeString = action.getStartTime(); // Get the start time as String
        System.out.println("Start time: " + startTimeString);

        LocalDate startDate = LocalDate.parse(startTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd")); // Convert to LocalDate
        startDatePicker.setValue(startDate);
        //startDatePicker.setValue(action.getStartTime()); // Set the start date (assumed to be LocalDate)
        //endDatePicker.setValue(action.getEndTime()); // Set the end date (assumed to be LocalDate)
        String endTimeString = action.getEndTime(); // Get the start time as String
        System.out.println("End time: " + endTimeString);
        LocalDate endDate = LocalDate.parse(endTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd")); // Convert to LocalDate
        endDatePicker.setValue(endDate);
        statusComboBox.getSelectionModel().select(action.getStatus()); // Select the status
    }

}
