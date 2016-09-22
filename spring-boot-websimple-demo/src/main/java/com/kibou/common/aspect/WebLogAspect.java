package com.kibou.common.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.kibou.util.WebUtils;

@Aspect
@Component
@Order(1) //get high priority
//@AspectComponent donot work!
public class WebLogAspect {

	private Logger logger = LoggerFactory.getLogger(getClass()); 
	
	public WebLogAspect(){
		logger.info(this.getClass() + "<init>");
	}
	@Pointcut("execution(public * com.kibou.web..*.*(..))") 
	//所有public的返回值任意 包为com.kibou.web及其子目录下的所有方法(参数任意)
	public void requestLog(){}
	
	@Before("requestLog()")
	public void doBeforeRequest(JoinPoint joinPoint){//aspect包下的Joinpoint 非aopaliance下的
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
		
		HttpServletRequest request = servletRequestAttributes.getRequest();
		
		Signature signature = joinPoint.getSignature();
		String methodSignature = signature.getDeclaringTypeName() + "." +signature.getName();
		logger.debug("HttpInvoke : " + methodSignature);
		
		String url = WebUtils.getFullURL(request, false);
		String clientIp = WebUtils.getRemoteAddr(request); //clientIp
		
		logger.info("request - clientIp : {}, request : {}",clientIp,url);
	}
	
	@AfterReturning(pointcut/*value*/ = "requestLog()", returning = "ret")
	public void doAfterReturningRequest(Object ret){
		logger.info("response - " + ret);
	}
	
}
