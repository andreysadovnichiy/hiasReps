package com.solveast.rreps.model.service;

import com.solveast.rreps.model.dao.FamilyDao;
import com.solveast.rreps.model.dao.ReportThreeDao;
import com.solveast.rreps.model.dao.ReportTwoDao;
import com.solveast.rreps.model.queries.family.FamilyQuery;
import com.solveast.rreps.model.queries.one.Report1;
import com.solveast.rreps.model.queries.three.Query3;
import com.solveast.rreps.model.queries.three.Report3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 10.12.2016.
 */
@Service
public class RThreeService {
    @Autowired
    private ReportThreeDao reportDao;
    @Autowired
    private FamilyDao familyDao;

    public List<Report3> getData(Timestamp from, Timestamp to) {
        List<Query3> query = reportDao.getQuery3(from, to);
        List<FamilyQuery> family = familyDao.getFamily();

        List<Report3> report = new ArrayList<>();

        for (Query3 item : query) {
            Report3 rep = item.toReport3();
            for (FamilyQuery familyItem : family) {
                if (item.getClientId() == familyItem.getApplicantId())
                    rep.incFamilyMember();
            }
            report.add(rep);
        }

        return report;
    }
}
