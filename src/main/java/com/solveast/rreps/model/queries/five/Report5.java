package com.solveast.rreps.model.queries.five;

import com.solveast.rreps.model.DateUtils;
import com.solveast.rreps.model.queries.family.Family;
import com.solveast.rreps.model.queries.family.Person;

/**
 * Created by Андрей on 12.12.2016.
 */
public class Report5 {
    private String iso3166_3;
    private Integer mUAC;
    private Integer fUAC;
    private Integer male_18_59;
    private Integer female_18_59;
    private Integer male_60_;
    private Integer female_60_;
    private Integer male_0_4;
    private Integer female_0_4;
    private Integer male_5_12;
    private Integer female_5_12;
    private Integer male_13_17;
    private Integer female_13_17;
    private Integer inds;
    private Integer cases;

    public Report5() {
        mUAC = 0;
        fUAC = 0;
        male_18_59 = 0;
        female_18_59 = 0;
        male_60_ = 0;
        female_60_ = 0;
        male_0_4 = 0;
        female_0_4 = 0;
        male_5_12 = 0;
        female_5_12 = 0;
        male_13_17 = 0;
        female_13_17 = 0;
        inds = 0;
        cases = 0;
    }

    public void incMUAC() {
        mUAC = mUAC + 1;
    }

    public void incFUAC() {
        fUAC = fUAC + 1;
    }

    public int getTotalByIso() {
        return getInds();
    }

    public void incInds(int familyNumber) {
        inds = inds + familyNumber;
    }

    public void incCases() {
        cases = cases + 1;
    }

    public void incAge(Person person) {
        String sex = person.getSexCd();
        int age = DateUtils.getAge(person.getBirthDate());

        if ("m".equals(sex) && age <= 4) male_0_4 = male_0_4 + 1;
        else if ("f".equals(sex) && age <= 4) female_0_4 = female_0_4 + 1;
        else if ("m".equals(sex) && age >= 5 && age <= 12) male_5_12 = male_5_12 + 1;
        else if ("f".equals(sex) && age >= 5 && age <= 12) female_5_12 = female_5_12 + 1;
        else if ("m".equals(sex) && age >= 13 && age <= 17) male_13_17 = male_13_17 + 1;
        else if ("f".equals(sex) && age >= 13 && age <= 17) female_13_17 = female_13_17 + 1;
        else if ("m".equals(sex) && age >= 18 && age <= 59) male_18_59 = male_18_59 + 1;
        else if ("f".equals(sex) && age >= 18 && age <= 59) female_18_59 = female_18_59 + 1;
        else if ("m".equals(sex) && age >= 60) male_60_ = male_60_ + 1;
        else if ("f".equals(sex) && age >= 60) female_60_ = female_60_ + 1;
    }

    public String getIso3166_3() {
        return iso3166_3;
    }

    public void setIso3166_3(String iso3166_3) {
        this.iso3166_3 = iso3166_3;
    }

    public Integer getmUAC() {
        return mUAC;
    }

    public void setmUAC(Integer mUAC) {
        this.mUAC = mUAC;
    }

    public Integer getfUAC() {
        return fUAC;
    }

    public void setfUAC(Integer fUAC) {
        this.fUAC = fUAC;
    }

    public Integer getMale_18_59() {
        return male_18_59;
    }

    public void setMale_18_59(Integer male_18_59) {
        this.male_18_59 = male_18_59;
    }

    public Integer getFemale_18_59() {
        return female_18_59;
    }

    public void setFemale_18_59(Integer female_18_59) {
        this.female_18_59 = female_18_59;
    }

    public Integer getMale_60_() {
        return male_60_;
    }

    public void setMale_60_(Integer male_60_) {
        this.male_60_ = male_60_;
    }

    public Integer getFemale_60_() {
        return female_60_;
    }

    public void setFemale_60_(Integer female_60_) {
        this.female_60_ = female_60_;
    }

    public Integer getMale_0_4() {
        return male_0_4;
    }

    public void setMale_0_4(Integer male_0_4) {
        this.male_0_4 = male_0_4;
    }

    public Integer getFemale_0_4() {
        return female_0_4;
    }

    public void setFemale_0_4(Integer female_0_4) {
        this.female_0_4 = female_0_4;
    }

    public Integer getMale_5_12() {
        return male_5_12;
    }

    public void setMale_5_12(Integer male_5_12) {
        this.male_5_12 = male_5_12;
    }

    public Integer getFemale_5_12() {
        return female_5_12;
    }

    public void setFemale_5_12(Integer female_5_12) {
        this.female_5_12 = female_5_12;
    }

    public Integer getMale_13_17() {
        return male_13_17;
    }

    public void setMale_13_17(Integer male_13_17) {
        this.male_13_17 = male_13_17;
    }

    public Integer getFemale_13_17() {
        return female_13_17;
    }

    public void setFemale_13_17(Integer female_13_17) {
        this.female_13_17 = female_13_17;
    }

    public Integer getInds() {
        return inds;
    }

    public void setInds(Integer inds) {
        this.inds = inds;
    }

    public Integer getCases() {
        return cases;
    }

    public void setCases(Integer cases) {
        this.cases = cases;
    }

    public boolean setUAC(Person person) {
        if (person != null && person.getAge() < 18 && person.getApplicant()) {
            if ("m".equals(person.getSexCd())) {
                incMUAC();
                return true;
            }
            else if ("f".equals(person.getSexCd())) {
                incFUAC();
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Report5{" +
                "iso3166_3='" + iso3166_3 + '\'' +
                ", mUAC=" + mUAC +
                ", fUAC=" + fUAC +
                ", male_18_59=" + male_18_59 +
                ", female_18_59=" + female_18_59 +
                ", male_60_=" + male_60_ +
                ", female_60_=" + female_60_ +
                ", male_0_4=" + male_0_4 +
                ", female_0_4=" + female_0_4 +
                ", male_5_12=" + male_5_12 +
                ", female_5_12=" + female_5_12 +
                ", male_13_17=" + male_13_17 +
                ", female_13_17=" + female_13_17 +
                ", inds=" + inds +
                ", cases=" + cases +
                '}';
    }
}
