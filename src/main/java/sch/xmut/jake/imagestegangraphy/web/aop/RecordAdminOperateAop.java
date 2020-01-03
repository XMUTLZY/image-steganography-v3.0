package sch.xmut.jake.imagestegangraphy.web.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sch.xmut.jake.imagestegangraphy.service.admin.AdminOperateService;

/**
 * Created by jake.lin on 2020/1/3
 */
@Component
@Aspect
public class RecordAdminOperateAop {
    @Autowired
    private AdminOperateService adminOperateService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Before("pointCut()")
    public void before() {
        logger.info("before AdminController init");
    }

    @Pointcut("execution(* sch.xmut.jake.imagestegangraphy.web.controller.admin.AdminController.*(..))")
    public void pointCut() {
        logger.info("pointCut AdminController init");
    }

    @AfterReturning(returning = "response", value = "pointCut()")
    public void afterRunning(JoinPoint joinPoint, Object response) {
        logger.info("after AdminController init");
    }
}
