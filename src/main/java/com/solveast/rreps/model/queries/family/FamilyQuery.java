package com.solveast.rreps.model.queries.family;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Андрей on 04.12.2016.
 */
public class FamilyQuery {
    private Long clientId;
    private Long applicantId;
    private String sexCd;
    private LocalDateTime birthDate;
    private String unRelationshipCd;

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

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getUnRelationshipCd() {
        return unRelationshipCd;
    }

    public void setUnRelationshipCd(String unRelationshipCd) {
        this.unRelationshipCd = unRelationshipCd;
    }

    public FamilyReport toFamilyReport() {
        FamilyReport report = new FamilyReport();

        report.setClientId(clientId);
        report.setApplicantId(applicantId);
        report.setSexCd(sexCd);
        report.setBirthDate(birthDate);

        return report;
    }

    public Person toPerson() {
        Person person = new Person();

        person.setClientId(getClientId());

        return person;
    }

    @Override
    public String toString() {
        return "FamilyQuery{" +
                "clientId=" + clientId +
                ", applicantId=" + applicantId +
                ", sexCd='" + sexCd + '\'' +
                ", birthDate=" + birthDate +
                ", unRelationshipCd='" + unRelationshipCd + '\'' +
                '}';
    }

}
