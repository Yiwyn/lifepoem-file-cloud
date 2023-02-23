package fun.lifepoem.manager.controller.api;

import fun.lifepoem.api.domain.LpFileInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yiwyn
 * @create 2023/2/23 22:30
 */
@RestController
@RequestMapping("/file/api")
public class FileApiController {


    @GetMapping("/file-info/{id}")
    LpFileInfo getFileInfo(@PathVariable("id") String id) {
        return new LpFileInfo() {{
            setFileId(1);
            setFileName("test");
        }};
    }

}
