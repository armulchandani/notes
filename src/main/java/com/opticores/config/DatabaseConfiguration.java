package com.opticores.config;

import static com.opticores.common.ApplicationConstants.*;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.opticores.dao.HibernateDAO;

/**
 * A base class responsible for configuring:
 * 
 * 1. Datasource 
 * 2. Enabling transaction management 
 * 3. Registering a LocalSessionFactoryBean which internally configures hibernate session factory
 * 
 * There are two ways a datasource is configured here:
 * 
 * 1. Container managed datasource looked up by JNDI( for SIT, UAT & PRODUCTION
 * environment) 
 * 
 * 2. By reading up configuration parameters from databaseconfig.properties (suitable in development environment where UNIT
 * testing is required)
 * 
 * Active profile is setup in {@link ApplicationInitializer}
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

	/**
	 * The function creates a spring framework provided instance of
	 * LocalSessionFactoryBean which internally builds hibernate Session
	 * factory.
	 * 
	 * The framework also registers it in the application context.
	 * 
	 * Referred from {@link HibernateDAO}
	 * 
	 * 
	 * @return an instance of session factory
	 */
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory
				.setPackagesToScan(new String[] { "com.opticores.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	/**
	 * Configures a container provided datasource and register it as spring bean
	 * 
	 * Only available if the active profile is INTEGRATION
	 * 
	 * @return an instance of Datasource
	 */
	@Bean(name = "dataSource")
	@Profile(PROFILE_INTEGRATION)
	public DataSource dataSource() {
		JndiDataSourceLookup jndidataSourceLookup = new JndiDataSourceLookup();
		DataSource dataSource = jndidataSourceLookup
				.getDataSource("jdbc/notes");
		return dataSource;
	}

	/**
	 * Creates a datasource object configured with necessary properties read from
	 * a property file databaseconfig.properties located inside src/main/resources
	 * 
	 * Only available if the active profile is DEV
	 * 
	 * @return an instance of Datasource
	 */
	@Bean(name = "dataSource")
	@Profile(PROFILE_DEV)
	public DataSource devDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment
				.getRequiredProperty(PROPERTY_JDBC_DRIVER));
		dataSource.setUrl(environment
				.getRequiredProperty(PROPERTY_JDBC_CONNECTION_URL));
		dataSource.setUsername(environment
				.getRequiredProperty(PROPERTY_JDBC_USERNAME));
		dataSource.setPassword(environment
				.getRequiredProperty(PROPERTY_JDBC_PASSWORD));
		return dataSource;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect",
				environment.getRequiredProperty(PROPERTY_HIBERNATE_DAILECT));
		return properties;
	}

	/** Configures a transaction manager and registers it as a spring bean
	 * 
	 * 
	 * @param an insatnce of session factory
	 * @return
	 */
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}

}
