/*
 * The MIT License
 *
 * Copyright 2019 WildBees Labs, Inc.
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
package com.wildbeeslabs.sensiblemetrics.sqoola.common.configuration;

import com.wildbeeslabs.sensiblemetrics.sqoola.common.executors.TransactionalAsyncTaskExecutor;
import com.wildbeeslabs.sensiblemetrics.sqoola.common.executors.impl.DelegatedTransactionalAsyncTaskExecutor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.jmx.ParentAwareNamingStrategy;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jmx.export.annotation.AnnotationJmxAttributeSource;
import org.springframework.jmx.export.naming.ObjectNamingStrategy;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.DefaultJpaDialect;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.UUID;

/**
 * Datasource configuration
 */
@Configuration
@EnableAsync
@EnableJpaAuditing
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = {
        "com.wildbeeslabs.sensiblemetrics.sqoola.common.model",
        "com.wildbeeslabs.sensiblemetrics.sqoola.common.repository"
    }
)
@PropertySources({
    @PropertySource("classpath:application.properties"),
    @PropertySource("classpath:application.yml")
})
public class DBConfig {

    /**
     * Default model/repository source packages
     */
    public static final String DEFAULT_REPOSITORY_PACKAGE = "com.wildbeeslabs.sensiblemetrics.sqoola.repository";
    public static final String DEFAULT_MODEL_PACKAGE = "com.wildbeeslabs.sensiblemetrics.sqoola.model";
    /**
     * Default task executor thread prefix
     */
    public static final String DEFAULT_TASK_EXECUTOR_THREAD_NAME_PREFIX = "Triggers";
    /**
     * Default task executor thread group name
     */
    public static final String DEFAULT_TASK_EXECUTOR_THREAD_GROUP_NAME = "Trigger group";
    /**
     * Default task executor pool size
     */
    public static final int DEFAULT_EXECUTOR_POOL_SIZE = 10;
    /**
     * Default task executor max pool size
     */
    public static final int DEFAULT_EXECUTOR_MAX_POOL_SIZE = 20;
    /**
     * Default task executor queue size
     */
    public static final int DEFAULT_EXECUTOR_QUEUE_SIZE = 100 * 1000;
    /**
     * Default persistence unit name
     */
    public static final String DEFAULT_PERSISTENCE_UNIT_NAME = "local";
    /**
     * Default domain name prefix
     */
    public static final String DEFAULT_DOMAIN_NAME_PREFIX = "domain_";

    @Autowired
    private Environment env;

