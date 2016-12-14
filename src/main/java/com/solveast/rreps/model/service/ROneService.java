package com.solveast.rreps.model.service;

import com.solveast.rreps.model.DateUtils;
import com.solveast.rreps.model.dao.FamilyDao;
import com.solveast.rreps.model.dao.ReportOneDao;
import com.solveast.rreps.model.queries.base.BaseQuery;
import com.solveast.rreps.model.queries.family.Family;
import com.solveast.rreps.model.queries.family.FamilyUtils;
import com.solveast.rreps.model.queries.family.Person;
import com.solveast.rreps.model.queries.one.Query1;
import com.solveast.rreps.model.queries.one.ReportAdapter;
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

    public Map<String, Object> getReport(Timestamp from, Timestamp to) {
        String title = "Количество новых клиентов, посетивших офис за отчетный период";
        Map<String, Object> toView = new HashMap<>();

        List<Query1> query = reportOneDao.getQuery(from, to);
        List<BaseQuery> familyQuery = familyDao.getFamilyBaseQuery();
        ReportAdapter report = new ReportAdapter();

        List<Person> personApplicant = new ArrayList<>();

        for (Query1 item : query) {
            Person person = item.toPerson();
            if(PersonUtils.isUnhcrDateAfterFrom(person, from.toLocalDateTime())
                    && PersonUtils.isIso3166_3Exists(person)
                    && PersonUtils.isSexCdExists(person))

                personApplicant.add(person);
        }

        List<Family> families = FamilyUtils.getFamilies(personApplicant, familyQuery);

        for (Family item : families) {
            report.set(item);
        }

        toView.put("inputData", families);
        toView.put("report", report);
        toView.put("title", DateUtils.getTitle(title, from, to));
        return toView;
    }
}
