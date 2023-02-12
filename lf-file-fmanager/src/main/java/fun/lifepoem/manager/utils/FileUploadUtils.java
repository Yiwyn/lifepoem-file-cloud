package fun.lifepoem.manager.utils;

import fun.lifepoem.api.domain.LpUrl;
import fun.lifepoem.core.utils.SnowFlakeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @author Yiwyn
 * @create 2023/2/7 22:10
 */
@Slf4j
public class FileUploadUtils {

    private static final long FILE_MAX_SIZE = 50 * 1024 * 1024;
    private static final long FILE_NAME_MAX_SIZE = 128;


    /**
     * 文件上传
     *
     * @param baseDir
     * @param file
     * @return
     * @throws IOException
     */
    public static String upload(String baseDir, MultipartFile file) throws IOException {
        int fileNameLength = Objects.requireNonNull(file.getOriginalFilename()).length();
        if (fileNameLength > FILE_NAME_MAX_SIZE) {
            throw new RuntimeException("文件名过长");
        }
        long fileSize = file.getSize();
        if (fileSize > FILE_MAX_SIZE) {
            throw new RuntimeException("文件过大");
        }

        String originalFilename = file.getOriginalFilename();
        long snowId = SnowFlakeUtils.Instance().getSnowId();

        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String absFile = generaterAbsFile(baseDir, String.valueOf(snowId), extension);

        file.transferTo(Paths.get(absFile));
        return snowId + extension;
    }

    public static LpUrl generaterUrl(@NonNull String domain, @NonNull String prefix, @NonNull String assestName) {

        LpUrl lpUrl = new LpUrl();
        int lastIndex = domain.lastIndexOf("/");
        if (lastIndex == domain.length() - 1) {
            domain = domain.substring(0, domain.length() - 1);
        }
        lpUrl.setDomain(domain);
        lpUrl.setPrefix(prefix);
        lpUrl.setAssestName(assestName);
        LpUrl url = clearSlash(lpUrl);

        lpUrl.setUrl(String.format("%s/%s/%s", lpUrl.getDomain(), lpUrl.getPrefix(), lpUrl.getAssestName()));
        return url;
    }

    /**
     * 计算上传文件的md5
     * @param fileInputStream
     * @return
     * @throws IOException
     */
    public static String calcFileMD5(InputStream fileInputStream) throws IOException {
        String md5 = DigestUtils.md5DigestAsHex(fileInputStream);
        return md5;
    }


    private static LpUrl clearSlash(LpUrl param) {

        String domain = param.getDomain();
        int lastIndex = domain.lastIndexOf("/");
        if (lastIndex == domain.length() - 1) {
            domain = domain.substring(0, domain.length() - 1);
        }
        param.setDomain(domain);
        param.setPrefix(param.getPrefix().replace("/", ""));
        param.setAssestName(param.getAssestName().replace("/", ""));
        return param;
    }

    /**
     * 生成绝对路径
     *
     * @param baseDir
     * @param fileName
     * @param extension
     * @return
     */
    private static String generaterAbsFile(String baseDir, String fileName, String extension) {
        return baseDir + fileName + extension;
    }


}
