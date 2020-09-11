package org.example.springtest.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.example.springtest.dto.UserChangesLog;
import org.example.springtest.service.LoggerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggerAspect {
    private UserChangesLog logger;
    final LoggerService loggerService;

    @Before(value = "@annotation(LoggerMarker)")
    public void beforeCreateNewUser(JoinPoint jointPoint){
        logger=new UserChangesLog();
        logger.setSignature(jointPoint.getSignature().toShortString());
        logger.setChangerLogin(getCurrentUsername());
        logger.setArguments(jointPoint.getArgs());

    }

    @AfterReturning(returning = "result", value = "@annotation(LoggerMarker)")
    public void afterCreateNewUser(JoinPoint joinPoint, Object result){
        if((boolean)result){
               loggerService.saveLog(logger);
        }
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}
