package com.solveast.rreps.model.queries.one;

import com.solveast.rreps.model.queries.base.BaseQuery;
import com.solveast.rreps.model.queries.family.Person;

/**
 * Created by Андрей on 06.11.2016.
 */
public class Query1 extends BaseQuery {
    private String iso3166_3;
    private Boolean applicant;

    public String getIso3166_3() {
        return iso3166_3;
    }

    public void setIso3166_3(String iso3166_3) {
        this.iso3166_3 = iso3166_3;
    }

    public Boolean getApplicant() {
        return applicant;
    }

    public void setApplicant(Boolean applicant) {
        this.applicant = applicant;
    }

    public Person toPerson() {
        Person person = new Person();

        person.setClientId(getClientId());
        person.setApplicantId(getApplicantId());
        person.setRegisterTime(getRegisterTime());
        person.setUnhcrDate(getUnhcrDate());
        person.setSexCd(getSexCd());
        person.setBirthDate(getBirthDate());
        person.setUnRelationshipCd(getUnRelationshipCd());
        return person;
    }

    @Override
    public String toString() {
        return "Query1{" +
                ", iso3166_3='" + iso3166_3 + '\'' +
                ", applicant=" + applicant +
                '}';
    }
}
