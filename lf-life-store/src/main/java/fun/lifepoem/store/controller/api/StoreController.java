package fun.lifepoem.store.controller.api;

import fun.lifepoem.api.domain.LpFileInfo;
import fun.lifepoem.core.domain.RestResponse;
import fun.lifepoem.store.domain.LpShareRecord;
import fun.lifepoem.store.mapper.LpShareRecordMapper;
import fun.lifepoem.store.service.StoreService;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Yiwyn
 * @create 2023/2/28 21:49
 */
@RestController
@RequestMapping("/store/api")
public class StoreController {

    @Resource
    private StoreService storeService;


    @GetMapping("/share-info")
    public RestResponse<String> getFileInfo(String shortKey) {
        String shareFileId = storeService.getShareFileId(shortKey);
        return RestResponse.success(shareFileId);
    }
}