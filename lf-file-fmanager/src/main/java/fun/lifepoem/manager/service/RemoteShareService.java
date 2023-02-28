package fun.lifepoem.manager.service;

import fun.lifepoem.api.domain.LpFileInfo;
import fun.lifepoem.core.domain.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Yiwyn
 * @create 2023/2/28 21:48
 */
@Service
@FeignClient(value = "${feign.file.name}", url = "${feign.file.ip}", path = "/sore/api")
public interface RemoteShareService {

    @GetMapping("/share-info")
    RestResponse<String> getFileInfo(String shortKey);

}
