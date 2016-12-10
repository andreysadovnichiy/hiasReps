package com.solveast.rreps.model.queries.one;

/**
 * Created by Андрей on 20.11.2016.
 */
public class Report1 {
    private String iso3166_3;
    private Integer male;
    private Integer female;
    private Integer boys;
    private Integer girls;

    public Report1() {
        this.male = 0;
        this.female = 0;
        this.boys = 0;
        this.girls = 0;
    }

    public String getIso3166_3() {
        return iso3166_3;
    }

    public void setIso3166_3(String iso3166_3) {
        this.iso3166_3 = iso3166_3;
    }

    public Integer getMale() {
        return male;
    }

    public void setMale(Integer male) {
        this.male = male;
    }

    public Integer getFemale() {
        return female;
    }

    public void setFemale(Integer female) {
        this.female = female;
    }

    public Integer getBoys() {
        return boys;
    }

    public void setBoys(Integer boys) {
        this.boys = boys;
    }

    public Integer getGirls() {
        return girls;
    }

    public void setGirls(Integer girls) {
        this.girls = girls;
    }

    public void addMale(){
        male++;
    }

    public void addFemale(){
        female++;
    }

    public void addBoy(){
        boys++;
    }

    public void addGirl(){
        girls++;
    }

    @Override
    public String toString() {
        return "Report1{" +
                ", iso3166_3='" + iso3166_3 + '\'' +
                ", male=" + male +
                ", female=" + female +
                ", boys=" + boys +
                ", girls=" + girls +
                '}';
    }
}
