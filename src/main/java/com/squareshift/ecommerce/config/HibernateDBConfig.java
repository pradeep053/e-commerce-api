package com.squareshift.ecommerce.config;

import com.squareshift.ecommerce.entity.CartItems;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.squareshift.ecommerce.repository",
        entityManagerFactoryRef = "dbEntityManagerFactory",
        transactionManagerRef = "dbTransactionManager")
public class HibernateDBConfig {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.db")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.db.configuration")
    public DataSource dataSource() {

        return dataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "dbEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean dbEntityManagerFactory(EntityManagerFactoryBuilder builder) {

        return builder.dataSource(dataSource()).packages(CartItems.class).build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager dbTransactionManager(final @Qualifier("dbEntityManagerFactory") LocalContainerEntityManagerFactoryBean dbEntityManagerFactory) {

        return new JpaTransactionManager(dbEntityManagerFactory.getObject());
    }
}
