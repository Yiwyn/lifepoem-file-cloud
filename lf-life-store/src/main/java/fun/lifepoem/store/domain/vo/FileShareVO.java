package fun.lifepoem.store.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Yiwyn
 * @create 2023/2/13 23:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileShareVO implements Serializable {
    private static final long serialVersionUID = 1L;

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
}
