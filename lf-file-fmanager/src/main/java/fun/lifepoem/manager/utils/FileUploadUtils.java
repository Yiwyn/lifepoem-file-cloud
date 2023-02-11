package fun.lifepoem.manager.utils;

import fun.lifepoem.api.domain.LfUrl;
import fun.lifepoem.core.utils.SnowFlakeUtils;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @author Yiwyn
 * @create 2023/2/7 22:10
 */
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

    public static LfUrl generaterUrl(@NonNull String domain, @NonNull String prefix, @NonNull String assestName) {

        LfUrl lfUrl = new LfUrl();
        lfUrl.setDomain(domain.replace("/", ""));
        lfUrl.setDomain(prefix.replace("/", ""));
        lfUrl.setDomain(assestName.replace("/", ""));
        LfUrl url = clearSlash(lfUrl);

        lfUrl.setUrl(String.format("%s/%s/%s", lfUrl.getDomain(),lfUrl.getPrefix(),lfUrl.getAssestName()));
        return url;
    }

    private static LfUrl clearSlash(LfUrl param) {
        LfUrl lfUrl = new LfUrl();
        lfUrl.setDomain(param.getDomain().replace("/", ""));
        lfUrl.setDomain(param.getPrefix().replace("/", ""));
        lfUrl.setDomain(param.getAssestName().replace("/", ""));
        return lfUrl;
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
