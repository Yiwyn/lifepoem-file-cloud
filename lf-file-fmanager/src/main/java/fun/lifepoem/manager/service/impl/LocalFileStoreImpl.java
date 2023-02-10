package fun.lifepoem.manager.service.impl;

import fun.lifepoem.api.domain.LfFile;
import fun.lifepoem.manager.service.IFileStoreService;
import fun.lifepoem.manager.utils.FileUploadUtils;
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
    public LfFile uploadFile(MultipartFile file) throws IOException {

        String fileName = FileUploadUtils.upload(localFilePath, file);
        String url = String.format("%s%s%s/%s", domain, contextPath, localFilePrefix, fileName);

        LfFile lfFile = LfFile.create(fileName, url);

        return lfFile;
    }
}
