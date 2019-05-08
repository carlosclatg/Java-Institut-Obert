package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {

	public static void main(String[] args) {
		
		
		//load the config file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanScopeapplicationcontext.xml");
		
		//retrieve beans from container
		Coach theCoach = context.getBean("myCoach", Coach.class);

		
		//close context
		context.close();

	}

}
