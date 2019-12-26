package sch.xmut.jake.imagestegangraphy.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sch.xmut.jake.imagestegangraphy.domain.user.UserEntity;
import sch.xmut.jake.imagestegangraphy.http.request.user.UserRequest;
import sch.xmut.jake.imagestegangraphy.http.response.user.UserResponse;
import sch.xmut.jake.imagestegangraphy.http.vo.user.User;
import sch.xmut.jake.imagestegangraphy.repository.user.UserRepository;
import sch.xmut.jake.imagestegangraphy.utils.SystemUtils;

/**
 * Created by jake.lin on 2019/12/24
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponse get(UserRequest userRequest) {
        UserResponse userResponse = new UserResponse();
        UserEntity userEntity = userRepository.findByMobile(userRequest.getMobile());
        if (userEntity == null) {
            userResponse.setMessage("查询不到该用户信息.");
            userResponse.setIsRegister(UserResponse.IS_REGISTER_NO);
            return userResponse;
        }
        User user = buildUserByEntity(userEntity);
        userResponse.setVo(user);
        userResponse.setIsRegister(UserResponse.IS_REGISTER_YES);
        return userResponse;
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
