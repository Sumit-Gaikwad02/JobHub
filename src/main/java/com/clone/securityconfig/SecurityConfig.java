package com.clone.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.clone.jwt.JwtAuthenticationEntryPoint;
import com.clone.jwt.JwtAuthenticationFilter;

/***** Author:Sumit *****/
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint point;

	@Autowired
	private JwtAuthenticationFilter filter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/users/register","/users/emailOtp", "/users/forgot-passwordOTP", "/users/reset-password", "/login-user").permitAll()
				.antMatchers("/users/**", "/postEvent/**", "/posts/**", "/postPhoto/**", "/Followers/**", "/users/searchBar",
						"/connections/**", "/groupPost/**", "/Education/**", "/Experience/**", "/groupPost/**", "/GroupPostPhoto/**", "/groupRequest/**", 
						"/group/**", "/message/**", "/notification/**", "/page/**", "/pageFollow/**", "/pagePost/**", "/voteOption/**", "/ResumeBuilder/**", "/Skills/**")
				.authenticated().anyRequest().permitAll().and().formLogin().disable().logout().logoutUrl("/logout")
				.logoutSuccessUrl("/logout-success").deleteCookies("JWT_TOKEN").and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).maximumSessions(1)
				.maxSessionsPreventsLogin(false).sessionRegistry(sessionRegistry()).and().and().exceptionHandling()
				.authenticationEntryPoint(point).accessDeniedPage("/access-denied");

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedOriginPattern("*");
		corsConfiguration.addAllowedHeader("Authorization");
		corsConfiguration.addAllowedHeader("Content-Type");
		corsConfiguration.addAllowedHeader("Accept");
		corsConfiguration.addAllowedMethod("POST");
		corsConfiguration.addAllowedMethod("GET");
		corsConfiguration.addAllowedMethod("DELETE");
		corsConfiguration.addAllowedMethod("PUT");
		corsConfiguration.addAllowedMethod("OPTIONS");
		corsConfiguration.setMaxAge(3600L);
		source.registerCorsConfiguration("/**", corsConfiguration);

		CorsFilter corsFilter = new CorsFilter(source);
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(corsFilter);
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
