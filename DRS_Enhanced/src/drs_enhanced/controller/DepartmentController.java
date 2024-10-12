/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drs_enhanced.controller;

/**
 *
 * @author Zhengjie GUO 
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import drs_enhanced.Department;

public class DepartmentController {

    @FXML
    private TableView<Department> departmentTable;
    @FXML
    private TableColumn<Department, Integer> departmentIdColumn;
    @FXML
    private TableColumn<Department, String> departmentNameColumn;
    @FXML
    private TableColumn<Department, String> responseTimeColumn;
    @FXML
    private TextField departmentNameField;
    @FXML
    private TextField responseTimeField;

    private ObservableList<Department> departmentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up the table columns
        departmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("departmentID"));
        departmentNameColumn.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
        responseTimeColumn.setCellValueFactory(new PropertyValueFactory<>("responseTime"));

        // Load sample data (replace with actual DB data later)
        loadSampleData();
    }

    private void loadSampleData() {
        departmentList.add(new Department(1, "Fire Department", "10 minutes"));
        departmentList.add(new Department(2, "Health Services", "5 minutes"));
        departmentTable.setItems(departmentList);
    }

    @FXML
    private void handleAddDepartment() {
        String name = departmentNameField.getText();
        String responseTime = responseTimeField.getText();

        if (name.isEmpty() || responseTime.isEmpty()) {
            showAlert("Validation Error", "Both fields are required.");
            return;
        }

        int newId = departmentList.size() + 1;
        Department newDepartment = new Department(newId, name, responseTime);
        departmentList.add(newDepartment);
        clearFields();
    }

    @FXML
    private void handleUpdateDepartment() {
        Department selectedDepartment = departmentTable.getSelectionModel().getSelectedItem();

        if (selectedDepartment == null) {
            showAlert("Selection Error", "Please select a department to update.");
            return;
        }

        selectedDepartment.setDepartmentName(departmentNameField.getText());
        selectedDepartment.setResponseTime(responseTimeField.getText());
        departmentTable.refresh();
        clearFields();
    }

    @FXML
    private void handleDeleteDepartment() {
        Department selectedDepartment = departmentTable.getSelectionModel().getSelectedItem();

        if (selectedDepartment == null) {
            showAlert("Selection Error", "Please select a department to delete.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this department?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                departmentList.remove(selectedDepartment);
            }
        });
    }

    private void clearFields() {
        departmentNameField.clear();
        responseTimeField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
