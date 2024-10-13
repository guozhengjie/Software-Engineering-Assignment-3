/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drs_enhanced.model;

import drs_enhanced.Assessment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssessmentModel {

    public List<String> getDisasters() {
        List<String> disasters = new ArrayList<>();
        String query = "SELECT DisasterID, type, location FROM disaster"; // Adjust your SQL query as needed

        try (Connection con = DatabaseUtil.getConnection(); // Get connection from DatabaseUtil
                 PreparedStatement pst = con.prepareStatement(query); ResultSet resultSet = pst.executeQuery()) {

            while (resultSet.next()) {
                String disasterId = resultSet.getString("DisasterID");
                String disasterType = resultSet.getString("type");
                String disasterLocation = resultSet.getString("location");
                disasters.add(disasterId + " - " + disasterType + " - " + disasterLocation); // Load into list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disasters;
    }

    public List<String> getDepartments() {
        List<String> departments = new ArrayList<>();
        String query = "SELECT departmentID, departmentName FROM department"; // Adjust your SQL query as needed

        try (Connection con = DatabaseUtil.getConnection(); // Get connection from DatabaseUtil
                 PreparedStatement pst = con.prepareStatement(query); ResultSet resultSet = pst.executeQuery()) {

            while (resultSet.next()) {
                String departmentId = resultSet.getString("departmentID");
                String departmentName = resultSet.getString("departmentName");
                departments.add(departmentId + " - " + departmentName); // Load into list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public boolean addAssessment(String disasterId, String departmentId, String assessmentDate, String findings, int severityLevel) {
        String query = "INSERT INTO assessments (disaster_id, department_id, assessment_date, findings, severity_level) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DatabaseUtil.getConnection(); // Get connection from DatabaseUtil
                 PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, disasterId);
            pst.setString(2, departmentId);
            pst.setString(3, assessmentDate);
            pst.setString(4, findings);
            pst.setInt(5, severityLevel);

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0; // Return true if the insertion was successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurred
        }
    }

    public List<Assessment> getAssessments() {
        List<Assessment> assessments = new ArrayList<>();
        String query = "SELECT * FROM assessments";

        try (Connection con = DatabaseUtil.getConnection(); // Get connection from DatabaseUtil
                 PreparedStatement pst = con.prepareStatement(query); ResultSet resultSet = pst.executeQuery()) {

            while (resultSet.next()) {
                int assessmentId = resultSet.getInt("assessment_id");
                String disasterId = resultSet.getString("disaster_id");
                String departmentId = resultSet.getString("department_id");
                String assessmentDate = resultSet.getString("assessment_date");
                String findings = resultSet.getString("findings");
                int severityLevel = resultSet.getInt("severity_level");

                assessments.add(new Assessment(assessmentId, disasterId, departmentId, assessmentDate, findings, severityLevel));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assessments;
    }

    public String getDisasterTypeById(String disasterId) {
        String query = "SELECT  type FROM disaster WHERE DisasterID = ?";
        try (Connection con = DatabaseUtil.getConnection(); // Get connection from DatabaseUtil
                 PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, disasterId);
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("type");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no disaster found
    }

    public String getDepartmentNameById(String departmentId) {
        String query = "SELECT departmentName FROM department WHERE departmentID = ?";
        try (Connection con = DatabaseUtil.getConnection(); // Get connection from DatabaseUtil
                 PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, departmentId);
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("departmentName");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no department found
    }

    public boolean updateAssessment(int assessmentId, String disasterId, String departmentId, String assessmentDate, String findings, int severityLevel) {
        String query = "UPDATE assessments SET disaster_id = ?, department_id = ?, assessment_date = ?, findings = ?, severity_level = ? WHERE assessment_id = ?";

        try (Connection con = DatabaseUtil.getConnection(); // Get connection from DatabaseUtil
                 PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, disasterId);
            pst.setString(2, departmentId);
            pst.setString(3, assessmentDate);
            pst.setString(4, findings);
            pst.setInt(5, severityLevel);
            pst.setInt(6, assessmentId);

            int rowsUpdated = pst.executeUpdate();
            return rowsUpdated > 0; // Returns true if the update was successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
