/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drs_enhanced.model;

import drs_enhanced.Action;
import drs_enhanced.Assessment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActionModel {

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
    
        public List<String> getAllTeams() {

        List<String> teams = new ArrayList<>();
        String query = "SELECT team_id, team_name FROM response_team"; // Adjust your SQL query as needed

        try (Connection con = DatabaseUtil.getConnection(); // Get connection from DatabaseUtil
                 PreparedStatement pst = con.prepareStatement(query); ResultSet resultSet = pst.executeQuery()) {

            while (resultSet.next()) {
                String team_id = resultSet.getString("team_id");
                String team_name = resultSet.getString("team_name");
                teams.add(team_id + " - " + team_name); // Load into list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }



    public boolean addAction(String disasterId, String teamId, String actionType, String actionDescription, String startTime, String endTime, String status) {
        String query = "INSERT INTO actions (disaster_id, team_id, action_type, action_description, start_time, end_time, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DatabaseUtil.getConnection(); // Get connection from DatabaseUtil
                 PreparedStatement pst = con.prepareStatement(query)) {

            // Set the parameters for the prepared statement
            pst.setString(1, disasterId);
            pst.setString(2, teamId);
            pst.setString(3, actionType);
            pst.setString(4, actionDescription);
            pst.setString(5, startTime);
            pst.setString(6, endTime);
            pst.setString(7, status);

            // Execute the insert operation
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0; // Return true if the insertion was successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurred
        }
    }

    public boolean updateAction(int actionId, String disasterId, String teamId, String actionType, String actionDescription, String startTime, String endTime, String status) {
        String query = "UPDATE actions SET disaster_id = ?, team_id = ?, action_type = ?, action_description = ?, start_time = ?, end_time = ?, status = ? WHERE action_id = ?";

        try (Connection con = DatabaseUtil.getConnection(); // Get connection from DatabaseUtil
                 PreparedStatement pst = con.prepareStatement(query)) {

            // Set the parameters for the prepared statement
            pst.setString(1, disasterId);
            pst.setString(2, teamId);
            pst.setString(3, actionType);
            pst.setString(4, actionDescription);
            pst.setString(5, startTime);
            pst.setString(6, endTime);
            pst.setString(7, status);
            pst.setInt(8, actionId); // Set the action ID for the WHERE clause

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0; // Return true if the update was successful

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurred
        }
    }

    public List<Action> getActions() {
    List<Action> actions = new ArrayList<>();
    String query = "SELECT * FROM actions"; // Adjust the query based on your actual table structure

    try (Connection con = DatabaseUtil.getConnection(); // Get connection from DatabaseUtil
         PreparedStatement pst = con.prepareStatement(query);
         ResultSet resultSet = pst.executeQuery()) {

        while (resultSet.next()) {
            int actionId = resultSet.getInt("action_id");
            String disasterId = resultSet.getString("disaster_id");
            String teamId = resultSet.getString("team_id");
            String actionType = resultSet.getString("action_type");
            String actionDescription = resultSet.getString("action_description");
            String startTime = resultSet.getString("start_time");
            String endTime = resultSet.getString("end_time");
            String status = resultSet.getString("status");

            actions.add(new Action(actionId, disasterId, teamId, actionType, actionDescription, startTime, endTime, status));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return actions;
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

    public String getTeamNameById(String team_id) {
        String query = "SELECT team_name FROM response_team WHERE team_id = ?";
        try (Connection con = DatabaseUtil.getConnection(); // Get connection from DatabaseUtil
                 PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, team_id);
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("team_name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no department found
    }

   



}
