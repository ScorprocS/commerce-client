package lu.sfeir.commerce.client.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggingAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ObjectMapper mapper;
	
	@Pointcut(value="execution(* lu.sfeir.commerce.client.restrepository.*.*(..))")
	public void loggingControllerPointCut() {
		
	}
	
	@Pointcut(value="execution(* lu.sfeir.commerce.client.handler.*.*(..))")
	public void loggingSericePointCut() {
		
	}
	
	
	@Around(value = "loggingControllerPointCut()")
	public Object loggingControllerAdvice(ProceedingJoinPoint pjp ) throws Throwable {
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().toString();

		Object[] array = pjp.getArgs();
		logger.info("Inside "+className +"."+
				 methodName+" method, with request "+mapper.writeValueAsString(array));
		Object response = pjp.proceed(); // run the method
		
		logger.info("Inside "+className +"."+
				 methodName+" method, with response "+mapper.writeValueAsString(response));
	
		return response;
	}
    
    
    @After(value="loggingSericePointCut()")
    public void afterReturn(JoinPoint joinPoint) throws Throwable {
    	String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().toString();
        logger.info("Method="+className+"."+ methodName);
        
    }
}
