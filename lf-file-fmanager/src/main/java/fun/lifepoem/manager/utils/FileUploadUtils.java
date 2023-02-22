package fun.lifepoem.manager.utils;

import fun.lifepoem.api.domain.LpUrl;
import fun.lifepoem.core.utils.SnowFlakeUtils;
import fun.lifepoem.manager.domain.LpSysFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
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
    public static LpSysFile upload(String baseDir, MultipartFile file) throws IOException {
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

        LpSysFile lpSysFile = new LpSysFile();
        lpSysFile.setFileName(snowId + extension);
        lpSysFile.setFileExtension(extension);
        lpSysFile.setFilePath(absFile);

        return lpSysFile;
    }


    public static BufferedInputStream getLoclFile(String path) throws IOException {
        InputStream inputStream = Files.newInputStream(Paths.get(path));
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        return bis;
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
