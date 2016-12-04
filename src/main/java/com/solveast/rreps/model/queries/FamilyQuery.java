package com.solveast.rreps.model.queries;

/**
 * Created by Андрей on 04.12.2016.
 */
public class FamilyQuery {
    private Long clientId;
    private Long applicantId;

    public FamilyQuery(Long clientId, Long applicantId) {
        this.clientId = clientId;
        this.applicantId = applicantId;
    }

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

    @Override
    public String toString() {
        return "FamilyQuery{" +
                "clientId=" + clientId +
                ", applicantId=" + applicantId +
                '}';
    }
}
