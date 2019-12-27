package sch.xmut.jake.imagestegangraphy.service;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sch.xmut.jake.cache.apicache.http.request.CacheRequest;
import sch.xmut.jake.cache.apicache.http.response.CacheResponse;
import sch.xmut.jake.imagestegangraphy.constants.CacheConstant;
import sch.xmut.jake.imagestegangraphy.constants.UserConstant;
import sch.xmut.jake.imagestegangraphy.domain.user.UserEntity;
import sch.xmut.jake.imagestegangraphy.http.request.user.UserRequest;
import sch.xmut.jake.imagestegangraphy.http.response.user.UserResponse;
import sch.xmut.jake.imagestegangraphy.http.vo.user.User;
import sch.xmut.jake.imagestegangraphy.repository.user.UserRepository;
import sch.xmut.jake.imagestegangraphy.service.cache.CacheService;
import sch.xmut.jake.imagestegangraphy.utils.SystemUtils;
import sch.xmut.jake.imagestegangraphy.http.response.BaseResponse;
import java.util.Date;

/**
 * Created by jake.lin on 2019/12/24
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CacheService cacheService;

    public UserResponse get(UserRequest userRequest) {
        UserResponse userResponse = new UserResponse();
        UserEntity userEntity = userRepository.findByMobile(userRequest.getMobile());
        if (userEntity == null) {
            userResponse.setMessage("查询不到该用户信息.");
            userResponse.setIsRegister(UserResponse.IS_REGISTER_NO);
            return userResponse;
        }
        userResponse.setIsRegister(UserResponse.IS_REGISTER_YES);
        return userResponse;
    }

    public BaseResponse register(UserRequest userRequest) {
        BaseResponse response = new BaseResponse();
        CacheRequest cacheRequest = new CacheRequest();
        cacheRequest.setMember(CacheConstant.WEB_CACHE_IMAGE_STEGANOGRAPHY_PROJECT_MEMBER);
        cacheRequest.setKey(CacheConstant.CODE_KEY);
        CacheResponse cacheResponse = cacheService.stringGet(cacheRequest);
        if (BaseResponse.FAILD_STATUS.equals(cacheResponse.getStatus()) || !cacheResponse.getValue().equals(userRequest.getCode())) {
            SystemUtils.buildErrorResponse(response, "验证码有误或已过期");
            return response;
        } else {
            UserEntity entity = new UserEntity();
            BeanUtils.copyProperties(userRequest, entity);
            String encrypt = new SecureRandomNumberGenerator().nextBytes().toString();//盐
            String encodePassword = new SimpleHash(UserConstant.ENCRYPTION_TYPE, userRequest.getPassword(), encrypt, UserConstant.ENCRYPTION_TIMES).toString();
            entity.setPassword(encodePassword);
            entity.setEncrypt(encrypt);
            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());
            userRepository.save(entity);
            return response;
        }
    }

    private User buildUserByEntity(UserEntity userEntity) {
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        if (userEntity.getCreateTime() != null) {
            user.setFormatCreateTime(SystemUtils.dateToFormat(userEntity.getCreateTime()));
        }
        if (userEntity.getUpdateTime() != null) {
            user.setFormatUpdateTime(SystemUtils.dateToFormat(userEntity.getUpdateTime()));
        }
        return user;
    }

}
