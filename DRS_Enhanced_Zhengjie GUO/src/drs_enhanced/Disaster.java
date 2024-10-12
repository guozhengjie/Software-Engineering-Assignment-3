/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drs_enhanced;

import java.time.LocalDate;

/**
 *
 * @author Jafrul Hasan
 */
public class Disaster {
    private int id;
    private String type;
    private String description;
    private String location;
    private LocalDate dateReported;
    private String status;
    private int severity;

    public Disaster(int id, String type, String description, String location, LocalDate dateReported, String status, int severity) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.location = location;
        this.dateReported = dateReported;
        this.status = status;
        this.severity = severity;
    }
    // Getters and Setters
    public int getId() { return id; }
    public String getType() { return type; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public LocalDate getDateReported() { return dateReported; }
    public String getStatus() { return status; }
    public int getSeverity() { return severity; }
}