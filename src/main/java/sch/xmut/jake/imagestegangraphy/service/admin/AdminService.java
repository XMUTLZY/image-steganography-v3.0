package sch.xmut.jake.imagestegangraphy.service.admin;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import sch.xmut.jake.cache.apicache.http.request.CacheRequest;
import sch.xmut.jake.cache.apicache.http.response.CacheResponse;
import sch.xmut.jake.imagestegangraphy.constants.AdminConstant;
import sch.xmut.jake.imagestegangraphy.constants.CacheConstant;
import sch.xmut.jake.imagestegangraphy.domain.admin.AdminEntity;
import sch.xmut.jake.imagestegangraphy.domain.admin.AdminRoleEntity;
import sch.xmut.jake.imagestegangraphy.http.request.admin.AdminRequest;
import sch.xmut.jake.imagestegangraphy.http.response.BaseResponse;
import sch.xmut.jake.imagestegangraphy.http.response.LayerResponse;
import sch.xmut.jake.imagestegangraphy.http.vo.admin.Admin;
import sch.xmut.jake.imagestegangraphy.repository.admin.AdminRepository;
import sch.xmut.jake.imagestegangraphy.repository.admin.AdminRoleRepository;
import sch.xmut.jake.imagestegangraphy.service.cache.CacheService;
import sch.xmut.jake.imagestegangraphy.utils.SystemUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jake.lin on 2019/1/1
 */
