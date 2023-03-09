package com.klimmenkov.spring.hibernate.lab_4.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, active from usr where username=?")
                .authoritiesByUsernameQuery("select u.username, ur.roles from usr u inner join user_role " +
                                            "ur on u.id = ur.user_id where u.username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/home", "/registration","/adminPage/**").permitAll()
                    //.antMatchers("/adminPage/**").hasRole("admin")
                    .antMatchers("/tenantPage/**").hasRole("tenant")
                    .antMatchers("/workerPage/**").hasRole("worker")
                    .antMatchers("/css/**", "/images/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/home")
                    .permitAll()
                .and()
                    .logout();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }
}
