package htp.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.apache.commons.dbcp.BasicDataSource;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Configuration
@ConfigurationProperties("database")
public class DatabaseConfig {

    private String url;

    private String login;

    private String password;

    private String initialSize;

    private String maxActive;



    @Bean(value = "dataSource", destroyMethod = "close")
    public BasicDataSource getDatasource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(login);
        dataSource.setPassword(password);
        dataSource.setInitialSize(Integer.parseInt(Objects.requireNonNull(initialSize)));
        dataSource.setMaxActive(Integer.parseInt(Objects.requireNonNull(maxActive)));
        return dataSource;
    }


}
