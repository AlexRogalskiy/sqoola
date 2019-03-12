//package com.wildbeeslabs.sensiblemetrics.sqoola.common.configuration;
//
//import ...
//
//@Configuration
//@ComponentScan(basePackages = "org.javers.spring.repository")
//@EnableTransactionManagement
//@EnableAspectJAutoProxy
//@EnableJpaRepositories({"org.javers.spring.repository"})
//public class JaversSpringJpaApplicationConfig {
//
//    //.. JaVers setup ..
//
//    /**
//     * Creates JaVers instance with {@link JaversSqlRepository}
//     */
//    @Bean
//    public Javers javers(PlatformTransactionManager txManager) {
//        JaversSqlRepository sqlRepository = SqlRepositoryBuilder
//                .sqlRepository()
//                .withConnectionProvider(jpaConnectionProvider())
//                .withDialect(DialectName.H2)
//                .build();
//
//        return TransactionalJaversBuilder
//                .javers()
//                .withTxManager(txManager)
//                .withObjectAccessHook(new HibernateUnproxyObjectAccessHook())
//                .registerJaversRepository(sqlRepository)
//                .build();
//    }
//
//    /**
//     * Enables auto-audit aspect for ordinary repositories.<br/>
//     *
//     * Use {@link org.javers.spring.annotation.JaversAuditable}
//     * to mark data writing methods that you want to audit.
//     */
//    @Bean
//    public JaversAuditableAspect javersAuditable(Javers javers) {
//        return new JaversAuditableAspect(javers, authorProvider(), commitPropertiesProvider());
//    }
//
//    /**
//     * Enables auto-audit aspect for Spring Data repositories. <br/>
//     *
//     * Use {@link org.javers.spring.annotation.JaversSpringDataAuditable}
//     * to annotate CrudRepository, PagingAndSortingRepository or JpaRepository
//     * you want to audit.
//     */
//    @Bean
//    public JaversSpringDataJpaAuditableRepositoryAspect javersSpringDataAspect(Javers javers) {
//        return new JaversSpringDataJpaAuditableRepositoryAspect(javers, authorProvider(),
//            commitPropertiesProvider());
//    }
//
//
//@Bean
//public JaversSpringDataJpaAuditableRepositoryAspect javersSpringDataAuditableAspect(Javers javers) {
//    return new JaversSpringDataJpaAuditableRepositoryAspect(javers, authorProvider(),
//    commitPropertiesProvider());
//    }
//
//@Bean
//public JaversSpringDataAuditableRepositoryAspect javersSpringDataAuditableAspect() {
//    return new JaversSpringDataAuditableRepositoryAspect(
//    javers(), authorProvider(), commitPropertiesProvider());
//    }
//
//    /**
//     * Required by auto-audit aspect. <br/><br/>
//     *
//     * Creates {@link SpringSecurityAuthorProvider} instance,
//     * suitable when using Spring Security
//     */
//    @Bean
//    public AuthorProvider authorProvider() {
//        return new SpringSecurityAuthorProvider();
//    }
//
//    /**
//     * Optional for auto-audit aspect. <br/>
//     * @see CommitPropertiesProvider
//     */
//    @Bean
//    public CommitPropertiesProvider commitPropertiesProvider() {
//        return () -> ImmutableMap.of("key", "ok");
//    }
//
//    /**
//     * Integrates {@link JaversSqlRepository} with Spring {@link JpaTransactionManager}
//     */
//    @Bean
//    public ConnectionProvider jpaConnectionProvider() {
//        return new JpaHibernateConnectionProvider();
//    }
//    //.. EOF JaVers setup ..
//
//
//    //.. Spring-JPA-Hibernate setup ..
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource());
//        em.setPackagesToScan("org.javers.spring.model");
//
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        em.setJpaProperties(additionalProperties());
//
//        return em;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(emf);
//
//        return transactionManager;
//    }
//
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
//        return new PersistenceExceptionTranslationPostProcessor();
//    }
//
//    @Bean
//    public DataSource dataSource(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.h2.Driver");
//        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
//        return dataSource;
//    }
//
//    Properties additionalProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.hbm2ddl.auto", "create");
//        properties.setProperty("hibernate.connection.autocommit", "false");
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//        return properties;
//    }
//    //.. EOF Spring-JPA-Hibernate setup ..
//}
//Spring configuration example for MongoDB
//Here is a working example of Spring Application Context with JaVers instance, JaVers auto-audit aspect and Spring Data MongoDB.
//
//package org.javers.spring.example;
//
//import ...
//
//@Configuration
//@ComponentScan(basePackages = "org.javers.spring.repository")
//@EnableMongoRepositories({"org.javers.spring.repository"})
//@EnableAspectJAutoProxy
//public class JaversSpringMongoApplicationConfig {
//    private static final String DATABASE_NAME = "mydatabase";
//
//    /**
//     * Creates JaVers instance backed by {@link MongoRepository}
//     */
//    @Bean
//    public Javers javers() {
//        MongoRepository javersMongoRepository =
//                new MongoRepository(mongo().getDatabase(DATABASE_NAME));
//
//        return JaversBuilder.javers()
//                .registerJaversRepository(javersMongoRepository)
//                .build();
//    }
//
//    /**
//     * MongoDB setup
//     */
//    @Bean
//    public MongoClient mongo() {
//        return new MongoClient();
//    }
//
//    /**
//     * required by Spring Data MongoDB
//     */
//    @Bean
//    public MongoTemplate mongoTemplate() throws Exception {
//        return new MongoTemplate(mongo(), DATABASE_NAME);
//    }
//
//    /**
//     * Enables auto-audit aspect for ordinary repositories.<br/>
//     *
//     * Use {@link org.javers.spring.annotation.JaversAuditable}
//     * to mark data writing methods that you want to audit.
//     */
//    @Bean
//    public JaversAuditableAspect javersAuditableAspect() {
//        return new JaversAuditableAspect(javers(), authorProvider(), commitPropertiesProvider());
//    }
//
//    /**
//     * Enables auto-audit aspect for Spring Data repositories. <br/>
//     *
//     * Use {@link org.javers.spring.annotation.JaversSpringDataAuditable}
//     * to annotate CrudRepositories you want to audit.
//     */
//    @Bean
//    public JaversSpringDataAuditableRepositoryAspect javersSpringDataAuditableAspect() {
//        return new JaversSpringDataAuditableRepositoryAspect(javers(), authorProvider(),
//                commitPropertiesProvider());
//    }
//
//    /**
//     * Required by auto-audit aspect. <br/><br/>
//     *
//     * Creates {@link SpringSecurityAuthorProvider} instance,
//     * suitable when using Spring Security
//     */
//    @Bean
//    public AuthorProvider authorProvider() {
//        return new SpringSecurityAuthorProvider();
//    }
//
//    /**
//     * Optional for auto-audit aspect. <br/>
//     * @see CommitPropertiesProvider
//     */
//    @Bean
//    public CommitPropertiesProvider commitPropertiesProvider() {
//        return () -> ImmutableMap.of("key", "ok");
//    }
//}
