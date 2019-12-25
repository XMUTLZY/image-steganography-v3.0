package sch.xmut.jake.imagestegangraphy.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sch.xmut.jake.imagestegangraphy.domain.admin.AdminPrivilegeRoleEntity;
import java.util.List;

/**
 * Created by jake.lin on 2019/12/25
 */
public interface AdminPrivilegeRoleRepository extends JpaRepository<AdminPrivilegeRoleEntity, Integer> {
    @Query(value = "select privilege_id from admin_privilege_role where role_id = :roleId", nativeQuery = true)
    List<Integer> findPrivilegeIdList(@Param("roleId") Integer roleId);
}
