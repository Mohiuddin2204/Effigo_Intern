package com.template.quesans.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Autowired
    private TenantProperties tenantProperties;

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        MultiTenantDataSource dataSource = new MultiTenantDataSource();
        Map<Object, Object> resolvedDataSources = new HashMap<>();

        for (Map.Entry<String, TenantProperties.DataSourceProperties> entry : tenantProperties.getDatasources().entrySet()) {
            String tenantId = entry.getKey();
            TenantProperties.DataSourceProperties tenantDataSourceProps = entry.getValue();

            HikariDataSource tenantDataSource = new HikariDataSource();
            tenantDataSource.setJdbcUrl(tenantDataSourceProps.getUrl());
            tenantDataSource.setUsername(tenantDataSourceProps.getUsername());
            tenantDataSource.setPassword(tenantDataSourceProps.getPassword());
            tenantDataSource.setDriverClassName("org.postgresql.Driver");

            resolvedDataSources.put(tenantId, tenantDataSource);
        }

        dataSource.setTargetDataSources(resolvedDataSources);
        dataSource.setDefaultTargetDataSource(resolvedDataSources.get(TenantResolver.DEFAULT_TENANT_ID));
        dataSource.afterPropertiesSet();

        return dataSource;
    }
}