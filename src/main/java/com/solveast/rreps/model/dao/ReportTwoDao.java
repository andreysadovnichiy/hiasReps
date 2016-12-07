package com.solveast.rreps.model.dao;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Андрей on 16.11.2016.
 */
@Repository
public class ReportTwoDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Query21> getQuery21(Timestamp from, Timestamp to) {

        Map namedParameters = new HashMap();
        namedParameters.put("from", from);
        namedParameters.put("to", to);

        String sql = "SELECT ta.client_id, ta.action_type, ta.action_result_id," +
                " cl.un_relationship_cd, cl.sex_cd, cl.birth_date," +
                " ta.real_time_start, ta.real_time_stop, ta.scheduled_time_start, ta.scheduled_time_stop" +
                " FROM tasks.t_action AS ta" +
                " LEFT JOIN clients.t_client AS cl" +
                " ON cl.client_id = ta.client_id" +
                " WHERE (ta.action_type = 'CLA' AND ta.action_result_id IN (2, 5, 10, 19, 20))" +
                " AND " +
                " ((ta.real_time_start BETWEEN :from AND :to)" +
                " OR (ta.real_time_stop BETWEEN :from AND :to)" +
                " OR (ta.scheduled_time_start BETWEEN :from AND :to)" +
                " OR (ta.scheduled_time_stop BETWEEN :from AND :to))";

        List<Query21> query =
                (List<Query21>) namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<Query21>() {
                    @Override
                    public Query21 mapRow(ResultSet rs, int i) throws SQLException {
                        Query21 item = new Query21();
                        item.setClientId(rs.getLong("client_id"));
                        item.setActionType(rs.getString("action_type"));
                        item.setActionResultId(rs.getInt("action_result_id"));
                        item.setSexCd(rs.getString("sex_cd"));
                        item.setBirthDate(rs.getTimestamp("birth_date"));
                        item.setUnRelationshipCd(rs.getString("un_relationship_cd"));
                        item.setRealTimeStart(rs.getTimestamp("real_time_start"));
                        item.setRealTimeStop(rs.getTimestamp("real_time_stop"));
                        item.setScheduledTimeStart(rs.getTimestamp("scheduled_time_start"));
                        item.setScheduledTimeStop(rs.getTimestamp("scheduled_time_stop"));
                        return item;
                    }
                });

        return query;
    }

    public List<Query22> getQuery22(Timestamp from, Timestamp to) {

        Map namedParameters = new HashMap();
        namedParameters.put("from", from);
        namedParameters.put("to", to);

        String sql = "SELECT cl.client_id, cl.un_relationship_cd, cl.sex_cd, cl.birth_date, cl.register_time" +
                " FROM clients.t_client AS cl" +
                " WHERE (cl.register_time BETWEEN :from AND :to)";

        List<Query22> query =
                (List<Query22>) namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<Query22>() {
                    @Override
                    public Query22 mapRow(ResultSet rs, int i) throws SQLException {
                        Query22 item = new Query22();
                        item.setClientId(rs.getLong("client_id"));
                        item.setRegisterTime(rs.getTimestamp("register_time"));
                        item.setBirthDate(rs.getTimestamp("birth_date"));
                        item.setSexCd(rs.getString("sex_cd"));
                        item.setUnRelationshipCd(rs.getString("un_relationship_cd"));
                        return item;
                    }
                });

        return query;
    }

    public List<Query23> getQuery23(Timestamp from, Timestamp to) {

        Map namedParameters = new HashMap();
        namedParameters.put("from", from);
        namedParameters.put("to", to);

        String sql = "SELECT fr.client_id, cl.sex_cd, cl.un_relationship_cd, cl.birth_date, fr.register_time" +
                " FROM clients.t_registration_form AS fr" +
                " LEFT JOIN clients.t_client AS cl ON cl.client_id = fr.client_id" +
                " WHERE (fr.register_time BETWEEN :from AND :to)";

        List<Query23> query =
                (List<Query23>) namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<Query23>() {
                    @Override
                    public Query23 mapRow(ResultSet rs, int i) throws SQLException {
                        Query23 item = new Query23();
                        item.setClientId(rs.getLong("client_id"));
                        item.setRegisterTime(rs.getTimestamp("register_time"));
                        item.setBirthDate(rs.getTimestamp("birth_date"));
                        item.setSexCd(rs.getString("sex_cd"));
                        item.setUnRelationshipCd(rs.getString("un_relationship_cd"));
                        return item;
                    }
                });

        return query;
    }
}
