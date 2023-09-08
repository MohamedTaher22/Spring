package aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {
	
	//	this will match on any modifier(optional) and any return type 
	//	in aopdemo.dao package in any class on any method
	//	with 0 to many arguments of any type
	@Pointcut("execution(* aopdemo.dao.*.*(..))")
	public void forDaoPackage() {}
	
	// create point cut for getter methods
	@Pointcut("execution(* aopdemo.dao.*.get*(..))")
	public void getter() {}
	
	// create point cut for setter methods
	@Pointcut("execution(* aopdemo.dao.*.set*(..))")
	public void setter() {}
	
	// create point cut: include package ... exclude getter/setter
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	public void forDaoPackageNoGetterSetter() {}
}





