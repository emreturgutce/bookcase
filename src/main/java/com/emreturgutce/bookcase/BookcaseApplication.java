package com.emreturgutce.bookcase;

import com.emreturgutce.bookcase.filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

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

		registrationBean.addUrlPatterns("/api/users/all");

		return registrationBean;
	}
}
