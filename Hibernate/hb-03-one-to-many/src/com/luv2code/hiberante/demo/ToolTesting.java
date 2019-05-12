package com.luv2code.hiberante.demo;

import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

class ToolTesting {

	@Test
	void RetrievingCourseFail() {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		//with bidirectional if we ask for a detail, we obtain the instructor as well and reverse as well.
		try {	
			//start transaction
			session.beginTransaction();
			
			//get a course
			int id = 11;
			Course course = session.get(Course.class, id);
			
			assertEquals(course, null);
			// delete course
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close(); //To close session anyway
			factory.close();
		}
		
	}
	


}
