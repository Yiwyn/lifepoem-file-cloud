package fun.lifepoem.manager.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName lp_user_file
 */
@Data
public class LpUserFile implements Serializable {
    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private Integer fileId;

    /**
     * 创建时间
     */
    private Date uploadDt;

    /**
     * 分组id
     */
    private Integer groupId;

    /**
     * 文件分组
     */
    private String groupName;

    /**
     * 分享次数
     */
    private Integer shareCount;

    private static final long serialVersionUID = 1L;
}