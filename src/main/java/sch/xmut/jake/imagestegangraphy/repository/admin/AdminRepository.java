package sch.xmut.jake.imagestegangraphy.repository.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sch.xmut.jake.imagestegangraphy.domain.admin.AdminEntity;
import java.util.List;

/**
 * Created by jake.lin on 2019/12/25
 */
public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
    AdminEntity findByMobile(String mobile);
    Page<AdminEntity> findAllByStatus(Pageable pageable, Integer status);
    List<AdminEntity> findAllByStatus(Integer status);
    List<AdminEntity> findAllById(Integer id);
    @Query(value = "select * from admin where user_name like concat('%', :userName,'%') and mobile like concat('%', :mobile,'%')" +
            " and (role_id = :roleId or :roleId = 0) and status = 1", nativeQuery = true)
    List<AdminEntity> adminSearch(@Param("userName") String userName, @Param("mobile") String mobile, @Param("roleId") Integer roleId);
}
