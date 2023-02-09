package fun.lifepoem.manager.controller;

import fun.lifepoem.core.domain.RestResponse;
import fun.lifepoem.manager.service.IFileStoreService;
import fun.lifepoem.manager.utils.FileUploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Yiwyn
 * @create 2023/2/9 20:42
 */
@Slf4j
@RequestMapping("/file")
@RestController
public class FileManagerController {

    @Autowired
    private IFileStoreService fileStoreService;

    @PostMapping("/upload")
    public RestResponse<String> upload(MultipartFile file) {

        String result = fileStoreService.uploadFile(file);

        return RestResponse.success(result);
    }

}
