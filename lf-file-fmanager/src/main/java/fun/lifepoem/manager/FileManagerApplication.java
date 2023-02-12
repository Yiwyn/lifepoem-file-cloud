package fun.lifepoem.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Yiwyn
 * @create 2023/2/7 22:00
 */
@SpringBootApplication
@MapperScan("fun.lifepoem.manager.mapper")
public class FileManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileManagerApplication.class, args);
    }
}
