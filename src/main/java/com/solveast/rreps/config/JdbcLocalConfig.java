package com.solveast.rreps.config;

import com.solveast.rreps.Application;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ClassUtils;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
class JdbcLocalConfig {

    @Value("${local.jdbc.dataSource.driverClassName}")
    private String driver;
    @Value("${local.jdbc.dataSource.url}")
    private String url;
    @Value("${local.jdbc.dataSource.username}")
    private String username;
    @Value("${local.jdbc.dataSource.password}")
    private String password;

    /*org.postgresql.Driver
    jdbc:postgresql://localhost:5432/study/
    postgres
    admin
    */

    @Bean
    public DataSource getDataSourceLocal() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    @Qualifier("jdbcTemplateLocal")
    public JdbcTemplate getJdbcTemplateLocal() {
        return new JdbcTemplate(getDataSourceLocal());
    }
}
