package andy.backyard.common.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by ZhangGuohua on 2017/10/19.
 */
@Data
public class Model extends BaseJSONObject {
    /**
     * product Id
     */
    private long id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 最近一次更改时间
     */
    private LocalDateTime lastUpdateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作员id, 0 表示 没有记录操作员，或者由用户自己操作
     */
    private long operator_id = 0;
}
