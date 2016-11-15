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

    @RequestMapping("/test/downloadExcel.xls")
    @ResponseBody
    public ReportOneBuilder testExcelView() {
        return new ReportOneBuilder();
    }

    @RequestMapping("/test/jdbc/remote/named1.xls")
    @ResponseBody
    public ModelAndView testNamedJdbcRemote() {

        Timestamp from = Timestamp.valueOf(LocalDateTime.now().minusMonths(6));
        Timestamp to = Timestamp.valueOf(LocalDateTime.now());

        return new ModelAndView("excelViewReportOne", "model", reportOneService.getData(from, to));
    }

    @RequestMapping("/test/jdbc/remote/named2.xls")
    @ResponseBody
    public ModelAndView testNamedJdbcRemote2() {

        Timestamp from = Timestamp.valueOf(LocalDateTime.now().minusMonths(6));
        Timestamp to = Timestamp.valueOf(LocalDateTime.now());

        Map namedParameters = new HashMap();
        namedParameters.put("from", from);
        namedParameters.put("to", to);

        String sql1 = "SELECT client_id, register_time" +
                " FROM clients.t_client" +
                " WHERE register_time BETWEEN :from AND :to";
//        " WHERE (register_time BETWEEN :from AND :to) AND applicant=TRUE"; applicant учитывать??

        List<TClient> clients =
                (List<TClient>) namedParameterJdbcTemplate.query(sql1, namedParameters, new RowMapper<TClient>() {
                    @Override
                    public TClient mapRow(ResultSet rs, int i) throws SQLException {
                        TClient client = new TClient();
                        client.setClientId(rs.getLong("client_id"));
                        client.setRegisterTime(rs.getTimestamp("register_time"));
//                        client.setApplicant(rs.getBoolean("applicant"));
                        return client;
                    }
                });

        String sql2 = "SELECT client_id, unhcr_date" +
                " FROM clients.t_registration_form" +
                " WHERE unhcr_date BETWEEN :from AND :to";

        List<TRegistrationForm> registrationForms =
                (List<TRegistrationForm>) namedParameterJdbcTemplate.query(sql2, namedParameters, new RowMapper<TRegistrationForm>() {
                    @Override
                    public TRegistrationForm mapRow(ResultSet rs, int i) throws SQLException {
                        TRegistrationForm form = new TRegistrationForm();
                        form.setClientId(rs.getLong("client_id"));
                        form.setUnhcrDate(rs.getTimestamp("unhcr_date"));
                        return form;
                    }
                });


        String sql3 = "SELECT action_id, client_id, action_type, action_state_cd" +
                " ,real_time_start, real_time_stop, scheduled_time_start, scheduled_time_stop" +
                " FROM tasks.t_action" +
                " WHERE action_type = 'CLA' AND action_state_cd = 'CLS' AND " +
                " ((real_time_start BETWEEN :from AND :to) OR (real_time_stop BETWEEN :from AND :to) OR " +
                " (scheduled_time_start BETWEEN :from AND :to) OR (scheduled_time_stop BETWEEN :from AND :to))";

        List<TAction> actions =
                (List<TAction>) namedParameterJdbcTemplate.query(sql3, namedParameters, new RowMapper<TAction>() {
                    @Override
                    public TAction mapRow(ResultSet rs, int i) throws SQLException {
                        TAction action = new TAction();
                        action.setActionId(rs.getLong("action_id"));
                        action.setClientId(rs.getLong("client_id"));
                        action.setActionType(rs.getString("action_type"));
                        action.setActionStateCd(rs.getString("action_state_cd"));
                        action.setRealTimeStart(rs.getTimestamp("real_time_start"));
                        action.setRealTimeStop(rs.getTimestamp("real_time_stop"));
                        action.setScheduledTimeStart(rs.getTimestamp("scheduled_time_start"));
                        action.setScheduledTimeStop(rs.getTimestamp("scheduled_time_stop"));
                        return action;
                    }
                });

        String sql31 = "SELECT JOIN";

        return null;
    }
}
