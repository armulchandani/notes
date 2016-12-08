package com.opticores.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static com.opticores.common.ApplicationConstants.*;


/** A base class responsible for configuring:
 * 
 * 1. Datasource
 * 2. Enabling transaction management
 * 3. Registering a LocalSessionFactoryBean which internally configures hibernate session factory 
 * 
 * @author anubhav
 *
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = { PROPERTY_DATABASECONFIG })
public class DatabaseConfiguration {
	
	@Autowired
    private Environment environment;
	
	
	@Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.opticores.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
	
	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty(PROPERTY_JDBC_DRIVER));
        dataSource.setUrl(environment.getRequiredProperty(PROPERTY_JDBC_CONNECTION_URL));
        dataSource.setUsername(environment.getRequiredProperty(PROPERTY_JDBC_USERNAME));
        dataSource.setPassword(environment.getRequiredProperty(PROPERTY_JDBC_PASSWORD));
        return dataSource;
    }
	
	
	private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty(PROPERTY_HIBERNATE_DAILECT));
        return properties;        
    }
	
	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }

}
