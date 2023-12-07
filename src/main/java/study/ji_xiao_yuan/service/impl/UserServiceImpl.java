package study.ji_xiao_yuan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import study.ji_xiao_yuan.dao.UserDao;
import study.ji_xiao_yuan.entity.pojo.User;
import study.ji_xiao_yuan.entity.result.R;
import study.ji_xiao_yuan.service.UserService;

/**
 * @author Persolute
 * @version 1.0
 * @description User ServiceImpl
 * @email 1538520381@qq.com
 * @date 2023/12/7 13:08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    /*
     * @author Persolute
     * @version 1.0
     * @description 注册
     * @email 1538520381@qq.com
     * @date 2023/12/7 15:08
     */
    @Override
    public R<String> register(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getAccount, user.getAccount());
        if (this.getOne(lambdaQueryWrapper) != null) {
            return R.error("该账号已存在");
        } else {
            this.save(user);
            return R.success("注册成功");
        }
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 登录
     * @email 1538520381@qq.com
     * @date 2023/12/7 15:07
     */
    @Override
    public User login(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getAccount, user.getAccount());
        return this.getOne(lambdaQueryWrapper);
    }
}
