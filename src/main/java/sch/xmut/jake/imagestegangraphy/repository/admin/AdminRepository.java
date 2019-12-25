package sch.xmut.jake.imagestegangraphy.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import sch.xmut.jake.imagestegangraphy.domain.admin.AdminEntity;

/**
 * Created by jake.lin on 2019/12/25
 */
public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
    AdminEntity findByMobile(String mobile);
}
