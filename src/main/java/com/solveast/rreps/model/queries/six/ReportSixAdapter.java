package com.solveast.rreps.model.queries.six;

import com.solveast.rreps.model.DateUtils;
import com.solveast.rreps.model.queries.family.Family;
import com.solveast.rreps.model.queries.family.Person;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Андрей on 13.12.2016.
 */
public class ReportSixAdapter {
    private Map<String, Report6> map;
    private int unableToProccess = 0;
    private int totalQueryRecords = 0;

    public ReportSixAdapter() {
        this.map = new TreeMap<>();
    }

    public Map<String, Report6> getReportMap() {
        return map;
    }

    public void set(Family family) {
        String iso3166_3 = family.getClient().getIso3166_3();
        Person personApplicant = family.getClient();

        if (iso3166_3 == null) {
            unableToProccess = unableToProccess + 1;
            return;
        }

        Report6 report = map.get(iso3166_3);
        if (report == null)
            report = new Report6();

        report.setIso3166_3(iso3166_3);
        report.incFamilyNumber();
        report.incTotalPersons(family.getFamilyPersonNumber());
        if (DateUtils.getAge(personApplicant) < 18 || "CHI".equals(personApplicant.getUnRelationshipCd()))
            report.incIUAC();
        map.put(iso3166_3, report);
    }

    public int getUnableToProccess() {
        return unableToProccess;
    }

    public int getTotalQueryRecords() {
        return totalQueryRecords;
    }
}
