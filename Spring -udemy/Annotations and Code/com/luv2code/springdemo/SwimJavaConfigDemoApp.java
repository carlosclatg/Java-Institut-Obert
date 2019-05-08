package com.luv2code.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//Example for configuration via code (no xml)

public class SwimJavaConfigDemoApp {

	public static void main(String[] args) {
		//read configuration from java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class);
		
		//get the bean from spring container
		SwimCoach theCoach = context.getBean("swimCoach", SwimCoach.class);
		//Coach thenewCoachSetter = context.getBean("coachSetter", Coach.class);
		
		
		
		//call a method on the bean
		System.out.println(theCoach.getDailyWorkOut());
		System.out.println(theCoach.getDailyFortune());
		System.out.println(theCoach.getEmail());
		System.out.println(theCoach.getTeam());

		//close context
		context.close();

	}

}
