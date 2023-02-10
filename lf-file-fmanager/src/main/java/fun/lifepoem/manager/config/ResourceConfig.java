package fun.lifepoem.manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @author Yiwyn
 * @create 2023/2/9 23:08
 */
@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    @Value("${lf-file.local-file-path}")
    private String localFilePath;

    @Value("${lf-file.local-file-prefix}")
    private String localFilePrefix;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(localFilePrefix + "/**")
                .addResourceLocations("file:" + localFilePath);
    }
}
