package com.example.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.sql.DataSource;

@Configuration
@ImportResource("classpath:/applicationContext.xml")
public class DataConfig {
    @Inject DataSource dataSource;

    @Bean
    public JdbcOperations jdbcTemplate() {
        return new JdbcTemplate(this.dataSource);
    }
}
