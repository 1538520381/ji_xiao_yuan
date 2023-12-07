package study.ji_xiao_yuan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import study.ji_xiao_yuan.dao.UserDao;
import study.ji_xiao_yuan.entity.pojo.User;
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
}
