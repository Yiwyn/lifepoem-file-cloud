package fun.lifepoem.store.service;

import fun.lifepoem.api.domain.LpFileInfo;
import fun.lifepoem.api.domain.LpUrl;
import fun.lifepoem.core.constant.Constants;
import fun.lifepoem.core.domain.UserSession;
import fun.lifepoem.core.session.SessionManager;
import fun.lifepoem.core.utils.CaptchaUtils;
import fun.lifepoem.core.utils.FileUtils;
import fun.lifepoem.core.utils.SecureUtils;
import fun.lifepoem.core.utils.SnowFlakeUtils;
import fun.lifepoem.store.domain.LpShareRecord;
import fun.lifepoem.store.domain.vo.FileShareVO;
import fun.lifepoem.store.mapper.LpShareRecordMapper;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author Yiwyn
 * @create 2023/2/22 21:31
 */
@Service
public class StoreService {


    @Value("${lf-file.local-file-prefix}")
    private String localFilePrefix;

    @Value("${lf-file.domain}")
    private String domain;


    @Autowired
    private LpShareRecordMapper lpShareRecordMapper;

    @Autowired
    private RemoteFileSercice remoteFileSercice;

    /**
     * 快速分享
     *
     * @param file 上传的文件
     * @return
     * @throws IOException
     */
    public FileShareVO fastShare(MultipartFile file) throws IOException {
        String md5 = FileUtils.calcFileMD5(file.getInputStream());


        return null;
    }


    public LpShareRecord getShareRecord(String shortKey, String shareKey) {
        LpShareRecord lpShareRecord = lpShareRecordMapper.selectByShortKeyAndShareKey(shortKey, shareKey);
        return lpShareRecord;
    }

    public String getShareFileId(String shortKey) {
        LpShareRecord lpShareRecord = lpShareRecordMapper.selectByShortKey(shortKey);
        if (ObjectUtils.isEmpty(lpShareRecord)) {
            return null;
        }
        return lpShareRecord.getFileId().toString();
    }


    /**
     * 生成url信息
     *
     * @param fileId 文件id
     * @return
     */
    public FileShareVO shareFile(String fileId) {

        LpFileInfo fileInfo = remoteFileSercice.getFileInfo(fileId);
        if (fileInfo == null) {
            return null;
        }

        Integer userId = SessionManager.get().getUserId();

        List<LpShareRecord> existShareRecord = lpShareRecordMapper.selectByUserAndFile(userId, fileId);
        //检测同一文件分享记录是否超过限定次数 ，超过则抛出异常
        if (existShareRecord.size() >= Constants.MAX_SHARE_ONEFILE_MAX_COUNT) {
            throw new RuntimeException("分享次数超过" + Constants.MAX_SHARE_ONEFILE_MAX_COUNT);
        }

        long snowKey = SnowFlakeUtils.Instance().getSnowId();

        String encode = SecureUtils.encode(String.valueOf(fileInfo.getFileId()));

        LpUrl lpUrl = generaterUrl(domain, localFilePrefix, encode);

        String url = lpUrl.getUrl();
        LpShareRecord lpShareRecord = new LpShareRecord();
        lpShareRecord.setShortKey(String.valueOf(snowKey));
        LpShareRecord record = saveShareInfo(lpShareRecord, fileInfo.getFileId(), url);
        FileShareVO fileShareVO = new FileShareVO();
        BeanUtils.copyProperties(record, fileShareVO);
        return fileShareVO;
    }

    /**
     * 保存分享信息
     *
     * @param lpShareRecord
     * @param fileId
     * @param url
     * @return
     */
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
        lpUrl.setDomain(domain);
        lpUrl.setPrefix(prefix);
        lpUrl.setAssestName(assestName);
        LpUrl url = clearSlash(lpUrl);

        lpUrl.setUrl(String.format("%s%s/%s", lpUrl.getDomain(), lpUrl.getPrefix(), lpUrl.getAssestName()));
        return url;
    }

    private static LpUrl clearSlash(LpUrl param) {

        String domain = param.getDomain();
        int lastIndex = domain.lastIndexOf("/");
        if (lastIndex == domain.length() - 1) {
            domain = domain.substring(0, domain.length() - 1);
        }
        param.setDomain(domain);
        param.setAssestName(param.getAssestName().replace("/", ""));
        return param;
    }


}
