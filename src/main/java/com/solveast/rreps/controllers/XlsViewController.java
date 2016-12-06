package com.solveast.rreps.controllers;

import com.solveast.rreps.model.DateConverterUtils;
import com.solveast.rreps.model.queries.three.Query3;
import com.solveast.rreps.model.service.ReportFourService;
import com.solveast.rreps.model.service.ReportOneService;
import com.solveast.rreps.model.service.ReportThreeService;
import com.solveast.rreps.model.service.ReportTwoService;
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
    private ReportFourService reportFourService;

    @RequestMapping("/reports/xls/report-one.xls")
    public ModelAndView xlsReportOne(@RequestParam("from")String fromString,
                                     @RequestParam("to")String toString) {

        Timestamp from = DateConverterUtils.toTimestamp(fromString);
        Timestamp to = DateConverterUtils.toTimestamp(toString);

        Map<String, Object> data = reportOneService.getData(from, to);

        return new ModelAndView("excelViewReportOne", "model", data);
    }

    @RequestMapping("/reports/xls/report-two.xls")
    public ModelAndView xlsReportTwo(@RequestParam("from")String fromString,
                                     @RequestParam("to")String toString) {

        Timestamp from = DateConverterUtils.toTimestamp(fromString);
        Timestamp to = DateConverterUtils.toTimestamp(toString);

        Map<String, Object> data = reportTwoService.getData(from, to);

        return new ModelAndView("excelViewReportTwo", "model", data);
    }

    @RequestMapping("/reports/xls/report-three.xls")
    public ModelAndView xlsReportThree(@RequestParam("from")String fromString,
                                       @RequestParam("to")String toString) {

        Timestamp from = DateConverterUtils.toTimestamp(fromString);
        Timestamp to = DateConverterUtils.toTimestamp(toString);

        List<Query3> data = reportThreeService.getData(from, to);

        return new ModelAndView("excelViewReportThree", "model", data);
    }

    @RequestMapping("/reports/xls/report-four.xls")
    public ModelAndView xlsReportFour(@RequestParam("from")String fromString,
                                      @RequestParam("to")String toString) {

        Timestamp from = DateConverterUtils.toTimestamp(fromString);
        Timestamp to = DateConverterUtils.toTimestamp(toString);

        Map<String, Object> data = reportFourService.getData(from, to);

        return new ModelAndView("excelViewReportFour", "model", data);
    }
}
