package com.solveast.rreps.model.service;

import com.solveast.rreps.model.dao.ReportThreeDao;
import com.solveast.rreps.model.queries.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Андрей on 27.11.2016.
 */

@Service
public class ReportThreeService {
    @Autowired
    private ReportThreeDao reportDao;


    public List<Query3> getData(Timestamp from, Timestamp to) {

        return reportDao.getQuery3(from, to);
    }
}
