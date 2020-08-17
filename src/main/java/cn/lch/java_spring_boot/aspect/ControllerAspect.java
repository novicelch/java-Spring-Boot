package cn.lch.java_spring_boot.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class ControllerAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    @Pointcut("execution(public * cn.lch.java_spring_boot.modules.controller.*.*(..))")
    @Order(1)
    public void ControllerPointCut(){}

    @Before(value = "cn.lch.java_spring_boot.aspect.ControllerAspect.ControllerPointCut()")
    public void beforeController(JoinPoint joinpoint){
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LOGGER.debug("请求来源：" + request.getRemoteAddr());
        LOGGER.debug("请求URL：" + request.getRequestURL().toString());
        LOGGER.debug("请求方式：" + request.getMethod());
        LOGGER.debug("响应方法：" +
                joinpoint.getSignature().getDeclaringTypeName() + "." +
                joinpoint.getSignature().getName());
        LOGGER.debug("请求参数：" + Arrays.toString(joinpoint.getArgs()));
    }

    @Around(value = "cn.lch.java_spring_boot.aspect.ControllerAspect.ControllerPointCut()")
    public Object aroundController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    @After(value = "cn.lch.java_spring_boot.aspect.ControllerAspect.ControllerPointCut()")
    public void afterController(){

    }
}
