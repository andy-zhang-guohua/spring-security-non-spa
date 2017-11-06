package andy.backyard.admin.service;

import andy.backyard.admin.repo.RoleRepository;
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
public class RoleService {
    @Autowired
    RoleRepository repo;

    public long addRole(String name) {
        andy.backyard.admin.entity.Role entity = new andy.backyard.admin.entity.Role();
        entity.setName(name);
        repo.save(entity);

        return entity.getId();
    }

    public andy.backyard.admin.model.Role findById(long roleId) {
        andy.backyard.admin.entity.Role entity = repo.findOne(roleId);
        andy.backyard.admin.model.Role model = DTOUtils.to(entity, andy.backyard.admin.model.Role.class);
        return model;
    }

    public andy.backyard.admin.model.Role findByName(String name) {
        andy.backyard.admin.entity.Role entity = repo.findByName(name);
        andy.backyard.admin.model.Role model = DTOUtils.to(entity, andy.backyard.admin.model.Role.class);
        return model;
    }

    public List<andy.backyard.admin.model.Role> listRoles() {
        List<andy.backyard.admin.entity.Role> entities = repo.findAll();
        List<andy.backyard.admin.model.Role> models = DTOUtils.to(entities, andy.backyard.admin.model.Role.class);
        return models;
    }
}
