package fun.lifepoem.manager.service;

import fun.lifepoem.api.domain.LpFile;
import fun.lifepoem.manager.domain.vo.FileShareVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * @author Yiwyn
 * @create 2023/2/9 22:49
 */
public interface IFileStoreService {

    LpFile uploadFile(MultipartFile file) throws IOException;

    FileShareVO generateUrl(String fileId);

    FileShareVO fastShare(MultipartFile file) throws IOException;

    BufferedInputStream getPathFile(String fileId) throws IOException;
}
