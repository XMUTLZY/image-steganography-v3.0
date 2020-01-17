package sch.xmut.jake.imagestegangraphy.web.aop;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sch.xmut.jake.cache.apicache.http.response.CacheResponse;
import sch.xmut.jake.imagestegangraphy.http.request.admin.AdminRequest;
import sch.xmut.jake.imagestegangraphy.http.request.user.UserRequest;
import sch.xmut.jake.imagestegangraphy.http.response.BaseResponse;
import sch.xmut.jake.imagestegangraphy.http.response.LayerResponse;
import sch.xmut.jake.imagestegangraphy.http.vo.admin.Admin;
import sch.xmut.jake.imagestegangraphy.http.vo.admin.AdminOperateLog;
import sch.xmut.jake.imagestegangraphy.service.admin.AdminOperateService;
import sch.xmut.jake.imagestegangraphy.service.admin.AdminService;
import sch.xmut.jake.imagestegangraphy.utils.SystemUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by jake.lin on 2020/1/3
 */
@Component
@Aspect
public class RecordAdminOperateAop {
    @Autowired
    private AdminOperateService adminOperateService;
    @Autowired
    private AdminService adminService;
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
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestMethod = joinPoint.getSignature().getName();//获取方法名
        CacheResponse cacheResponse = adminService.getAdminInfoCache();// 获取当前管理员信息
        AdminOperateLog adminOperateLog = new AdminOperateLog();
        adminOperateLog.setIp(SystemUtils.getIpAddr(request));
        buildAdminOperateLogInfo(adminOperateLog, cacheResponse, requestMethod, joinPoint, response);
        adminOperateService.insert(adminOperateLog);
    }

    private void buildAdminOperateLogInfo(AdminOperateLog adminOperateLog, CacheResponse cacheResponse, String requestMethod,
                                          JoinPoint joinPoint, Object response) {
        Admin admin = JSONObject.parseObject(cacheResponse.getValue(), Admin.class);
        adminOperateLog.setAdminId(admin.getId());
        adminOperateLog.setUserName(admin.getUserName());
        adminOperateLog.setRoleName(admin.getRoleName());
        adminOperateLog.setOperateTime(SystemUtils.dateToFormat(new Date()));
        switch (requestMethod) {
            case "login":
                BaseResponse baseResponse1 = (BaseResponse) response;
                if (baseResponse1.getStatusCode() == BaseResponse.SUCCESS_CODE) {
                    adminOperateLog.setOperateStr(buildAdminOperateStr(adminOperateLog, "登录了后台"));
                }
                break;
            case "userList":
                LayerResponse layerResponse1 = (LayerResponse) response;
                if (layerResponse1.getCode() == LayerResponse.SUCCESS_CODE) {
                    adminOperateLog.setOperateStr(buildAdminOperateStr(adminOperateLog, "查询了用户列表"));
                }
                break;
            case "userDelete":
                LayerResponse layerResponse2 = (LayerResponse) response;
                if (layerResponse2.getCode() == LayerResponse.SUCCESS_CODE) {
                    String mobile = (String) joinPoint.getArgs()[0];
                    adminOperateLog.setOperateStr(buildAdminOperateStr(adminOperateLog, "删除了手机号为" + mobile + "的用户信息"));
                }
                break;
            case "userGet":
                BaseResponse baseResponse3 = (BaseResponse) response;
                if (baseResponse3.getStatusCode() == BaseResponse.SUCCESS_CODE) {
                    String mobile = (String) joinPoint.getArgs()[0];
                    adminOperateLog.setOperateStr(buildAdminOperateStr(adminOperateLog, "查询了手机号为" + mobile + "的用户信息"));
                }
                break;
            case "userUpdate":
                BaseResponse baseResponse4 = (BaseResponse) response;
                if (baseResponse4.getStatusCode() == BaseResponse.SUCCESS_CODE) {
                    String mobile = ((UserRequest) joinPoint.getArgs()[0]).getMobile();
                    adminOperateLog.setOperateStr(buildAdminOperateStr(adminOperateLog, "修改了手机号为" + mobile + "的用户信息"));
                }
                break;
            case "userSearch":
                LayerResponse layerResponse3 = (LayerResponse) response;
                if (layerResponse3.getCode() == LayerResponse.SUCCESS_CODE) {
                    adminOperateLog.setOperateStr(buildAdminOperateStr(adminOperateLog, "模糊查询了用户列表"));
                }
                break;
            case "orderList":
                LayerResponse layerResponse4 = (LayerResponse) response;
                if (layerResponse4.getCode() == LayerResponse.SUCCESS_CODE) {
                    adminOperateLog.setOperateStr(buildAdminOperateStr(adminOperateLog, "查询了订单列表"));
                }
                break;
            case "adminList":
                LayerResponse layerResponse5 = (LayerResponse) response;
                if (layerResponse5.getCode() == LayerResponse.SUCCESS_CODE) {
                    adminOperateLog.setOperateStr(buildAdminOperateStr(adminOperateLog, "查询了管理员列表"));
                }
                break;
            case "adminDelete":
                BaseResponse baseResponse5 = (BaseResponse) response;
                if (baseResponse5.getStatusCode() == BaseResponse.SUCCESS_CODE) {
                    AdminRequest adminRequest = (AdminRequest) joinPoint.getArgs()[0];
                    adminOperateLog.setOperateStr(buildAdminOperateStr(adminOperateLog, "删除了手机号为" + adminRequest.getMobile() + "的管理员信息"));
                }
                break;
            case "adminSearch":
                LayerResponse layerResponse6 = (LayerResponse) response;
                if (layerResponse6.getCode() == LayerResponse.SUCCESS_CODE) {
                    adminOperateLog.setOperateStr(buildAdminOperateStr(adminOperateLog, "模糊查询管理员列表"));
                }
                break;
            case "adminAdd":
                BaseResponse baseResponse6 = (BaseResponse) response;
                if (baseResponse6.getStatusCode() == BaseResponse.SUCCESS_CODE) {
                    AdminRequest adminRequest = (AdminRequest) joinPoint.getArgs()[0];
                    adminOperateLog.setOperateStr(buildAdminOperateStr(adminOperateLog, "添加了手机号为" + adminRequest.getMobile() + "的管理员信息"));
                }
                break;
            case "operateRecords":
                adminOperateLog.setOperateStr(buildAdminOperateStr(adminOperateLog, "查询了操作记录列表"));
                break;
            default:
                break;
        }
    }

    private String buildAdminOperateStr(AdminOperateLog adminOperateLog, String operate) {
        return adminOperateLog.getUserName() + "(" + adminOperateLog.getRoleName() + ")" + operate;
    }
}
