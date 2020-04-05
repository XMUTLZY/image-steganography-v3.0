package sch.xmut.jake.imagestegangraphy.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sch.xmut.jake.imagestegangraphy.domain.user.UserEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by jake.lin on 2019/12/24
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByMobile(String mobile);
    @Query(value = "select * from user u where u.mobile like concat('%', :mobile,'%') and u.account_name " +
            "like concat('%', :accountName, '%') and u.company like concat('%', :company, '%')", nativeQuery = true)
    List<UserEntity> findUser(@Param("mobile") String mobile, @Param("company") String company, @Param("accountName") String accountName);
    Page<UserEntity> findAllByStatus(Pageable pageable, Integer status);
    UserEntity findAllById(Integer id);
    @Query(value = "select * from user u where u.create_time > :lastDate and u.create_time < :nowDate", nativeQuery = true)
    List<UserEntity> findUserByDate(@Param("lastDate") String lastDate, @Param("nowDate") String nowDate);
    UserEntity findByMobileAndStatus(String mobile, Integer status);
}
