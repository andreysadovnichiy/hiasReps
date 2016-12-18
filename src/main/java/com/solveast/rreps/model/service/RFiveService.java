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
import com.solveast.rreps.model.queries.five.ReportFiveAdapter;
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
        List<BaseQuery> familyQuery = familyDao.getFamilyBaseQuery();
        ReportFiveAdapter reportAdapter = new ReportFiveAdapter();
        List<Person> personApplicant = new ArrayList<>();

        for (Query5 item : query) {
            personApplicant.add(item.toPerson());
        }

        List<Family> families = FamilyUtils.getFamilies(personApplicant, familyQuery);

        for (Family item : families) {
            reportAdapter.set(item);
        }

        data.put("title", DateUtils.getTitle(title, from, to));
        data.put("report", reportAdapter);
        data.put("total", reportAdapter.getTotalClientsWithFamilyNumber());
        data.put("unknown", reportAdapter.getUnableToProccess());
        data.put("rawData", query);
        return data;
    }
}
