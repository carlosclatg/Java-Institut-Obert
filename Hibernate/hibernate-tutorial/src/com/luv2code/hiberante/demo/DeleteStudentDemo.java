package com.luv2code.hiberante.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.luv2code.hibernate.demo.entity.Student;


public class DeleteStudentDemo {

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
			
			//delete Student by primary key
			Student myStudent = session.get(Student.class, studentId);
			session.delete(myStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
			//Careful if the entity does not exist, it throws exception.
			
			//NEW CODE
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//update email in all student
			session.createQuery("delete from Student where id=2").executeUpdate();
			
			
			//commit transaction
			session.getTransaction().commit();
			
		} finally {
			factory.close();
		}
		
	}

}
