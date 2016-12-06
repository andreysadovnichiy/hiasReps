package com.solveast.rreps.model.queries.family;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Андрей on 04.12.2016.
 */
public class FamilyReport {
    private Long clientId;
    private Long applicantId;
    private Integer familyMembersNumber;
    private String sexCd;
    private LocalDateTime birthDate;
    private Integer age;
    private Boolean more18;

    public FamilyReport() {
    }

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

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getFamilyMembersNumber() {
        return familyMembersNumber;
    }

    public void setFamilyMembersNumber(Integer familyMembersNumber) {
        this.familyMembersNumber = familyMembersNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getMore18() {
        return more18;
    }

    public void setMore18(Boolean more18) {
        this.more18 = more18;
    }

    @Override
    public String toString() {
        return "FamilyReport{" +
                "clientId=" + clientId +
                ", applicantId=" + applicantId +
                ", familyMembersNumber=" + familyMembersNumber +
                ", sexCd='" + sexCd + '\'' +
                ", birthDate=" + birthDate +
                ", age=" + age +
                ", more18=" + more18 +
                '}';
    }
}
