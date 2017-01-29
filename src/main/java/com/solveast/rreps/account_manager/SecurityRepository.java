package com.solveast.rreps.account_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class SecurityRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    Map namedParameters = new HashMap();

    public Account findOneByLogin(String login) {

        Map namedParameters = new HashMap();
        namedParameters.put("login", login);

        String sql = "SELECT tp.person_id, tp.login, tp.pwd, tp.person_type" +
                " FROM portal.t_person as tp" +
                " WHERE tp.login = :login";

        Account query = null;
        try {
            query = (Account) namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new RowMapper<Account>() {
                @Override
                public Account mapRow(ResultSet rs, int i) throws SQLException {
                    Account account = new Account();
                    account.setId(rs.getLong("person_id"));
                    account.setLogin(rs.getString("login"));
                    account.setPassword(rs.getString("pwd"));
                    account.setPersonType(rs.getString("person_type"));
                    return account;
                }
            });
        } catch (DataAccessException e) {
            return null;
        }

        return query;
    }
}