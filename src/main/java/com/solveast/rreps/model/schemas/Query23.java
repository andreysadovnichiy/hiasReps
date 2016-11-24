package com.solveast.rreps.model.schemas;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Андрей on 25.11.2016.
 */
public class Query23 {
    private Long clientId;
    private LocalDateTime registerTime;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        if (registerTime != null)
            this.registerTime = registerTime.toLocalDateTime();
    }

    @Override
    public String toString() {
        return "Query22{" +
                "clientId=" + clientId +
                ", registerTime=" + registerTime +
                '}';
    }
}
