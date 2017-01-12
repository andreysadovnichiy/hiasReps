package com.solveast.rreps.model.dao;

import com.solveast.rreps.model.DateUtils;
import com.solveast.rreps.model.queries.five.Query5;
import com.solveast.rreps.model.queries.six.Query6;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Андрей on 12.12.2016.
 */
@Repository
public class ReportSixDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Query6> getQuery(LocalDateTime from, LocalDateTime to) {

        Map namedParameters = new HashMap();
        namedParameters.put("from", DateUtils.toTimestamp(from));
        namedParameters.put("to", DateUtils.toTimestamp(to));

        String sql = "SELECT " +
                " cl.client_id, cl.applicant_id, un_relationship_cd, cl.iso3166_3, cl.sex_cd, cl.birth_date," +
                " ta.real_time_start, ta.real_time_stop, ta.scheduled_time_start, ta.scheduled_time_stop" +
                " FROM tasks.t_action AS ta" +
                " LEFT JOIN tasks.t_action_client AS tc ON ta.action_id = tc.action_id" +
                " LEFT JOIN clients.t_client AS cl ON tc.client_id = cl.client_id" +
                " LEFT JOIN clients.t_registration_state AS rs ON cl.client_id = rs.client_id " + //--fix deleted
                " WHERE " +
//                " (rs.file_status_id > 0) AND" + //--fix deleted
                " (rs.file_status_id > 0 OR rs.file_status_id IS NULL) AND" + //--fix deleted
//                " (ta.service_id = 139 AND ta.action_state_cd = 'CLS') AND " +
                " (ta.service_id = 139 AND ta.action_result_id IN (2, 5, 10, 19, 20)) AND " +
                " ((ta.real_time_start BETWEEN :from AND :to)" +
                " OR (ta.real_time_stop BETWEEN :from AND :to)" +
                " OR (ta.scheduled_time_start BETWEEN :from AND :to)" +
                " OR (ta.scheduled_time_stop BETWEEN :from AND :to))";

        List<Query6> query =
                (List<Query6>) namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<Query6>() {
                    @Override
                    public Query6 mapRow(ResultSet rs, int i) throws SQLException {
                        Query6 item = new Query6();
                        item.setClientId(rs.getLong("client_id"));
                        item.setApplicantId(rs.getLong("applicant_id"));
                        item.setUnRelationshipCd(rs.getString("un_relationship_cd"));
                        item.setSexCd(rs.getString("sex_cd"));
                        item.setIso3166_3(rs.getString("iso3166_3"));
                        item.setBirthDate(rs.getTimestamp("birth_date"));
                        item.setRealTimeStart(rs.getTimestamp("real_time_start"));
                        item.setRealTimeStop(rs.getTimestamp("real_time_stop"));
                        item.setScheduledTimeStart(rs.getTimestamp("scheduled_time_start"));
                        item.setScheduledTimeStop(rs.getTimestamp("scheduled_time_stop"));
                        return item;
                    }
                });

        return query;
    }
}
