package com.solveast.rreps.model.queries.family;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Андрей on 04.12.2016.
 */
public class FamilyQuery {
    private Long clientId;
    private Long applicantId;
    private Integer familyMembersNumber;
    private String sexCd;
    private LocalDateTime birthDate;

    public FamilyQuery() {
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

    public Integer getFamilyMembersNumber() {
        return familyMembersNumber;
    }

    public void setFamilyMembersNumber(Integer familyMembersNumber) {
        this.familyMembersNumber = familyMembersNumber;
    }

    @Override
    public String toString() {
        return "FamilyQuery{" +
                "clientId=" + clientId +
                ", applicantId=" + applicantId +
                ", familyMembersNumber=" + familyMembersNumber +
                ", sexCd='" + sexCd + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
