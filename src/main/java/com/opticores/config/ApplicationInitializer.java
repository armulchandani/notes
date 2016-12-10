package com.opticores.config;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import static com.opticores.common.ApplicationConstants.PROFILE_DEV;

/** The base application initializer class conforming to Servlet 3.0 specification 
 *  i.e XML less application.
 *  
 *  This class holds following responsibilities:
 *  1. Creates a new object of AnnotationConfigWebApplicationContext
 *  2. Registering the main configuration class {@link ApplicationConfiguration}
 *  3. Registering spring framework 'DispatcherServlet'
 *  4. Setting up the active profile. Profiles are configured in {@link DatabaseConfiguration} for datasource.
 * 
 * 
 * @author anubhav
 *
 */
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	


	@Override
	protected WebApplicationContext createRootApplicationContext() {
		
		WebApplicationContext context=super.createRootApplicationContext();
		((ConfigurableEnvironment)context.getEnvironment()).setActiveProfiles(PROFILE_DEV);
		
		return context;
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{ApplicationConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	} 

	

}
