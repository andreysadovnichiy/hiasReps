package com.solveast.rreps.model.service;

import com.solveast.rreps.model.dao.ReportTwoDao;
import com.solveast.rreps.model.queries.two.Query21;
import com.solveast.rreps.model.queries.two.Query22;
import com.solveast.rreps.model.queries.two.Query23;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Андрей on 25.11.2016.
 */

/*

*/
@Service
public class ReportTwoService {
    @Autowired
    private ReportTwoDao reportDao;

    private Set<Long> clients = new HashSet<>();

    public Map<String, Object> getData(Timestamp from, Timestamp to) {
        Map<String, Object> data = new HashMap<>();

        List<Query21> rawData21 = reportDao.getQuery21(from, to);
        List<Query22> rawData22 = reportDao.getQuery22(from, to);
        List<Query23> rawData23 = reportDao.getQuery23(from, to);

        data.put("rawData21", rawData21);
        data.put("rawData22", rawData22);
        data.put("rawData23", rawData23);

        clients.clear();

        for (Query21 item : rawData21) {
            clients.add(item.getClientId());
        }

        for (Query22 item : rawData22) {
            clients.add(item.getClientId());
        }

        for (Query23 item : rawData23) {
            clients.add(item.getClientId());
        }

        data.put("clientsNumber", clients.size());
        data.put("clientsList", clients);
        data.put("from", from);
        data.put("to", to);

        return data;
    }
}
