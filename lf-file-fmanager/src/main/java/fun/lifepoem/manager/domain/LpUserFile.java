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
     * 分享码
     */
    private String shareKey;

    /**
     * 分享链接
     */
    private String shareLink;

    /**
     * 过期时间
     */
    private Date expiryDt;

    /**
     * 创建时间
     */
    private Date createDt;

    private static final long serialVersionUID = 1L;
}