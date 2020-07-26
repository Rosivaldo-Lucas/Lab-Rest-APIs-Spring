package com.ufpb.cursorest.laboratorio03;


import com.ufpb.cursorest.laboratorio03.domain.filters.TokenFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Laboratorio03Application {

	@Bean
	public FilterRegistrationBean<com.ufpb.cursorest.laboratorio03.domain.filters.TokenFilter> filterJwt() {
		FilterRegistrationBean<TokenFilter> filterRB = new FilterRegistrationBean<TokenFilter>();
		filterRB.setFilter(new TokenFilter());
		filterRB.addUrlPatterns("/disciplinas/*", "/usuarios/*");
		return filterRB;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Laboratorio03Application.class, args);
	}

}
