package com.solveast.rreps.model.dao;

import com.solveast.rreps.model.queries.FamilyQuery;
import com.solveast.rreps.model.queries.Query1;
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
public class FamilyDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<FamilyQuery> getFamily() {

        Map namedParameters = new HashMap();

        String sql = "SELECT cl.client_id, cl.applicant_id" +
                " FROM clients.t_client AS cl" +
                " ORDER BY cl.client_id";

        List<FamilyQuery> query =
                (List<FamilyQuery>) namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<FamilyQuery>() {
                    @Override
                    public FamilyQuery mapRow(ResultSet rs, int i) throws SQLException {
                        FamilyQuery item = new FamilyQuery();
                        item.setClientId(rs.getLong("client_id"));
                        item.setApplicantId(rs.getLong("applicant_id"));
                        return item;
                    }
                });

        return query;
    }
}
