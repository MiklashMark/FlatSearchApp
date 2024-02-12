package by.itacademy.flatSearch.userService.aop.api;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;

public interface IAuditedAspect {

    Object getAction(ProceedingJoinPoint joinPoint) throws Throwable;

}
