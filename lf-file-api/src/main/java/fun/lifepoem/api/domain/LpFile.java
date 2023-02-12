package fun.lifepoem.api.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Yiwyn
 * @create 2023/2/10 21:23
 */
@Data
public class LpFile implements Serializable {
    private static final long serialVersionUID = 1L;
    private String fileName;

    private String url;

    private LpFile() {
    }

    private LpFile(String fileName, String url) {
        this.fileName = fileName;
        this.url = url;
    }

    public static LpFile create(String fileName, String url) {
        return new LpFile(fileName, url);
    }


}
