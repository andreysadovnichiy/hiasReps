package com.solveast.rreps.model.queries.one;

import com.solveast.rreps.model.queries.family.Family;
import com.solveast.rreps.model.queries.family.Person;

import java.util.List;


/**
 * Created by Андрей on 10.12.2016.
 */
public class Intro extends Family {
    private String iso3166_3;
    private Integer totalFamilyMembers;

    public Intro(Person person, List<Person> family, String iso3166_3) {
        super(person, family);
        this.iso3166_3 = iso3166_3;
    }

    public Intro() {
    }

    public String getIso3166_3() {
        return iso3166_3;
    }

    public void setIso3166_3(String iso3166_3) {
        this.iso3166_3 = iso3166_3;
    }

    public Integer getTotalFamilyMembers() {
        return super.getFamilyPersonNumber();
    }

    public void setTotalFamilyMembers(Integer totalFamilyMembers) {
        this.totalFamilyMembers = totalFamilyMembers;
    }

    @Override
    public String toString() {
        return "Intro{" +
                "iso3166_3='" + iso3166_3 + '\'' +
                '}';
    }
}
