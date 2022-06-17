package com.cgi.userservice;

import com.cgi.userservice.filter.UserFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
//@EnableEurekaClient
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean getFilter()
	{
		UrlBasedCorsConfigurationSource urlConfig = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(true);
		List<String> all = Arrays.asList("*");

		config.setAllowedOrigins(all);
		config.setAllowedMethods(all);
		config.setAllowedHeaders(all);

		urlConfig.registerCorsConfiguration("/**",config);

		FilterRegistrationBean fBean = new FilterRegistrationBean(new CorsFilter(urlConfig));
		fBean.setFilter(new UserFilter());
		fBean.addUrlPatterns("/users/api/updateUser/*");
		fBean.addUrlPatterns("/users/api/deleteUser/*");
		fBean.addUrlPatterns("/users/api/getAllusers");
		return fBean;
	}
}
