package fun.lifepoem.store.service;

import fun.lifepoem.api.domain.LpUrl;
import fun.lifepoem.core.domain.UserSession;
import fun.lifepoem.core.session.SessionManager;
import fun.lifepoem.core.utils.CaptchaUtils;
import fun.lifepoem.core.utils.FileUtils;
import fun.lifepoem.store.domain.LpShareRecord;
import fun.lifepoem.store.domain.vo.FileShareVO;
import fun.lifepoem.store.mapper.LpShareRecordMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * @author Yiwyn
 * @create 2023/2/22 21:31
 */
@Service
public class StoreService {


    @Value("${lf-file.local-file-prefix}")
    private String localFilePrefix;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${lf-file.domain}")
    private String domain;


    @Autowired
    private LpShareRecordMapper lpShareRecordMapper;


    public FileShareVO fastShare(MultipartFile file) throws IOException {
        String md5 = FileUtils.calcFileMD5(file.getInputStream());


        return null;
    }

    public FileShareVO generateUrl(String fileId) {
        LpSysFile lpSysFile = lpSysFileMapper.selectByPrimaryKey(Long.parseLong(fileId));
        if (lpSysFile == null) {
            return null;
        }
        LpUrl lpUrl = generaterUrl(domain + contextPath, localFilePrefix, lpSysFile.getFileName());

        String url = lpUrl.getUrl();
        LpShareRecord lpShareRecord = new LpShareRecord();
        LpShareRecord record = saveShareInfo(lpShareRecord, lpSysFile.getId(), url);
        FileShareVO fileShareVO = new FileShareVO();
        BeanUtils.copyProperties(record, fileShareVO);
        return fileShareVO;
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


    private static LpUrl generaterUrl(@NonNull String domain, @NonNull String prefix, @NonNull String assestName) {

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


}
