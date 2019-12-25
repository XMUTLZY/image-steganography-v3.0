package sch.xmut.jake.imagestegangraphy.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sch.xmut.jake.imagestegangraphy.domain.admin.AdminPrivilegeEntity;
import java.util.List;

/**
 * Created by jake.lin on 2019/12/25
 */
public interface AdminPrivilegeRepository extends JpaRepository<AdminPrivilegeEntity,Integer> {
    @Query(value = "select * from admin_privilege where id in(:list)", nativeQuery = true)
    List<AdminPrivilegeEntity> getPrivilegeList(@Param("list") List<Integer> list);
}
