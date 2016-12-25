package com.solveast.rreps.model.dao;

import com.solveast.rreps.model.queries.seven.Query7;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Андрей on 22.12.2016.
 */
@Repository
public class ReportSevenDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Query7> getQuery() {
        Map namedParameters = new HashMap();

        String sql = "SELECT " +
                " cl.client_id, ccsn.court_case_state_id, ccsn.name, ls.ladget_date, ls.decision_date, cs.ms_rejection_cd, ls.instance_cd, ls.court_decision_cd," +
                " cl.iso3166_3, cl.sex_cd, pc.name as pcname" +
                " FROM lawsuits.t_court_case_lawsuit as ls" +
                " LEFT JOIN lawsuits.t_court_case AS cs ON ls.court_case_id = cs.court_case_id" +
                " LEFT JOIN lawsuits.r_court_case_state_name AS ccsn ON cs.court_case_state_id = ccsn.court_case_state_id"+
                " LEFT JOIN lawsuits.t_court_case_client AS cc ON ls.court_case_id = cc.court_case_id" +
                " LEFT JOIN clients.t_client AS cl ON cc.client_id = cl.client_id" +
                " LEFT JOIN clients.t_registration_state AS rs " + //--fix deleted
                " ON cl.client_id = rs.client_id " + //--fix deleted
                " LEFT JOIN portal.t_country_name AS pc " +
                " ON pc.iso3166_3 = cl.iso3166_3 " +
                " WHERE (rs.file_status_id > 0 OR rs.file_status_id IS NULL)";

        List<Query7> query =
                (List<Query7>) namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<Query7>() {
                    @Override
                    public Query7 mapRow(ResultSet rs, int i) throws SQLException {
                        Query7 item = new Query7();
                        item.setClientId(rs.getLong("client_id"));
                        item.setSexCd(rs.getString("sex_cd"));
                        item.setIso3166_3(rs.getString("pcname"));
                        item.setDecisionDate(rs.getString("decision_date"));
                        item.setLadgetDate(rs.getString("ladget_date"));
                        item.setCourtDecisionCd(rs.getString("court_decision_cd"));
                        item.setMsRejectionCd(rs.getString("ms_rejection_cd"));
                        item.setInstanceCd(rs.getString("instance_cd"));
                        item.setCourtCaseStateId(rs.getLong("court_case_state_id"));
                        item.setCourtCaseStateName(rs.getString("name"));
                        return item;
                    }
                });

        return query;
    }
}
