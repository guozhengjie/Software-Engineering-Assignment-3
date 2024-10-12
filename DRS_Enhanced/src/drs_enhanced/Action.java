/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package drs_enhanced;

/**
 *
 * @author Zhengjie GUO
 */
public class Action {
    private int actionId;
    private String disasterId;
    private String teamId;
    private String actionType;
    private String actionDescription;
    private String startTime;
    private String endTime;
    private String status;

    public Action(int actionId, String disasterId, String teamId, String actionType, String actionDescription, String startTime, String endTime, String status) {
        this.actionId = actionId;
        this.disasterId = disasterId;
        this.teamId = teamId;
        this.actionType = actionType;
        this.actionDescription = actionDescription;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    // Getters and Setters
    public int getActionId() {
        return actionId;
    }

    public String getDisasterId() {
        return disasterId;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getActionType() {
        return actionType;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Action ID: " + actionId + ", Disaster ID: " + disasterId + ", Team ID: " + teamId + 
               ", Action Type: " + actionType + ", Description: " + actionDescription + 
               ", Start: " + startTime + ", End: " + endTime + ", Status: " + status;
    }
}