# Using Layout of Thymeleaf in Spring MVC

### How to run
mvn jetty:run


### Add dependency

	&lt;!-- https://mvnrepository.com/artifact/org.thymeleaf/thymeleaf-spring6 --&gt;
	&lt;dependency&gt;
		&lt;groupId&gt;org.thymeleaf&lt;/groupId&gt;
		&lt;artifactId&gt;thymeleaf-spring6&lt;/artifactId&gt;
		&lt;version&gt;3.1.2.RELEASE&lt;/version&gt;
	&lt;/dependency&gt;


### Spring MVC Configuration

	package net.java_school.spring.config;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.context.ApplicationContext;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.ComponentScan;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.context.support.ResourceBundleMessageSource;
	import org.springframework.web.servlet.config.annotation.EnableWebMvc;
	import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
	import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
	import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
	import org.thymeleaf.spring6.SpringTemplateEngine;
	import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
	import org.thymeleaf.spring6.view.ThymeleafViewResolver;

	@Configuration
	@EnableWebMvc
	@ComponentScan("net.java_school.blog")
	public class MvcWebConfig implements WebMvcConfigurer {

		@Autowired
		private ApplicationContext applicationContext;

		@Bean
		public ResourceBundleMessageSource messageSource() {
			ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
			messageSource.setBasename("messages");
			messageSource.setDefaultEncoding("UTF-8");//If the encoding of the Java property file is UTF-8
			return messageSource;
		}

		@Override
		public void addResourceHandlers(final ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/static/**").addResourceLocations("/static/");
		}
		
		@Bean
		public SpringResourceTemplateResolver templateResolver() {
			SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
			templateResolver.setApplicationContext(applicationContext);
			templateResolver.setPrefix("/WEB-INF/views/");
			templateResolver.setSuffix(".html");
			<strong>templateResolver.setCacheable(false);</strong>//Development Mode
			return templateResolver;
		}

		@Bean
		public SpringTemplateEngine templateEngine() {
			SpringTemplateEngine templateEngine = new SpringTemplateEngine();
			templateEngine.setTemplateResolver(templateResolver());
			templateEngine.setEnableSpringELCompiler(true);
			return templateEngine;
		}

		@Override
		public void configureViewResolvers(ViewResolverRegistry registry) {
			ThymeleafViewResolver resolver = new ThymeleafViewResolver();
			<strong>resolver.setCharacterEncoding("UTF-8");</strong>
			resolver.setTemplateEngine(templateEngine());
			registry.viewResolver(resolver);
		}

	}


	package net.java_school.spring.config;

	import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

	public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

		@Override
		protected Class&lt;?&gt;[] getRootConfigClasses() {
			return null;
		}

		@Override
		protected Class&lt;?&gt;[] getServletConfigClasses() {
			return new Class[] { MvcWebConfig.class };
		}

		@Override
		protected String[] getServletMappings() {
			return new String[] { "/" };
		}

	}

