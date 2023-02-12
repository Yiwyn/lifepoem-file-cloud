package fun.lifepoem.manager.service.impl;

import fun.lifepoem.api.domain.LpFile;
import fun.lifepoem.api.domain.LpUrl;
import fun.lifepoem.manager.service.IFileStoreService;
import fun.lifepoem.manager.utils.FileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Yiwyn
 * @create 2023/2/9 22:50
 */
@Primary
@Service
@Slf4j
public class LocalFileStoreImpl implements IFileStoreService {

    @Value("${lf-file.domain}")
    private String domain;

    @Value("${lf-file.local-file-path}")
    private String localFilePath;

    @Value("${lf-file.local-file-prefix}")
    private String localFilePrefix;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public LpFile uploadFile(MultipartFile file) throws IOException {

        String md5 = FileUploadUtils.calcFileMD5(file.getInputStream());
        log.info("md5:{}", md5);
        String fileName = FileUploadUtils.upload(localFilePath, file);

        LpUrl lpUrl = FileUploadUtils.generaterUrl(domain + contextPath, localFilePrefix, fileName);
        LpFile lpFile = LpFile.create(fileName, lpUrl.getUrl());
        return lpFile;
    }

    @Override
    public String generateUrl(String fileId) {
//        LfUrl lfUrl = FileUploadUtils.generaterUrl(domain, localFilePrefix, fileName);
        return null;
    }
}
