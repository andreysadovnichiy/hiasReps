package com.solveast.rreps.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
class JdbcRemoteConfig {
    @Value("${jdbc.dataSource.driverClassName}")
    private String driver;
    @Value("${jdbc.dataSource.url}")
    private String url;
    @Value("${jdbc.dataSource.username}")
    private String username;
    @Value("${jdbc.dataSource.password}")
    private String password;

    @Bean
    public DataSource getDataSourceRemote() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    @Qualifier("jdbcTemplateRemote")
    public JdbcTemplate getJdbcTemplateRemote() {
        return new JdbcTemplate(getDataSourceRemote());
    }

    @Bean
    NamedParameterJdbcTemplate getNamedParameterJdbcTemplateRemote(){
        return new NamedParameterJdbcTemplate(getDataSourceRemote());
    }
}
