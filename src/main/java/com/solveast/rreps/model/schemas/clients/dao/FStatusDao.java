package com.solveast.rreps.model.schemas.clients.dao;

import com.solveast.rreps.model.schemas.clients.FStatus;
import com.solveast.rreps.model.schemas.clients.TClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by Андрей on 28.10.2016.
 */
@Component
public class FStatusDao {
    @Autowired
    @Qualifier("jdbcTemplateRemote")
    private JdbcTemplate jdbcTemplate;


    public List<FStatus> getFStatus(Date begin, Date end) {
        return null;
    }

}
