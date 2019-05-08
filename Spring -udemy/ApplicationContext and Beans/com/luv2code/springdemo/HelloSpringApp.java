package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		//Ejemplo básico de inversión de control.
		//load the spring config file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//retrieve bean from spring container
		//myCoach is the id
		//Coach is the interface
		Coach theCoach = context.getBean("myCoach", Coach.class);
		
		
		// call methods on the bean
		System.out.println(theCoach.getDailyWorkOut());
		//Call the method for fortune Service
		System.out.println(theCoach.getDailyFortune());
		
		//close the context
		context.close();
	}

}
