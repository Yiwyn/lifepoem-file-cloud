package fun.lifepoem.manager.service.impl;

import fun.lifepoem.api.domain.LpFile;
import fun.lifepoem.api.domain.LpUrl;
import fun.lifepoem.manager.domain.LpSysFile;
import fun.lifepoem.manager.mapper.LpSysFileMapper;
import fun.lifepoem.manager.service.IFileStoreService;
import fun.lifepoem.manager.utils.FileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private LpSysFileMapper lpSysFileMapper;

    @Override
    public LpFile uploadFile(MultipartFile file) throws IOException {

        String md5 = FileUploadUtils.calcFileMD5(file.getInputStream());
        log.info("计算文件md5:{}", md5);

        LpSysFile lpSysFile = lpSysFileMapper.selectByMD5(md5);

        if (lpSysFile != null) {
            return LpFile.create(lpSysFile.getFileName(), null);
        }
        String fileName = FileUploadUtils.upload(localFilePath, file);
//        LpUrl lpUrl = FileUploadUtils.generaterUrl(domain + contextPath, localFilePrefix, fileName);
        LpFile lpFile = LpFile.create(fileName, null);
        return lpFile;
    }

    @Override
    public String generateUrl(String fileId) {
//        LfUrl lfUrl = FileUploadUtils.generaterUrl(domain, localFilePrefix, fileName);
        return null;
    }
}
