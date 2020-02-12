package htp.config;


import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Objects;


@Configuration
@PropertySource("classpath:database.properties")
public class DatabaseConfig {

    private Environment properties;

    @Autowired
    public DatabaseConfig(Environment environment){
        this.properties= environment;
    }

    public DatabaseConfig() {
    }

    @Bean(value = "dataSource", destroyMethod = "close")
    public BasicDataSource getDatasource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setUsername(properties.getProperty("login"));
        dataSource.setPassword(properties.getProperty("password"));
        dataSource.setInitialSize(Integer.parseInt(Objects.requireNonNull(properties.getProperty("initialSize"))));
        return dataSource;
    }

}
