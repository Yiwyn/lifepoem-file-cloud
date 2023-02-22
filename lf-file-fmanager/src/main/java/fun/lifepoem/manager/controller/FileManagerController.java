package fun.lifepoem.manager.controller;

import fun.lifepoem.api.domain.LpFile;
import fun.lifepoem.core.domain.RestResponse;
import fun.lifepoem.manager.service.IFileStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;

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
    public RestResponse<LpFile> upload(MultipartFile file) {

        if (file == null || file.getSize() == 0) {
            return RestResponse.fail("什么都没有上传");
        }
        LpFile lpFile = null;
        try {
            lpFile = fileStoreService.uploadFile(file);
        } catch (Exception e) {
            return RestResponse.fail(e.getMessage());
        }

        return RestResponse.success(lpFile);
    }


    @GetMapping("/p/{fileId}")
    public void restContext(@PathVariable("fileId") String fieldId, HttpServletResponse response) throws IOException {


        BufferedInputStream file = fileStoreService.getPathFile(fieldId);

        if (file == null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("无文件");
            return;
        }

        ServletOutputStream outputStream = response.getOutputStream();
        byte[] buffer = new byte[1024 * 2];

        while (file.read(buffer) != -1) {
            outputStream.write(buffer);
        }
        file.close();
        outputStream.flush();
        outputStream.close();

    }

}
