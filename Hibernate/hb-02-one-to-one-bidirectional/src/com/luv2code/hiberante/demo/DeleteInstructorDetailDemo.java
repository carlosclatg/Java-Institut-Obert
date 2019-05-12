package com.luv2code.hiberante.demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		//create a session
		
		Session session = factory.getCurrentSession();
		
		
		//with bidirectional if we ask for a detail, we obtain the instructor as well and reverse as well.
		try {	
			//start transaction
			session.beginTransaction();

			int theId = 3;
	
			//get the instructor detail
			
			InstructorDetail detail = session.get(InstructorDetail.class, theId);
			
			//print the instructor detail
			System.out.println("DEtail");
			System.out.println(detail.toString());
			
			//print the associated instructor
			System.out.println("instructor");
			System.out.println(detail.getInstructor().toString());
			
			//now let's delete the instructor detail
			//delete detail and instructor both they are linked bidirectional
			
			
			//remove associated object reference, else a exception is thrown because the it tries to remove an object which is fully linked and we
			// have just removed the cascade.all.
			detail.getInstructor().setInstructorDetail(null);
			
			session.delete(detail); //throws exception if instructor detail is not deleted
			
			
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



