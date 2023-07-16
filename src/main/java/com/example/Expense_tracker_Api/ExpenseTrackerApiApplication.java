package com.example.Expense_tracker_Api;

import com.example.Expense_tracker_Api.Filters.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExpenseTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApiApplication.class, args);
	}


	@Bean
	public FilterRegistrationBean<AuthFilter> filterFilterRegistrationBean()
	{
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
		AuthFilter authFilter = new AuthFilter();
		registrationBean.setFilter(authFilter);
		registrationBean.addUrlPatterns("/ExpenseTracker/categories");
		registrationBean.addUrlPatterns("/ExpenseTracker/category/*");
		registrationBean.addUrlPatterns("/ExpenseTracker/Transactions");
		registrationBean.addUrlPatterns("/ExpenseTracker/Category/*/Transaction/*");
		return registrationBean;
	}
}