    /**
     * Returns {@link LocalContainerEntityManagerFactoryBean} configuration
     *
     * @return {@link LocalContainerEntityManagerFactoryBean} configuration
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(defaultDataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProperties());
        factoryBean.setJpaDialect(jpaDialect());
        factoryBean.setPackagesToScan(DEFAULT_REPOSITORY_PACKAGE, DEFAULT_MODEL_PACKAGE);
        factoryBean.setPersistenceUnitName(DEFAULT_PERSISTENCE_UNIT_NAME);
        //factoryBean.setPersistenceUnitManager(persistenceUnitManager());
        return factoryBean;
    }

    /**
     * Returns spring datasource {@link Properties} properties
     *
     * @return spring datasource {@link Properties} properties
     */
    @Bean
    public Properties properties() {
        final Properties properties = new Properties();
        //properties.put("spring.datasource.testOnBorrow", env.getRequiredProperty("sqoola.datasource.testOnBorrow"));
        //properties.put("spring.datasource.initialization-mode", env.getRequiredProperty("sqoola.datasource.initializationMode"));
        //properties.put("spring.datasource.testWhileIdle", env.getRequiredProperty("sqoola.datasource.testWhileIdle"));
        //properties.put("spring.datasource.timeBetweenEvictionRunsMillis", env.getRequiredProperty("sqoola.datasource.timeBetweenEvictionRunsMillis"));
        //properties.put("spring.datasource.minEvictableIdleTimeMillis", env.getRequiredProperty("sqoola.datasource.minEvictableIdleTimeMillis"));
        //properties.put("spring.datasource.validationQuery", env.getRequiredProperty("sqoola.datasource.validationQuery"));
        //properties.put("spring.datasource.max-active", env.getRequiredProperty("sqoola.datasource.maxActive"));
        //properties.put("spring.datasource.max-idle", env.getRequiredProperty("sqoola.datasource.maxIdle"));
        //properties.put("spring.datasource.max-wait", env.getRequiredProperty("sqoola.datasource.maxWait"));
        //properties.put("spring.datasource.cachePrepStmts", env.getRequiredProperty("sqoola.datasource.cachePrepStmts"));
        //properties.put("spring.datasource.prepStmtCacheSize", env.getRequiredProperty("sqoola.datasource.prepStmtCacheSize"));
        //properties.put("spring.datasource.prepStmtCacheSqlLimit", env.getRequiredProperty("sqoola.datasource.prepStmtCacheSqlLimit"));
        //properties.put("spring.datasource.useServerPrepStmts", env.getRequiredProperty("sqoola.datasource.useServerPrepStmts"));
        //properties.put("spring.datasource.useLocalSessionState", env.getRequiredProperty("sqoola.datasource.useLocalSessionState"));
        //properties.put("spring.datasource.useLocalTransactionState", env.getRequiredProperty("sqoola.datasource.useLocalTransactionState"));
        //properties.put("spring.datasource.rewriteBatchedStatements", env.getRequiredProperty("sqoola.datasource.rewriteBatchedStatements"));
        //properties.put("spring.datasource.cacheResultSetMetadata", env.getRequiredProperty("sqoola.datasource.cacheResultSetMetadata"));
        //properties.put("spring.datasource.cacheServerConfiguration", env.getRequiredProperty("sqoola.datasource.cacheServerConfiguration"));
        //properties.put("spring.datasource.elideSetAutoCommits", env.getRequiredProperty("sqoola.datasource.elideSetAutoCommits"));
        //properties.put("spring.datasource.maintainTimeStats", env.getRequiredProperty("sqoola.datasource.maintainTimeStats"));
        //properties.put("spring.datasource.verifyServerCertificate", env.getRequiredProperty("sqoola.datasource.verifyServerCertificate"));
        //properties.put("spring.datasource.useSqlComments", env.getRequiredProperty("sqoola.datasource.useSqlComments"));
        //properties.put("spring.datasource.maxStatementsPerConnection", env.getRequiredProperty("sqoola.datasource.maxStatementsPerConnection"));
        //properties.put("spring.datasource.maxStatements", env.getRequiredProperty("sqoola.datasource.maxStatements"));
        //properties.put("spring.datasource.minPoolSize", env.getRequiredProperty("sqoola.datasource.minPoolSize"));
        //properties.put("spring.datasource.maxPoolSize", env.getRequiredProperty("sqoola.datasource.maxPoolSize"));
        //properties.put("spring.datasource.autoCommit", env.getRequiredProperty("sqoola.datasource.autoCommit"));
        //properties.put("spring.datasource.allowPoolSuspension", env.getRequiredProperty("sqoola.datasource.allowPoolSuspension"));
        //properties.put("spring.datasource.checkoutTimeout", env.getRequiredProperty("sqoola.datasource.checkoutTimeout"));
        //properties.put("spring.datasource.idleConnectionTestPeriod", env.getRequiredProperty("sqoola.datasource.idleConnectionTestPeriod"));
        //properties.put("spring.datasource.initialPoolSize", env.getRequiredProperty("sqoola.datasource.initialPoolSize"));
        //properties.put("spring.datasource.testConnectionOnCheckin", env.getRequiredProperty("sqoola.datasource.testConnectionOnCheckin"));
        //properties.put("spring.datasource.allowUrlInLocalInfile", env.getRequiredProperty("sqoola.datasource.allowUrlInLocalInfile"));
        //properties.put("spring.datasource.useReadAheadInput", env.getRequiredProperty("sqoola.datasource.useReadAheadInput"));
        //properties.put("spring.datasource.useUnbufferedIO", env.getRequiredProperty("sqoola.datasource.useUnbufferedIO"));
        return properties;
    }

