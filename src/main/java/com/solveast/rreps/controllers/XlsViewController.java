package com.solveast.rreps.controllers;

import com.solveast.rreps.model.dao.ReportThreeDao;
import com.solveast.rreps.model.dao.ReportTwoDao;
import com.solveast.rreps.model.queries.Query21;
import com.solveast.rreps.model.queries.Query22;
import com.solveast.rreps.model.queries.Query23;
import com.solveast.rreps.model.queries.Query3;
import com.solveast.rreps.model.service.ReportFourService;
import com.solveast.rreps.model.service.ReportOneService;
import com.solveast.rreps.model.service.ReportThreeService;
import com.solveast.rreps.model.service.ReportTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by Андрей on 28.10.2016.
 */
@RestController
public class XlsViewController {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ReportOneService reportOneService;
    @Autowired
    private ReportTwoService reportTwoService;
    @Autowired
    private ReportThreeService reportThreeService;
    @Autowired
    private ReportFourService reportFourService;


    @RequestMapping("/test/jdbc/remote/named1.xls")
    public ModelAndView xlsReportOne() {

        Timestamp from = Timestamp.valueOf(LocalDateTime.now().minusMonths(6));
        Timestamp to = Timestamp.valueOf(LocalDateTime.now());

        Map<String, Object> data = reportOneService.getData(from, to);

        return new ModelAndView("excelViewReportOne", "model", data);
    }

    @RequestMapping("/test/jdbc/remote/named2.xls")
    public ModelAndView xlsReportTwo() {

        Timestamp from = Timestamp.valueOf(LocalDateTime.now().minusMonths(6));
        Timestamp to = Timestamp.valueOf(LocalDateTime.now());

        Map<String, Object> data = reportTwoService.getData(from, to);

        return new ModelAndView("excelViewReportOne", "model", data);
    }

    @RequestMapping("/test/jdbc/remote/named3.xls")
    public ModelAndView xlsReportThree() {

        Timestamp from = Timestamp.valueOf(LocalDateTime.now().minusMonths(6));
        Timestamp to = Timestamp.valueOf(LocalDateTime.now());

        List<Query3> query = reportThreeService.getData(from, to);

        return new ModelAndView("excelViewReportOne", "model", query);
    }

    @RequestMapping("/test/jdbc/remote/named4.xls")
    public ModelAndView xlsReportFour() {

        Timestamp from = Timestamp.valueOf(LocalDateTime.now().minusMonths(6));
        Timestamp to = Timestamp.valueOf(LocalDateTime.now());

        Map<String, Object> data = reportFourService.getData(from, to);

        return new ModelAndView("excelViewReportOne", "model", data);
    }

}
