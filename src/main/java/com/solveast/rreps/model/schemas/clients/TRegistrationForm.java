package com.solveast.rreps.model.schemas.clients;

import java.util.Date;

/**
 * Created by Андрей on 28.10.2016.
 */
public class TRegistrationForm {
    private Long clientId;
    private Date unhcrDate;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Date getUnhcrDate() {
        return unhcrDate;
    }

    public void setUnhcrDate(Date unhcrDate) {
        this.unhcrDate = unhcrDate;
    }

    @Override
    public String toString() {
        return "TRegistrationForm{" +
                "clientId=" + clientId +
                ", unhcrDate=" + unhcrDate +
                '}';
    }
}
