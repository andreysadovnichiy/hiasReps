package com.solveast.rreps.model.service;

import com.solveast.rreps.model.DateUtils;
import com.solveast.rreps.model.dao.FamilyDao;
import com.solveast.rreps.model.dao.ReportTwoDao;
import com.solveast.rreps.model.queries.base.BaseQuery;
import com.solveast.rreps.model.queries.family.Person;
import com.solveast.rreps.model.queries.two.Query21;
import com.solveast.rreps.model.queries.two.Query22;
import com.solveast.rreps.model.queries.two.Query23;
import com.solveast.rreps.model.queries.two.Report4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Андрей on 25.11.2016.
 */
@Service
public class ReportTwoService {
    @Autowired
    private ReportTwoDao reportDao;
    @Autowired
    private FamilyDao familyDao;

    private Map<Long, Person> clients;

    public Map<String, Object> getData(LocalDateTime from, LocalDateTime to) {
        String title = "Table 2: Population group in ";
        Map<String, Object> data = new HashMap<>();

        List<Query21> rawData21 = reportDao.getQuery21(from, to);
        List<Query22> rawData22 = reportDao.getQuery22(from, to);
        List<Query23> rawData23 = reportDao.getQuery23(from, to);
        List<Query23> forDeleteRawData23 = new ArrayList<>();

        List<BaseQuery> familyBaseQuery = familyDao.getFamilyBaseQuery();

        for (Query23 item : rawData23) {
            if(item.getUnhcrDate()!= null && from.isAfter(item.getUnhcrDate()))
                forDeleteRawData23.add(item);
        }
        rawData23.removeAll(forDeleteRawData23);

        Set<Long> unableToProcess = new HashSet<>();
        clients = new HashMap<>();

        for (Query21 item : rawData21) {
            if (item.getClientId() != null && item.getBirthDate() != null && item.getSexCd() != null)
                clients.put(item.getClientId(), item.toPerson());
            else
                unableToProcess.add(item.getClientId());
        }

        for (Query22 item : rawData22) {
            if (item.getClientId() != null && item.getBirthDate() != null && item.getSexCd() != null)
                clients.put(item.getClientId(), item.toPerson());
            else
                unableToProcess.add(item.getClientId());        }

        for (Query23 item : rawData23) {
            if (item.getClientId() != null && item.getBirthDate() != null && item.getSexCd() != null)
                clients.put(item.getClientId(), item.toPerson());
            else
                unableToProcess.add(item.getClientId());        }

        for (BaseQuery item : familyBaseQuery) {
            Person person = clients.get(item.getApplicantId());
            if (person != null)
                clients.put(item.getClientId(), item.toPerson());
        }

        Report4 report = new Report4();
        for( Map.Entry<Long, Person> entry : clients.entrySet() ){
            report.set(entry.getValue());
        }

        report.setTotalUnableToProccess(unableToProcess.size());

        data.put("rawData21", rawData21);
        data.put("rawData22", rawData22);
        data.put("rawData23", rawData23);

        data.put("report", report);
        data.put("title", DateUtils.getTitle(title,from,to));

        return data;
    }
}
