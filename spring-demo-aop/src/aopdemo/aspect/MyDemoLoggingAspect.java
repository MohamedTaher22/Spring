package aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
	
	// this is were we add all of our related advises for logging
	 
//	this will match on any modifier(optional) and any return type 
//	in aopdemo.dao package in any class on any method
//	with 0 to many arguments of any type
	@Before("execution(* aopdemo.dao.*.*(..))")
	public void beforeAddAccountAdvice() {
		
		System.out.println("\n=====>>>>> Executing @Before advice on method");
	}
}
