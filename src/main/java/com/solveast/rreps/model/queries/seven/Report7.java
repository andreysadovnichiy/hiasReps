package com.solveast.rreps.model.queries.seven;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 22.12.2016.
 */
public class Report7 {
    private String iso3166_3;
    private int activeM;
    private int activeF;
    private int legalArt5;
    private int legalArt6;
    private int legalArt8;
    private int instance1Pos;
    private int instance1Neg;
    private int instance1Pnd;
    private int instance1Cls;
    private int instance2Pos;
    private int instance2Neg;
    private int instance2Pnd;
    private int instance2ClsSsp;
    private int instance3Pos;
    private int instance3Neg;
    private int instance3Pnd;
    private int instance3ClsSsp;
    private int statusPnd1;
    private int statusPnd2;
    private int statusPnd3PosFin;
    private int statusPnd3NegFin;
    private int statusClosedPosFin;
    private int statusClosedExhausted;

    private List<Long> clientIds = new ArrayList<>();

    public void set(Query7 item) {
        boolean isClentFirstTimeToProcess = !clientIds.contains(item.getClientId());
        if (isClentFirstTimeToProcess)
            clientIds.add(item.getClientId());

        setIso3166_3(item.getIso3166_3());

        if (isClentFirstTimeToProcess) {
            if ("m".equals(item.getSexCd()))
                activeM = activeM + 1;
            else if ("f".equals(item.getSexCd()))
                activeF = activeF + 1;
        }

        switch (item.getMsRejectionCd()) {
            case "A5":
                legalArt5 = legalArt5 + 1;
                break;
            case "A6":
                legalArt6 = legalArt6 + 1;
                break;
            case "A8":
                legalArt8 = legalArt8 + 1;
                break;
        }

        String inst = item.getInstanceCd();
        String decision = item.getCourtDecisionCd();
        long courtCaseStateId = item.getCourtCaseStateId();

        if ("C1".equals(inst) && "POS".equals(decision))
            instance1Pos = instance1Pos + 1;
        else if ("C1".equals(inst) && "NEG".equals(decision))
            instance1Neg = instance1Neg + 1;
        else if ("C1".equals(inst) && "PND".equals(decision)) {
            instance1Pnd = instance1Pnd + 1;
            statusPnd1 = statusPnd1 + 1;
        } else if ("C1".equals(inst) && "CLS".equals(decision))
            instance1Cls = instance1Cls + 1;

        else if ("C2".equals(inst) && "POS".equals(decision))
            instance2Pos = instance2Pos + 1;
        else if ("C2".equals(inst) && "NEG".equals(decision))
            instance2Neg = instance2Neg + 1;
        else if ("C2".equals(inst) && "PND".equals(decision)) {
            instance2Pnd = instance2Pnd + 1;
            statusPnd2 = statusPnd2 + 1;
        } else if ("C2".equals(inst) && ("CLS".equals(decision) || "SSP".equals(decision)))
            instance2ClsSsp = instance2ClsSsp + 1;

        else if ("C3".equals(inst) && "POS".equals(decision))
            instance3Pos = instance3Pos + 1;
        else if ("C3".equals(inst) && "NEG".equals(decision))
            instance3Neg = instance3Neg + 1;
        else if ("C3".equals(inst) && "PND".equals(decision))
            instance3Pnd = instance3Pnd + 1;
        else if ("C3".equals(inst) && ("CLS".equals(decision) || "SSP".equals(decision)))
            instance3ClsSsp = instance3ClsSsp + 1;

        if (courtCaseStateId == 4)
            statusPnd3PosFin = statusPnd3PosFin + 1;
        else if (courtCaseStateId == 3)
            statusPnd3NegFin = statusPnd3NegFin + 1;
        else if (courtCaseStateId == 6)
            statusClosedPosFin = statusClosedPosFin + 1;
        else if (courtCaseStateId == 5)
            statusClosedExhausted = statusClosedExhausted + 1;
    }

    public Report7() {
        activeM = 0;
        activeF = 0;
        legalArt5 = 0;
        legalArt6 = 0;
        legalArt8 = 0;
        instance1Pos = 0;
        instance1Neg = 0;
        instance1Pnd = 0;
        instance1Cls = 0;
        instance2Pos = 0;
        instance2Neg = 0;
        instance2Pnd = 0;
        instance2ClsSsp = 0;
        instance3Pos = 0;
        instance3Neg = 0;
        instance3Pnd = 0;
        instance3ClsSsp = 0;
        statusPnd1 = 0;
        statusPnd2 = 0;
        statusPnd3PosFin = 0;
        statusPnd3NegFin = 0;
        statusClosedPosFin = 0;
        statusClosedExhausted = 0;
    }

    public String getIso3166_3() {
        return iso3166_3;
    }

    public void setIso3166_3(String iso3166_3) {
        this.iso3166_3 = iso3166_3;
    }

    public int getActiveM() {
        return activeM;
    }

    public void setActiveM(int activeM) {
        this.activeM = activeM;
    }

    public int getActiveF() {
        return activeF;
    }

    public void setActiveF(int activeF) {
        this.activeF = activeF;
    }

    public int getActiveT() {
        return getActiveF() + getActiveM();
    }

    public int getLegalArt5() {
        return legalArt5;
    }

