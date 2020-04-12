package com.htp.security.config;


import com.htp.security.filter.AuthenticationTokenFilter;
import com.htp.security.util.TokenUtil;
import com.htp.services.UserDetailsServiceImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Slf4j
@Data
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("com.htp")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    private final TokenUtil tokenUtil;


    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder, PasswordEncoder passwordEncoder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean (AuthenticationManager authenticationManager) {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter(tokenUtil, userDetailsService);
        authenticationTokenFilter.setAuthenticationManager(authenticationManager);
        return authenticationTokenFilter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/registration").not().fullyAuthenticated()
                .antMatchers("/admin","/admin/**").hasRole("ADMIN")
                .antMatchers("/products","/products/**").hasRole("ADMIN")
                .antMatchers("/users","/users/**").hasRole("USER")
                .antMatchers("/audit/applications/**","/audit/applications").permitAll()
                .antMatchers("/applications","/applications/**").hasRole("USER")
                .antMatchers("/v2/api-docs", "/configuration/ui/**", "/swagger-resources/**", "/configuration/security/**", "/swagger-ui.html", "/webjars/**").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-ui.html#").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(authenticationTokenFilterBean(authenticationManagerBean()), UsernamePasswordAuthenticationFilter.class);

    }


    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/v2/api-docs", "/configuration/ui/**", "/swagger-resources/**", "/configuration/security/**", "/swagger-ui.html", "/webjars/**");
    }

}

