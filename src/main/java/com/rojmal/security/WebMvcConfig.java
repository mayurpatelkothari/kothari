package com.rojmal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.rojmal.EndPoint;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/views/";
	private static final String VIEW_RESOLVER_SUFFIX = ".jsp";

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController(EndPoint.INDEX).setViewName("index");
		registry.addViewController(EndPoint.HOME).setViewName("home");
		registry.addViewController(EndPoint.REGISATION).setViewName("signup");
		registry.addViewController(EndPoint.CONTACT).setViewName("contact");
		registry.addViewController(EndPoint.GROUP).setViewName("group");
		registry.addViewController(EndPoint.LOGIN).setViewName("login");
		registry.addViewController(EndPoint.OFFER).setViewName("offer");
		registry.addViewController(EndPoint.PRICING).setViewName("pricing");
		registry.addViewController(EndPoint.FORGOT_PASSWORD).setViewName("forgotpassword");

	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix(VIEW_RESOLVER_PREFIX);
		resolver.setSuffix(VIEW_RESOLVER_SUFFIX);
		return resolver;
	}

}
