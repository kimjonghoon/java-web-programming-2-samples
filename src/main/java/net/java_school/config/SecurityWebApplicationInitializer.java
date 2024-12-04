package net.java_school.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
 
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	@Override
	protected boolean enableHttpSessionEventPublisher() {
		return true;
	}
}