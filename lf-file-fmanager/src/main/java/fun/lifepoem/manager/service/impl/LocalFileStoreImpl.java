package fun.lifepoem.manager.service.impl;

import fun.lifepoem.api.domain.LpFile;
import fun.lifepoem.api.domain.LpUrl;
import fun.lifepoem.core.domain.UserSession;
import fun.lifepoem.core.session.SessionManager;
import fun.lifepoem.core.utils.CaptchaUtils;
import fun.lifepoem.manager.domain.LpShareRecord;
import fun.lifepoem.manager.domain.LpSysFile;
import fun.lifepoem.manager.domain.LpUserFile;
import fun.lifepoem.manager.domain.vo.FileShareVO;
import fun.lifepoem.manager.mapper.LpShareRecordMapper;
import fun.lifepoem.manager.mapper.LpSysFileMapper;
import fun.lifepoem.manager.mapper.LpUserFileMapper;
import fun.lifepoem.manager.service.IFileStoreService;
import fun.lifepoem.manager.utils.FileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
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

    @Autowired
    private LpShareRecordMapper lpShareRecordMapper;

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
        saveUserUploadFile(uploadFile, md5);

        LpFile lpFile = LpFile.create(uploadFile.getFileName(), null);
        return lpFile;
    }

    @Override
    public FileShareVO generateUrl(String fileId) {
        LpSysFile lpSysFile = lpSysFileMapper.selectByPrimaryKey(Long.parseLong(fileId));
        if (lpSysFile == null) {
            return null;
        }
        LpUrl lpUrl = FileUploadUtils.generaterUrl(domain + contextPath, localFilePrefix, lpSysFile.getFileName());

        String url = lpUrl.getUrl();
        LpShareRecord lpShareRecord = new LpShareRecord();
        LpShareRecord record = saveShareInfo(lpShareRecord, lpSysFile.getId(), url);
        FileShareVO fileShareVO = new FileShareVO();
        BeanUtils.copyProperties(record, fileShareVO);
        return fileShareVO;
    }


    //    快速分享功能 ，上传文件同时生成sharekey 多设备可下载
    @Override
    public FileShareVO fastShare(MultipartFile file) throws IOException {
        String md5 = FileUploadUtils.calcFileMD5(file.getInputStream());


        return null;
    }

    private void saveUserUploadFile(LpSysFile sysFile, String md5) {
        sysFile.setMd5(md5);
        sysFile.setDelFlag(false);
        sysFile.setCreateDt(new Date());
        lpSysFileMapper.insert(sysFile);
        log.info("保存文件信息完成");
        UserSession userSession = SessionManager.get();
        LpUserFile userFile = new LpUserFile();
        userFile.setUserId(userSession.getUserId());
        userFile.setFileId(sysFile.getId());
        userFile.setUploadDt(new Date());
        lpUserFileMapper.insert(userFile);

    }

    @Override
    public BufferedInputStream getPathFile(String fileId) throws IOException {
        LpSysFile lpSysFile = lpSysFileMapper.selectByPrimaryKey(Long.parseLong(fileId));
        BufferedInputStream file = FileUploadUtils.getLoclFile(lpSysFile.getFilePath());
        return file;
    }

    private LpShareRecord saveShareInfo(LpShareRecord lpShareRecord, int fileId, String url) {
        UserSession userSession = SessionManager.get();
        lpShareRecord.setUserId(userSession.getUserId());
        lpShareRecord.setFileId(fileId);
        lpShareRecord.setCreateDt(new Date());
        lpShareRecord.setShareLink(url);
        lpShareRecord.setExpiryDt(new Date());
        lpShareRecord.setExpiryStatus(false);
        String captcha = CaptchaUtils.generaterCaptcha(6);
        lpShareRecord.setShareKey(captcha);
        lpShareRecordMapper.insert(lpShareRecord);
        return lpShareRecord;
    }

}
