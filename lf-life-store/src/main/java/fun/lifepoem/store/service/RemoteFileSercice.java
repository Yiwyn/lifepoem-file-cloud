package fun.lifepoem.store.service;

import fun.lifepoem.api.domain.LpFileInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Yiwyn
 * @create 2023/2/22 22:32
 */
@Service
@FeignClient(value = "${feign.file.name}", url = "${feign.file.ip}", path = "/file/api")
public interface RemoteFileSercice {

    @GetMapping("/file-info/{id}")
    LpFileInfo getFileInfo(@PathVariable("id") String fileId);

}
