package hibernate.demo;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Course;
import hibernate.demo.entity.Instructor;
import hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
										
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start a transaction
			session.beginTransaction();
			// how to solve lazy loading problem if the session is closed
			//	
			// option 2: Hibernate query with HQL
			
			
			// get the instructor from db
			int theId = 1;
			
			Query<Instructor> query =
					session.createQuery("select i from Instructor i " 
										+ "JOIN FETCH i.courses "
										+ "where i.id=:theInstructorId",
							Instructor.class);
			
			// set parameter on query
			query.setParameter("theInstructorId", theId);
			
			// execute query and get instructor
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("luv2code: Instructor: "+tempInstructor);
			
			
			// commit transaction
			session.getTransaction().commit();
			
			// close the session
			session.close();
			
			System.out.println("\nluv2code: the session is now closed!\n");
			
			
			// get course for the instructor
			System.out.println("luv2code: Courses: "+ tempInstructor.getCourses());
						
			
			System.out.println("luv2code: Done!");
		} finally {
			// add clean up code
			session.close();
			
			factory.close();
		} 
		

	}

}
