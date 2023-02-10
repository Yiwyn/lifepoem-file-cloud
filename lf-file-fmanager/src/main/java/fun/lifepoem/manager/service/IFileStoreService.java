package fun.lifepoem.manager.service;

import fun.lifepoem.api.domain.LfFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Yiwyn
 * @create 2023/2/9 22:49
 */
public interface IFileStoreService {

    LfFile uploadFile(MultipartFile file) throws IOException;
}
