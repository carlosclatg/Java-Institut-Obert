package com.luv2code.hiberante.demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class CreateInstructorDemo {

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
			
			//create a Instructor and Detail
			Instructor tempInstructor = new Instructor("Susan", "Public", "susan@mail.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("canalyoutube", "I love coding");
			//associate objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			//start transaction
			session.beginTransaction();
			
			//save the instructor together with instructor details (instructor contains details object) because CascadeType.All
			session.save(tempInstructor);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		} finally {
			
			session.close();
			factory.close();
		}
		
	}

}



