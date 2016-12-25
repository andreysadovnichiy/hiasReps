package com.solveast.rreps.model.queries.seven;

import com.solveast.rreps.model.DateUtils;

import java.time.LocalDateTime;


/**
 * Created by Андрей on 22.12.2016.
 */
public class Query7 {
    private Long clientId;
    private String iso3166_3;
    private String sexCd;

    private LocalDateTime ladgetDate;
    private LocalDateTime decisionDate;
    private String msRejectionCd;
    private String instanceCd;
    private String courtDecisionCd;
    private Long courtCaseStateId;
    private String courtCaseStateName;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getIso3166_3() {
        return iso3166_3;
    }

    public void setIso3166_3(String iso3166_3) {
        this.iso3166_3 = iso3166_3;
    }

    public String getSexCd() {
        return sexCd;
    }

    public void setSexCd(String sexCd) {
        this.sexCd = sexCd;
    }

    public LocalDateTime getLadgetDate() {
        return ladgetDate;
    }

    public void setLadgetDate(LocalDateTime ladgetDate) {
        this.ladgetDate = ladgetDate;
    }

    public void setLadgetDate(String ladgetDate) {
        this.ladgetDate = DateUtils.toLocalDateTimeMultiFormat(ladgetDate);
    }

    public LocalDateTime getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(LocalDateTime decisionDate) {
        this.decisionDate = decisionDate;
    }

    public void setDecisionDate(String decisionDate) {
        this.decisionDate = DateUtils.toLocalDateTimeMultiFormat(decisionDate);
    }

    public String getMsRejectionCd() {
        return msRejectionCd;
    }

    public void setMsRejectionCd(String msRejectionCd) {
        this.msRejectionCd = msRejectionCd;
    }

    public String getInstanceCd() {
        return instanceCd;
    }

    public void setInstanceCd(String instanceCd) {
        this.instanceCd = instanceCd;
    }

    public String getCourtDecisionCd() {
        return courtDecisionCd;
    }

    public void setCourtDecisionCd(String courtDecisionCd) {
        this.courtDecisionCd = courtDecisionCd;
    }

    public Long getCourtCaseStateId() {
        return courtCaseStateId;
    }

    public void setCourtCaseStateId(Long courtCaseStateId) {
        this.courtCaseStateId = courtCaseStateId;
    }

    public String getCourtCaseStateName() {
        return courtCaseStateName;
    }

    public void setCourtCaseStateName(String courtCaseStateName) {
        this.courtCaseStateName = courtCaseStateName;
    }

    @Override
    public String toString() {
        return "Query7{" +
                "clientId=" + clientId +
                ", iso3166_3='" + iso3166_3 + '\'' +
                ", sexCd='" + sexCd + '\'' +
                ", ladgetDate=" + ladgetDate +
                ", decisionDate=" + decisionDate +
                ", msRejectionCd='" + msRejectionCd + '\'' +
                ", instanceCd='" + instanceCd + '\'' +
                ", courtDecisionCd='" + courtDecisionCd + '\'' +
                ", courtCaseStateId=" + courtCaseStateId +
                ", courtCaseStateName='" + courtCaseStateName + '\'' +
                '}';
    }
}
