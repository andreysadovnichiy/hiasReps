package com.solveast.rreps.model.db.schemas.clients;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Андрей on 28.10.2016.
 */
public class TRegistrationForm {
    private Long clientId;
    private LocalDateTime unhcrDate;
    private String unhcr_num;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getUnhcrDate() {
        return unhcrDate;
    }

    public void setUnhcrDate(LocalDateTime unhcrDate) {
        this.unhcrDate = unhcrDate;
    }

    public void setUnhcrDate(Timestamp unhcrDate) {
        this.unhcrDate = unhcrDate.toLocalDateTime();
    }

    public String getUnhcr_num() {
        return unhcr_num;
    }

    public void setUnhcr_num(String unhcr_num) {
        this.unhcr_num = unhcr_num;
    }

    @Override
    public String toString() {
        return "TRegistrationForm{" +
                "clientId=" + clientId +
                ", unhcrDate=" + unhcrDate +
                ", unhcr_num='" + unhcr_num + '\'' +
                '}';
    }
}
