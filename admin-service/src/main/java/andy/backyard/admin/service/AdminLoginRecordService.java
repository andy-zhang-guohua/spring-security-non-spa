package andy.backyard.admin.service;

import andy.backyard.admin.repo.AdminLoginRecordRepository;
import andy.backyard.admin.entity.AdminLoginRecord;
import andy.backyard.common.utils.DTOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by ZhangGuohua on 2017/10/19.
 */
@Slf4j
@Service
public class AdminLoginRecordService {
    @Autowired
    AdminLoginRecordRepository repo;

    /**
     * 记录一次管理员用户登录
     *
     * @param username      管理员用户名
     * @param loginTime     登录时间
     * @param remoteAddress 登录地址
     * @return 登录记录的id
     */
    public long recordLogin(String username, LocalDateTime loginTime, String remoteAddress) {
        AdminLoginRecord entity = new AdminLoginRecord();
        entity.setUsername(username);
        entity.setLoginTime(loginTime);
        entity.setRemoteAddress(remoteAddress);
        repo.save(entity);

        return entity.getId();
    }

    public andy.backyard.admin.model.AdminLoginRecord findUserLatestLoginRecord(String username) {
        AdminLoginRecord entity = repo.findUserLatestLoginRecord(username);
        return DTOUtils.to(entity, andy.backyard.admin.model.AdminLoginRecord.class);
    }

    public andy.backyard.admin.model.AdminLoginRecord findUserLatestLoginRecord(String username, String remoteAddress) {
        AdminLoginRecord entity = repo.findUserLatestLoginRecord(username, remoteAddress);
        return DTOUtils.to(entity, andy.backyard.admin.model.AdminLoginRecord.class);
    }
}
