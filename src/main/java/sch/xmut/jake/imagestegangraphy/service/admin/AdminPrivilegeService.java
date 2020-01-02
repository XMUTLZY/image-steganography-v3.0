package sch.xmut.jake.imagestegangraphy.service.admin;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sch.xmut.jake.imagestegangraphy.domain.admin.AdminEntity;
import sch.xmut.jake.imagestegangraphy.domain.admin.AdminPrivilegeEntity;
import sch.xmut.jake.imagestegangraphy.http.vo.admin.Admin;
import sch.xmut.jake.imagestegangraphy.http.vo.admin.AdminPrivilege;
import sch.xmut.jake.imagestegangraphy.repository.admin.AdminPrivilegeRepository;
import sch.xmut.jake.imagestegangraphy.repository.admin.AdminPrivilegeRoleRepository;
import sch.xmut.jake.imagestegangraphy.repository.admin.AdminRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jake.lin on 2020/1/1
 */
@Service
public class AdminPrivilegeService {
    @Autowired
    private AdminPrivilegeRoleRepository adminPrivilegeRoleRepository;
    @Autowired
    private AdminPrivilegeRepository adminPrivilegeRepository;
    @Autowired
    private AdminRepository adminRepository;

    public List<AdminPrivilege> getAllAdminPrivilege(Admin admin) {
        AdminEntity adminEntity = adminRepository.findByMobile(admin.getMobile());
        List<Integer> roleIdList = adminPrivilegeRoleRepository.findPrivilegeIdList(adminEntity.getRoleId());
        List<AdminPrivilege> entityList = convertToAdminPrivilegeList(adminPrivilegeRepository.getPrivilegeList(roleIdList));
        return entityList;
    }

    private List<AdminPrivilege> convertToAdminPrivilegeList(List<AdminPrivilegeEntity> entityList) {
        List<AdminPrivilege> adminPrivilegeList = new ArrayList<>();
        if (CollectionUtils.isEmpty(entityList))
            return null;
        for (AdminPrivilegeEntity adminPrivilegeEntity : entityList) {
            AdminPrivilege adminPrivilege = new AdminPrivilege();
            BeanUtils.copyProperties(adminPrivilegeEntity, adminPrivilege);
            adminPrivilegeList.add(adminPrivilege);
        }
        return adminPrivilegeList;
    }
}
