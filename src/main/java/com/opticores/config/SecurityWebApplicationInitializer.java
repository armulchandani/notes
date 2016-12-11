package com.opticores.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * This is an important class, looked upon by Spring framework to create and
 * register an instance of {@link DelegatingFilterProxy} from method
 * {@link #onStartup(javax.servlet.ServletContext)}
 * 
 * 
 * {@link DelegatingFilterProxy} and other filters in chain intercepts all the
 * requests URIS as configured inside method {@link #configure(HttpSecurity)} of
 * class {@link ApplicationSecurityConfiguration}
 * 
 * 
 * @author anubhav
 *
 */
public class SecurityWebApplicationInitializer extends
		AbstractSecurityWebApplicationInitializer {

}
