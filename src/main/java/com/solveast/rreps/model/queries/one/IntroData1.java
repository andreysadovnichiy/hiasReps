package com.solveast.rreps.model.queries.one;

import com.solveast.rreps.model.DateConverterUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Андрей on 06.11.2016.
 */
public class IntroData1 {
    private Long clientId;
    private Long applicantId;
    private Integer applicantsNumber;
    private LocalDateTime registerTime;
    private LocalDateTime unhcrDate;
    private String sexCd;
    private String iso3166_3;
    private LocalDateTime birthDate;
    private Integer age;
    private String un_relationship_cd;
    private Boolean more18;
    private Boolean applicant;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Integer getApplicantsNumber() {
        return applicantsNumber;
    }

    public void setApplicantsNumber(Integer applicantsNumber) {
        this.applicantsNumber = applicantsNumber;
    }

    public Long getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Long applicantId) {
        this.applicantId = applicantId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = DateConverterUtils.toLocalDateTime(registerTime);
    }

    public LocalDateTime getUnhcrDate() {
        return unhcrDate;
    }

    public void setUnhcrDate(Timestamp unhcrDate) {
        this.registerTime = DateConverterUtils.toLocalDateTime(unhcrDate);
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
        this.birthDate = DateConverterUtils.toLocalDateTime(birthDate);
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
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

    public Boolean getMore18() {
        return more18;
    }

    public void setMore18(Boolean more18) {
        this.more18 = more18;
    }

    @Override
    public String toString() {
        return "IntroData1{" +
                "clientId=" + clientId +
                ", applicantId=" + applicantId +
                ", applicantsNumber=" + applicantsNumber +
                ", registerTime=" + registerTime +
                ", unhcrDate=" + unhcrDate +
                ", sexCd='" + sexCd + '\'' +
                ", iso3166_3='" + iso3166_3 + '\'' +
                ", birthDate=" + birthDate +
                ", age=" + age +
                ", un_relationship_cd='" + un_relationship_cd + '\'' +
                ", more18=" + more18 +
                ", applicant=" + applicant +
                '}';
    }
}
