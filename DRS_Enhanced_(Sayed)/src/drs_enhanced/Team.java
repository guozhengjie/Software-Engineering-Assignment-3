/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drs_enhanced;

/**
 *
 * @author Sayed
 */
public class Team {
    private int teamId;
    private String teamName;
    private String contactInfo;

    public Team(int teamId, String teamName, String contactInfo) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.contactInfo = contactInfo;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
