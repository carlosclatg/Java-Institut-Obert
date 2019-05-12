package com.luv2code.hiberante.demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class CreateCoursesDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		//create a session
		
		Session session = factory.getCurrentSession();
		
		try {
			

			//start transaction
			session.beginTransaction();

			//Get the instructor from DB
			int id = 1;
			Instructor instructor = session.get(Instructor.class, id);
			
			//Create courses
			Course tempCourse1 = new Course("hibernate");
			Course tempCourse2 = new Course("chess");
			
			
			//add courses to instructor
			instructor.add(tempCourse1);
			instructor.add(tempCourse2);
			
			//save the courses
			//session.save(instructor);-> No necesary, already saved in the referenced object. 
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		} finally {
			
			session.close();
			factory.close();
		}
		
	}

}



