package com.solveast.rreps.model.db.schemas.clients;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Андрей on 28.10.2016.
 */
public class TClient {
    private Long clientId;
    private Long applicantId;
    private LocalDateTime registerTime;
    private String sexCd;
    private String iso3166_3;
    private Date birthDate;
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

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime.toLocalDateTime();
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getApplicant() {
        return applicant;
    }

    public void setApplicant(Boolean applicant) {
        this.applicant = applicant;
    }

    @Override
    public String toString() {
        return "TClient{" +
                "clientId=" + clientId +
                ", applicantId=" + applicantId +
                ", registerTime=" + registerTime +
                ", sexCd='" + sexCd + '\'' +
                ", iso3166_3='" + iso3166_3 + '\'' +
                ", birthDate=" + birthDate +
                ", applicant=" + applicant +
                '}';
    }
}
