package com.solveast.rreps.model.schemas;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Андрей on 25.11.2016.
 */
public class Query21 {
    private Long clientId;
    private String actionType;
    private String actionStateCd;
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

    public String getActionStateCd() {
        return actionStateCd;
    }

    public void setActionStateCd(String actionStateCd) {
        this.actionStateCd = actionStateCd;
    }

    public LocalDateTime getRealTimeStart() {
        return realTimeStart;
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

    @Override
    public String toString() {
        return "Query21{" +
                "clientId=" + clientId +
                ", actionType='" + actionType + '\'' +
                ", actionStateCd='" + actionStateCd + '\'' +
                ", realTimeStart=" + realTimeStart +
                ", realTimeStop=" + realTimeStop +
                ", scheduledTimeStart=" + scheduledTimeStart +
                ", scheduledTimeStop=" + scheduledTimeStop +
                '}';
    }
}
