package fun.lifepoem.store.controller;

import fun.lifepoem.core.domain.RestResponse;
import fun.lifepoem.store.domain.LpShareRecord;
import fun.lifepoem.store.domain.vo.FileShareVO;
import fun.lifepoem.store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
        FileShareVO fileShareVO;
        try {
            fileShareVO = storeService.shareFile(fileId);
            if (ObjectUtils.isEmpty(fileShareVO)){
                return RestResponse.fail("文件找不到了");
            }
        } catch (Exception e) {
            return RestResponse.fail(e.getMessage());
        }
        return RestResponse.success(fileShareVO);
    }


    @PostMapping("/fast-share")
    public RestResponse<FileShareVO> fastShare(MultipartFile file) throws IOException {
        FileShareVO result = storeService.fastShare(file);
        return RestResponse.success(result);
    }


    @GetMapping("/read-file")
    public void readFile(String shortKey, String shareKey, HttpServletResponse response) throws IOException {
        LpShareRecord shareRecord = storeService.getShareRecord(shortKey, shareKey);
        if (ObjectUtils.isEmpty(shareRecord)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("url错误");
        } else {
            response.sendRedirect(shareRecord.getShareLink());
        }

    }
}
