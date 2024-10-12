/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drs_enhanced.model;

/**
 *
 * @author Jafrul Hasan
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String URL = "jdbc:mysql://localhost/drs"; // Replace with your DB URL
    private static final String USERNAME = "root"; // Replace with your DB username
    private static final String PASSWORD = "Emon@123"; // Replace with your DB password

    // Static method to get a database connection
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // Use correct driver class for MySQL
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Unable to load MySQL JDBC Driver", e);
        }
    }
}