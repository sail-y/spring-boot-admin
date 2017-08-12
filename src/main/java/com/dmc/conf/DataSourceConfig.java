package com.dmc.conf;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;

/**
 *   druid数据源自动配置
 */
@Configuration
@EnableConfigurationProperties(DruidDataSourceProperties.class)
@ConditionalOnClass({DruidDataSourceProperties.class})
public class DataSourceConfig {

    @Autowired
    private DruidDataSourceProperties properties;

    private DruidDataSource dataSource;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        this.dataSource = new DruidDataSource();
        this.dataSource.setDriverClassName(properties.getDriverClassName());
        this.dataSource.setUrl(properties.getUrl());
        if (properties.getUsername() != null) {
            this.dataSource.setUsername(properties.getUsername());
        }
        if (properties.getPassword() != null) {
            this.dataSource.setPassword(properties.getPassword());
        }
        this.dataSource.setInitialSize(properties.getInitialSize());
        this.dataSource.setMaxActive(properties.getMaxActive());
        this.dataSource.setTestWhileIdle(true);
        this.dataSource.setMinIdle(properties.getMinIdle());
        this.dataSource.setTestOnBorrow(properties.isTestOnBorrow());
        this.dataSource.setTestOnReturn(properties.isTestOnReturn());
        this.dataSource.setValidationQuery(properties.getValidationQuery());
        return this.dataSource;
    }

    @PreDestroy
    public void close() {
        if (this.dataSource != null) {
            this.dataSource.close();
        }
    }
}
