package com.luv2code.hiberante.demo;

import java.util.ArrayList;

import javax.xml.parsers.FactoryConfigurationError;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.luv2code.hibernate.demo.entity.Student;
import com.sun.webkit.ThemeClient;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create a session
		
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 1;
			
			//get session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//update student
			Student myStudent = session.get(Student.class, studentId);
			myStudent.setFirstName("Scooby");
			
			//commit transaction
			session.getTransaction().commit();
			
			
			//NEW CODE
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//update email all student
			session.createQuery("update Student set email='foo@email.es'").executeUpdate();
			
			
			//commit transaction
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
		
	}

}
