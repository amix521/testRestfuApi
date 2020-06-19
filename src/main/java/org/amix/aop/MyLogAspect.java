package org.amix.aop;

import java.util.Arrays;

import org.amix.target.MyLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyLogAspect {
	
	@Pointcut("@annotation(org.amix.target.MyLog)")
	public void logCut(){
		
	}
	
	@Before("logCut()")
	public void logBefore(){
		
	}
	
	@After("@annotation(myLog)")
	public void logAfter(JoinPoint joinPoint,MyLog myLog){
		
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		String logArgs = Arrays.toString(joinPoint.getArgs());
		System.out.println("执行了"+targetName+"类的"+methodName+"方法,参数为"+logArgs);
	}
	
	/**
	 * 这个TM的一定要加return，不然没返回值
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("logCut()")
	public Object logAround(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("[Aspect1] around advise 1");
		Object result =pjp.proceed();
        System.out.println("[Aspect1] around advise2");
        return result;

	}
	
	@AfterReturning("logCut()")
	public void logAfterReturning(){
		
	}
	
	//异常情况处理
	@AfterThrowing("logCut()")
	public void logAfterThrowing(){
		
	}
}
