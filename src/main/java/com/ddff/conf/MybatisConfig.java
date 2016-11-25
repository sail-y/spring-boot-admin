/*
 * Copyright (c) 2016 xiaomaihd and/or its affiliates.All Rights Reserved.
 *            http://www.xiaomaihd.com
 */
package com.ddff.conf;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

/**
 * Created by YangFan on 2016/10/14 下午5:53.
 * <p/>
 */
@Configuration
@EnableTransactionManagement
public class MybatisConfig {


    @Autowired
    private DataSourceProperties config;

    private DruidDataSource pool;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        this.pool = new DruidDataSource();
        this.pool.setDriverClassName(config.getDriverClassName());
        this.pool.setUrl(config.getUrl());
        if (config.getUsername() != null) {
            this.pool.setUsername(config.getUsername());
        }
        if (config.getPassword() != null) {
            this.pool.setPassword(config.getPassword());
        }
        this.pool.setInitialSize(10);
        this.pool.setMaxActive(100);
        this.pool.setTestWhileIdle(true);
        this.pool.setMinIdle(0);
        this.pool.setTestOnBorrow(false);
        this.pool.setTestOnReturn(false);
        this.pool.setValidationQuery("select 1");
        return this.pool;
    }

    @PreDestroy
    public void close() {
        if (this.pool != null) {
            this.pool.close();
        }
    }
}
