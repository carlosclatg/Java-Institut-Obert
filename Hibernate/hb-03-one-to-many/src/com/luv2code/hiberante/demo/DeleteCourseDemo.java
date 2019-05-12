package com.luv2code.hiberante.demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class DeleteCourseDemo {

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
		
		
		//with bidirectional if we ask for a detail, we obtain the instructor as well and reverse as well.
		try {	
			//start transaction
			session.beginTransaction();
			
			//get a course
			int id = 11;
			Course course = session.get(Course.class, id);
			
			// delete course
			session.delete(course);
			System.out.println();
			
			//

			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close(); //To close session anyway
			factory.close();
		}
		
	}

}



