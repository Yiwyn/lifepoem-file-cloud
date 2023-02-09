package fun.lifepoem.manager.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Yiwyn
 * @create 2023/2/9 22:49
 */
public interface IFileStoreService {

    String uploadFile(MultipartFile file);
}