    public void setLegalArt5(int legalArt5) {
        this.legalArt5 = legalArt5;
    }

    public int getLegalArt6() {
        return legalArt6;
    }

    public void setLegalArt6(int legalArt6) {
        this.legalArt6 = legalArt6;
    }

    public int getLegalArt8() {
        return legalArt8;
    }

    public void setLegalArt8(int legalArt8) {
        this.legalArt8 = legalArt8;
    }

    public int getInstance1Pos() {
        return instance1Pos;
    }

    public void setInstance1Pos(int instance1Pos) {
        this.instance1Pos = instance1Pos;
    }

    public int getInstance1Neg() {
        return instance1Neg;
    }

    public void setInstance1Neg(int instance1Neg) {
        this.instance1Neg = instance1Neg;
    }

    public int getInstance1Pnd() {
        return instance1Pnd;
    }

    public void setInstance1Pnd(int instance1Pnd) {
        this.instance1Pnd = instance1Pnd;
    }

    public int getInstance1Cls() {
        return instance1Cls;
    }

    public void setInstance1Cls(int instance1Cls) {
        this.instance1Cls = instance1Cls;
    }

    public int getInstance2Pos() {
        return instance2Pos;
    }

    public void setInstance2Pos(int instance2Pos) {
        this.instance2Pos = instance2Pos;
    }

    public int getInstance2Neg() {
        return instance2Neg;
    }

    public void setInstance2Neg(int instance2Neg) {
        this.instance2Neg = instance2Neg;
    }

    public int getInstance2Pnd() {
        return instance2Pnd;
    }

    public void setInstance2Pnd(int instance2Pnd) {
        this.instance2Pnd = instance2Pnd;
    }

    public int getInstance2ClsSsp() {
        return instance2ClsSsp;
    }

    public void setInstance2ClsSsp(int instance2ClsSsp) {
        this.instance2ClsSsp = instance2ClsSsp;
    }

    public int getInstance3Pos() {
        return instance3Pos;
    }

    public void setInstance3Pos(int instance3Pos) {
        this.instance3Pos = instance3Pos;
    }

    public int getInstance3Neg() {
        return instance3Neg;
    }

    public void setInstance3Neg(int instance3Neg) {
        this.instance3Neg = instance3Neg;
    }

    public int getInstance3Pnd() {
        return instance3Pnd;
    }

    public void setInstance3Pnd(int instance3Pnd) {
        this.instance3Pnd = instance3Pnd;
    }

    public int getInstance3ClsSsp() {
        return instance3ClsSsp;
    }

    public void setInstance3ClsSsp(int instance3ClsSsp) {
        this.instance3ClsSsp = instance3ClsSsp;
    }

    public int getStatusPnd1() {
        return statusPnd1;
    }

    public void setStatusPnd1(int statusPnd1) {
        this.statusPnd1 = statusPnd1;
    }

    public int getStatusPnd2() {
        return statusPnd2;
    }

    public void setStatusPnd2(int statusPnd2) {
        this.statusPnd2 = statusPnd2;
    }

    public int getStatusPnd3PosFin() {
        return statusPnd3PosFin;
    }

    public void setStatusPnd3PosFin(int statusPnd3PosFin) {
        this.statusPnd3PosFin = statusPnd3PosFin;
    }

    public int getStatusPnd3NegFin() {
        return statusPnd3NegFin;
    }

    public void setStatusPnd3NegFin(int statusPnd3NegFin) {
        this.statusPnd3NegFin = statusPnd3NegFin;
    }

    public int getStatusClosedPosFin() {
        return statusClosedPosFin;
    }

    public void setStatusClosedPosFin(int statusClosedPosFin) {
        this.statusClosedPosFin = statusClosedPosFin;
    }

    public int getStatusClosedExhausted() {
        return statusClosedExhausted;
    }

    public void setStatusClosedExhausted(int statusClosedExhausted) {
        this.statusClosedExhausted = statusClosedExhausted;
    }

    @Override
    public String toString() {
        return "Report7{" +
                "iso3166_3='" + iso3166_3 + '\'' +
                ", activeM=" + activeM +
                ", activeF=" + activeF +
                ", legalArt5=" + legalArt5 +
                ", legalArt6=" + legalArt6 +
                ", legalArt8=" + legalArt8 +
                ", instance1Pos=" + instance1Pos +
                ", instance1Neg=" + instance1Neg +
                ", instance1Pnd=" + instance1Pnd +
                ", instance1Cls=" + instance1Cls +
                ", instance2Pos=" + instance2Pos +
                ", instance2Neg=" + instance2Neg +
                ", instance2Pnd=" + instance2Pnd +
                ", instance2ClsSsp=" + instance2ClsSsp +
                ", instance3Pos=" + instance3Pos +
                ", instance3Neg=" + instance3Neg +
                ", instance3Pnd=" + instance3Pnd +
                ", instance3ClsSsp=" + instance3ClsSsp +
                ", statusPnd1=" + statusPnd1 +
                ", statusPnd2=" + statusPnd2 +
                ", statusPnd3PosFin=" + statusPnd3PosFin +
                ", statusPnd3NegFin=" + statusPnd3NegFin +
                ", statusClosedPosFin=" + statusClosedPosFin +
                ", statusClosedExhausted=" + statusClosedExhausted +
                '}';
    }
}



