package drs_enhanced.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import drs_enhanced.Team;

/**
 *
 * @author Sayed
 */
public class TeamController {

    @FXML
    private TableView<Team> teamTable;
    @FXML
    private TableColumn<Team, Integer> teamIdColumn;
    @FXML
    private TableColumn<Team, String> teamNameColumn;
    @FXML
    private TableColumn<Team, String> contactInfoColumn;
    @FXML
    private TextField teamNameField;
    @FXML
    private TextField contactInfoField;

    private ObservableList<Team> teamList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up the table columns
        teamIdColumn.setCellValueFactory(new PropertyValueFactory<>("teamId"));
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        contactInfoColumn.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));

        // Load sample data (replace with actual DB data later)
        loadSampleData();
    }

    private void loadSampleData() {
        teamList.add(new Team(1, "Firefighters Team", "firefighters@example.com"));
        teamList.add(new Team(2, "Medical Team", "medical@example.com"));
        teamTable.setItems(teamList);
    }

    @FXML
    private void handleAddTeam() {
        String name = teamNameField.getText();
        String contact = contactInfoField.getText();

        if (name.isEmpty() || contact.isEmpty()) {
            showAlert("Validation Error", "Both fields are required.");
            return;
        }

        int newId = teamList.size() + 1;
        Team newTeam = new Team(newId, name, contact);
        teamList.add(newTeam);
        clearFields();
    }

    @FXML
    private void handleUpdateTeam() {
        Team selectedTeam = teamTable.getSelectionModel().getSelectedItem();

        if (selectedTeam == null) {
            showAlert("Selection Error", "Please select a team to update.");
            return;
        }

        selectedTeam.setTeamName(teamNameField.getText());
        selectedTeam.setContactInfo(contactInfoField.getText());
        teamTable.refresh();
        clearFields();
    }

    @FXML
    private void handleDeleteTeam() {
        Team selectedTeam = teamTable.getSelectionModel().getSelectedItem();

        if (selectedTeam == null) {
            showAlert("Selection Error", "Please select a team to delete.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this team?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                teamList.remove(selectedTeam);
            }
        });
    }

    private void clearFields() {
        teamNameField.clear();
        contactInfoField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
