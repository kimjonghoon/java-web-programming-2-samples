package net.java_school.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

import java.util.Properties;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.validation.Validator;

@Configuration
@EnableWebMvc
@MapperScan("net.java_school.mapper")
@ComponentScan("net.java_school.controller,net.java_school.user,net.java_school.board,net.java_school.blog")
public class MvcWebConfig implements WebMvcConfigurer {

	@Autowired
	private ApplicationContext applicationContext;
/*	
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseTrailingSlashMatch(true);
	}
*/	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.
			addResourceHandler("/static/**").
			addResourceLocations("/static/");
	}
	
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
	    return new SpringSecurityDialect();
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("WEB-INF/messages");
		messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(10);
		return messageSource;
	}
	
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(this.applicationContext);
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCacheable(false);
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		templateEngine.setTemplateEngineMessageSource(messageSource());
		templateEngine.addDialect(springSecurityDialect());
		return templateEngine;
	}

	@Bean
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
/*
https://mvnrepository.com/artifact/org.slf4j/slf4j-api
https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
https://mvnrepository.com/artifact/com.oracle.database.jdbc/ojdbc11
https://mvnrepository.com/artifact/org.aspectj/aspectjweaver
https://mvnrepository.com/artifact/jakarta.servlet/jakarta.servlet-api
https://mvnrepository.com/artifact/jakarta.servlet.jsp.jstl/jakarta.servlet.jsp.jstl-api
https://mvnrepository.com/artifact/org.glassfish.web/jakarta.servlet.jsp.jstl
https://mvnrepository.com/artifact/org.apache.commons/commons-dbcp2
https://mvnrepository.com/artifact/org.mybatis/mybatis
https://mvnrepository.com/artifact/org.mybatis/mybatis-spring
https://mvnrepository.com/artifact/commons-logging/commons-logging
https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api
https://mvnrepository.com/artifact/org.hibernate.validator/hibernate-validator
https://mvnrepository.com/artifact/org.thymeleaf/thymeleaf-spring6
https://mvnrepository.com/artifact/org.eclipse.jetty/jetty-maven-plugin
https://mvnrepository.com/artifact/com.mysql/mysql-connector-j
https://mvnrepository.com/artifact/tools.jackson.core/jackson-databind
*/
		//Global Variables
		Map<String, Object> staticVariables = new HashMap<>();
		staticVariables.put("java", "25"); //Java Version
		staticVariables.put("spring", "7.0.7"); //Spring Framework Version
		staticVariables.put("thymeleafSpring6", "3.1.3.RELEASE"); //Thymeleaf Spring6 Version
		staticVariables.put("jettyMavenPlugin", "11.0.26"); //jetty-maven-plugin Version
		staticVariables.put("springSecurityVersion", "7.0.4"); //Spring Security Version
		staticVariables.put("oracleJdbcDriver", "23.26.1.0.0"); //ojdbc11 Version
		staticVariables.put("dbcp2", "2.14.0"); //commons-dbcp2 Version
		staticVariables.put("thymeleafExtraSpringSecurity6", "3.1.3.RELEASE"); //Thymeleaf-Extra-Spring-Security6 Version
		staticVariables.put("ojdbc11Version", "24.26.1.0.0"); //ojdbc11 Version
		staticVariables.put("aspectjweaverVersion", "1.9.25.1"); //aspectweaver Version
		staticVariables.put("servletVersion", "6.1.0"); //servlet Version
		staticVariables.put("jstlApiVersion", "3.0.2"); //jstl-api Version
		staticVariables.put("jstlVersion", "3.0.1"); //jstl Version
		staticVariables.put("commonsDbcp2Version", "2.14.0"); //common-dbcp2 Version
		staticVariables.put("mybatisVersion", "3.5.19"); //mybatis Version
		staticVariables.put("mybatisSpringVersion", "4.0.0"); //mybatis-spring Version
		staticVariables.put("commonsLoggingVersion", "1.3.6"); //commons-logging Version
		staticVariables.put("log4jVersion", "2.25.4"); //log4j Version
		staticVariables.put("hibernateValidatorVersion", "9.1.0.Final"); //hibernate-validator Version
		staticVariables.put("thymeleafSpring6Version", "3.1.3.RELEASE"); //thymeleaf-spring6 Version
		staticVariables.put("mysqlJdbcDriverVersion", "9.6.0"); //mysql jdbc driver Version
		staticVariables.put("jacksonDatabindVersion", "3.1.2"); //jackson-databind Version
		viewResolver.setStaticVariables(staticVariables);
		return viewResolver;
	}
	
	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("web");
		dataSource.setPassword("programming2");
		dataSource.setMaxTotal(100);
		dataSource.setPoolPreparedStatements(true);
		dataSource.setDefaultAutoCommit(true);
		dataSource.setValidationQuery("SELECT 1 FROM DUAL");
		
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionBean = new SqlSessionFactoryBean();
		sessionBean.setDataSource(dataSource());
		return sessionBean.getObject();
	}
	
	@Bean
	public SimpleMappingExceptionResolver exceptionResolver() {
		SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
		Properties properties = new Properties();
		properties.put("org.springframework.web.servlet.NoHandlerFoundException", "error/404");
		resolver.setExceptionMappings(properties);
		resolver.setDefaultErrorView("error/error");
		return resolver;
	}
}
