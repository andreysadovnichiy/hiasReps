package com.solveast.rreps.model.db.schemas.tasks;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Андрей on 07.11.2016.
 */
public class TAction {
    private Long actionId;
    private Long clientId;
    private String actionType;
    private String actionStateCd;
    private LocalDateTime realTimeStart;
    private LocalDateTime realTimeStop;
    private LocalDateTime scheduledTimeStart;
    private LocalDateTime scheduledTimeStop;

    public TAction() {
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getActionStateCd() {
        return actionStateCd;
    }

    public void setActionStateCd(String actionStateCd) {
        this.actionStateCd = actionStateCd;
    }

    public LocalDateTime getRealTimeStart() {
        return realTimeStart;
    }

    public void setRealTimeStart(LocalDateTime realTimeStart) {
        this.realTimeStart = realTimeStart;
    }

    public void setRealTimeStart(Timestamp realTimeStart) {
        this.realTimeStart = realTimeStart.toLocalDateTime();
    }

    public LocalDateTime getRealTimeStop() {
        return realTimeStop;
    }

    public void setRealTimeStop(LocalDateTime realTimeStop) {
        this.realTimeStop = realTimeStop;
    }

    public void setRealTimeStop(Timestamp realTimeStop) {
        this.realTimeStop = realTimeStop.toLocalDateTime();
    }

    public LocalDateTime getScheduledTimeStart() {
        return scheduledTimeStart;
    }

    public void setScheduledTimeStart(LocalDateTime scheduledTimeStart) {
        this.scheduledTimeStart = scheduledTimeStart;
    }

    public void setScheduledTimeStart(Timestamp scheduledTimeStart) {
        this.scheduledTimeStart = scheduledTimeStart.toLocalDateTime();
    }

    public LocalDateTime getScheduledTimeStop() {
        return scheduledTimeStop;
    }

    public void setScheduledTimeStop(LocalDateTime scheduledTimeStop) {
        this.scheduledTimeStop = scheduledTimeStop;
    }

    public void setScheduledTimeStop(Timestamp scheduledTimeStop) {
        this.scheduledTimeStop = scheduledTimeStop.toLocalDateTime();
    }

    @Override
    public String toString() {
        return "TAction{" +
                "actionId=" + actionId +
                ", clientId=" + clientId +
                ", actionType='" + actionType + '\'' +
                ", actionStateCd='" + actionStateCd + '\'' +
                ", realTimeStart=" + realTimeStart +
                ", realTimeStop=" + realTimeStop +
                ", scheduledTimeStart=" + scheduledTimeStart +
                ", scheduledTimeStop=" + scheduledTimeStop +
                '}';
    }
}
