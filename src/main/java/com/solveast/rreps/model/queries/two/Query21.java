package com.solveast.rreps.model.queries.two;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Андрей on 25.11.2016.
 */
public class Query21 {
    private Long clientId;
    private String sexCd;
    private LocalDateTime birthDate;
    private String unRelationshipCd;
    private String actionType;
    private Integer actionResultId;
    private LocalDateTime realTimeStart;
    private LocalDateTime realTimeStop;
    private LocalDateTime scheduledTimeStart;
    private LocalDateTime scheduledTimeStop;

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

    public Integer getActionResultId() {
        return actionResultId;
    }

    public void setActionResultId(Integer actionResultId) {
        this.actionResultId = actionResultId;
    }

    public LocalDateTime getRealTimeStart() {
        return realTimeStart;
    }

    public String getSexCd() {
        return sexCd;
    }

    public void setSexCd(String sexCd) {
        this.sexCd = sexCd;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        if (birthDate != null)
            this.birthDate = birthDate.toLocalDateTime();
    }

    public String getUnRelationshipCd() {
        return unRelationshipCd;
    }

    public void setUnRelationshipCd(String unRelationshipCd) {
        this.unRelationshipCd = unRelationshipCd;
    }

    public void setRealTimeStart(Timestamp realTimeStart) {
        if (realTimeStart != null)
            this.realTimeStart = realTimeStart.toLocalDateTime();
    }

    public LocalDateTime getRealTimeStop() {
        return realTimeStop;
    }

    public void setRealTimeStop(Timestamp realTimeStop) {
        if (realTimeStop != null)
            this.realTimeStop = realTimeStop.toLocalDateTime();
    }

    public LocalDateTime getScheduledTimeStart() {
        return scheduledTimeStart;
    }

    public void setScheduledTimeStart(Timestamp scheduledTimeStart) {
        if (scheduledTimeStart != null)
            this.scheduledTimeStart = scheduledTimeStart.toLocalDateTime();
    }

    public LocalDateTime getScheduledTimeStop() {
        return scheduledTimeStop;
    }

    public void setScheduledTimeStop(Timestamp scheduledTimeStop) {
        if (scheduledTimeStop != null)
            this.scheduledTimeStop = scheduledTimeStop.toLocalDateTime();
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public void setRealTimeStart(LocalDateTime realTimeStart) {
        this.realTimeStart = realTimeStart;
    }

    public void setRealTimeStop(LocalDateTime realTimeStop) {
        this.realTimeStop = realTimeStop;
    }

    public void setScheduledTimeStart(LocalDateTime scheduledTimeStart) {
        this.scheduledTimeStart = scheduledTimeStart;
    }

    public void setScheduledTimeStop(LocalDateTime scheduledTimeStop) {
        this.scheduledTimeStop = scheduledTimeStop;
    }

    @Override
    public String toString() {
        return "Query21{" +
                "clientId=" + clientId +
                ", sexCd='" + sexCd + '\'' +
                ", birthDate=" + birthDate +
                ", unRelationshipCd='" + unRelationshipCd + '\'' +
                ", actionType='" + actionType + '\'' +
                ", actionResultId=" + actionResultId +
                ", realTimeStart=" + realTimeStart +
                ", realTimeStop=" + realTimeStop +
                ", scheduledTimeStart=" + scheduledTimeStart +
                ", scheduledTimeStop=" + scheduledTimeStop +
                '}';
    }
}
