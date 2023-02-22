package fun.lifepoem.store.service;

import fun.lifepoem.api.domain.LpFileInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

/**
 * @author Yiwyn
 * @create 2023/2/22 22:32
 */
@FeignClient("file")
@Service
public interface RemoteFileSercice {

    LpFileInfo getFileInfo(String fileId);

}
