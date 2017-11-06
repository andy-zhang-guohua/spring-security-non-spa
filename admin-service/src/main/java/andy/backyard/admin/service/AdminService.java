package andy.backyard.admin.service;

import andy.backyard.admin.entity.Admin;
import andy.backyard.admin.repo.AdminRepository;
import andy.backyard.common.utils.PasswordUtils;
import andy.backyard.common.exception.CommonErrorCode;
import andy.backyard.common.exception.ApplicationException;
import andy.backyard.common.utils.DTOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by ZhangGuohua on 2017/10/19.
 */
@Slf4j
@Service
public class AdminService {
    @Autowired
    AdminRepository repo;

    public long addAdmin(String username, String password) {
        String encodedPassword = PasswordUtils.encode(password);

        Admin entity = new Admin();
        entity.setUsername(username);
        entity.setPassword(encodedPassword);
        repo.save(entity);

        return entity.getId();
    }

    public andy.backyard.admin.model.Admin updateAdminPassword(long adminId, String newRawPassword) {
        String encodedPassword = PasswordUtils.encode(newRawPassword);

        Admin entity = repo.findOne(adminId);
        if (entity == null) {
            log.warn("找不到管理员账号 : adminId = {}", adminId);
            throw new ApplicationException("找不到管理员账号", CommonErrorCode.ADMIN_NOT_FOUND);
        }
        entity.setPassword(encodedPassword);
        return DTOUtils.to(repo.save(entity), andy.backyard.admin.model.Admin.class);
    }

    /**
     * 管理员禁用某个管理员账号
     *
     * @param adminId
     * @param operatorId
     * @return
     */
    public andy.backyard.admin.model.Admin disableAdmin(long adminId, long operatorId) {
        if (operatorId <= 0) {
            log.error("禁用管理员账号操作必须由某个管理员执行: operatorId={}", operatorId);
            throw new ApplicationException("禁用管理员账号操作必须由某个管理员执行", CommonErrorCode.OPERATION_NOT_ALLOWED);
        }

        Admin entity = repo.findOne(adminId);
        if (entity == null) {
            log.warn("找不到管理员账号 : adminId = {}", adminId);
            throw new ApplicationException("找不到管理员账号", CommonErrorCode.ADMIN_NOT_FOUND);
        }

        if (!entity.isEnabled())
            // 账号已经被禁用，则什么都不做，直接返回
            return DTOUtils.to(entity, andy.backyard.admin.model.Admin.class);

        final boolean targetState = false;
        return DTOUtils.to(enableAdmin(entity, targetState, operatorId), andy.backyard.admin.model.Admin.class);
    }

    /**
     * 管理员启用某个管理员账号
     *
     * @param adminId
     * @param operatorId
     * @return
     */
    public andy.backyard.admin.model.Admin enableAdmin(long adminId, long operatorId) {
        if (operatorId <= 0) {
            log.error("启用管理员账号操作必须由某个管理员执行: operatorId={}", operatorId);
            throw new ApplicationException("启用管理员账号操作必须由某个管理员执行", CommonErrorCode.OPERATION_NOT_ALLOWED);
        }

        Admin entity = repo.findOne(adminId);
        if (entity == null) {
            log.warn("找不到管理员账号 : adminId = {}", adminId);
            throw new ApplicationException("找不到管理员账号", CommonErrorCode.ADMIN_NOT_FOUND);
        }

        if (entity.isEnabled())
            // 账号已经被启用，则什么都不做，直接返回
            return DTOUtils.to(entity, andy.backyard.admin.model.Admin.class);


        final boolean targetState = true;
        return DTOUtils.to(enableAdmin(entity, targetState, operatorId), andy.backyard.admin.model.Admin.class);
    }

    private Admin enableAdmin(Admin entity, boolean enabled, long operatorId) {
        entity.setEnabled(enabled);
        entity.setOperatorId(operatorId);
        entity.setLastUpdateTime(LocalDateTime.now());
        repo.save(entity);
        return entity;
    }

    /**
     * 系统锁定管理员账号(非管理员操作，而是在某些条件下自动触发，比如超过指定次数登录失败)
     *
     * @param adminId
     * @return
     */
    public andy.backyard.admin.model.Admin lockAdmin(long adminId) {
        Admin entity = repo.findOne(adminId);
        if (entity == null) {
            log.warn("找不到管理员账号 : adminId = {}", adminId);
            throw new ApplicationException("找不到管理员账号", CommonErrorCode.ADMIN_NOT_FOUND);
        }

        if (entity.isLocked())
            // 账号已经被锁定，则什么都不做，直接返回
            return DTOUtils.to(entity, andy.backyard.admin.model.Admin.class);

        final boolean targetState = true;
        return DTOUtils.to(lockAdmin(entity, targetState), andy.backyard.admin.model.Admin.class);
    }

    /**
     * 系统解锁管理员账号(非管理员操作，而是在某些条件下自动触发，比如管理员重置了被锁定管理员账号的密码)
     *
     * @param adminId
     * @return
     */
    public andy.backyard.admin.model.Admin unlockAdmin(long adminId) {
        Admin entity = repo.findOne(adminId);
        if (entity == null) {
            log.warn("找不到管理员账号 : adminId = {}", adminId);
            throw new ApplicationException("找不到管理员账号", CommonErrorCode.ADMIN_NOT_FOUND);
        }

        if (!entity.isLocked())
            // 账号已经被解锁，则什么都不做，直接返回
            return DTOUtils.to(entity, andy.backyard.admin.model.Admin.class);

        final boolean targetState = false;
        return DTOUtils.to(lockAdmin(entity, targetState), andy.backyard.admin.model.Admin.class);
    }

    private Admin lockAdmin(Admin entity, boolean locked) {
        entity.setLocked(locked);
        entity.setLastUpdateTime(LocalDateTime.now());
        repo.save(entity);
        return entity;
    }

    public andy.backyard.admin.model.Admin findById(long adminId) {
        Admin entity = repo.findOne(adminId);
        andy.backyard.admin.model.Admin model = DTOUtils.to(entity, andy.backyard.admin.model.Admin.class);
        return model;
    }

    public andy.backyard.admin.model.Admin findByUsername(String username) {
        Admin entity = repo.findByUsername(username);
        andy.backyard.admin.model.Admin model = DTOUtils.to(entity, andy.backyard.admin.model.Admin.class);
        return model;
    }

    public List<andy.backyard.admin.model.Admin> listAdmins() {
        List<Admin> entities = repo.findAll();
        List<andy.backyard.admin.model.Admin> models = DTOUtils.to(entities, andy.backyard.admin.model.Admin.class);
        return models;
    }

    public Page<andy.backyard.admin.model.Admin> pageAdmins(Pageable pageable) {
        Page<Admin> entities = repo.findAll(pageable);
        Page<andy.backyard.admin.model.Admin> models = DTOUtils.to(entities, andy.backyard.admin.model.Admin.class);
        return models;
    }
}
