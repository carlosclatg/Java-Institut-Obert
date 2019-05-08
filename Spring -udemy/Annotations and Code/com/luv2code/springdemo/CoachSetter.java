package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException.Gone;


@Component
public class CoachSetter implements Coach {
	
	@Autowired
	@Qualifier("secondClasswithFortuneService")
	private FortuneService fortuneService;
	
	
	//define a default constructor with no args
	
	public CoachSetter () {
		System.out.println(">> CoachSetter:  go through default constructor");
	}
	
	/*@Autowired
	public void setFortuneService(FortuneService fortuneService) {
		System.out.println(">>go Through setter in CoachSetter");
		this.fortuneService = fortuneService;
	}*/
	/*
	@Autowired
	public void doSomeCrazyStuff(FortuneService fortuneService){
		System.out.println(">>go Through somecreazystuff in CoachSetter");
		this.fortuneService = fortuneService;
	}*/
	
	@Override
	public String getDailyWorkOut() {
		return "practice your setters modafoca";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
