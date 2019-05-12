package com.luv2code.hiberante.demo;

import javax.xml.parsers.FactoryConfigurationError;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.luv2code.hibernate.demo.entity.Student;
import com.sun.webkit.ThemeClient;

public class ReadStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create a session
		
		Session session = factory.getCurrentSession();
		
		try {
			//create a student object
			System.out.println("Creating new student object");
			Student tempStudent = new Student("Daffy", "Duck", "daffy@duck.com");
			
			//start transaction
			session.beginTransaction();
			
			System.out.println(tempStudent.toString());
			//save the student object
			session.save(tempStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Saving done!");
			
			
			//Retrieving an object from DB
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("Getting student with id: " + tempStudent.getId());
			
			Student studentRetrieved = session.get(Student.class, tempStudent.getId());
			
			//commit transaction
			session.getTransaction().commit();
			
			
			System.out.println("The studen retrieved is:" + studentRetrieved);
			System.out.println("Ended");
			
			
		} finally {
			factory.close();
		}
		
	}

}
