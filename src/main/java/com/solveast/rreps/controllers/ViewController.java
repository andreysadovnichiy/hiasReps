package com.solveast.rreps.controllers;

import com.solveast.rreps.model.db.schemas.clients.TClient;
import com.solveast.rreps.model.db.schemas.clients.TRegistrationForm;
import com.solveast.rreps.model.db.schemas.tasks.TAction;
import com.solveast.rreps.model.schemas.Query1;
import com.solveast.rreps.model.service.ReportOneService;
import com.solveast.rreps.model.view.excel.ReportOneBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
