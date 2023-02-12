package fun.lifepoem.core.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Yiwyn
 * @create 2023/2/12 16:11
 */
@Data
public class UserSession implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer userId;

}
