package com.klimmenkov.spring.hibernate.lab_4.aspect;

import com.klimmenkov.spring.hibernate.lab_4.entity.Log;
import com.klimmenkov.spring.hibernate.lab_4.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private LogService logService;

    @Around("com.klimmenkov.spring.hibernate.lab_4.aspect.pointcut.LoggingPointcut.allShowAddDeleteMethods()")
    public Object afterReturningLoggingAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String cookieValue = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("login")) {
                    cookieValue = cookie.getValue();
                    break;
                }
            }
        }
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        Log log = new Log();
        log.setUser(cookieValue);
        log.setAction(methodName);
        log.setDate(new Timestamp(System.currentTimeMillis()));
        logService.saveLog(log);

        return joinPoint.proceed();
    }
}
