package htp.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("jwttoken")
public class JwtTokenConfig {

    private String secret;

    private Integer expire;
}
