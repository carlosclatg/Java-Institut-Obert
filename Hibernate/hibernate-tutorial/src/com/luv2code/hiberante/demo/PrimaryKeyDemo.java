package com.luv2code.hiberante.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create a session
		
		Session session = factory.getCurrentSession();
		
		try {
			//create a students object
			System.out.println("Creating new student object");
			Student tempStudent1 = new Student("Paul10", "Wall1", "paul1@wall.com");
			Student tempStudent2 = new Student("Paul20", "Wall2", "paul2@wall.com");
			Student tempStudent3 = new Student("Paul30", "Wall3", "paul3@wall.com");
			Student tempStudent4 = new Student("Paul40", "Wall4", "paul4@wall.com");
			//start transaction
			session.beginTransaction();
			
			//save the student object
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			session.save(tempStudent4);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		} finally {
			factory.close();
		}

	}

}
