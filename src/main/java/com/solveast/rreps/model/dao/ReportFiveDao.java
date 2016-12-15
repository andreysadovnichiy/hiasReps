package com.solveast.rreps.model.dao;

import com.solveast.rreps.model.DateUtils;
import com.solveast.rreps.model.queries.five.Query5;
import com.solveast.rreps.model.queries.two.Query21;
import com.solveast.rreps.model.queries.two.Query22;
import com.solveast.rreps.model.queries.two.Query23;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Андрей on 12.12.2016.
 */
@Repository
public class ReportFiveDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Query5> getQuery(LocalDateTime from, LocalDateTime to) {

        Map namedParameters = new HashMap();
        namedParameters.put("from", DateUtils.toTimestamp(from));
        namedParameters.put("to", DateUtils.toTimestamp(to));

        String sql = "SELECT " +
                " cl.client_id, cl.applicant_id, cl.iso3166_3, cl.sex_cd, cl.birth_date,  " +
                " ta.real_time_start, ta.real_time_stop, ta.scheduled_time_start, ta.scheduled_time_stop" +
                " FROM tasks.t_action AS ta" +
                " LEFT JOIN tasks.t_action_client AS tc ON ta.action_id = tc.action_id" +
                " LEFT JOIN clients.t_client AS cl ON tc.client_id = cl.client_id" +
                " WHERE (ta.service_id = 10 AND ta.action_state_cd = 'CLS')" +
                " AND " +
                " ((ta.real_time_start BETWEEN :from AND :to)" +
                " OR (ta.real_time_stop BETWEEN :from AND :to)" +
                " OR (ta.scheduled_time_start BETWEEN :from AND :to)" +
                " OR (ta.scheduled_time_stop BETWEEN :from AND :to))";

        List<Query5> query =
                (List<Query5>) namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<Query5>() {
                    @Override
                    public Query5 mapRow(ResultSet rs, int i) throws SQLException {
                        Query5 item = new Query5();
                        item.setClientId(rs.getLong("client_id"));
                        item.setApplicantId(rs.getLong("applicant_id"));
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
