package com.solveast.rreps.model.schemas;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Андрей on 06.11.2016.
 */
public class Query1 {
    private Long clientId;
    private Long applicantId;
    private LocalDateTime registerTime;
    private LocalDateTime unhcrDate;
    private String sexCd;
    private String iso3166_3;
    private LocalDateTime birthDate;
    private String un_relationship_cd;
    private Boolean applicant;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        if (registerTime != null)
            this.registerTime = registerTime.toLocalDateTime();
    }

    public LocalDateTime getUnhcrDate() {
        return unhcrDate;
    }

    public void setUnhcrDate(Timestamp unhcrDate) {
        if (unhcrDate != null)
            this.unhcrDate = unhcrDate.toLocalDateTime();
    }

    public String getSexCd() {
        return sexCd;
    }

    public void setSexCd(String sexCd) {
        this.sexCd = sexCd;
    }

    public String getIso3166_3() {
        return iso3166_3;
    }

    public void setIso3166_3(String iso3166_3) {
        this.iso3166_3 = iso3166_3;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        if (birthDate != null)
            this.birthDate = birthDate.toLocalDateTime();
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    public void setUnhcrDate(LocalDateTime unhcrDate) {
        this.unhcrDate = unhcrDate;
    }

    public Boolean getApplicant() {
        return applicant;
    }

    public void setApplicant(Boolean applicant) {
        this.applicant = applicant;
    }

    public String getUn_relationship_cd() {
        return un_relationship_cd;
    }

    public void setUn_relationship_cd(String un_relationship_cd) {
        this.un_relationship_cd = un_relationship_cd;
    }

    @Override
    public String toString() {
        return "Query1{" +
                "clientId=" + clientId +
                ", applicantId=" + applicantId +
                ", registerTime=" + registerTime +
                ", unhcrDate=" + unhcrDate +
                ", sexCd='" + sexCd + '\'' +
                ", iso3166_3='" + iso3166_3 + '\'' +
                ", birthDate=" + birthDate +
                ", un_relationship_cd='" + un_relationship_cd + '\'' +
                ", applicant=" + applicant +
                '}';
    }
}
