package fun.lifepoem.manager.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName lp_share_record
 */
@Data
public class LpShareRecord implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 分享人id
     */
    private Integer userId;

    /**
     * 分享的文件id
     */
    private Integer fileId;

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
     * 过期状态
     */
    private Boolean expiryStatus;

    /**
     * 分享时间
     */
    private Date createDt;

    private static final long serialVersionUID = 1L;
}