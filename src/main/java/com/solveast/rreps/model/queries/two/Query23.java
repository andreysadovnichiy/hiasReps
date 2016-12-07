package com.solveast.rreps.model.queries.two;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Андрей on 25.11.2016.
 */
public class Query23 {
    private Long clientId;
    private String sexCd;
    private String unRelationshipCd;
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

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    public String getSexCd() {
        return sexCd;
    }

    public void setSexCd(String sexCd) {
        this.sexCd = sexCd;
    }

    @Override
    public String toString() {
        return "Query23{" +
                "clientId=" + clientId +
                ", sexCd='" + sexCd + '\'' +
                ", birthDate=" + birthDate +
                ", registerTime=" + registerTime +
                '}';
    }
}
