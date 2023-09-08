package aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import aopdemo.dao.AccountDAO;

public class AfterFinallyDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		
		// get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// call method to find the accounts
		List<Account> theAccounts = null;
				
		try {
			// add a boolean flag to simulate exception
			boolean tripWire = false;
			theAccounts = theAccountDAO.findAccounts(tripWire);
			
		} catch (Exception e) {
			System.out.println("\n\nMain Program... cauht exception: " + e);
		}
		
		
		System.out.println("\n\nMain Program: AfterFinallyDemoApp");
		System.out.println("----------");
		
		System.out.println(theAccounts + "\n");
		
		// close the context
		context.close();
	}

}







