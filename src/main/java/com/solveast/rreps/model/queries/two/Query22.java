package com.solveast.rreps.model.queries.two;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Андрей on 25.11.2016.
 */
public class Query22 {
    private Long clientId;
    private String sexCd;
    private LocalDateTime birthDate;
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

    @Override
    public String toString() {
        return "Query22{" +
                "clientId=" + clientId +
                ", sexCd='" + sexCd + '\'' +
                ", birthDate=" + birthDate +
                ", registerTime=" + registerTime +
                '}';
    }
}
