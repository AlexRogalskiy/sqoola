/*
 * The MIT License
 *
 * Copyright 2017 WildBees Labs.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.ubs.network.api.gateway.core.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ubs.network.api.gateway.common.service.interfaces.ext.IPropertiesConfiguration;

import java.util.Properties;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

import org.hibernate.SessionFactory;
import com.zaxxer.hikari.HikariDataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * Application JPA Configuration
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
@Configuration("gatewayCoreJpaConfiguration")
@EnableAutoConfiguration
@EnableAsync
@EnableJpaAuditing
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.ubs.network.api.gateway.core.repository"},
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
//@ImportResource("classpath:hibernate.cfg.xml")
//@PropertySource({"classpath:application.default.yml"})
public class JpaConfiguration {

//    @PersistenceContext(unitName = "ds2", type = PersistenceContextType.TRANSACTION)
//    private EntityManager em;
    @Autowired
    @Qualifier("gatewayCorePropertiesConfiguration")
    private IPropertiesConfiguration propertyConfig;

    /**
     * Get Property Source placeholder
     *
     * @return property source placeholder
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * Get Default DataSource properties
     *
     * @return default DataSource properties
     */
    @Bean
    @Primary
    @ConfigurationProperties(ignoreInvalidFields = true, prefix = "datasource.gatewaycoreapp")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * Get DataSource configuration
     *
     * @return DataSource configuration
     */
    @Bean
    public DataSource dataSource() {
        final DataSourceProperties dataSourceProperties = dataSourceProperties();
        final HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder
                .create(dataSourceProperties.getClassLoader())
                .driverClassName(dataSourceProperties.getDriverClassName())
                .url(dataSourceProperties.getUrl())
                .username(dataSourceProperties.getUsername())
                .password(dataSourceProperties.getPassword())
                .type(HikariDataSource.class)
                .build();
        // Basic datasource properties
        dataSource.setAllowPoolSuspension(propertyConfig.getProperty("datasource.gatewaycoreapp.allowPoolSuspension", Boolean.class));
        dataSource.setAutoCommit(propertyConfig.getProperty("datasource.gatewaycoreapp.autoCommit", Boolean.class));
        dataSource.setMaximumPoolSize(propertyConfig.getProperty("datasource.gatewaycoreapp.maxPoolSize", Integer.class));
        // Hikari specific properties
        dataSource.setValidationTimeout(propertyConfig.getProperty("datasource.gatewaycoreapp.validationTimeout", Long.class));
        dataSource.setMinimumIdle(propertyConfig.getProperty("datasource.gatewaycoreapp.minIdle", Integer.class));
        dataSource.setIdleTimeout(propertyConfig.getProperty("datasource.gatewaycoreapp.idleTimeout", Long.class));
        dataSource.setMaxLifetime(propertyConfig.getProperty("datasource.gatewaycoreapp.maxLifeTime", Long.class));
        dataSource.setInitializationFailTimeout(propertyConfig.getProperty("datasource.gatewaycoreapp.initializationFailTimeout", Long.class));
        dataSource.setAllowPoolSuspension(propertyConfig.getProperty("datasource.gatewaycoreapp.allowPoolSuspension", Boolean.class));
        dataSource.setConnectionTimeout(propertyConfig.getProperty("datasource.gatewaycoreapp.connectionTimeout", Long.class));
        dataSource.setAutoCommit(propertyConfig.getProperty("datasource.gatewaycoreapp.autoCommit", Boolean.class));
        dataSource.setInitializationFailTimeout(propertyConfig.getProperty("datasource.gatewaycoreapp.initializationFailTimeout", Long.class));
        dataSource.setLoginTimeout(propertyConfig.getProperty("datasource.gatewaycoreapp.loginTimeout", Long.class));
        // MySQL specific properties
        /*dataSource.addDataSourceProperty("cachePrepStmts", propertyConfig.getProperty("datasource.gatewaycoreapp.cachePrepStmts"));
        dataSource.addDataSourceProperty("prepStmtCacheSize", propertyConfig.getProperty("datasource.gatewaycoreapp.prepStmtCacheSize"));
        dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", propertyConfig.getProperty("datasource.gatewaycoreapp.prepStmtCacheSqlLimit"));
        dataSource.addDataSourceProperty("useServerPrepStmts", propertyConfig.getProperty("datasource.gatewaycoreapp.useServerPrepStmts"));
        dataSource.addDataSourceProperty("useLocalSessionState", propertyConfig.getProperty("datasource.gatewaycoreapp.useLocalSessionState"));
        dataSource.addDataSourceProperty("useLocalTransactionState", propertyConfig.getProperty("datasource.gatewaycoreapp.useLocalTransactionState"));
        dataSource.addDataSourceProperty("rewriteBatchedStatements", propertyConfig.getProperty("datasource.gatewaycoreapp.rewriteBatchedStatements"));
        dataSource.addDataSourceProperty("cacheResultSetMetadata", propertyConfig.getProperty("datasource.gatewaycoreapp.cacheResultSetMetadata"));
        dataSource.addDataSourceProperty("cacheServerConfiguration", propertyConfig.getProperty("datasource.gatewaycoreapp.cacheServerConfiguration"));
        dataSource.addDataSourceProperty("elideSetAutoCommits", propertyConfig.getProperty("datasource.gatewaycoreapp.elideSetAutoCommits"));
        dataSource.addDataSourceProperty("maintainTimeStats", propertyConfig.getProperty("datasource.gatewaycoreapp.maintainTimeStats"));
        dataSource.addDataSourceProperty("allowUrlInLocalInfile", propertyConfig.getProperty("datasource.gatewaycoreapp.allowUrlInLocalInfile"));
        dataSource.addDataSourceProperty("useReadAheadInput", propertyConfig.getProperty("datasource.gatewaycoreapp.useReadAheadInput"));
        dataSource.addDataSourceProperty("useUnbufferedIO", propertyConfig.getProperty("datasource.gatewaycoreapp.useUnbufferedIO"));*/
        return dataSource;
    }

    /**
     * Get Combo pool DataSource configuration
     *
     * @return DataSource configuration
     * @throws java.beans.PropertyVetoException
     */
    //@Bean
    public DataSource dataSource2() throws PropertyVetoException {
        final ComboPooledDataSource dataSource2 = new ComboPooledDataSource();
        dataSource2.setAcquireIncrement(propertyConfig.getProperty("datasource.gatewaycoreapp.acquireIncrement", Integer.class));
        dataSource2.setMaxStatementsPerConnection(propertyConfig.getProperty("datasource.gatewaycoreapp.maxStatementsPerConnection", Integer.class));
        dataSource2.setMaxStatements(propertyConfig.getProperty("datasource.gatewaycoreapp.maxStatements", Integer.class));
        dataSource2.setMaxPoolSize(propertyConfig.getProperty("datasource.gatewaycoreapp.maxPoolSize", Integer.class));
        dataSource2.setMinPoolSize(propertyConfig.getProperty("datasource.gatewaycoreapp.minPoolSize", Integer.class));
        dataSource2.setJdbcUrl(propertyConfig.getMandatoryProperty("datasource.gatewaycoreapp.url"));
        dataSource2.setUser(propertyConfig.getMandatoryProperty("datasource.gatewaycoreapp.username"));
        dataSource2.setPassword(propertyConfig.getMandatoryProperty("datasource.gatewaycoreapp.password"));
        dataSource2.setDriverClass(propertyConfig.getMandatoryProperty("datasource.gatewaycoreapp.driverClassName"));
        return dataSource2;
    }

    /**
     * Get DataSource configuration
     *
     * @return DataSource configuration
     * @throws java.beans.PropertyVetoException
     */
    //@Bean
    public DataSource dataSource3() throws PropertyVetoException {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(propertyConfig.getMandatoryProperty("datasource.gatewaycoreapp.driverClassName"));
        dataSource.setUrl(propertyConfig.getMandatoryProperty("datasource.gatewaycoreapp.url"));
        dataSource.setUsername(propertyConfig.getMandatoryProperty("datasource.gatewaycoreapp.username"));
        dataSource.setPassword(propertyConfig.getMandatoryProperty("datasource.gatewaycoreapp.password"));
        return dataSource;
    }

    /**
     * Get Entity Manager Factory bean
     *
     * @return local container emf bean
     * @throws javax.naming.NamingException
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan(new String[]{"com.ubs.network.api.gateway.common.model", "com.ubs.network.api.gateway.core.model"});
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProperties());
        factoryBean.setPersistenceUnitName("local");
//        factoryBean.setPersistenceUnitManager(persistenceUnitManager());
        return factoryBean;
    }

    /**
     * Get Hibernate JPA adapter
     *
     * @return hibernate JPA adapter
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
//        hibernateJpaVendorAdapter.setShowSql(true);
//        hibernateJpaVendorAdapter.setGenerateDdl(true);
//        hibernateJpaVendorAdapter.setDatabasePlatform(propertyConfig.getMandatoryProperty("datasource.gatewaycoreapp.hibernate.dialect"));
        return hibernateJpaVendorAdapter;
    }

    /**
     * Get Persistence exception translation processor
     *
     * @return persistence exception translation processor
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    /**
     * Get JPA properties configuration
     *
     * @return JPA properties configuration
     */
    private Properties jpaProperties() {
        final Properties properties = new Properties();
        properties.put("hibernate.dialect", propertyConfig.getMandatoryProperty("datasource.gatewaycoreapp.hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", propertyConfig.getProperty("datasource.gatewaycoreapp.hibernate.hbm2ddl.method"));
        properties.put("hibernate.show_sql", propertyConfig.getProperty("datasource.gatewaycoreapp.hibernate.showSql"));
        properties.put("hibernate.format_sql", propertyConfig.getProperty("datasource.gatewaycoreapp.hibernate.formatSql"));
        properties.put("hibernate.max_fetch_depth", propertyConfig.getProperty("datasource.gatewaycoreapp.hibernate.maxFetchDepth"));
        properties.put("hibernate.default_batch_fetch_size", propertyConfig.getProperty("datasource.gatewaycoreapp.hibernate.defaultBatchFetchSize"));
        properties.put("hibernate.default_schema", propertyConfig.getProperty("datasource.gatewaycoreapp.hibernate.defaultSchema"));
        properties.put("hibernate.globally_quoted_identifiers", propertyConfig.getProperty("datasource.gatewaycoreapp.hibernate.globallyQuotedIdentifiers"));
        properties.put("hibernate.generate_statistics", propertyConfig.getProperty("datasource.gatewaycoreapp.hibernate.generateStatistics"));
        properties.put("hibernate.bytecode.use_reflection_optimizer", propertyConfig.getProperty("datasource.gatewaycoreapp.hibernate.bytecode.useReflectionOptimizer"));

        // Configure Lucene Search
        properties.put("spring.jpa.properties.hibernate.search.default.directory_provider", propertyConfig.getProperty("spring.jpa.properties.hibernate.search.default.directoryProvider"));
        properties.put("spring.jpa.properties.hibernate.search.default.indexBase", propertyConfig.getProperty("spring.jpa.properties.hibernate.search.default.indexBase"));
        properties.put("spring.jpa.properties.hibernate.search.default.batch.merge_factor", propertyConfig.getProperty("spring.jpa.properties.hibernate.search.default.batch.mergeFactor"));
        properties.put("spring.jpa.properties.hibernate.search.default.batch.max_buffered_docs", propertyConfig.getProperty("spring.jpa.properties.hibernate.search.default.batch.maxBufferedDocs"));

        if (StringUtils.isNotEmpty(propertyConfig.getProperty("datasource.gatewaycoreapp.hibernate.hbm2ddl.importFiles"))) {
            properties.put("hibernate.hbm2ddl.import_files", propertyConfig.getProperty("datasource.gatewaycoreapp.hibernate.hbm2ddl.importFiles"));
        }

        // Configure Hibernate Cache
        properties.put("hibernate.cache.use_second_level_cache", propertyConfig.getProperty("datasource.gatewaycoreapp.hibernate.cache.useSecondLevelCache"));
        properties.put("hibernate.cache.use_query_cache", propertyConfig.getProperty("datasource.gatewaycoreapp.hibernate.cache.useQueryCache"));
        properties.put("hibernate.cache.region.factory_class", propertyConfig.getProperty("datasource.gatewaycoreapp.hibernate.cache.region.factoryClass"));

        // Configure Connection Pool
        properties.put("hibernate.c3p0.min_size", propertyConfig.getProperty("datasource.gatewaycoreapp.hibernate.c3p0.minSize"));
        properties.put("hibernate.c3p0.max_size", propertyConfig.getProperty("datasource.gatewaycoreapp.hibernate.c3p0.maxSize"));
        properties.put("hibernate.c3p0.timeout", propertyConfig.getProperty("datasource.gatewaycoreapp.hibernate.c3p0.timeout"));
        properties.put("hibernate.c3p0.max_statements", propertyConfig.getProperty("datasource.gatewaycoreapp.hibernate.c3p0.maxStatements"));
        properties.put("hibernate.c3p0.idle_test_period", propertyConfig.getProperty("datasource.gatewaycoreapp.hibernate.c3p0.idleTestPeriod"));
        return properties;
    }

    /**
     * Get Transaction Manager
     *
     * @param emf - entities manager factory
     * @return transaction manager
     */
    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }

    /**
     * Get Session Factory bean
     *
     * @return session factory bean
     */
    @Bean
    public FactoryBean<SessionFactory> sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        return sessionFactory;
    }

    /**
     * Get Persistence Unit Manager
     *
     * @return persistence unit manager
     */
    @Bean
    public PersistenceUnitManager persistenceUnitManager() {
        DefaultPersistenceUnitManager manager = new DefaultPersistenceUnitManager();
        manager.setDefaultDataSource(dataSource());
        return manager;
    }

    /**
     * Get Persistence Annotation processor
     *
     * @return persistence annotation processor
     */
    @Bean
    public BeanPostProcessor postProcessor() {
        PersistenceAnnotationBeanPostProcessor postProcessor = new PersistenceAnnotationBeanPostProcessor();
        return postProcessor;
    }
}
