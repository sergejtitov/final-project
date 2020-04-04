package htp.config;


import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;

@Configuration
@ComponentScan("htp")
public class JdbcTemplateConfig {

    private BasicDataSource basicDataSource;

    @Autowired
    public JdbcTemplateConfig(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }

    public JdbcTemplateConfig() {
    }

    @Bean("jdbcTemplate")
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(basicDataSource);
    }

    @Bean("namedJdbcTemplate")
    public NamedParameterJdbcTemplate getNamedJdbcTemplate() {
        return new NamedParameterJdbcTemplate(basicDataSource);
    }

    @Bean("transactionManager")
    public JpaTransactionManager getTransactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
