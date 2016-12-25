package com.solveast.rreps.model.service;

import com.solveast.rreps.model.DateUtils;
import com.solveast.rreps.model.dao.ReportSevenDao;
import com.solveast.rreps.model.queries.seven.Query7;
import com.solveast.rreps.model.queries.seven.Report7;
import com.solveast.rreps.model.queries.seven.ReportSevenAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Андрей on 22.12.2016.
 */
@Service
public class RSevenService {
    @Autowired
    private ReportSevenDao dao;

    public Map<String, Object> getData(LocalDateTime from, LocalDateTime to) {
        String title = "Annex I.  Reporting on court cases related to RSD by R2P for ";
        Map<String, Object> data = new HashMap<>();
        ReportSevenAdapter reportAdapter = new ReportSevenAdapter();

        List<Query7> query = dao.getQuery();
        query = applyTrueDateInterval(query, from, to);

        for (Query7 item : query) {
            reportAdapter.set(item);
        }

        data.put("title", DateUtils.getTitle(title, from, to));
        data.put("rawData", query);
        data.put("report", reportAdapter.getMap());
        return data;
    }

    private List<Query7> applyTrueDateInterval(List<Query7> query, LocalDateTime from, LocalDateTime to) {
        List<Query7> delete = new ArrayList<>();

        for (Query7 item : query) {
            if (item.getLadgetDate() != null && item.getLadgetDate().isBefore(to) && item.getLadgetDate().isAfter(from))
                continue;
            if (item.getDecisionDate() != null && item.getDecisionDate().isBefore(to) && item.getDecisionDate().isAfter(from))
                continue;
            delete.add(item);
        }
        query.removeAll(delete);

        return query;
    }
}
