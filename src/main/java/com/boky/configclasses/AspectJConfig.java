package com.boky.configclasses;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.boky.SubjectParser.services.services.error.EditServiceException;

@Component
@Aspect
public class AspectJConfig {

	public static int num = 0;

	@Around("execution (* com.boky.SubjectParser..*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		logger.trace(crateTabs(num++) + "Start " + "." + joinPoint.getSignature().getName());

		Object result = joinPoint.proceed();

		logger.trace(crateTabs(--num) + "End" + joinPoint.getSignature().getName());
		return result;
	}

	private String crateTabs(int num) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < num; i++) {
			sb.append("\t");
		}
		return sb.toString();
	}

	@Around("execution (* com.boky.SubjectParser.services.services.EditServiceHelper.*(..))")
	public Object handleEditServiceExceptions(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result;
		try {
			result = joinPoint.proceed();
		} catch (Exception e) {
			Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
			logger.error(e.getMessage(), e);
			throw new EditServiceException(e);
		}
		return result;

	}
}
