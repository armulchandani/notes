package com.opticores.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/** The basic class acting as a main configuration class looked upon by 
 *  the spring framework and have following responsibilities:
 *  
 *   1. Enable Web MVC
 *   2. Add automatic component scan specifying the base package
 * 
 * @author anubhav
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.opticores")
@Import({DatabaseConfiguration.class})
public class ApplicationConfiguration {

}
