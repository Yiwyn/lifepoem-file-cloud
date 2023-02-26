package fun.lifepoem.store.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName lp_group_record
 */
@Data
public class LpGroupRecord implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 分组名
     */
    private String groupName;

    /**
     * 父分组id
     */
    private Integer parentId;

    /**
     * 启用状态
     */
    private Boolean status;

    private static final long serialVersionUID = 1L;
}