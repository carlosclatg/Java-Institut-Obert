package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterDemoApp {

	public static void main(String[] args) {
		
		//Load spring config file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//retrieve bean from spring container
		
		CricketCoach cricketCoach = (CricketCoach) context.getBean("myCricketCoach", CricketCoach.class);
		
		//call methods
		
		System.out.println(cricketCoach.getDailyFortune());
		System.out.println(cricketCoach.getDailyWorkOut());
		System.out.println(cricketCoach.getEmailAdressString());
		System.out.println(cricketCoach.getTeam());
		
		//close context
		context.close();
	}

}
