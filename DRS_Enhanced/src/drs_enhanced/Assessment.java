/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drs_enhanced;

/**
 *
 * @author Sayed
 */
public class Assessment {
    private int assessmentId;
    private String disasterId;
    private String departmentId;
    private String assessmentDate;
    private String findings;
    private int severityLevel;

    public Assessment(int assessmentId, String disasterId, String departmentId, String assessmentDate, String findings, int severityLevel) {
        this.assessmentId = assessmentId;
        this.disasterId = disasterId;
        this.departmentId = departmentId;
        this.assessmentDate = assessmentDate;
        this.findings = findings;
        this.severityLevel = severityLevel;
    }

    // Getters and toString() method
    public int getAssessmentId() {
        return assessmentId;
    }

    public String getDisasterId() {
        return disasterId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public String getAssessmentDate() {
        return assessmentDate;
    }

    public String getFindings() {
        return findings;
    }

    public int getSeverityLevel() {
        return severityLevel;
    }

    @Override
    public String toString() {
        return "Assessment ID: " + assessmentId + ", Disaster ID: " + disasterId + ", Department ID: " + departmentId + 
               ", Date: " + assessmentDate + ", Findings: " + findings + ", Severity: " + severityLevel;
    }
}