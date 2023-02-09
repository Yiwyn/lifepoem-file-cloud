package fun.lifepoem.manager.service.impl;

import fun.lifepoem.manager.service.IFileStoreService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Yiwyn
 * @create 2023/2/9 22:50
 */
@Primary
@Service
public class LocalFileStoreImpl implements IFileStoreService {

    @Override
    public String uploadFile(MultipartFile file) {

        return "success";
    }
}
