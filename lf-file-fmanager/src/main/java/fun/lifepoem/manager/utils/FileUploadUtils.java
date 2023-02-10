package fun.lifepoem.manager.utils;

import fun.lifepoem.core.utils.SnowFlakeUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.lang.model.element.VariableElement;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @author Yiwyn
 * @create 2023/2/7 22:10
 */
public class FileUploadUtils {

    private static final long FILE_MAX_SIZE = 50 * 1024 * 1024;
    private static final long FILE_NAME_MAX_SIZE = 128;


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

    public static String generaterAbsFile(String baseDir, String fileName, String extension) {
        return baseDir + fileName + extension;
    }


}
