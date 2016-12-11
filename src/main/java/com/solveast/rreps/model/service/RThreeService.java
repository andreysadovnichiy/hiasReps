package com.solveast.rreps.model.service;

import com.solveast.rreps.model.dao.FamilyDao;
import com.solveast.rreps.model.dao.ReportThreeDao;
import com.solveast.rreps.model.queries.family.FamilyQuery;
import com.solveast.rreps.model.queries.three.Query3;
import com.solveast.rreps.model.queries.three.Report3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
                if ((long) item.getClientId() == (long) familyItem.getApplicantId())
                    rep.incFamilyMember();
            }
            report.add(rep);
        }

        Collections.sort(report, new Comparator<Report3>() {
            @Override
            public int compare(Report3 o1, Report3 o2) {
                return o1.getIso3166_3().compareTo(o2.getIso3166_3());
            }
        });

        return report;
    }
}
