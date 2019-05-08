package com.luv2code.springdemo;



import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


//Spring will register the bean with id "thatSillyCoach"
//@Component("thatSillyCoach")

//register with the default bean id className with first letter lowercase
//scope singleton (default) only one, prototype one instance for each request.
//For Scope prototype predestroy method does not execute.
@Component
@Scope("prototype")
public class TennisCoach implements Coach {
	
	@Autowired
	@Qualifier("newImplementationFortune")
	private FortuneService fortuneService;
	
	//Inject a value from a file
	@Value("${foo.team}")
	private String others;
	
	//Spring will scan for a component that implement FortuneService interface
	
	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public TennisCoach() {
	}
	
	@Override
	public String getDailyWorkOut() {
		return "practice your backhand volley";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}
	
	//define init methodc(after instatiating, before injection and others)
	
	@PostConstruct
	public void doMyStartUpStuff() {
		System.out.println("Tennis init method");
	}
	
	//define destroy method (before destroying)
	@PreDestroy
	public void doMyCleanUpStuff() {
		System.out.println("Tennis destroy method");
	}
}
