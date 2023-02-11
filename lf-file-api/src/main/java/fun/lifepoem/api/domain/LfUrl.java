package fun.lifepoem.api.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Yiwyn
 * @create 2023/2/11 22:56
 */
@Data
public class LfUrl implements Serializable {
    private static final long serialVersionUID = 1L;

    //    www.lifeopoem.com
    private String domain;

    //    p
    private String prefix;

    //    test.txt
    private String assestName;


    private String url;

}