    /**
     * Returns default {@link DataSource} configuration
     *
     * @return default {@link DataSource} configuration
     */
    @Bean(destroyMethod = "close")
    public HikariDataSource defaultDataSource() {
        final HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName(env.getRequiredProperty("sqoola.datasource.driver"));
        dataSourceConfig.setPoolName(env.getRequiredProperty("sqoola.datasource.hibernate.hikari.poolName"));
        dataSourceConfig.setRegisterMbeans(env.getRequiredProperty("sqoola.datasource.hibernate.hikari.registerMbeans", Boolean.class));
        dataSourceConfig.setJdbcUrl(env.getRequiredProperty("sqoola.datasource.url"));
        dataSourceConfig.setUsername(env.getRequiredProperty("sqoola.datasource.username"));
        dataSourceConfig.setPassword(env.getRequiredProperty("sqoola.datasource.password"));
        dataSourceConfig.setConnectionTestQuery(env.getRequiredProperty("sqoola.datasource.connectionTestQuery"));
        //dataSourceConfig.setDataSourceClassName(env.getRequiredProperty("sqoola.datasource.hibernate.hikari.dataSourceClassName"));
        dataSourceConfig.setMinimumIdle(env.getRequiredProperty("sqoola.datasource.hibernate.hikari.minimumIdle", Integer.class));
        dataSourceConfig.setMaximumPoolSize(env.getRequiredProperty("sqoola.datasource.hibernate.hikari.maximumPoolSize", Integer.class));
        dataSourceConfig.setIdleTimeout(env.getRequiredProperty("sqoola.datasource.hibernate.hikari.idleTimeout", Integer.class));
        dataSourceConfig.setIsolateInternalQueries(env.getRequiredProperty("sqoola.datasource.hibernate.hikari.isolateInternalQueries", Boolean.class));
        //dataSourceConfig.setLeakDetectionThreshold(env.getRequiredProperty("sqoola.datasource.hibernate.hikari.leakDetectionThreshold", Integer.class));
        dataSourceConfig.setDataSourceProperties(properties());
        return new HikariDataSource(dataSourceConfig);
    }

//    /**
//     * Returns default {@link DataSourceProperties} configuration
//     *
//     * @return default {@link DataSourceProperties} configuration
//     */
//    @Bean
//    @Primary
//    @ConditionalOnMissingBean
//    @ConfigurationProperties(ignoreInvalidFields = true, prefix = "sqoola.datasource")
//    public DataSourceProperties dataSourceProperties() {
//        return new DataSourceProperties();
//    }

    /**
     * Returns {@link JpaVendorAdapter} configuration
     *
     * @return {@link JpaVendorAdapter} configuration
     */
    @Bean
    @ConditionalOnMissingBean
    public JpaVendorAdapter jpaVendorAdapter() {
        final HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(env.getRequiredProperty("sqoola.datasource.jpa.generateDdl", Boolean.class));
        jpaVendorAdapter.setShowSql(env.getRequiredProperty("sqoola.datasource.jpa.showSql", Boolean.class));
        jpaVendorAdapter.setDatabasePlatform(env.getRequiredProperty("sqoola.datasource.databasePlatform"));
        return jpaVendorAdapter;
    }

//    @Bean
//    public JpaTransactionManager jpaTransactionManager(final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
//        final JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
//        return transactionManager;
//    }

