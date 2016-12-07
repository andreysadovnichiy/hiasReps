package com.solveast.rreps.model.dao;

import com.solveast.rreps.model.queries.deleted.DeletedQuery;
import com.solveast.rreps.model.queries.family.FamilyQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Андрей on 07.12.2016.
 */
@Repository
public class DeletedDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Set<Long> getDeleted() {

        Map namedParameters = new HashMap();

        String sql = "SELECT client_id FROM clients.t_registration_state WHERE file_status_id = 0";

        List<DeletedQuery> query =
                (List<DeletedQuery>) namedParameterJdbcTemplate.query(sql, namedParameters, new RowMapper<DeletedQuery>() {
                    @Override
                    public DeletedQuery mapRow(ResultSet rs, int i) throws SQLException {
                        DeletedQuery item = new DeletedQuery();
                        item.setClientId(rs.getLong("client_id"));
                        return item;
                    }
                });

        Set<Long> setItems = new HashSet<>();
        for (DeletedQuery item : query) {
            if (!setItems.contains(item.getClientId())) {
                setItems.add(item.getClientId());
            }
        }

        return setItems;
    }
}
