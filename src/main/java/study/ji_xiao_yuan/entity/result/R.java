package study.ji_xiao_yuan.entity.result;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.apache.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Persolute
 * @version 1.0
 * @description 统一返回结果封装
 * @email 1538520381@qq.com
 * @date 2023/12/7 10:20
 */
@Data
@ApiModel(value = "自定义返回结果R", description = "成功访问:{\"code\":\"0\";\"msg\":\"success\";\"data\":{ xx }}")
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String msg;
    private T data;
    private Map<String, Object> map = new HashMap<>();

    public static <T> R<T> success() {
        R<T> r = new R<T>();
        r.code = 0;
        r.msg = "success";
        return r;
    }

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.code = 0;
        r.msg = "success";
        r.data = object;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R<T> r = new R<T>();
        r.code = HttpStatus.SC_INTERNAL_SERVER_ERROR;
        r.msg = msg;
        return r;
    }

    public static <T> R<T> error(int code, String msg) {
        R<T> r = new R<T>();
        r.code = code;
        r.msg = msg;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
