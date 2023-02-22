package fun.lifepoem.core.utils;

import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Yiwyn
 * @create 2023/2/22 21:34
 */
public class FileUtils {

    private FileUtils() {

    }

    /**
     * 计算上传文件的md5
     *
     * @param fileInputStream
     * @return
     * @throws IOException
     */
    public static String calcFileMD5(InputStream fileInputStream) throws IOException {
        String md5 = DigestUtils.md5DigestAsHex(fileInputStream);
        return md5;
    }


}
