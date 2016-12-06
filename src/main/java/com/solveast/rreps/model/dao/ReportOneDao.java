package com.solveast.rreps.model.dao;

import com.solveast.rreps.model.queries.one.Query1;
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
public class ReportOneDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Query1> getQuery(Timestamp from, Timestamp to) {

        Map namedParameters = new HashMap();
        namedParameters.put("from", from);
        namedParameters.put("to", to);

        String sql = "SELECT cl.client_id, cl.register_time, rf.unhcr_date," +
                " cl.sex_cd, cl.iso3166_3, cl.birth_date, un_relationship_cd, cl.applicant" +
                " FROM clients.t_client AS cl" +
                " LEFT JOIN clients.t_registration_form AS rf" +
                " ON cl.client_id = rf.client_id" +
                " WHERE (cl.register_time BETWEEN :from AND :to) AND applicant = TRUE";

        List<Query1> query =
                (List<Query1>) namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<Query1>() {
                    @Override
                    public Query1 mapRow(ResultSet rs, int i) throws SQLException {
                        Query1 item = new Query1();
                        item.setClientId(rs.getLong("client_id"));
                        item.setRegisterTime(rs.getTimestamp("register_time"));
                        item.setUnhcrDate(rs.getTimestamp("unhcr_date"));
                        item.setSexCd(rs.getString("sex_cd"));
                        item.setIso3166_3(rs.getString("iso3166_3"));
                        item.setBirthDate(rs.getTimestamp("birth_date"));
                        item.setUn_relationship_cd(rs.getString("un_relationship_cd"));
                        item.setApplicant(rs.getBoolean("applicant"));
                        return item;
                    }
                });

        return query;
    }
}
