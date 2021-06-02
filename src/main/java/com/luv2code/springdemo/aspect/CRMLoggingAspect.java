package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	@Before("forAppFlow()")
	private void before(JoinPoint joinPoint) {
		
		logger.info("====>>> In @Before: calling method: "
				+ joinPoint.getSignature().toShortString());
		
		Object[] args = joinPoint.getArgs();

		for (Object arg : args) {
			logger.info("====>>> argument: " + arg);
		}
	}
	
	@AfterReturning(
			pointcut = "forAppFlow()",
			returning = "result")
	private void afterReturning(JoinPoint joinPoint, Object result) {
		
		logger.info("====>>> In @AfterReturning: from method: " + joinPoint.getSignature());
		logger.info("====>>> Result: " + result);
		
		
	}
}
