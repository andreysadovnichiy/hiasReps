package com.solveast.rreps.model.dao;

import com.solveast.rreps.model.queries.base.BaseQuery;
import com.solveast.rreps.model.queries.family.FamilyQuery;
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
 * Created by Андрей on 16.11.2016.
 */
@Repository
public class FamilyDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<FamilyQuery> getFamily() {

        Map namedParameters = new HashMap();

        String sql = "SELECT cl.client_id, cl.applicant_id, cl.sex_cd, cl.birth_date, cl.un_relationship_cd" +
                " FROM clients.t_client AS cl";

        List<FamilyQuery> query =
                (List<FamilyQuery>) namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<FamilyQuery>() {
                    @Override
                    public FamilyQuery mapRow(ResultSet rs, int i) throws SQLException {
                        FamilyQuery item = new FamilyQuery();
                        item.setClientId(rs.getLong("client_id"));
                        item.setApplicantId(rs.getLong("applicant_id"));
                        item.setSexCd(rs.getString("sex_cd"));
                        item.setBirthDate(rs.getTimestamp("birth_date"));
                        item.setUnRelationshipCd(rs.getString("un_relationship_cd"));
                        return item;
                    }
                });

        return query;
    }

    public List<BaseQuery> getFamilyBaseQuery() {

        Map namedParameters = new HashMap();

        String sql = "SELECT cl.client_id, cl.applicant_id, cl.register_time, rf.unhcr_date," +
                " cl.sex_cd, cl.birth_date, cl.un_relationship_cd" +
                " FROM clients.t_client AS cl" +
                " LEFT JOIN clients.t_registration_form AS rf" +
                " ON cl.client_id = rf.client_id";

        List<BaseQuery> query =
                (List<BaseQuery>) namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<BaseQuery>() {
                    @Override
                    public BaseQuery mapRow(ResultSet rs, int i) throws SQLException {
                        BaseQuery item = new BaseQuery();
                        item.setClientId(rs.getLong("client_id"));
                        item.setApplicantId(rs.getLong("applicant_id"));
                        item.setRegisterTime(rs.getTimestamp("register_time"));
                        item.setRegisterTime(rs.getTimestamp("unhcr_date"));
                        item.setSexCd(rs.getString("sex_cd"));
                        item.setBirthDate(rs.getTimestamp("birth_date"));
                        item.setUnRelationshipCd(rs.getString("un_relationship_cd"));
                        return item;
                    }
                });

        return query;
    }

}
