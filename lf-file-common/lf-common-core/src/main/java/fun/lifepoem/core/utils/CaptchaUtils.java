package fun.lifepoem.core.utils;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @author Yiwyn
 * @create 2023/2/13 21:54
 * @desc 验证码工具
 */
public class CaptchaUtils {

    public static String generaterCaptcha(int size) {
        size = size == 0 ? 6 : size;

        long time = new Date().getTime();

        Random random = new Random();

        int[] captcha = new int[size];

        int speed = (int) (time % 10);
        speed = speed == 0 ? 1 : speed;
        for (int i = 0; i < size; i++) {
            int rValue = random.nextInt(99);
            captcha[i] = (rValue % speed);
        }
        StringBuilder code = new StringBuilder();
        for (int i : captcha) {
            code.append(i);
        }
        return code.toString();
    }


}
