package cn.lch.java_spring_boot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);

    @Pointcut("@annotation(cn.lch.java_spring_boot.aspect.ServiceAnnotation)")
    @Order(2)
    public void ServicePointCut(){}

    @Before(value = "cn.lch.java_spring_boot.aspect.ServiceAspect.ServicePointCut()")
    public void beforeService(JoinPoint joinpoint) {
        LOGGER.debug("==== This is before service ====");
    }

    @Around(value = "cn.lch.java_spring_boot.aspect.ServiceAspect.ServicePointCut()")
    public Object aroundService(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        LOGGER.debug("==== This is around service ==== ");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    @After(value = "cn.lch.java_spring_boot.aspect.ServiceAspect.ServicePointCut()")
    public void afterService() {
        LOGGER.debug("==== This is after service ====");
    }
}
