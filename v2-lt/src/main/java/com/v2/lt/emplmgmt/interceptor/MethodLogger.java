package com.v2.lt.emplmgmt.interceptor;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MethodLogger {
	
	Logger logger = LoggerFactory.getLogger("console");
	
	@Pointcut("execution(* com.v2.lt.emplmgmt.service.*.*(..))")
	public void methodsInPackage() {}
	
	//
	@Around("methodsInPackage() && @annotation(com.v2.lt.emplmgmt.annotation.Logged)")
	public Object around(ProceedingJoinPoint point) {
	    long start = System.currentTimeMillis();
	    String status = "SUCCESS";
	    String exception = "";
	    Object result = null;
	    try {
			result = point.proceed();
		} catch (Throwable e) {
			status = "FAIL";
			exception = e.getMessage();
			e.printStackTrace();
		}
	    Long time = System.currentTimeMillis() - start;
	    logger.error("Status : "+status+", Method : "+point.getSignature().getName()+", Args : " +Arrays.toString(point.getArgs())+", Execution time : "+time+", Exception : "+exception);
	    return result;
	}
	
}