@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AdminRoleRepository adminRoleRepository;
    @Autowired
    private CacheService cacheService;

    public Admin getAdmin(String mobile) {
        if (adminRepository.findByMobile(mobile) == null)
            return null;
        Admin vo = new Admin();
        BeanUtils.copyProperties(adminRepository.findByMobile(mobile), vo);
        AdminRoleEntity adminRoleEntity = adminRoleRepository.findAllById(vo.getRoleId());
        vo.setRoleName(adminRoleEntity.getName());
        return vo;
    }

    public BaseResponse login(AdminRequest adminRequest) {
        clearAdminInfoCache();
        Subject subject = SecurityUtils.getSubject();
        BaseResponse response = new BaseResponse();
        Admin admin = getAdmin(adminRequest.getMobile());
        if (admin == null) {
            SystemUtils.buildErrorResponse(response, "账号不存在");
        }
        String encrypt = admin.getEncrypt();
        String encodePassword = new SimpleHash(AdminConstant.ENCRYPTION_TYPE, adminRequest.getPassword(), encrypt, AdminConstant.ENCRYPTION_TIMES).toString();
        UsernamePasswordToken token = new UsernamePasswordToken(adminRequest.getMobile(), encodePassword);
        //执行登录
        try {
            subject.login(token);
            token.setRememberMe(true);
            buildAdminCache(admin);//设置登录缓存
            response.setMessage("登录成功");
        } catch (IncorrectCredentialsException e) {
            SystemUtils.buildErrorResponse(response, "登录密码错误");
        } catch (ExcessiveAttemptsException e) {
            SystemUtils.buildErrorResponse(response, "登录失败次数过多");
        } catch (LockedAccountException e) {
            SystemUtils.buildErrorResponse(response, "账号已被锁定");
        } catch (DisabledAccountException e) {
            SystemUtils.buildErrorResponse(response, "账号已被禁用");
        } catch (ExpiredCredentialsException e) {
            SystemUtils.buildErrorResponse(response, "账号已过期");
        } catch (UnknownAccountException e) {
            SystemUtils.buildErrorResponse(response, "账号不存在");
        } catch (UnauthorizedException e) {
            SystemUtils.buildErrorResponse(response, "您没有得到相应的授权");
        } catch (Exception e) {
            SystemUtils.buildErrorResponse(response, "用户名或密码错误");
        }
        return response;
    }

    public LayerResponse adminList(Pageable pageable) {
        LayerResponse response = new LayerResponse();
        Page<AdminEntity> adminEntityPage = adminRepository.findAllByStatus(pageable, AdminConstant.ADMIN_STATUS_PASS);
        if (adminEntityPage == null && !adminEntityPage.isEmpty())
            return null;
        List<Admin> adminList = new ArrayList<>();
        for (AdminEntity adminEntity : adminEntityPage) {
            Admin admin = new Admin();
            BeanUtils.copyProperties(adminEntity, admin);
            String roleName = adminRoleRepository.findAllById(adminEntity.getRoleId()).getName();
            admin.setRoleName(roleName);
            adminList.add(admin);
        }
        List<Admin> list = convertToAdminList(adminRepository.findAllByStatus(AdminConstant.ADMIN_STATUS_PASS));
        response.setData(adminList);
        response.setCount(list.size());
        return response;
    }

    public BaseResponse adminDelete(AdminRequest adminRequest) {
        BaseResponse response = new BaseResponse();
        AdminEntity adminEntity = adminRepository.findByMobile(adminRequest.getMobile());
        if (adminEntity == null) {
            SystemUtils.buildErrorResponse(response, "管理员不存在");
            return response;
        }
        adminEntity.setStatus(AdminConstant.ADMIN_STATUS_NOPASS);
        adminRepository.save(adminEntity);
        return response;
    }

    public LayerResponse adminSearch(AdminRequest adminRequest) {
        LayerResponse response = new LayerResponse();
        List<AdminEntity> adminEntityList;
        if (adminRequest.getId() != null) {
            adminEntityList = adminRepository.findAllById(adminRequest.getId());
        } else {
            adminEntityList = adminRepository.adminSearch(adminRequest.getUserName(), adminRequest.getMobile(), adminRequest.getRoleId());
        }
        if (CollectionUtils.isEmpty(adminEntityList)) {
            response.setMsg("查询不到该管理员");
            return response;
        }
        List<Admin> adminList = convertToAdminList(adminEntityList);
        response.setData(adminList);
        response.setCount(adminList.size());
        return response;
    }

    @Transactional
    public BaseResponse adminAdd(AdminRequest adminRequest) {
        AdminEntity adminEntity = new AdminEntity();
        Date date = new Date();
        adminEntity.setCreateTime(date);
        adminEntity.setUpdateTime(date);
        adminEntity.setMobile(adminRequest.getMobile());
        adminEntity.setRoleId(adminRequest.getRoleId());
        adminEntity.setUserName(adminRequest.getUserName());
        String encrypt = new SecureRandomNumberGenerator().nextBytes().toString();
        adminEntity.setEncrypt(encrypt);
        String encodePassword = new SimpleHash(AdminConstant.ENCRYPTION_TYPE, adminRequest.getPassword(), encrypt, AdminConstant.ENCRYPTION_TIMES).toString();
        adminEntity.setPassword(encodePassword);
        adminRepository.save(adminEntity);
        return new BaseResponse();
    }

    private List<Admin> convertToAdminList(List<AdminEntity> adminEntityList) {
        List<Admin> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(adminEntityList))
            return null;
        for (AdminEntity adminEntity : adminEntityList) {
            Admin admin = new Admin();
            BeanUtils.copyProperties(adminEntity, admin);
            String roleName = adminRoleRepository.findAllById(adminEntity.getRoleId()).getName();
            admin.setRoleName(roleName);
            list.add(admin);
        }
        return list;
    }

    public void buildAdminCache(Admin admin) {
        CacheRequest cacheRequest = new CacheRequest();
        cacheRequest.setMember(CacheConstant.WEB_CACHE_IMAGE_STEGANOGRAPHY_PROJECT_MEMBER);
        cacheRequest.setKey(CacheConstant.ADMIN_INFO_KEY);
        cacheRequest.setValue(JSONObject.toJSONString(admin));
        cacheService.stringAdd(cacheRequest);
    }

    public CacheResponse getAdminInfoCache() {
        CacheRequest cacheRequest = new CacheRequest();
        cacheRequest.setMember(CacheConstant.WEB_CACHE_IMAGE_STEGANOGRAPHY_PROJECT_MEMBER);
        cacheRequest.setKey(CacheConstant.ADMIN_INFO_KEY);
        return cacheService.stringGet(cacheRequest);
    }

    public void clearAdminInfoCache() {
        CacheRequest cacheRequest = new CacheRequest();
        cacheRequest.setMember(CacheConstant.WEB_CACHE_IMAGE_STEGANOGRAPHY_PROJECT_MEMBER);
        cacheRequest.setKey(CacheConstant.ADMIN_INFO_KEY);
        cacheService.stringDelete(cacheRequest);
    }
}
