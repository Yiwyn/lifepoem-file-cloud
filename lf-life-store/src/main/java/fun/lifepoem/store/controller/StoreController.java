package fun.lifepoem.store.controller;

import fun.lifepoem.core.domain.RestResponse;
import fun.lifepoem.store.domain.vo.FileShareVO;
import fun.lifepoem.store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Yiwyn
 * @create 2023/2/22 21:28
 */
@RestController
@RequestMapping("/store")
public class StoreController {


    @Autowired
    private StoreService storeService;


    @GetMapping("/share")
    public RestResponse<FileShareVO> share(String fileId) {
        FileShareVO fileShareVO = storeService.shareFile(fileId);
        return RestResponse.success(fileShareVO);
    }


    @PostMapping("/fast-share")
    public RestResponse<FileShareVO> fastShare(MultipartFile file) throws IOException {
        FileShareVO result = storeService.fastShare(file);
        return RestResponse.success(result);
    }


}
