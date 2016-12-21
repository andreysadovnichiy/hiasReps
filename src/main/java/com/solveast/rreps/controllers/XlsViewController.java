package com.solveast.rreps.controllers;

import com.solveast.rreps.model.DateUtils;
import com.solveast.rreps.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by Андрей on 28.10.2016.
 */
@RestController
public class XlsViewController {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private ROneService rOneService;
    @Autowired
    private ReportTwoService reportTwoService;
    @Autowired
    private RThreeService rTreeService;
    @Autowired
    private RFiveService rFiveService;
    @Autowired
    private RSixService rSixService;


    @RequestMapping("/reports/xls/report-1.xls")
    public ModelAndView xlsReportOne(@RequestParam("from") String from,
                                     @RequestParam("to") String to) {

        Map<String, Object> data = rOneService.getReport(DateUtils.toLocalDateTime(from), DateUtils.toLocalDateTime(to));

        return new ModelAndView("excelViewROne", "model", data);
    }

    @RequestMapping("/reports/xls/report-3.xls")
    public ModelAndView xlsReportThree(@RequestParam("from") String from,
                                       @RequestParam("to") String to) {

        Map<String, Object> data = rTreeService.getData(DateUtils.toLocalDateTime(from), DateUtils.toLocalDateTime(to));

        return new ModelAndView("excelViewReportThree", "model", data);
    }

    @RequestMapping("/reports/xls/report-2-4.xls")
    public ModelAndView xlsReportFour(@RequestParam("from") String from,
                                      @RequestParam("to") String to) {

        Map<String, Object> data = reportTwoService.getData(DateUtils.toLocalDateTime(from), DateUtils.toLocalDateTime(to));

        return new ModelAndView("excelViewRFour", "model", data);
    }

    @RequestMapping("/reports/xls/report-5.xls")
    public ModelAndView xlsReportFive(@RequestParam("from") String from,
                                      @RequestParam("to") String to) {

        Map<String, Object> data = rFiveService.getData(DateUtils.toLocalDateTime(from), DateUtils.toLocalDateTime(to));

        return new ModelAndView("excelViewRFive", "model", data);
    }

    @RequestMapping("/reports/xls/report-6.xls")
    public ModelAndView xlsReportSix(@RequestParam("from") String from,
                                     @RequestParam("to") String to) {

        Map<String, Object> data = rSixService.getData(DateUtils.toLocalDateTime(from), DateUtils.toLocalDateTime(to));

        return new ModelAndView("excelViewRSix", "model", data);
    }
}
