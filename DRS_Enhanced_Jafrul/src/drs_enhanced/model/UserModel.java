/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drs_enhanced.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {

    // Method to check login credentials
    public boolean authenticate(String username, String password) {
        String query = "SELECT * FROM users WHERE username=? AND password=?";
        try (Connection con = DatabaseUtil.getConnection(); // Get connection from DatabaseUtil
                 PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();
            return rs.next();  // Returns true if a matching user is found
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