    /**
     * Returns {@link HibernateTransactionManager} configuration by initial {@link SessionFactory} instance
     *
     * @param sessionFactory - initial {@link SessionFactory} instance
     * @return {@link HibernateTransactionManager} configuration
     */
    @Bean
    public HibernateTransactionManager transactionManager(final SessionFactory sessionFactory) {
        final HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

//    /**
//     * Returns platform transaction manager instance {@link PlatformTransactionManager}
//     *
//     * @param entityManagerFactory - initial local container entity manager factory instance {@link LocalContainerEntityManagerFactoryBean}
//     * @param observers            - initial collection of observers {@link TransactionObserver}
//     * @return platform transaction manager {@link PlatformTransactionManager}
//     */
//    @Bean
//    @ConditionalOnMissingBean({PlatformTransactionManager.class})
//    public PlatformTransactionManager transactionManager(final LocalContainerEntityManagerFactoryBean entityManagerFactory, final List<TransactionObserver<Object>> observers) {
//        final EntityManagerFactory factory = entityManagerFactory.getObject();
//        return new ObservableTransactionManager<>(factory, observers);
//    }

    /**
     * Returns transactional task executor instance {@link TransactionalAsyncTaskExecutor}
     *
     * @param transactionManager - initial transaction manager {@link PlatformTransactionManager}
     * @return transactional task executor {@link TransactionalAsyncTaskExecutor}
     */
    @Bean
    public TransactionalAsyncTaskExecutor transactionalAsyncTaskExecutor(final PlatformTransactionManager transactionManager) {
        final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix(DEFAULT_TASK_EXECUTOR_THREAD_NAME_PREFIX);
        threadPoolTaskExecutor.setThreadGroupName(DEFAULT_TASK_EXECUTOR_THREAD_GROUP_NAME);
        threadPoolTaskExecutor.setCorePoolSize(DEFAULT_EXECUTOR_POOL_SIZE);
        threadPoolTaskExecutor.setMaxPoolSize(DEFAULT_EXECUTOR_MAX_POOL_SIZE);
        threadPoolTaskExecutor.setQueueCapacity(DEFAULT_EXECUTOR_QUEUE_SIZE);
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskExecutor.afterPropertiesSet();
        return new DelegatedTransactionalAsyncTaskExecutor(transactionManager, threadPoolTaskExecutor);
    }

    @Bean
    @Primary
    public TransactionTemplate transactionTemplate(final PlatformTransactionManager transactionManager) {
        return new TransactionTemplate(transactionManager);
    }

    /**
     * Returns {@link ParentAwareNamingStrategy} configuration
     *
     * @return {@link ParentAwareNamingStrategy} configuration
     */
    @Bean
    @ConditionalOnMissingBean(value = ObjectNamingStrategy.class, search = SearchStrategy.CURRENT)
    public ParentAwareNamingStrategy objectNamingStrategy() {
        final ParentAwareNamingStrategy namingStrategy = new ParentAwareNamingStrategy(new AnnotationJmxAttributeSource());
        namingStrategy.setDefaultDomain(DEFAULT_DOMAIN_NAME_PREFIX + UUID.randomUUID().toString());
        return namingStrategy;
    }

    /**
     * Returns jpa {@link Properties} configuration
     *
     * @return jpa {@link Properties} configuration
     */
    @Bean
    public Properties jpaProperties() {
        final Properties jpaProperties = new Properties();
        // connection properties
        //jpaProperties.put("hibernate.connection.provider_class", env.getRequiredProperty("sqoola.datasource.hibernate.connection.providerClass"));
        jpaProperties.put("hibernate.connection.pool_size", env.getRequiredProperty("sqoola.datasource.hibernate.connection.poolSize"));
        jpaProperties.put("hibernate.connection.autocommit", env.getRequiredProperty("sqoola.datasource.hibernate.connection.autocommit"));
        jpaProperties.put("hibernate.connection.release_mode", env.getRequiredProperty("sqoola.datasource.hibernate.connection.releaseMode"));
        jpaProperties.put("hibernate.connection.useUnicode", env.getRequiredProperty("sqoola.datasource.hibernate.connection.useUnicode"));
        jpaProperties.put("hibernate.connection.charSet", env.getRequiredProperty("sqoola.datasource.hibernate.connection.charSet"));
        jpaProperties.put("hibernate.connection.characterEncoding", env.getRequiredProperty("sqoola.datasource.hibernate.connection.characterEncoding"));

        // general properties
        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("sqoola.datasource.hibernate.dialect"));
        jpaProperties.put("hibernate.current_session_context_class", env.getRequiredProperty("sqoola.datasource.hibernate.currentSessionContextClass"));
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("sqoola.datasource.hibernate.hbm2ddl.auto"));
        jpaProperties.put("hibernate.hbm2ddl.import_files", env.getRequiredProperty("sqoola.datasource.hibernate.hbm2ddl.importFiles"));
        jpaProperties.put("hibernate.ejb.naming_strategy", env.getRequiredProperty("sqoola.datasource.hibernate.ejb.namingStrategy"));
        jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("sqoola.datasource.hibernate.showSql"));
        jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("sqoola.datasource.hibernate.formatSql"));
        jpaProperties.put("hibernate.use_sql_comments", env.getRequiredProperty("sqoola.datasource.hibernate.useSqlComments"));
        jpaProperties.put("hibernate.enable_lazy_load_no_trans", env.getRequiredProperty("sqoola.datasource.hibernate.enableLazyLoadNoTrans"));
        jpaProperties.put("hibernate.max_fetch_depth", env.getRequiredProperty("sqoola.datasource.hibernate.maxFetchDepth"));
        jpaProperties.put("hibernate.default_batch_fetch_size", env.getRequiredProperty("sqoola.datasource.hibernate.defaultBatchFetchSize"));
        jpaProperties.put("hibernate.generate_statistics", env.getRequiredProperty("sqoola.datasource.hibernate.generateStatistics"));
        jpaProperties.put("hibernate.globally_quoted_identifiers", env.getRequiredProperty("sqoola.datasource.hibernate.globallyQuotedIdentifiers"));
        jpaProperties.put("hibernate.id.new_generator_mappings", env.getRequiredProperty("sqoola.datasource.hibernate.id.newGeneratorMappings"));
        jpaProperties.put("hibernate.transaction.flush_before_completion", env.getRequiredProperty("sqoola.datasource.hibernate.transaction.flushBeforeCompletion"));
        jpaProperties.put("hibernate.transaction.auto_close_session", env.getRequiredProperty("sqoola.datasource.hibernate.transaction.autoCloseSession"));
        jpaProperties.put("hibernate.getString.merge.entity_copy_observer", env.getRequiredProperty("sqoola.datasource.hibernate.event.merge.entityCopyObserver"));
        jpaProperties.put("hibernate.bytecode.use_reflection_optimizer", env.getRequiredProperty("sqoola.datasource.hibernate.bytecode.useReflectionOptimizer"));
        jpaProperties.put("hibernate.temp.use_jdbc_metadata_defaults", env.getRequiredProperty("sqoola.datasource.hibernate.temp.useJdbcMetadataDefaults"));
        //jpaProperties.put("hibernate.multi_tenant_connection_provider", env.getRequiredProperty("sqoola.datasource.hibernate.multiTenantConnectionProvider"));
        //jpaProperties.put("hibernate.multiTenancy", env.getRequiredProperty("sqoola.datasource.hibernate.multiTenancy"));

        // cache properties
        //jpaProperties.put("hibernate.cache.ehcache.missing_cache_strategy", env.getRequiredProperty("sqoola.datasource.hibernate.cache.ehcache.missingCacheStrategy"));
        //jpaProperties.put("hibernate.cache.region.factory_class", env.getRequiredProperty("sqoola.datasource.hibernate.cache.region.factoryClass"));
        //jpaProperties.put("hibernate.cache.use_second_level_cache", env.getRequiredProperty("sqoola.datasource.hibernate.cache.useSecondLevelCache"));
        //jpaProperties.put("hibernate.cache.use_structured_entries", env.getRequiredProperty("sqoola.datasource.hibernate.cache.useStructuredEntries"));
        //jpaProperties.put("hibernate.cache.use_query_cache", env.getRequiredProperty("sqoola.datasource.hibernate.cache.useQueryCache"));
        //jpaProperties.put("net.sf.ehcache.configurationResourceName", env.getRequiredProperty("sqoola.datasource.hibernate.cache.configurationResourceName"));

        // search properties
        jpaProperties.put("hibernate.search.exclusive_index_use", env.getRequiredProperty("sqoola.datasource.hibernate.search.exclusiveIndexUse"));
        jpaProperties.put("hibernate.search.lucene_version", env.getRequiredProperty("sqoola.datasource.hibernate.search.luceneVersion"));
        //jpaProperties.put("hibernate.search.default.worker.execution", env.getRequiredProperty("sqoola.datasource.hibernate.search.default.worker.execution"));
        //jpaProperties.put("hibernate.search.default.directory_provider", env.getRequiredProperty("sqoola.datasource.hibernate.search.default.directoryProvider"));
        //jpaProperties.put("hibernate.search.default.indexBase", env.getRequiredProperty("sqoola.datasource.hibernate.search.default.indexBase"));
        //jpaProperties.put("hibernate.search.default.indexPath", env.getRequiredProperty("sqoola.datasource.hibernate.search.default.indexPath"));
        //jpaProperties.put("hibernate.search.default.chunk_size", env.getRequiredProperty("sqoola.datasource.hibernate.search.default.chunkSize"));
        //jpaProperties.put("hibernate.search.default.indexmanager", env.getRequiredProperty("sqoola.datasource.hibernate.search.default.indexmanager"));
        //jpaProperties.put("hibernate.search.default.metadata_cachename", env.getRequiredProperty("sqoola.datasource.hibernate.search.default.metadataCachename"));
        //jpaProperties.put("hibernate.search.default.data_cachename", env.getRequiredProperty("sqoola.datasource.hibernate.search.default.dataCachename"));
        //jpaProperties.put("hibernate.search.default.locking_cachename", env.getRequiredProperty("sqoola.datasource.hibernate.search.default.lockingCachename"));
        //jpaProperties.put("hibernate.search.default.batch.merge_factor", env.getRequiredProperty("sqoola.datasource.hibernate.search.default.batch.mergeFactor"));
        //jpaProperties.put("hibernate.search.default.batch.max_buffered_docs", env.getRequiredProperty("sqoola.datasource.hibernate.search.default.batch.maxBufferedDocs"));

        // fetch/batch properties
        jpaProperties.put("hibernate.jdbc.fetch_size", env.getRequiredProperty("sqoola.datasource.hibernate.jdbc.fetchSize"));
        jpaProperties.put("hibernate.jdbc.batch_size", env.getRequiredProperty("sqoola.datasource.hibernate.jdbc.batchSize"));
        jpaProperties.put("hibernate.jdbc.batch_versioned_data", env.getRequiredProperty("sqoola.datasource.hibernate.jdbc.batchVersionedData"));
        jpaProperties.put("hibernate.order_inserts", env.getRequiredProperty("sqoola.datasource.hibernate.orderInserts"));
        jpaProperties.put("hibernate.order_updates", env.getRequiredProperty("sqoola.datasource.hibernate.orderUpdates"));

        // connection pool properties
        jpaProperties.put("hibernate.c3p0.min_size", env.getRequiredProperty("sqoola.datasource.hibernate.c3p0.minSize"));
        jpaProperties.put("hibernate.c3p0.max_size", env.getRequiredProperty("sqoola.datasource.hibernate.c3p0.maxSize"));
        jpaProperties.put("hibernate.c3p0.timeout", env.getRequiredProperty("sqoola.datasource.hibernate.c3p0.timeout"));
        jpaProperties.put("hibernate.c3p0.max_statements", env.getRequiredProperty("sqoola.datasource.hibernate.c3p0.maxStatements"));
        jpaProperties.put("hibernate.c3p0.idle_test_period", env.getRequiredProperty("sqoola.datasource.hibernate.c3p0.idleTestPeriod"));

        // native properties
        jpaProperties.setProperty("org.hibernate.SQL", "true");
        jpaProperties.setProperty("org.hibernate.type", "true");
        return jpaProperties;
    }

    /**
     * Returns {@link JpaDialect} configuration
     *
     * @return {@link JpaDialect} configuration
     */
    @Bean
    public JpaDialect jpaDialect() {
        return new DefaultJpaDialect();
    }

    /**
     * Returns {@link PersistenceExceptionTranslationPostProcessor} configuration
     *
     * @return {@link PersistenceExceptionTranslationPostProcessor} configuration
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    /**
     * Returns {@link PersistenceUnitManager} configuration
     *
     * @return {@link PersistenceUnitManager} configuration
     */
    @Bean
    public PersistenceUnitManager persistenceUnitManager() {
        final DefaultPersistenceUnitManager manager = new DefaultPersistenceUnitManager();
        manager.setDefaultDataSource(defaultDataSource());
        manager.setDefaultPersistenceUnitName(DEFAULT_PERSISTENCE_UNIT_NAME);
        return manager;
    }

    /**
     * Returns {@link BeanPostProcessor} configuration
     *
     * @return {@link BeanPostProcessor} configuration
     */
    @Bean
    public BeanPostProcessor postProcessor() {
        final PersistenceAnnotationBeanPostProcessor postProcessor = new PersistenceAnnotationBeanPostProcessor();
        postProcessor.setDefaultPersistenceUnitName(DEFAULT_PERSISTENCE_UNIT_NAME);
        return postProcessor;
    }

    /**
     * Returns {@link LocalSessionFactoryBean} configuration
     *
     * @return {@link LocalSessionFactoryBean} configuration
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(defaultDataSource());
        sessionFactory.setPackagesToScan(DEFAULT_REPOSITORY_PACKAGE, DEFAULT_MODEL_PACKAGE);
        sessionFactory.setHibernateProperties(jpaProperties());
        return sessionFactory;
    }
}
