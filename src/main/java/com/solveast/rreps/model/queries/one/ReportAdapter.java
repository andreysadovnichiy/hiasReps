package com.solveast.rreps.model.queries.one;

import com.solveast.rreps.model.queries.family.Family;
import com.solveast.rreps.model.queries.family.Person;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Андрей on 13.12.2016.
 */
public class ReportAdapter {
    private Map<String, Report1> map;
    private int unableToProccess = 0;
    private int totalClientsWithFamilyNumber = 0;

    public ReportAdapter() {
        this.map = new TreeMap<>();
    }

    public Map<String, Report1> getReportMap() {
        return map;
    }

    public void set(Family family) {
        set(family.getClient(), family.getClient().getIso3166_3());

        for (Person item : family.getFamily()) {
            set(item, family.getClient().getIso3166_3());
        }
    }

    public void set(Person person, String iso3166_3) {
        String sex = person.getSexCd();

        if (iso3166_3 == null || sex == null) {
            unableToProccess++;
            return;
        }

        boolean lessThen18 = person.isLessThen18();

        Report1 report = map.get(iso3166_3);
        if (report == null)
            report = new Report1();

        report.setIso3166_3(iso3166_3);
        if ("m".equals(sex) && lessThen18)
            report.addBoy();
        else if ("f".equals(sex) && lessThen18)
            report.addGirl();
        else if ("m".equals(sex) && !lessThen18)
            report.addMale();
        else if ("f".equals(sex) && !lessThen18)
            report.addFemale();

        totalClientsWithFamilyNumber++;
        map.put(iso3166_3, report);
    }

    public int getUnableToProccess() {
        return unableToProccess;
    }

    public int getTotalClientsWithFamilyNumber() {
        return totalClientsWithFamilyNumber;
    }
}
