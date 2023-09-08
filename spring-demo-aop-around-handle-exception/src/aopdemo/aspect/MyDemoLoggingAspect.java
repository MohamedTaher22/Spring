package aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Around("execution(* aopdemo.service.*.getFrotune(..))")
	public Object arrounGetFortune(
			ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		// print the method we are advising on
		String method = proceedingJoinPoint.getSignature().toShortString();
		myLogger.info("\n====>>> Executing @Around on method: " + method);
		
		// begin time stamp
		long begin = System.currentTimeMillis();
		
		// execute the method
		Object result = null;
		
		try {
			result = proceedingJoinPoint.proceed();
		} catch (Exception e) {
			// log the exception
			myLogger.warning(e.getMessage());
			
			// if you want to handle it:
			// give user a custom message
			// and change the value of result
			// result = "Major accident! but no worries, help on the way!";
			
			// if you want to re-throw exception
			throw e;
		}
		
		// end time stamp
		long end = System.currentTimeMillis();
		
		// compute duration and display it
		long duration = end -begin;
		myLogger.info("\n====>>> Duration: "+ duration/1000.0 + " seconds");
		
		return result;
	}
	
	
	@After("execution(* aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
		
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n====>>> Executing @After (finally) on method: " + method);
		
	}	
	
	@AfterThrowing(
			pointcut = "execution(* aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing = "theExc"
			)
	public void afterThrowingFindAccountsAdvice(
			JoinPoint theJoinPoint, Throwable theExc) {
		
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n====>>> Executing @AfterThrowing on method: " + method);
		
		myLogger.info("\n        the Exception is: " + theExc);
		
	}
	
	
	
	@AfterReturning(
			pointcut = "execution(* aopdemo.dao.AccountDAO.findAccounts(..))",
			returning = "result"
			)
	public void afterReturningFindAccountAdvice(
			JoinPoint theJoinPoint, List<Account> result) {
		
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n====>>> Executing @AfterReturning on method: " + method);
		
		myLogger.info("\n        result is: " + result);
		
		// post-process the data
		
		// convert the account names to upper case
		convertAccountNamesToUpperCase(result);
		
		myLogger.info("\n        result is: " + result);
	}
	
	private void convertAccountNamesToUpperCase(List<Account> result) {
		// loop through accounts
		for(Account tempAccount : result) {
			// get upper case version of name
			String theUpperCase = tempAccount.getName().toUpperCase();
			// update the name on the account
			tempAccount.setName(theUpperCase);
		}		
	}

	// apply point cut declaration on this method
	@Before("aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		
		myLogger.info("\n====>>> Executing @Before advice on method");
		
		// display the method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		myLogger.info("\n        method: " + methodSig);
		
		// display method arguments
		
		Object[] args = theJoinPoint.getArgs();
		
		for(Object tempArg: args) {
			myLogger.info("        " + tempArg);
			
			if(tempArg instanceof Account) {
				Account theAccount = (Account) tempArg;
				
				myLogger.info("account name: " + theAccount.getName());
				myLogger.info("account level: " + theAccount.getLevel());
			}
		}
		
	}
	
}

