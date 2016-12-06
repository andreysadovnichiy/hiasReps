package com.solveast.rreps.model.service;

import com.solveast.rreps.model.dao.ReportThreeDao;
import com.solveast.rreps.model.queries.three.Query3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Андрей on 27.11.2016.
 */

@Service
public class ReportThreeService {
    @Autowired
    private ReportThreeDao reportDao;
    @Autowired
    private FamilyService familyService;


    public List<Query3> getData(Timestamp from, Timestamp to) {
        List<Query3> query = reportDao.getQuery3(from, to);
        Map<Long, Integer> familyMap = familyService.getFamilyMap();

        List<Query3> forDelete = new ArrayList<>();

        for (Query3 item : query) {
            item.setTotalFamilyMembers(familyMap.get(item.getClientId()));
            if(item.getUnhcrNum() == null) {
                forDelete.add(item);
            }
        }
        query.removeAll(forDelete);

        return query;
    }

}
