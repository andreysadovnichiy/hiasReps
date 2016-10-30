package com.solveast.rreps.controllers;

import com.solveast.rreps.model.schemas.clients.FStatus;
import com.solveast.rreps.model.schemas.clients.Person;
import com.solveast.rreps.model.schemas.clients.Person1;
import com.solveast.rreps.model.service.report_one.ReportOneService;
import com.solveast.rreps.model.view.excel.ReportOneBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Андрей on 28.10.2016.
 */
@Controller
public class ViewController {
    @Autowired
    @Qualifier("jdbcTemplateRemote")
    private JdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("jdbcTemplateLocal")
    private JdbcTemplate localJdbcTemplate;
    @Autowired
    private ReportOneService reportOneService;
/*
    excelViewReportTwo.(class)=com.solveast.rreps.model.view.excel.ReportTwoBuilder
excelViewReportThree.(class)=com.solveast.rreps.model.view.excel.ReportThreeBuilder
excelViewReportFour.(class)=com.solveast.rreps.model.view.excel.ReportFourBuilder
excelViewReportFive.(class)=com.solveast.rreps.model.view.excel.ReportFiveBuilder
excelViewReportSix.(class)=com.solveast.rreps.model.view.excel.ReportSixBuilder
excelViewReportSeven.(class)=com.solveast.rreps.model.view.excel.ReportSevenBuilder
    */
    @RequestMapping("/test/downloadExcel.xls")
    @ResponseBody
    public ReportOneBuilder testExcelView() {
//        @Bean(name="excelViewResolver")
        return  new ReportOneBuilder();
//        return new ModelAndView("excelViewReportOne", "listBooks", null);
    }

    @RequestMapping("/test/jdbc/local")
    @ResponseBody
    public String testJdbcLocal() {

        String sql1 = "SELECT * FROM [study].person";
        List<Person1> persons = localJdbcTemplate.query(sql1, new RowMapper<Person1>() {
            @Override
            public Person1 mapRow(ResultSet rs, int i) throws SQLException {
                return new Person1(rs.getLong("id"), rs.getInt("age"));
            }
        });

        if (persons == null)
            return "null";
        if (persons.isEmpty())
            return "isEmpty";
        if (persons.get(0) != null)
            return persons.get(0).toString();

        return "test";
    }

    @RequestMapping("/test/jdbc/remote")
    @ResponseBody
    public String testJdbcRemote() {

        String sql = "SELECT * FROM [clients].r_file_status";
//        String sql = "SELECT * FROM clients.r_file_status";
//        String sql = "select * from test.clients.r_file_status";

        String report = reportOneService.getReport();

        List<FStatus> items = jdbcTemplate.query(sql, new RowMapper<FStatus>() {
            @Override
            public FStatus mapRow(ResultSet rs, int rowNum) {
                FStatus item = new FStatus();
                try {
                    item.setFileStatusId(rs.getLong("file_status_id"));
                    item.setActive(rs.getBoolean("active"));
                    item.setOrd(rs.getInt("ord"));
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
//                    e.printStackTrace();
                }
                return item;
            }
        });

        if (items == null)
            return "null";
        if (items.isEmpty())
            return "isEmpty";
        if (items.get(0) != null)
            return items.get(0).toString();

        return "test";
    }
}
