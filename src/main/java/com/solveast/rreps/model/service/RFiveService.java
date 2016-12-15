package com.solveast.rreps.model.service;

import com.solveast.rreps.model.DateUtils;
import com.solveast.rreps.model.dao.FamilyDao;
import com.solveast.rreps.model.dao.ReportFiveDao;
import com.solveast.rreps.model.queries.base.BaseQuery;
import com.solveast.rreps.model.queries.family.Family;
import com.solveast.rreps.model.queries.family.FamilyQuery;
import com.solveast.rreps.model.queries.family.FamilyUtils;
import com.solveast.rreps.model.queries.family.Person;
import com.solveast.rreps.model.queries.five.Query5;
import com.solveast.rreps.model.queries.five.Report5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Андрей on 12.12.2016.
 */
@Service
public class RFiveService {
    @Autowired
    private ReportFiveDao reportDao;
    @Autowired
    private FamilyDao familyDao;

    public Map<String, Object> getData(LocalDateTime from, LocalDateTime to) {
        String title = "Table 3: New asylum seekers registered by the R2P in";
        Map<String, Object> data = new HashMap<>();

        List<Query5> query = reportDao.getQuery(from, to);
//        List<BaseQuery> familyBaseQuery = familyDao.getFamilyBaseQuery();
        List<BaseQuery> familyQuery = familyDao.getFamilyBaseQuery();

        Map<String, Report5> reportMap = new TreeMap<>();
        List<Person> personsApplicant = new ArrayList<>();
        Set<Long> unableToProcess = new HashSet<>();

        for (Query5 item : query) {
            Report5 rep = reportMap.get(item.getIso3166_3());
            if (rep == null)
                rep = new Report5();
            Person person = item.toPerson();
            rep.setPerson(person, 100);
            reportMap.put(item.getIso3166_3(), rep);
            personsApplicant.add(person);
        }

        List<Family> families = FamilyUtils.getFamilies(personsApplicant, familyQuery);

        for (Family item : families) {
            String iso3166_3 = item.getClient().getIso3166_3();
            Report5 rep = reportMap.get(iso3166_3);
            if (rep == null)
                continue;
            rep.setFamily(item);
        }


        data.put("title", DateUtils.getTitle(title, from, to));
        data.put("report", reportMap);
        data.put("rawData", query);
        return data;
    }
}
