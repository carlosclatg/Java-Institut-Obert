
package com.luv2code.hiberante.demo;

import java.awt.List;
import java.util.ArrayList;

import javax.xml.parsers.FactoryConfigurationError;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start transaction
			session.beginTransaction();
			
			//query all students
			ArrayList<Student> theStudentsList = (ArrayList) session.createQuery("from Student").getResultList();
			System.out.println(theStudentsList.isEmpty());
			//display students
			displayStudents(theStudentsList);
			
			
			//query students that lastName="Wall1"
			theStudentsList = (ArrayList<Student>) session.createQuery("from Student s where s.lastName='Wall1'").getResultList();
			System.out.println("Students with lastName Wall1");
			displayStudents(theStudentsList);
			
			
			
			//query students that lastName="Wall1" OR firstName="Paul40"
			theStudentsList = (ArrayList<Student>) session.createQuery("from Student s where s.lastName='Wall1' OR s.firstName='Paul40'").getResultList();
			System.out.println("Students with lastName=\"Wall1\" OR firstName=\"Paul40\"");
			displayStudents(theStudentsList);
			
			
			theStudentsList = (ArrayList<Student>) session.createQuery("from Student s where s.email "
					+ "LIKE 'daffy%'").getResultList();
			System.out.println("Students with email beginning by daffy");
			displayStudents(theStudentsList);
			
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		} finally {
			factory.close();
		}
		
	}

	private static void displayStudents(ArrayList<Student> theStudentsList) {
		for (Student e: theStudentsList) {
			System.out.println(e.toString());
		}
	}

}
