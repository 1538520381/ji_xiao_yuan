package study.ji_xiao_yuan.common;

/**
 * @author Persolute
 * @version 1.0
 * @description 存取当前使用者标识id
 * @email 1538520381@qq.com
 * @date 2023/12/7 11:37
 */
public class BaseContext {
    // 当前用户微信唯一标识openId(String)、当前管理员id(Long)
    private static ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Object id) {
        threadLocal.set(id);
    }

    public static Object getCurrentId() {
        return threadLocal.get();
    }
}
