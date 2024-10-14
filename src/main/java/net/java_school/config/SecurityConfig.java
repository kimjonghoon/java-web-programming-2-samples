package net.java_school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
				.requestMatchers(HttpMethod.GET, "/users/welcome").permitAll()
				.requestMatchers(HttpMethod.POST, "/users/signUp").permitAll()
				.requestMatchers(HttpMethod.GET, "/users/signUp").permitAll()
				.requestMatchers(HttpMethod.GET, "/en/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/ko/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/").permitAll()
				.requestMatchers(HttpMethod.GET, "/static/**").permitAll()
				.requestMatchers(HttpMethod.GET, "/favicon.ico").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin(form -> form.loginPage("/users/login").permitAll().loginProcessingUrl("/login"))
			.logout((logout) -> logout.logoutSuccessUrl("/"))
			.httpBasic(withDefaults());
		return http.build();
	}
}
