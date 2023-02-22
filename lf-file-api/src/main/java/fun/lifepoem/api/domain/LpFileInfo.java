package fun.lifepoem.api.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Yiwyn
 * @create 2023/2/22 22:35
 */
@Data
public class LpFileInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer fileId;

    private String fileName;
}
