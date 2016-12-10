package com.opticores.common;

/** A simple class holding all the application related constants
 *  avoiding hard coding in other application components.
 *  
 *  Referred from {@link DatabaseConfiguration}
 * 
 * 
 * @author anubhav
 *
 */
public class ApplicationConstants {
	
	public static final String PROPERTY_DATABASECONFIG="classpath:databaseconfig.properties";
	
	public static final String PROPERTY_HIBERNATE_DAILECT="hibernate.dialect";
	
	public static final String PROPERTY_JDBC_CONNECTION_URL="jdbc.url";
	
	public static final String PROPERTY_JDBC_USERNAME="jdbc.username";
	
	public static final String PROPERTY_JDBC_PASSWORD="jdbc.password";
	
	public static final String PROPERTY_JDBC_DRIVER="jdbc.driverClassName";
	
	public static final String PROFILE_DEV = "DEV";
	public static final String PROFILE_INTEGRATION = "INTEGRATION";

}
