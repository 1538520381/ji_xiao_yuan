package study.ji_xiao_yuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import study.ji_xiao_yuan.entity.pojo.User;
import study.ji_xiao_yuan.entity.result.R;

/**
 * @author Persolute
 * @version 1.0
 * @description User Service
 * @email 1538520381@qq.com
 * @date 2023/12/7 13:07
 */
public interface UserService extends IService<User> {
    R<String> register(User user);
    User login(User user);
}
