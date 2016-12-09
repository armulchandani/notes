package com.opticores.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Very simple configuration class responsible for configuring the way an
 * application resources need to be secure
 * 
 * 
 * @author anubhav
 *
 */
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfiguration extends
		WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	@Qualifier(value = "basicUserDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers("/api/notes/**")
				.authenticated().and().csrf().disable();
	}

}