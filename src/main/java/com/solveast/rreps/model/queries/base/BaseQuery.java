package com.solveast.rreps.model.queries.base;

import com.solveast.rreps.model.DateUtils;
import com.solveast.rreps.model.queries.family.Person;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Андрей on 10.12.2016.
 */
public class BaseQuery {
    private Long clientId;
    private Long applicantId;
    private LocalDateTime registerTime;
    private LocalDateTime unhcrDate;
    private String sexCd;
    private LocalDateTime birthDate;
    private String unRelationshipCd;
    private Integer fileStatusId;
    private Integer age;

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
        this.age = DateUtils.getAge(birthDate);
        this.birthDate = DateUtils.toLocalDateTime(birthDate);
    }

    public Integer getAge() {
        if (birthDate != null)
            return age;
        else
            return 100;
    }

    public String getUnRelationshipCd() {
        return unRelationshipCd;
    }

    public void setUnRelationshipCd(String unRelationshipCd) {
        this.unRelationshipCd = unRelationshipCd;
    }

    public Boolean isLessThen18() {
        if ("CHI".equals(unRelationshipCd))
            return true;
        if (age <= 18)
            return true;
        return false;
    }

    public Integer getFileStatusId() {
        return fileStatusId;
    }

    public void setFileStatusId(Integer fileStatusId) {
        this.fileStatusId = fileStatusId;
    }

    public Person toPerson() {
        Person person = new Person();

        person.setClientId(getClientId());
        person.setApplicantId(getApplicantId());
        person.setRegisterTime(getRegisterTime());
        person.setUnhcrDate(getUnhcrDate());
        person.setSexCd(getSexCd());
        person.setBirthDate(getBirthDate());
        person.setUnRelationshipCd(getUnRelationshipCd());
        person.setFileStatusId(getFileStatusId());
        return person;
    }

    @Override
    public String toString() {
        return "BaseQuery{" +
                "clientId=" + clientId +
                ", applicantId=" + applicantId +
                ", fileStatusId=" + fileStatusId +
                ", registerTime=" + registerTime +
                ", unhcrDate=" + unhcrDate +
                ", sexCd='" + sexCd + '\'' +
                ", birthDate=" + birthDate +
                ", unRelationshipCd='" + unRelationshipCd + '\'' +
                '}';
    }
}
