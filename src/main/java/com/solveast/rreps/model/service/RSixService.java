package com.solveast.rreps.model.service;

import com.solveast.rreps.model.DateUtils;
import com.solveast.rreps.model.dao.FamilyDao;
import com.solveast.rreps.model.dao.ReportSixDao;
import com.solveast.rreps.model.queries.base.BaseQuery;
import com.solveast.rreps.model.queries.family.Family;
import com.solveast.rreps.model.queries.family.FamilyUtils;
import com.solveast.rreps.model.queries.family.Person;
import com.solveast.rreps.model.queries.six.Query6;
import com.solveast.rreps.model.queries.six.ReportSixAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Андрей on 12.12.2016.
 */
@Service
public class RSixService {
    @Autowired
    private ReportSixDao reportDao;
    @Autowired
    private FamilyDao familyDao;

    public Map<String, Object> getData(LocalDateTime from, LocalDateTime to) {
        String title = "Table 4: Registration interviews in";
        Map<String, Object> data = new HashMap<>();
        ReportSixAdapter reportAdapter = new ReportSixAdapter();
        Set<Person> personApplicant = new HashSet<>();

        List<Query6> query = reportDao.getQuery(from, to);
        List<BaseQuery> familyQuery = familyDao.getFamilyBaseQuery();

        for (Query6 item : query)
            personApplicant.add(item.toPerson());

        List<Family> families = FamilyUtils.getFamilies(personApplicant, familyQuery);

        for (Family item : families)
            reportAdapter.set(item);

        data.put("title", DateUtils.getTitle(title, from, to));
        data.put("report", reportAdapter);
        data.put("total", reportAdapter.getTotalQueryRecords());
        data.put("unknown", reportAdapter.getUnableToProccess());
        data.put("rawData", query);
        data.put("families", families);
        return data;
    }
}
