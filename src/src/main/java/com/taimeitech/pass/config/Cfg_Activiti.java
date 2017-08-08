package com.taimeitech.pass.config;

import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;


import org.apache.tools.ant.taskdefs.SQLExec;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@Configuration
public class Cfg_Activiti {

    @Bean
    @Primary
   public javax.sql.DataSource activitiDataSource() {
        return DataSourceBuilder
                .create()
                .url("jdbc:mysql://192.168.1.207:3306/activiti01?useUnicode=true&characterEncoding=utf8&useSSL=false&allowMultiQueries=true")
                .username("root")
                .password("tmmysql123")
                .driverClassName("com.mysql.jdbc.Driver")
                .build();
    }

    @Bean
    public EntityManagerFactory getEntityManagerFactory(DataSource dataSource) {
        DataSource ds = dataSource;
        LocalContainerEntityManagerFactoryBean beanFactory = new LocalContainerEntityManagerFactoryBean();
        beanFactory.setDataSource(ds);
        beanFactory.setPackagesToScan("com.taimeitech.pass.entity");
        return beanFactory.getObject();
    }

    @Bean
    public PlatformTransactionManager getTtransactionManager(DataSource dataSource){
        org.springframework.jdbc.datasource.DataSourceTransactionManager dataSourceTransactionManager = new org.springframework.jdbc.datasource.DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }

    //流程配置，与spring整合采用SpringProcessEngineConfiguration这个实现
    @Bean
    public ProcessEngineConfiguration processEngineConfiguration(DataSource dataSource, PlatformTransactionManager transactionManager, EntityManagerFactory entityManagerFactory){
        SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
        processEngineConfiguration.setDataSource(dataSource);
        processEngineConfiguration.setJpaEntityManagerFactory(entityManagerFactory);
        processEngineConfiguration.setTransactionManager(transactionManager);
        return processEngineConfiguration;
    }

    //流程引擎，与spring整合使用factoryBean
    @Bean
    public ProcessEngineFactoryBean processEngine(ProcessEngineConfiguration processEngineConfiguration){
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
        return processEngineFactoryBean;
    }

    //八大接口
    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine){
        return processEngine.getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine){
        return processEngine.getRuntimeService();
    }

    @Bean
    public TaskService taskService(ProcessEngine processEngine){
        return processEngine.getTaskService();
    }

    @Bean
    public HistoryService historyService(ProcessEngine processEngine){
        return processEngine.getHistoryService();
    }

    @Bean
    public FormService formService(ProcessEngine processEngine){
        return processEngine.getFormService();
    }

    @Bean
    public IdentityService identityService(ProcessEngine processEngine){
        return processEngine.getIdentityService();
    }

    @Bean
    public ManagementService managementService(ProcessEngine processEngine){
        return processEngine.getManagementService();
    }

    @Bean
    public DynamicBpmnService dynamicBpmnService(ProcessEngine processEngine){
        return processEngine.getDynamicBpmnService();
    }

    //八大接口 end
}
