package com.solveast.rreps.config;

import com.solveast.rreps.account_manager.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
class JdbcConfig {
    @Value("${jdbc.dataSource.driverClassName}")
    private String driver;
    @Value("${jdbc.dataSource.url}")
    private String url;
    @Value("${jdbc.dataSource.username}")
    private String username;
    @Value("${jdbc.dataSource.password}")
    private String password;

    @Value("${jdbc.dataSource.lookup.prod}")
    private String jdbcLookup;

    @Bean
    public Account accountTestForModelInject(){
        return new Account(1l,"логин","пасс","роль",true);
    }

    @Bean
    @Lazy
    public DataSource getDataSourceProperty() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    @Resource(name = "jdbc/refugee")
    @Lazy
    public DataSource getDataSourceJndiLookup() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = dsLookup.getDataSource("java:comp/env/jdbc/refugee");
        return dataSource;
    }

    @Bean
    NamedParameterJdbcTemplate getNamedParameterJdbcTemplateRemote() {
        return new NamedParameterJdbcTemplate(getDataSourceJndiLookup());
    }
}
