package com.muhardin.endy.belajar.hibernate;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration 
@PropertySource("classpath:app.properties")
@ComponentScan(basePackages = "com.muhardin.endy")
@EnableTransactionManagement
public class AppConfig {
    
    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;
    @Value("${hibernate.dialect}")
    private String hibernateDialect;
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    public DataSource dataSource(){
        org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
        
        ds.setDriverClassName(jdbcDriver);
        ds.setUrl(jdbcUrl);
        ds.setUsername(jdbcUsername);
        ds.setPassword(jdbcPassword);
        
        ds.setInitialSize(1);
        ds.setMaxActive(4);
        
        return ds;
    }
    
    @Bean
    public Properties hibernateProperties(){
        Properties prop = new Properties();
        
        prop.put("hibernate.dialect", hibernateDialect);
        prop.put("hibernate.show_sql", true);
        prop.put("hibernate.format_sql", true);
        prop.put("hibernate.use_sql_comments", true);
        prop.put("hibernate.hbm2ddl.auto", "update");
        
        return prop;
    }
    
    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
        
        sfb.setDataSource(dataSource());
        sfb.setPackagesToScan("com.muhardin.endy.belajar.hibernate.entity");
        sfb.setHibernateProperties(hibernateProperties());
        
        return sfb;
    }
    
    @Bean
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager txManager = new HibernateTransactionManager(sessionFactory().getObject());
        return txManager;
    }
}
