package com.opticores.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/** The base application initializer class conforming to Servlet 3.0 specification 
 *  i.e XML less application.
 *  
 *  This class holds following responsibilities:
 *  1. Creates a new object of AnnotationConfigWebApplicationContext
 *  2. Registering the main configuration class {@link ApplicationConfiguration}
 *  3. Registering spring framework 'DispatcherServlet'
 * 
 * 
 * @author anubhav
 *
 */
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[]{ApplicationConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[]{"/"};
	} 

	

}
