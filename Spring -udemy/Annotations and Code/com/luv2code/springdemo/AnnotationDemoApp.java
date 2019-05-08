package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {

	public static void main(String[] args) {
		// read spring config file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//get the bean from spring container
		TennisCoach theCoach = (TennisCoach) context.getBean("tennisCoach", Coach.class);
		//Coach thenewCoachSetter = context.getBean("coachSetter", Coach.class);
		
		
		
		//call a method on the bean
		System.out.println(theCoach.getDailyWorkOut());
		System.out.println(theCoach.getDailyFortune());
		System.out.println(theCoach.getOthers());
		//System.out.println(thenewCoachSetter.getDailyWorkOut());
		//System.out.println(thenewCoachSetter.getDailyFortune());
		//close context
		context.close();

	}

}
