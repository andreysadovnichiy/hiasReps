package com.solveast.rreps.model.schemas.clients;

import java.util.Date;

/**
 * Created by Андрей on 28.10.2016.
 */
public class TClient {
    private Long clientId;
    private Long applicantId;
    private Date registerTime;
    private Character sexCd;
    private String iso3166_3;
    private Date birthDate;

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

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Character getSexCd() {
        return sexCd;
    }

    public void setSexCd(Character sexCd) {
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

    @Override
    public String toString() {
        return "TClient{" +
                "clientId=" + clientId +
                ", applicantId=" + applicantId +
                ", registerTime=" + registerTime +
                ", sexCd=" + sexCd +
                ", iso3166_3='" + iso3166_3 + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
