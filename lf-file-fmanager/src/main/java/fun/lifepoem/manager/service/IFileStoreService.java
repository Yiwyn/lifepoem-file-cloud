package fun.lifepoem.manager.service;

import fun.lifepoem.api.domain.LpFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Yiwyn
 * @create 2023/2/9 22:49
 */
public interface IFileStoreService {

    LpFile uploadFile(MultipartFile file) throws IOException;

    String generateUrl(String fileId);

    LpFile fastShare(MultipartFile file);
}
