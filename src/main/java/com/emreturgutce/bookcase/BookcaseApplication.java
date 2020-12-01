package com.emreturgutce.bookcase;

import com.emreturgutce.bookcase.filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class BookcaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookcaseApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<AuthFilter> filterFilterRegistrationBean() {
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();

		AuthFilter authFilter = new AuthFilter();

		registrationBean.setFilter(authFilter);

		registrationBean.addUrlPatterns("/api/books/*");

		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("*");
		configuration.addAllowedHeader("*");
		source.registerCorsConfiguration("/**", configuration);
		filterRegistrationBean.setFilter(new CorsFilter(source));
		filterRegistrationBean.setOrder(0);
		return filterRegistrationBean;
	}
}
