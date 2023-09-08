package hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Instructor;
import hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
										
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// create the objects
			/*
			Instructor tempInstructor = 
					new Instructor("mohamed", "ashraf", "mo@gmail.com");
			
			InstructorDetail tempInstructorDetail =
					new InstructorDetail("www.youtube.com/mo", "movies");
				*/	
			Instructor tempInstructor = 
					new Instructor("mahmoud", "ragab", "ragab@gmail.com");
			
			InstructorDetail tempInstructorDetail =
					new InstructorDetail("www.youtube.com/ragab", "army");
			
			
			
			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			
			// start a transaction
			session.beginTransaction();
			
			
			// save the instructor
			//
			// Note: this will also save the details objects
			// because of CascadeType.ALL
			//
			System.out.println("Saving instructor: "+tempInstructor);
			session.save(tempInstructor);
			
			
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			factory.close();
		} 
		

	}

}
