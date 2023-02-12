package fun.lifepoem.manager.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @TableName lp_sys_file
 */
@Data
@EqualsAndHashCode
public class LpSysFile implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件扩展名
     */
    private String fileExtension;

    /**
     * 文件的本地存储位置
     */
    private String filePath;

    /**
     * 文件md5值
     */
    private String md5;

    /**
     * 删除标志
     */
    private Boolean delFlag;

    /**
     * 创建时间
     */
    private Date createDt;

    /**
     * 更新时间
     */
    private Date updateDt;

    private static final long serialVersionUID = 1L;
}