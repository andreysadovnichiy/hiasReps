package com.solveast.rreps.model.service;

import com.solveast.rreps.model.dao.ReportTwoDao;
import com.solveast.rreps.model.queries.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Андрей on 27.11.2016.
 */

@Service
public class ReportFourService {
    @Autowired
    private ReportTwoDao reportDao;

    private Report4 report = new Report4();

    public Map<String, Object> getData(Timestamp from, Timestamp to) {
        Map<String, Object> data = new HashMap<>();

        List<Query21> rawData21 = reportDao.getQuery21(from, to);
        List<Query22> rawData22 = reportDao.getQuery22(from, to);
        List<Query23> rawData23 = reportDao.getQuery23(from, to);

        Integer nowYear = LocalDate.now().getYear();

        rawData21.forEach(item -> {
                    if (item.getBirthDate() != null)
                        report.set(item.getSexCd(), nowYear - item.getBirthDate().getYear());
                }
        );

        rawData22.forEach(item -> {
                    if (item.getBirthDate() != null)
                        report.set(item.getSexCd(), nowYear - item.getBirthDate().getYear());
                }
        );

        rawData23.forEach(item -> {
                    if (item.getBirthDate() != null)
                        report.set(item.getSexCd(), nowYear - item.getBirthDate().getYear());
                }
        );

        data.put("rawData21", rawData21);
        data.put("rawData22", rawData22);
        data.put("rawData23", rawData23);

        data.put("processedData", report);

        return data;
    }
}
