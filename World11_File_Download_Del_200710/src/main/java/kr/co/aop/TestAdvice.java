package kr.co.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component  //객체로만들때
@Aspect  //aop위한 advice입니다
public class TestAdvice {
	
	@Around("execution(* kr.co.service.AOPTestService*.*(..))")  //target * kr.co.service.AOPTestService*
	public void duration(ProceedingJoinPoint pjp) throws Throwable {  //반드시!! 파라미터 넣어주기!
		System.out.println("befor 야 ");
		long start = System.currentTimeMillis();
		pjp.proceed();  //Service Impl의 핵심코드(for문)라고 생각하면됨!! 
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		System.out.println("after이야");
	}
	
	@After("execution(* kr.co.service.AOPTestService*.*(..))")
	public void end() {
		System.out.println("*********************************");  //이런게 하나하나가 advice
	}
	
	@Before("execution(* kr.co.service.AOPTestService*.*(..))")  //before advice   jointpoint여기서 정의 
	public void start(JoinPoint jp) {
		System.out.println("::::::::::::::::::::::::::::::::::");
		System.out.println(jp.getKind());  //advice의 type 알아낼 수 있다! 
		System.out.println(jp.getSignature());  
		System.out.println(jp.getTarget());
		System.out.println("::::::::::::::::::::::::::::::::::");
	}
	
	
}
