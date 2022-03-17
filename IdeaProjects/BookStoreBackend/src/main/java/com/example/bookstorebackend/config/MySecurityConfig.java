package com.example.bookstorebackend.config;

import com.example.bookstorebackend.service.CustomUserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDataService customUserDataService;

    @Autowired
    private JwtAuthenticationFilter jwtFilter;


    // The exempted paths where authentication using Jwt token is not required
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf()
                .disable()
                .cors()
                .disable()
                .authorizeRequests()
                .antMatchers("/generateToken")
                .permitAll()
                .antMatchers("/userservice/get")
                .permitAll()
                .antMatchers("/userservice/register")
                .permitAll()
                .antMatchers("/userservice/verify")
                .permitAll()
                .antMatchers("/userservice/deleteByEmailId/{emailId}")
                .permitAll()
                .antMatchers("/userservice/getByEmailId/{emailId}")
                .permitAll()
                .antMatchers("/bookstore/get")
                .permitAll()
                .antMatchers("/bookstore/getById/**")
                .permitAll()
                .antMatchers("/bookstore/delete")
                .permitAll()
                .antMatchers("/bookstore/add")
                .permitAll()
                .antMatchers("/orderdataservice/getAllOrders")
                .permitAll()
                .antMatchers("/bookstore/add")
                .permitAll()
                .antMatchers("/bookstore/add")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
    //
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(customUserDataService);
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }
}
