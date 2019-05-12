package com.luv2code.hiberante.demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class DeleteDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		//create a session
		
		Session session = factory.getCurrentSession();
		
		try {	
			//start transaction
			session.beginTransaction();

			
			//get instructor by PK
			//delete instructor
			
			int id = 1;
			Instructor theInstructor = session.get(Instructor.class, id);
			
			//It will also delete instructor details (CASCADETYPE.ALL)
			if(theInstructor != null) session.delete(theInstructor);		
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		} finally {
			factory.close();
		}
		
	}

}



