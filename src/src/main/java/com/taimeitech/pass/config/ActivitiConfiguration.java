//package com.taimeitech.pass.config;
//
//import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//@Configuration
//public class ActivitiConfiguration extends AbstractProcessEngineAutoConfiguration {
//
//
//
//    public DataSource activitiDataSource() {
//        return DataSourceBuilder
//                .create()
//                .url("jdbc:mysql://192.168.1.207:3306/activiti01?useUnicode=true&characterEncoding=utf8&useSSL=false&allowMultiQueries=true")
//                .username("root")
//                .password("tmmysql123")
//                .driverClassName("com.mysql.jdbc.Driver")
//                .build();
//    }
//
//    public JpaTransactionManager getTransactionManager(){
//        JpaTransactionManager factory = new JpaTransactionManager();
//        factory.setEntityManagerFactory(getEntityManagerFactory());
//        return  factory;
//    }
//
//
//    public EntityManagerFactory getEntityManagerFactory() {
//        DataSource ds = activitiDataSource();
//        LocalContainerEntityManagerFactoryBean beanFactory =new LocalContainerEntityManagerFactoryBean();
//        beanFactory.setDataSource(ds);
//        beanFactory.setPackagesToScan("com.taimeitech.pass.entity");
//        return beanFactory.getObject();
//    }
//
//    @Bean
//    public org.activiti.spring.ProcessEngineFactoryBean getProcessEngineFactoryBean(){
//        org.activiti.spring.ProcessEngineFactoryBean factoryBean = new org.activiti.spring.ProcessEngineFactoryBean();
//        factoryBean.setProcessEngineConfiguration(getSpringProcessEngineFactoryBean());
//        return factoryBean;
//    }
//
//    @Bean
//    public org.activiti.spring.SpringProcessEngineConfiguration getSpringProcessEngineFactoryBean(){
//        org.activiti.spring.SpringProcessEngineConfiguration  configuration =new org.activiti.spring.SpringProcessEngineConfiguration();
//        configuration.setDataSource(activitiDataSource());
//        configuration.setTransactionManager(getTransactionManager());
//        return configuration;
//    }
//}