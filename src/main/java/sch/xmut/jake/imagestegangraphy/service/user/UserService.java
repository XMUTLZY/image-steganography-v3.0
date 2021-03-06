package sch.xmut.jake.imagestegangraphy.service.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import sch.xmut.jake.cache.apicache.http.request.CacheRequest;
import sch.xmut.jake.cache.apicache.http.response.CacheResponse;
import sch.xmut.jake.imagestegangraphy.constants.CacheConstant;
import sch.xmut.jake.imagestegangraphy.constants.OrderConstant;
import sch.xmut.jake.imagestegangraphy.constants.UserConstant;
import sch.xmut.jake.imagestegangraphy.domain.order.OrderEntity;
import sch.xmut.jake.imagestegangraphy.domain.user.UserEntity;
import sch.xmut.jake.imagestegangraphy.http.request.user.ExtractImageRequest;
import sch.xmut.jake.imagestegangraphy.http.request.user.UserRequest;
import sch.xmut.jake.imagestegangraphy.http.response.LayerResponse;
import sch.xmut.jake.imagestegangraphy.http.response.user.ExtractResultResponse;
import sch.xmut.jake.imagestegangraphy.http.response.user.UserResponse;
import sch.xmut.jake.imagestegangraphy.http.vo.user.User;
import sch.xmut.jake.imagestegangraphy.repository.order.OrderRepository;
import sch.xmut.jake.imagestegangraphy.repository.user.UserRepository;
import sch.xmut.jake.imagestegangraphy.service.cache.CacheService;
import sch.xmut.jake.imagestegangraphy.utils.SystemUtils;
import sch.xmut.jake.imagestegangraphy.http.response.BaseResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by jake.lin on 2019/12/24
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CacheService cacheService;
    @Autowired
    private OrderRepository orderRepository;

    public UserResponse get(UserRequest userRequest) {
        UserResponse userResponse = new UserResponse();
        UserEntity userEntity = userRepository.findByMobileAndStatus(userRequest.getMobile(), UserConstant.USER_STATUS_PASS);
        if (userEntity == null) {
            userResponse.setMessage("查询不到该用户信息.");
            userResponse.setIsRegister(UserResponse.IS_REGISTER_NO);
            return userResponse;
        }
        userResponse.setIsRegister(UserResponse.IS_REGISTER_YES);
        return userResponse;
    }

    @Transactional
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

    public BaseResponse login(UserRequest userRequest) {
        BaseResponse baseResponse = new BaseResponse();
        UserEntity userEntity = userRepository.findByMobileAndStatus(userRequest.getMobile(), UserConstant.USER_STATUS_PASS);
        User user = buildUserByEntity(userEntity);
        if (userEntity == null) {
            SystemUtils.buildErrorResponse(baseResponse, "用户不存在或已被拉黑");
            return baseResponse;
        }
        String encrypt = userEntity.getEncrypt();
        String encodePassword = new SimpleHash(UserConstant.ENCRYPTION_TYPE, userRequest.getPassword(), encrypt, UserConstant.ENCRYPTION_TIMES).toString();
        if (!encodePassword.equals(userEntity.getPassword())) {
            SystemUtils.buildErrorResponse(baseResponse, "密码错误");
        } else {
            baseResponse.setMessage("登录成功");
            //将登录信息放入缓存
            CacheRequest cacheRequest = new CacheRequest();
            cacheRequest.setMember(CacheConstant.WEB_CACHE_IMAGE_STEGANOGRAPHY_PROJECT_MEMBER);
            cacheRequest.setKey(CacheConstant.USER_INFO_KEY);
            cacheRequest.setValue(JSONObject.toJSONString(user));
            cacheService.stringAdd(cacheRequest);
        }
        return baseResponse;
    }

    public LayerResponse getUserList(Pageable pageable) {
        Page<UserEntity> pageList = userRepository.findAllByStatus(pageable, UserConstant.USER_STATUS_PASS);
        List<User> userList = new ArrayList<>();
        for (UserEntity userEntity : pageList) {
            User user = new User();
            BeanUtils.copyProperties(userEntity, user);
            userList.add(user);
        }
        List<User> list = convertToUserList(userRepository.findAll());//所有用户数 用于获取数量
        LayerResponse response = new LayerResponse();
        response.setData(userList);
        response.setCount(list.size());
        return response;
    }

    public LayerResponse userDelete(String mobile) {
        UserEntity userEntity = userRepository.findByMobileAndStatus(mobile, UserConstant.USER_STATUS_PASS);
        userEntity.setStatus(UserConstant.USER_STATUS_NOPASS);
        userEntity.setUpdateTime(new Date());
        userRepository.save(userEntity);
        return new LayerResponse();
    }

    public BaseResponse userGet(String mobile) {
        BaseResponse response = new BaseResponse();
        UserEntity userEntity = userRepository.findByMobileAndStatus(mobile, UserConstant.USER_STATUS_PASS);
        if (userEntity == null) {
            return null;
        }
        User user = new User();
        BeanUtils.copyProperties(userEntity, user);
        response.setVo(user);
        return response;
    }

    @Transactional
    public BaseResponse userUpdate(UserRequest userRequest) {
        BaseResponse response = new BaseResponse();
        UserEntity userEntity = userRepository.findByMobileAndStatus(userRequest.getMobile(), UserConstant.USER_STATUS_PASS);
        if (userEntity == null) {
            SystemUtils.buildErrorResponse(response, "用户不存在");
            return response;
        }
        userEntity.setAccountName(userRequest.getAccountName());
        userEntity.setCareer(userRequest.getCareer());
        userEntity.setCity(userRequest.getCity());
        userEntity.setCompany(userRequest.getCompany());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setPortrait(userRequest.getPortrait());
        userEntity.setRealName(userRequest.getRealName());
        userEntity.setUpdateTime(new Date());
        userRepository.save(userEntity);
        return response;
    }

    public LayerResponse userSearch(UserRequest userRequest) {
        LayerResponse response = new LayerResponse();
        List<UserEntity> userEntityList = userRepository.findUser(userRequest.getMobile(), userRequest.getCompany(), userRequest.getAccountName());
        List<User> userList = convertToUserList(userEntityList);
        if (CollectionUtils.isEmpty(userList)) {
            response.setMsg("查询不到用户信息");
            return response;
        }
        response.setData(userList);
        response.setCount(userList.size());
        return response;
    }

    public BaseResponse updatePassword(UserRequest userRequest) {
        BaseResponse response = new BaseResponse();
        User user = getUserInfoFromCache();
        UserEntity userEntity = userRepository.findByMobileAndStatus(user.getMobile(), UserConstant.USER_STATUS_PASS);
        String encrypt = userEntity.getEncrypt();
        String encodePassword = new SimpleHash(UserConstant.ENCRYPTION_TYPE, userRequest.getOldPassword(), encrypt, UserConstant.ENCRYPTION_TIMES).toString();
        if (encodePassword.equals(userEntity.getPassword())) {
            String newPassword = new SimpleHash(UserConstant.ENCRYPTION_TYPE, userRequest.getNewPassword(), encrypt, UserConstant.ENCRYPTION_TIMES).toString();
            userEntity.setPassword(newPassword);
            userRepository.save(userEntity);
        } else {
            SystemUtils.buildErrorResponse(response, "原密码输入错误");
        }
        return response;
    }

    private List<User> convertToUserList(List<UserEntity> userEntityList) {
        if (CollectionUtils.isEmpty(userEntityList))
            return null;
        List<User> list = new ArrayList<>();
        for (UserEntity userEntity : userEntityList) {
            User user = new User();
            BeanUtils.copyProperties(userEntity, user);
            list.add(user);
        }
        return list;
    }

    public User getUserInfoFromCache() {
        CacheRequest cacheRequest = new CacheRequest();
        cacheRequest.setMember(CacheConstant.WEB_CACHE_IMAGE_STEGANOGRAPHY_PROJECT_MEMBER);
        cacheRequest.setKey(CacheConstant.USER_INFO_KEY);
        User user = JSON.parseObject(cacheService.stringGet(cacheRequest).getValue(), User.class);
        return user;
    }

    public void clearUserInfoFromCache() {
        CacheRequest cacheRequest = new CacheRequest();
        cacheRequest.setMember(CacheConstant.WEB_CACHE_IMAGE_STEGANOGRAPHY_PROJECT_MEMBER);
        cacheRequest.setKey(CacheConstant.USER_INFO_KEY);
        cacheService.stringDelete(cacheRequest);
    }

    public void recordVisitNumberByDay() {
        CacheRequest cacheRequest = new CacheRequest();
        cacheRequest.setMember(CacheConstant.WEB_CACHE_IMAGE_STEGANOGRAPHY_PROJECT_MEMBER);
        cacheRequest.setKey(CacheConstant.USER_VISIT_NUMBER + ":" + SystemUtils.dateToRedis(new Date()));
        String number = cacheService.stringGet(cacheRequest).getValue();
        if (StringUtils.hasText(number)) {
            cacheRequest.setValue(String.valueOf(Integer.parseInt(number) + 666));
        } else {
            cacheRequest.setValue("666");
        }
        cacheService.stringAdd(cacheRequest);
        cacheRequest.setLifeTime(CacheConstant.USER_VISIT_NUMBER_DAY);
        cacheService.keySetTime(cacheRequest);
        recordVisitNumberTotal();
    }

    public void recordVisitNumberTotal() {
        CacheRequest cacheRequest = new CacheRequest();
        cacheRequest.setMember(CacheConstant.WEB_CACHE_IMAGE_STEGANOGRAPHY_PROJECT_MEMBER);
        cacheRequest.setKey(CacheConstant.USER_VISIT_NUMBER);
        String number = cacheService.stringGet(cacheRequest).getValue();
        if (StringUtils.hasText(number)) {
            cacheRequest.setValue(String.valueOf(Integer.parseInt(number) + 666));
        } else {
            cacheRequest.setValue("666");
        }
        cacheService.stringAdd(cacheRequest);
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

    public ExtractResultResponse extractImage(ExtractImageRequest request) throws IOException {
        ExtractResultResponse resultResponse = new ExtractResultResponse();
        //将用户上传的两张图像转化为byte[]
        byte[] imageByte1 = SystemUtils.getByteByNetImage(request.getMajorImage());
        byte[] imageByte2 = SystemUtils.getByteByNetImage(request.getAssistImage());
        User user = getUserInfoFromCache();
        //排除获取当前用户没有支付的订单
        List<OrderEntity> orderEntityList = orderRepository.findAllByUserIdAndOrderStatusNot(user.getId(), OrderConstant.PAYMENT_STATUS_NO);
        for (OrderEntity orderEntity : orderEntityList) {
            byte[] orderImage1Byte = null;
            byte[] orderImage2Byte = null;
            try {
                orderImage1Byte = SystemUtils.getByteByNetImage(orderEntity.getResultImage1());
                orderImage2Byte = SystemUtils.getByteByNetImage(orderEntity.getResultImage2());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (Arrays.equals(imageByte1, orderImage1Byte) && Arrays.equals(imageByte2, orderImage2Byte)) {
                resultResponse.setResultImage(orderEntity.getOrginalImage());
                resultResponse.setResultDate(orderEntity.getHiddenData());
                return resultResponse;
            }
        }
        SystemUtils.buildErrorResponse(resultResponse, "该图像未经本站处理");
        return resultResponse;
    }

}
