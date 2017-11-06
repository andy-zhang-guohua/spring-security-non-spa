package andy.backyard.common.entity;

import andy.backyard.common.model.BaseJSONObject;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by ZhangGuohua on 2017/10/19.
 */
@MappedSuperclass
@Data
public abstract class EntityCommon extends BaseJSONObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, columnDefinition = "bigint COMMENT '自增id'")
    private Long id;

    @Column(name = "create_time", nullable = false, columnDefinition = "DATETIME COMMENT '创建时间'", updatable = false)
    private LocalDateTime createTime;

    @Column(name = "last_update_time", nullable = true, columnDefinition = "DATETIME COMMENT '最后更新时间'")
    private LocalDateTime lastUpdateTime;

    @Column(name = "remark", nullable = true, columnDefinition = "varchar(1000) COMMENT '备注'")
    private String remark;

    @Column(name = "operator_id", nullable = true, columnDefinition = "bigint COMMENT '操作人id'")
    private Long operatorId = 0L;

    /**
     * 乐观锁控制
     */
    @Version
    private Long version;

    public EntityCommon() {
        createTime = LocalDateTime.now();
        lastUpdateTime = createTime;
    }
}
