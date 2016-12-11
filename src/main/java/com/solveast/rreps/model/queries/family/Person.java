package com.solveast.rreps.model.queries.family;

import com.solveast.rreps.model.DateUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Андрей on 10.12.2016.
 */
public class Person {
    private Long clientId;
    private Long applicantId;
    private LocalDateTime registerTime;
    private LocalDateTime unhcrDate;
    private String sexCd;
    private LocalDateTime birthDate;
    private String unRelationshipCd;
    private Integer age;
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

    public Boolean getApplicant() {
        if(applicantId == null || applicantId == 0)
            return true;
        else
            return false;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = DateUtils.toLocalDateTime(registerTime);
    }

    public LocalDateTime getUnhcrDate() {
        return unhcrDate;
    }

    public void setUnhcrDate(LocalDateTime unhcrDate) {
        this.unhcrDate = unhcrDate;
    }

    public void setUnhcrDate(Timestamp unhcrDate) {
        this.unhcrDate = DateUtils.toLocalDateTime(unhcrDate);
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

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = DateUtils.toLocalDateTime(birthDate);
    }

    public String getUnRelationshipCd() {
        return unRelationshipCd;
    }

    public void setUnRelationshipCd(String unRelationshipCd) {
        this.unRelationshipCd = unRelationshipCd;
    }

    public Integer getAge() {
        return DateUtils.getAge(birthDate);
    }

    public Boolean isLessThen18() {
        if (DateUtils.getAge(birthDate) < 18)
            return true;
        if ("CHI".equals(unRelationshipCd)) {
            return true;
        }
        return false;
    }
}
