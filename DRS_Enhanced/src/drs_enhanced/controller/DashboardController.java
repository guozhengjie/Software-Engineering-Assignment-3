/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drs_enhanced.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class DashboardController {

    // FXML Elements
    @FXML
    private TextField activeDisastersField;

    @FXML
    private TextField totalAssessmentsField;

    @FXML
    private TextField departmentsInvolvedField;

    @FXML
    private TextField criticalDisastersField;

    @FXML
    private PieChart disasterSeverityChart;

    @FXML
    private BarChart<String, Number> monthlyReportsBarChart;

    @FXML
    private ListView<String> recentActivityListView;

    // Initialize method called when the FXML page is loaded
    @FXML
    public void initialize() {
        loadStatistics();
        loadDisasterSeverityChart();
        loadMonthlyReportsChart();
        loadRecentActivity();
    }

    // Load statistics data into text fields
    private void loadStatistics() {
        // Sample data - Replace these with real data from your data source
        int activeDisasters = 5;
        int totalAssessments = 120;
        int departmentsInvolved = 10;
        int criticalDisasters = 2;

        // Set values in the text fields
        activeDisastersField.setText(String.valueOf(activeDisasters));
        totalAssessmentsField.setText(String.valueOf(totalAssessments));
        departmentsInvolvedField.setText(String.valueOf(departmentsInvolved));
        criticalDisastersField.setText(String.valueOf(criticalDisasters));
    }

    // Load data into the PieChart for disaster severity distribution
    private void loadDisasterSeverityChart() {
        ObservableList<PieChart.Data> severityData = FXCollections.observableArrayList(
            new PieChart.Data("Low", 10),
            new PieChart.Data("Moderate", 30),
            new PieChart.Data("High", 20),
            new PieChart.Data("Critical", 5)
        );
        disasterSeverityChart.setData(severityData);
    }

    // Load data into the BarChart for monthly assessment reports
    private void loadMonthlyReportsChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Reports");

        // Sample data - Replace with real monthly data
        series.getData().add(new XYChart.Data<>("Jan", 5));
        series.getData().add(new XYChart.Data<>("Feb", 8));
        series.getData().add(new XYChart.Data<>("Mar", 12));
        series.getData().add(new XYChart.Data<>("Apr", 15));
        series.getData().add(new XYChart.Data<>("May", 18));
        series.getData().add(new XYChart.Data<>("Jun", 10));

        monthlyReportsBarChart.getData().add(series);
    }

    // Load data into the ListView for recent activities
    private void loadRecentActivity() {
        ObservableList<String> activities = FXCollections.observableArrayList(
            "Disaster ID 101 - New Assessment Completed",
            "Department ID 5 - Responded to Disaster ID 102",
            "Severity of Disaster ID 104 updated to 'Critical'",
            "Monthly report submitted for September",
            "New department added to the system"
        );

        recentActivityListView.setItems(activities);
    }
}
