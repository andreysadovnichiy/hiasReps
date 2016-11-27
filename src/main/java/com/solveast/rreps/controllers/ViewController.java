package com.solveast.rreps.controllers;

import com.solveast.rreps.model.service.ReportOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created by Андрей on 28.10.2016.
 */
@Controller
public class ViewController {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ReportOneService reportOneService;


  /*  @RequestMapping("/test/jdbc/remote/named1.xls")
    @ResponseBody
    public ModelAndView testNamedJdbcRemote() {

        Timestamp from = Timestamp.valueOf(LocalDateTime.now().minusMonths(6));
        Timestamp to = Timestamp.valueOf(LocalDateTime.now());

        return new ModelAndView("excelViewReportOne", "model", reportOneService.getData(from, to));
    }*/
}
