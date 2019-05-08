package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemo {

	public static void main(String[] args) {
		// load spring config file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// retrieve bean from spring container
		Coach theCoach = context.getBean("tennisCoach", Coach.class);
		Coach theCoach2 = context.getBean("tennisCoach", Coach.class);
		
		//check if they are the same 
		
		System.out.println(theCoach == theCoach2);
		System.out.println(theCoach);
		System.out.println(theCoach2);
		
		//close application context
		
		context.close();

	}

}
