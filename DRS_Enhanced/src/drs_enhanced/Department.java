/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drs_enhanced;

/**
 *
 * @author Zhengjie GUO
 */
public class Department {
    private int departmentID;
    private String departmentName;
    private String responseTime;

    public Department(int departmentID, String departmentName, String responseTime) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.responseTime = responseTime;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }
}
