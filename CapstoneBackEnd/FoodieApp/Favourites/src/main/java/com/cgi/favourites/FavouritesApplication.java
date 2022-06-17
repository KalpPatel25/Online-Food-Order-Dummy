package com.cgi.favourites;

import com.cgi.favourites.filter.FavouritesFilter;
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
public class FavouritesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavouritesApplication.class, args);
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
		fBean.setFilter(new FavouritesFilter());
		//fBean.addUrlPatterns("/fav/*");
		fBean.addUrlPatterns("/some/*");
		return fBean;
	}

}
