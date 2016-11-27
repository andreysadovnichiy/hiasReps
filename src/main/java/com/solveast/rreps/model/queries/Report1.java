package com.solveast.rreps.model.queries;

/**
 * Created by Андрей on 20.11.2016.
 */
public class Report1 {
    private Integer id;
    private String iso3166_3;
    private Integer male;
    private Integer female;
    private Integer boys;
    private Integer girls;

    public Report1(Integer id, String iso3166_3, Integer male, Integer female, Integer boys, Integer girls) {
        this.id = id;
        this.iso3166_3 = iso3166_3;
        this.male = male;
        this.female = female;
        this.boys = boys;
        this.girls = girls;
    }

    public Report1() {
        id = 0;
        iso3166_3 = "";
        male = 0;
        female = 0;
        boys = 0;
        girls = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Report1{" +
                "id=" + id +
                ", iso3166_3='" + iso3166_3 + '\'' +
                ", male=" + male +
                ", female=" + female +
                ", boys=" + boys +
                ", girls=" + girls +
                '}';
    }
}
