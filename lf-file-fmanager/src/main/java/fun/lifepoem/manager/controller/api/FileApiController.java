package fun.lifepoem.manager.controller.api;

import fun.lifepoem.api.domain.LpFileInfo;
import fun.lifepoem.core.domain.RestResponse;
import fun.lifepoem.manager.domain.LpSysFile;
import fun.lifepoem.manager.mapper.LpSysFileMapper;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Yiwyn
 * @create 2023/2/23 22:30
 */
@RestController
@RequestMapping("/file/api")
public class FileApiController {

    @Resource
    private LpSysFileMapper lpSysFileMapper;

    @GetMapping("/file-info/{id}")
    RestResponse<LpFileInfo> getFileInfo(@PathVariable("id") String id) {
        LpSysFile lpSysFile = lpSysFileMapper.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(lpSysFile)) {
            return RestResponse.fail("无文件");
        }
        LpFileInfo lpFileInfo = new LpFileInfo();
        lpFileInfo.setFileName(lpSysFile.getFileName());
        lpFileInfo.setFileId(lpSysFile.getId());
        return RestResponse.success(lpFileInfo);
    }

}
