package com.solveast.rreps.model.queries.six;

import com.solveast.rreps.model.queries.family.Family;
import com.solveast.rreps.model.queries.family.Person;

/**
 * Created by Андрей on 18.12.2016.
 */
public class Report6 {
    private String iso3166_3;
    private int familyNumber = 0;
    private int totalPersons = 0;
    private int iUAC = 0;

    public Report6(String iso3166_3, Integer familyNumber, Integer totalPersons, Integer iUAC) {
        this.iso3166_3 = iso3166_3;
        this.familyNumber = familyNumber;
        this.totalPersons = totalPersons;
        this.iUAC = iUAC;
    }

    public Report6() {
    }

    public String getIso3166_3() {
        return iso3166_3;
    }

    public void setIso3166_3(String iso3166_3) {
        this.iso3166_3 = iso3166_3;
    }

    public Integer getFamilyNumber() {
        return familyNumber;
    }

    public void setFamilyNumber(Integer familyNumber) {
        this.familyNumber = familyNumber;
    }

    public void incFamilyNumber() {
        familyNumber = familyNumber + 1;
    }

    public Integer getTotalPersons() {
        return totalPersons;
    }

    public void setTotalPersons(Integer totalPersons) {
        this.totalPersons = totalPersons;
    }

    public void incTotalPersons(int count) {
        totalPersons = totalPersons + count;
    }

    public Integer getiUAC() {
        return iUAC;
    }

    public void setiUAC(Integer iUAC) {
        this.iUAC = iUAC;
    }

    public void incIUAC() {
        iUAC = iUAC + 1;
    }

    @Override
    public String toString() {
        return "Report6{" +
                "iso3166_3='" + iso3166_3 + '\'' +
                ", familyNumber=" + familyNumber +
                ", totalPersons=" + totalPersons +
                ", iUAC=" + iUAC +
                '}';
    }
}

