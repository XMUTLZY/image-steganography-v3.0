package sch.xmut.jake.imagestegangraphy.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import sch.xmut.jake.imagestegangraphy.domain.admin.AdminRoleEntity;

/**
 * Created by jake.lin on 2019/12/25
 */
public interface AdminRoleRepository extends JpaRepository<AdminRoleEntity, Integer> {
    AdminRoleEntity findAllById(Integer id);
}
