/*
 * Copyright (c) 2016 xiaomaihd and/or its affiliates.All Rights Reserved.
 *            http://www.xiaomaihd.com
 */
package com.dmc.jwt;

import com.dmc.jwt.JsonWebTokenAuthenticationFilter;
import com.dmc.jwt.JsonWebTokenAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by YangFan on 2016/11/28 上午10:27.
 * <p/>
 */
public abstract class JsonWebTokenSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JsonWebTokenAuthenticationProvider authenticationProvider;

    @Autowired
    private JsonWebTokenAuthenticationFilter jsonWebTokenFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // This method is here with the @Bean annotation so that Spring can
        // autowire it
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers()
                .frameOptions()
                .sameOrigin()
                .and()
                // disable CSRF, http basic, form login
                .csrf().disable() //
                .httpBasic().disable() //
                .formLogin().disable()

                // ReST is stateless, no sessions
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //

                .and()

                // return 403 when not authenticated
                .exceptionHandling().authenticationEntryPoint(new NoAuthenticationEntryPoint());

        // Let child classes set up authorization paths
        setupAuthorization(http);

        http.addFilterBefore(jsonWebTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    protected abstract void setupAuthorization(HttpSecurity http) throws Exception;
}