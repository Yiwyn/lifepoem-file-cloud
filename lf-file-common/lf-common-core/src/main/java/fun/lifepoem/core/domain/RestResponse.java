package fun.lifepoem.core.domain;

import fun.lifepoem.core.constant.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Yiwyn
 * @create 2023/2/9 20:57
 */
@Data
@NoArgsConstructor
public class RestResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 成功
     */
    public static final int SUCCESS = Constants.SUCCESS;

    /**
     * 失败
     */
    public static final int FAIL = Constants.FAIL;

    private int code;
    private String messge;
    private T data;

    private RestResponse(int code, String messge, T data) {
        this.code = code;
        this.messge = messge;
        this.data = data;
    }

    public static <T> RestResponse<T> success(String message, T data) {
        return new RestResponse<>(SUCCESS, message, data);
    }

    public static <T> RestResponse<T> success(T data) {
        return new RestResponse<>(SUCCESS, "请求成功", data);
    }

    public static <T> RestResponse<T> fail(String message) {
        return new RestResponse<>(FAIL, message, null);
    }

}

