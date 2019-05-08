package com.luv2code.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//@ComponentScan("com.luv2code.springdemo")
@Configuration
@PropertySource("classpath:sport.properties") //define where the file is
public class SportConfig {
	
	//define a bean for sadFortuneService
	//methodName is the bean id -->sadFortuneService123
	@Bean
	public FortuneService sadFortuneService123() {
		return new SadFortuneService();
	}
	
	//define bean for our swim coach and inject dependecy
	//methodName is the bean id -->swimCoach
	//funciona porque el método swimCoach (id del bean) tiene el mismo nombre que la classe SwimCoach, con lowercase la primera letra.
	@Bean
	public Coach swimCoach() {
		return new SwimCoach(sadFortuneService123());
	}
	
}
