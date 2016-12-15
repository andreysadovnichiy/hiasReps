package com.solveast.rreps.model.dao;

import com.solveast.rreps.model.DateUtils;
import com.solveast.rreps.model.queries.three.Query3;
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
 * Created by Андрей on 27.11.2016.
 */
@Repository
public class ReportThreeDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Query3> getQuery3(LocalDateTime from, LocalDateTime to) {

        Map namedParameters = new HashMap();
        namedParameters.put("from", DateUtils.toTimestamp(from));
        namedParameters.put("to", DateUtils.toTimestamp(to));

        String sql = "SELECT cl.client_id, cl.iso3166_3, cln.name, cln.family_name," +
                " rf.unhcr_num, rf.unhcr_date, rs.file_status_id, fsn.name AS file_status_name" +
                " FROM clients.t_registration_form as rf" +
                " LEFT JOIN clients.t_client AS cl ON rf.client_id = cl.client_id" +
                " LEFT JOIN clients.t_client_name AS cln ON cl.client_id = cln.client_id" +
                " LEFT JOIN clients.t_registration_state AS rs ON rf.client_id = rs.client_id" +
                " LEFT JOIN clients.r_file_status_name AS fsn ON rs.file_status_id = fsn.file_status_id" +
                " WHERE (rf.unhcr_num IS NOT NULL OR rf.unhcr_date IS NOT NULL) " +
                " AND cln.language_cd = 'en' AND fsn.language_cd = 'en'" +
                " AND cl.applicant = true" +
                " AND rs.file_status_id > 1";

        List<Query3> query =
                (List<Query3>) namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<Query3>() {
                    @Override
                    public Query3 mapRow(ResultSet rs, int i) throws SQLException {
                        Query3 item = new Query3();
                        item.setClientId(rs.getLong("client_id"));
                        item.setIso3166_3(rs.getString("iso3166_3"));
                        item.setName(rs.getString("name"));
                        item.setFamilyName(rs.getString("family_name"));
                        item.setUnhcrDate(rs.getTimestamp("unhcr_date"));
                        item.setUnhcrNum(rs.getString("unhcr_num"));
                        item.setFileStatusId(rs.getInt("file_status_id"));
                        item.setFileStatusName(rs.getString("file_status_name"));
                        return item;
                    }
                });

        return query;
    }
}
