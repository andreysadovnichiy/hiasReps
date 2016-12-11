package com.solveast.rreps.model.service;

import com.solveast.rreps.model.dao.FamilyDao;
import com.solveast.rreps.model.dao.ReportOneDao;
import com.solveast.rreps.model.queries.base.BaseQuery;
import com.solveast.rreps.model.queries.family.Family;
import com.solveast.rreps.model.queries.family.Person;
import com.solveast.rreps.model.queries.one.Intro;
import com.solveast.rreps.model.queries.one.Query1;
import com.solveast.rreps.model.queries.one.Report1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Андрей on 10.12.2016.
 */
@Service
public class ROneService {
    @Autowired
    private ReportOneDao reportOneDao;
    @Autowired
    private FamilyDao familyDao;

    private Map<String, Object> familyBaseQuery;
    private Map<String, Report1> report;


    public Map<String, Object> getReport(Timestamp from, Timestamp to) {
        Map<String, Object> toView = new HashMap<>();

        List<Query1> query = reportOneDao.getQuery(from, to);
        List<BaseQuery> familyBaseQuery = familyDao.getFamilyBaseQuery();
        List<Intro> intros = new ArrayList<>();
//        report = new HashMap<>();
        report = new TreeMap<>();

        Person person;
        Intro intro;
        for (Query1 item : query) {
            person = item.toPerson();
            if (!PersonUtils.isUnhcrDateAfterFrom(person, from.toLocalDateTime()))
                continue;
            intro = new Intro(person, getFamily(person.getClientId(), familyBaseQuery), item.getIso3166_3());
            intros.add(intro);
        }

        for (Intro item : intros) {
            fillReport(item.getClient(), item.getIso3166_3());

            List<Person> family = item.getFamily();
            for (Person familyItem : family) {
                fillReport(familyItem, item.getIso3166_3());
            }
        }


        toView.put("intro", intros);
        toView.put("report", report);
        return toView;
    }

    private void fillReport(Person item, String iso3166_3) {
        String sex;
        Boolean lessThen18;

        Report1 rep = report.get(iso3166_3);
        if (rep == null)
            rep = new Report1();

        rep.setIso3166_3(iso3166_3);
        sex = item.getSexCd();

        if (item.getBirthDate() != null) {
            lessThen18 = item.isLessThen18();
            if ("m".equals(sex) && lessThen18)
                rep.addBoy();
            else if ("f".equals(sex) && lessThen18)
                rep.addGirl();
            else if ("m".equals(sex) && !lessThen18)
                rep.addMale();
            else if ("f".equals(sex) && !lessThen18)
                rep.addFemale();

            report.put(iso3166_3, rep);
        }
    }

    private List<Person> getFamily(Long clientId, List<BaseQuery> familyBaseQuery) {
        List<Person> family = new ArrayList<>();

        for (BaseQuery item : familyBaseQuery) {
            if ((long) item.getApplicantId() == (long) clientId) {
                family.add(item.toPerson());
            }
        }
        return family;
    }
}
