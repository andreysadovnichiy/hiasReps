package com.solveast.rreps.controllers;

import com.solveast.rreps.model.DateUtils;
import com.solveast.rreps.model.queries.three.Query3;
import com.solveast.rreps.model.queries.three.Report3;
import com.solveast.rreps.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
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
    private ROneService rOneService;
    @Autowired
    private RThreeService rTreeService;

    @RequestMapping("/reports/xls/report-one.xls")
    public ModelAndView xlsReportOne(@RequestParam("from") String fromString,
                                     @RequestParam("to") String toString) {

        Timestamp from = DateUtils.toTimestamp(fromString);
        Timestamp to = DateUtils.toTimestamp(toString);

        Map<String, Object> data = rOneService.getReport(from, to);

        return new ModelAndView("excelViewROne", "model", data);
    }

    @RequestMapping("/reports/xls/report-three.xls")
    public ModelAndView xlsReportThree(@RequestParam("from") String fromString,
                                       @RequestParam("to") String toString) {

        Timestamp from = DateUtils.toTimestamp(fromString);
        Timestamp to = DateUtils.toTimestamp(toString);

        List<Report3> data = rTreeService.getData(from, to);

        return new ModelAndView("excelViewReportThree", "model", data);
    }

    @RequestMapping("/reports/xls/report-four.xls")
    public ModelAndView xlsReportFour(@RequestParam("from") String fromString,
                                      @RequestParam("to") String toString) {

        Timestamp from = DateUtils.toTimestamp(fromString);
        Timestamp to = DateUtils.toTimestamp(toString);

        Map<String, Object> data = reportTwoService.getData(from, to);

        return new ModelAndView("excelViewRFour", "model", data);
    }
}
