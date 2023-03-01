package fun.lifepoem.core.utils;

import org.jasypt.util.text.BasicTextEncryptor;
import org.omg.CORBA.StringHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Yiwyn
 * @create 2023/3/1 20:27
 */
public class SecureUtils {

    private static final BasicTextEncryptor encryptor = new BasicTextEncryptor();

    static {
        encryptor.setPassword("yiwyn");
    }

    public static String encode(String clearText) {
        String encrypt = encryptor.encrypt(clearText);
        return encrypt;
    }

    public static String decode(String cipherText) {
        String decrypt = encryptor.decrypt(cipherText);
        return decrypt;
    }


}
