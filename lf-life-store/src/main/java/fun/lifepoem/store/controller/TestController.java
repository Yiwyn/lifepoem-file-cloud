package fun.lifepoem.store.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yiwyn
 * @create 2023/2/4 22:07
 */
@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping("/demo")
    public String demo() {
        return "demo";
    }
}
