package andy.backyard.admin.service;

import andy.backyard.admin.entity.RoleAuthority;
import andy.backyard.admin.enums.AdminAuthority;
import andy.backyard.admin.repo.RoleAuthorityRepository;
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
public class RoleAuthorityService {
    @Autowired
    RoleAuthorityRepository repo;

    public long addRoleAuthority(long roleId, AdminAuthority authority) {
        RoleAuthority entity = new RoleAuthority();
        entity.setRoleId(roleId);
        entity.setAuthority(authority);
        repo.save(entity);

        return entity.getId();
    }

    public List<andy.backyard.admin.model.RoleAuthority> listAuthoritiesOfRole(long roleId) {
        List<RoleAuthority> entities = repo.listAuthoritiesOfRole(roleId);
        List<andy.backyard.admin.model.RoleAuthority> models = DTOUtils.to(entities, andy.backyard.admin.model.RoleAuthority.class);
        return models;
    }
}
