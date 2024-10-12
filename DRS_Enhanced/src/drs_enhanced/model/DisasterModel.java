/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drs_enhanced.model;

import drs_enhanced.Disaster;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;  // Import java.sql.Date for handling LocalDate

public class DisasterModel {

    public DisasterModel() {
    }

 

    // Method to add a disaster report with LocalDate
    public boolean addDisaster(String type, String description, String location, LocalDate dateReported, String status, int severity) {
        String query = "INSERT INTO disaster (type, description, location, date_reported, status, severity) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DatabaseUtil.getConnection(); // Get connection from DatabaseUtil
                 PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, type);
            pst.setString(2, description);
            pst.setString(3, location);
            pst.setDate(4, Date.valueOf(dateReported));  // Convert LocalDate to java.sql.Date
            pst.setString(5, status);
            pst.setInt(6, severity);

            return pst.executeUpdate() > 0;  // Return true if insert was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to fetch all disasters
    public List<String> getAllDisasters() {
        List<String> disasters = new ArrayList<>();
        String query = "SELECT * FROM disaster";
        try (Connection con = DatabaseUtil.getConnection(); // Get connection from DatabaseUtil
                 PreparedStatement pst = con.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                disasters.add("ID: " + rs.getInt("DisasterID") + ", Type: " + rs.getString("type") + ", Status: " + rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disasters;
    }

//    // Method to update disaster status
//    public boolean updateDisasterStatus(int disasterId, String newStatus) {
//        String query = "UPDATE disaster SET status=? WHERE disaster_id=?";
//        try (PreparedStatement pst = con.prepareStatement(query)) {
//            pst.setString(1, newStatus);
//            pst.setInt(2, disasterId);
//
//            return pst.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
    // Method to update a disaster report
    public boolean updateDisaster(int disasterId, String type, String description, String location, LocalDate dateReported, String status, int severity) {
        String query = "UPDATE disaster SET type=?, description=?, location=?, date_reported=?, status=?, severity=? WHERE DisasterID=?";
        try (Connection con = DatabaseUtil.getConnection(); // Get connection from DatabaseUtil
                 PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, type);
            pst.setString(2, description);
            pst.setString(3, location);
            pst.setDate(4, Date.valueOf(dateReported));  // Convert LocalDate to java.sql.Date
            pst.setString(5, status);
            pst.setInt(6, severity);
            pst.setInt(7, disasterId);

            return pst.executeUpdate() > 0;  // Return true if update was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

// Method to get a disaster by ID for editing
    public Disaster getDisasterById(int disasterId) {
        String query = "SELECT * FROM disaster WHERE DisasterID=?";
        try (Connection con = DatabaseUtil.getConnection(); // Get connection from DatabaseUtil
                 PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, disasterId);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Disaster(
                            rs.getInt("DisasterID"),
                            rs.getString("type"),
                            rs.getString("description"),
                            rs.getString("location"),
                            rs.getDate("date_reported").toLocalDate(), // Convert java.sql.Date to LocalDate
                            rs.getString("status"),
                            rs.getInt("severity")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Return null if no disaster is found
    }

    // Method to delete a disaster entry
    public boolean deleteDisaster(int disasterId) {
        String query = "DELETE FROM disaster WHERE disaster_id=?";
        try (Connection con = DatabaseUtil.getConnection(); // Get connection from DatabaseUtil
                 PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, disasterId);

            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
