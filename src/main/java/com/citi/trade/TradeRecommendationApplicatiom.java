package com.citi.trade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication
@EnableJpaRepositories
@ComponentScan
public  class TradeRecommendationApplicatiom {
	public static void main(String[] args) {
		SpringApplication.run(TradeRecommendationApplicatiom.class, args);
	}
	
}
