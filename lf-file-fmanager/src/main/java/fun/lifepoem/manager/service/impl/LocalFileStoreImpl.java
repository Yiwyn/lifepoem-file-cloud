package fun.lifepoem.manager.service.impl;

import fun.lifepoem.api.domain.LpFile;
import fun.lifepoem.core.domain.UserSession;
import fun.lifepoem.core.session.SessionManager;
import fun.lifepoem.manager.domain.LpSysFile;
import fun.lifepoem.manager.domain.LpUserFile;
import fun.lifepoem.manager.mapper.LpSysFileMapper;
import fun.lifepoem.manager.mapper.LpUserFileMapper;
import fun.lifepoem.manager.service.IFileStoreService;
import fun.lifepoem.manager.utils.FileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

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

    @Autowired
    private LpUserFileMapper lpUserFileMapper;

    @Override
    public LpFile uploadFile(MultipartFile file) throws IOException {

        String md5 = FileUploadUtils.calcFileMD5(file.getInputStream());
        log.info("计算文件md5:{}", md5);

        LpSysFile lpSysFile = lpSysFileMapper.selectByMD5(md5);

        if (lpSysFile != null) {
            log.info("该md文件已经存在");
            return LpFile.create(lpSysFile.getFileName(), null);
        }
        LpSysFile uploadFile = FileUploadUtils.upload(localFilePath, file);
        //保存文件信息
        saveUserUploadFile(uploadFile, md5, 1000);

        LpFile lpFile = LpFile.create(uploadFile.getFileName(), null);
        return lpFile;
    }

    @Override
    public String generateUrl(String fileId) {
//        LfUrl lfUrl = FileUploadUtils.generaterUrl(domain, localFilePrefix, fileName);
        return null;
    }

    private void saveUserUploadFile(LpSysFile sysFile, String md5, long expiryDate) {
        sysFile.setMd5(md5);
        sysFile.setDelFlag(false);
        sysFile.setCreateDt(new Date());
        lpSysFileMapper.insert(sysFile);
        log.info("保存文件信息完成");
        UserSession userSession = SessionManager.get();
        LpUserFile userFile = new LpUserFile();
        userFile.setUserId(userSession.getUserId());
        userFile.setFileId(sysFile.getId());
        userFile.setCreateDt(new Date());
        lpUserFileMapper.insert(userFile);

    }

}
