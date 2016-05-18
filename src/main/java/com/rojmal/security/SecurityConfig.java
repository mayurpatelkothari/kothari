package com.rojmal.security;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	@Autowired
	UserDetailsService userDetailsService;
	
	@Inject
    private CustomAuthenticationSuccessHandler success;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(userDetailsService);
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
            .anyRequest().permitAll()
            .and()
        .csrf()
            .disable()
        .formLogin()
            .loginPage("/login")
            .failureUrl("/login?error")
            .successHandler(success)
            .permitAll()
            .and()
        .logout()
            .permitAll()
            .and()
        .exceptionHandling()
        	.accessDeniedPage("/data/login-error");;
    }

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@PostConstruct
	public void postConstruct() {
		SecurityContextHolderStrategy statergy = SecurityContextHolder.getContextHolderStrategy();
		logger.debug("Current strategy is : {}", statergy);
		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
		statergy = SecurityContextHolder.getContextHolderStrategy();
		logger.info("New strategy is : {}", statergy);

	}

//	private AuthenticationSuccessHandler authenticationSuccessHandler() {
//		return new SavedRequestAwareAuthenticationSuccessHandler() {
//			@Override
//			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//					Authentication authentication) throws ServletException, IOException {
//				super.onAuthenticationSuccess(request, response, authentication);
//			}
//		};
//	}
}