package fun.lifepoem.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Yiwyn
 * @create 2023/2/4 22:08
 */
@SpringBootApplication
@EnableFeignClients
public class LpStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(LpStoreApplication.class, args);
    }
}
