package andy.backyard.admin.service;

import andy.backyard.admin.repo.AdminRoleRepository;
import andy.backyard.admin.entity.AdminRole;
import andy.backyard.common.utils.DTOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZhangGuohua on 2017/10/19.
 */
@Slf4j
@Service
public class AdminRoleService {
    @Autowired
    AdminRoleRepository repo;

    public long addAdminRole(long adminId, long roleId) {
        AdminRole entity = new AdminRole();
        entity.setAdminId(adminId);
        entity.setRoleId(roleId);
        repo.save(entity);

        return entity.getId();
    }

    public List<andy.backyard.admin.model.AdminRole> listAdminRoles(long adminId) {
        List<AdminRole> entities = repo.listRolesOfAdmin(adminId);
        List<andy.backyard.admin.model.AdminRole> models = DTOUtils.to(entities, andy.backyard.admin.model.AdminRole.class);
        return models;
    }
}
