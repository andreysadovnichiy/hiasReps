package com.solveast.rreps.model.db.schemas.clients.dao;

import com.solveast.rreps.model.db.schemas.clients.TClient;
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
public class TClientDao {
    @Autowired
    @Qualifier("jdbcTemplateRemote")
    private JdbcTemplate jdbcTemplate;


    public List<TClient> getTClientsByDuration(Date begin, Date end) {
        return null;
    }

    public TClient getClientById(Long id) {
        TClient tClient = new TClient();
        tClient.setClientId(1L);
        tClient.setBirthDate(new Date());
        return tClient;
    }
}
