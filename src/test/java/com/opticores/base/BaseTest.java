package com.opticores.base;

import static org.junit.Assert.assertNotNull;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opticores.config.DatabaseConfiguration;

/** Base Test class for creating unit test cases
 * 
 * @author anubhav
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DatabaseConfiguration.class})
public class BaseTest {
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Test
	public void sessionFactoryObjectShouldNotBeNull(){
		assertNotNull(sessionFactory);
		
	}
	

}
