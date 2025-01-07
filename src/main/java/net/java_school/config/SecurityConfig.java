package net.java_school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.jdbcAuthentication().dataSource(dataSource).passwordEncoder(this.passwordEncoder());
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorize) -> authorize
				.requestMatchers(HttpMethod.DELETE, "/boards/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PATCH, "/boards/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/boards/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/boards/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/boards/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PATCH, "/users/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/users/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/users/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/admin/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET, "/board/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/spring-security/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/thymeleaf/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/user/welcome").permitAll()
				.requestMatchers(HttpMethod.POST, "/user/signUp").permitAll()
				.requestMatchers(HttpMethod.GET, "/user/signUp").permitAll()
				.requestMatchers(HttpMethod.GET, "/en/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/ko/**").permitAll()
				//.requestMatchers(HttpMethod.POST, "/").permitAll() //테스트
				.requestMatchers(HttpMethod.GET, "/").permitAll()
				.requestMatchers(HttpMethod.GET, "/static/**").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin(form -> form.loginPage("/user/login").permitAll().loginProcessingUrl("/login"))
			.logout((logout) -> logout.logoutSuccessUrl("/"))
			.httpBasic(withDefaults()).csrf(AbstractHttpConfigurer::disable);
			
		return http.build();
	}
}