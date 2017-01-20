package com.solveast.rreps.model.queries.five;

import com.solveast.rreps.model.queries.family.Family;
import com.solveast.rreps.model.queries.family.Person;
import com.solveast.rreps.model.queries.one.Report1;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Андрей on 13.12.2016.
 */
public class ReportFiveAdapter {
    private Map<String, Report5> map;
    private int unableToProccess = 0;
    private int totalClientsWithFamilyNumber = 0;

    public ReportFiveAdapter() {
        this.map = new TreeMap<>();
    }

    public Map<String, Report5> getReportMap() {
        return map;
    }

    public void set(Family family) {
        String iso3166_3 = family.getClient().getIso3166_3();

        set(family.getClient(), iso3166_3);
        for (Person item : family.getFamily()) {
            set(item, iso3166_3);
        }

        Report5 report = map.get(iso3166_3);
        if (report == null)
            report = new Report5();

        report.setIso3166_3(iso3166_3);
        report.incCases();
        report.incInds(family.getFamily().size() + 1);
        map.put(iso3166_3, report);
    }

    public void set(Person person, String iso3166_3) {
        String sex = person.getSexCd();
        LocalDateTime birthDate = person.getBirthDate();

        if (iso3166_3 == null || sex == null || birthDate == null) {
            unableToProccess++;
            return;
        }

        Report5 report = map.get(iso3166_3);
        if (report == null)
            report = new Report5();

        report.setIso3166_3(iso3166_3);
        report.incAge(person);
        report.setUAC(person);

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
