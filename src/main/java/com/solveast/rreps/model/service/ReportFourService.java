package com.solveast.rreps.model.service;

import com.solveast.rreps.model.dao.DeletedDao;
import com.solveast.rreps.model.dao.ReportTwoDao;
import com.solveast.rreps.model.queries.two.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Андрей on 27.11.2016.
 */

@Service
public class ReportFourService {
    @Autowired
    private ReportTwoDao reportDao;
    @Autowired
    private DeletedDao deletedDao;
    @Autowired
    private FamilyService familyService;

    private List<Query21> rawData21 = null;
    private List<Query22> rawData22 = null;
    private List<Query23> rawData23 = null;
    private Report4 report = new Report4();


    public Map<String, Object> getData(Timestamp from, Timestamp to) {
        Map<String, Object> data = new HashMap<>();

        rawData21 = reportDao.getQuery21(from, to);
        rawData22 = reportDao.getQuery22(from, to);
        rawData23 = reportDao.getQuery23(from, to);

        fixDeletedClients();

        Integer nowYear = LocalDate.now().getYear();
        Set<Long> unknownBirthDay = new HashSet<>();
        Set<Long> total = new HashSet<>();

        for (Query21 item : rawData21) {
            if (item.getBirthDate() != null) {
                report.set(item.getClientId(), item.getSexCd(), nowYear - item.getBirthDate().getYear());
                total.add(item.getClientId());
            } else
                unknownBirthDay.add(item.getClientId());
        }
        for (Query22 item : rawData22) {
            if (item.getBirthDate() != null) {
                report.set(item.getClientId(), item.getSexCd(), nowYear - item.getBirthDate().getYear());
                total.add(item.getClientId());
            } else
                unknownBirthDay.add(item.getClientId());
        }
        for (Query23 item : rawData23) {
            if (item.getBirthDate() != null) {
                report.set(item.getClientId(), item.getSexCd(), nowYear - item.getBirthDate().getYear());
                total.add(item.getClientId());
            } else
                unknownBirthDay.add(item.getClientId());
        }

        report = fixFamily();

        data.put("rawData21", rawData21);
        data.put("rawData22", rawData22);
        data.put("rawData23", rawData23);
        data.put("processedData", report);
        data.put("unknownBirthDayTotal", unknownBirthDay.size());
        data.put("total", total.size());
        data.put("from", from);
        data.put("to", to);

        return data;
    }

    private void fixDeletedClients() {
        Set<Long> deletedSet = deletedDao.getDeleted();

        List<Query21> forDeleteQuery21 = new ArrayList<>();
        for (Query21 item : rawData21) {
            if (deletedSet.contains(item.getClientId()))
                forDeleteQuery21.add(item);
        }
        rawData21.removeAll(forDeleteQuery21);

        List<Query22> forDeleteQuery22 = new ArrayList<>();
        for (Query22 item : rawData22) {
            if (deletedSet.contains(item.getClientId()))
                forDeleteQuery22.add(item);
        }
        rawData22.removeAll(forDeleteQuery22);

        List<Query23> forDeleteQuery23 = new ArrayList<>();
        for (Query23 item : rawData23) {
            if (deletedSet.contains(item.getClientId()))
                forDeleteQuery23.add(item);
        }
        rawData23.removeAll(forDeleteQuery23);
    }

    private Report4 fixFamily() {
        Set<Long> clientIdSet = new HashSet<>();
        List<FamilyReport2> families = familyService.getFamilyForReport2();

        for (Query21 item : rawData21)
            clientIdSet.add(item.getClientId());
        for (Query22 item : rawData22)
            clientIdSet.add(item.getClientId());
        for (Query23 item : rawData23)
            clientIdSet.add(item.getClientId());

        for (FamilyReport2 item : families) {
            if (item.getApplicantId() != 0 && item.getAge() != 100 && clientIdSet.contains(item.getApplicantId()))
                report.set(item.getClientId(), item.getSexCd(), item.getAge());
        }

        return report;
    }
}
