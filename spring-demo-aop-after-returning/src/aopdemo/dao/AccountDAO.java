package aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import aopdemo.Account;

@Component
public class AccountDAO {
	
	private String name;
	private String serviceCode;
	
	public List<Account> findAccounts(){
		
		List<Account> myAccounts = new ArrayList<>();
		
		// create sample accounts
		Account tmp1 = new Account("Mohamed", "gold");
		Account tmp2 = new Account("eid", "silver");
		Account tmp3 = new Account("momen", "platinum");
		
		// add them to our accounts list
		myAccounts.add(tmp1);
		myAccounts.add(tmp2);
		myAccounts.add(tmp3);
	
		return myAccounts;
	}

	public void addAccount(Account theAccount, boolean vipFlag) {
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
	}
	
	public boolean doWork() {
		System.out.println(getClass() + ": doWork()");
		return false;
	}

	public String getName() {
		System.out.println(getClass() + ": in getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + ": in setName()");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass() + ": in getServiceCode()");
		return serviceCode;
	}

	public void setServiceCode(String service) {
		System.out.println(getClass() + ": in setServiceCode()");
		this.serviceCode = service;
	}
	
	
}
