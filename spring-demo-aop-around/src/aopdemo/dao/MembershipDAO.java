package aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {
	
	public boolean addAccount() {
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
		return true;
	}
	
	public void goToSleep() {
		System.out.println(getClass() + ": goToSleep()");
	}
}
