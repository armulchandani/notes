package com.opticores.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

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
public class ApplicationInitializer implements WebApplicationInitializer{

	public void onStartup(ServletContext servletContext)
			throws ServletException {
		
		// Create an instance of annotation based web application context
		AnnotationConfigWebApplicationContext context= new AnnotationConfigWebApplicationContext();
		context.register(ApplicationConfiguration.class);
		context.setServletContext(servletContext);
		
		// Registering Spring Dispatcher servlet
		ServletRegistration.Dynamic dispatcher= servletContext.addServlet("dispatcher", new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
	}

}
