package com.opticores.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opticores.model.ErrorMessage;

/**
 * A custom authentication entry point to provide more control in a way
 * authentication failure error message is handled.
 * 
 * This is registered inside {@link ApplicationSecurityConfiguration}}
 * 
 * @author anubhav
 *
 */
@Component
public class ApplicationAuthenticationEntry implements AuthenticationEntryPoint {

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setErrorCode("1401");
		errorMessage
				.setMessage("Authentication required to access this resource");

		objectMapper.writer().writeValue(response.getOutputStream(),
				errorMessage);

	}

}
