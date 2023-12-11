package study.ji_xiao_yuan.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.ji_xiao_yuan.entity.dto.LoginDTO;
import study.ji_xiao_yuan.entity.pojo.User;
import study.ji_xiao_yuan.entity.result.R;
import study.ji_xiao_yuan.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Persolute
 * @version 1.0
 * @description User Controller
 * @email 1538520381@qq.com
 * @date 2023/12/7 13:10
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /*
     * @author Persolute
     * @version 1.0
     * @description 注册
     * @email 1538520381@qq.com
     * @date 2023/12/7 15:09
     */
    @PostMapping("/register")
    public R<String> register(HttpServletRequest request, @RequestBody User user) {
        if (user.getAccount() == null) {
            return R.error("请输入账号");
        } else if (user.getPassword() == null) {
            return R.error("请输入密码");
        } else {
            R<String> r = userService.register(user);
            login(request, user);
            return r;
        }
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 管理员登录
     * @email 1538520381@qq.com
     * @date 2023/12/7 15:09
     */
    @PostMapping("/adminLogin")
    public R<String> login(HttpServletRequest request, @RequestBody User user) {
        if (user.getAccount() == null) {
            return R.error("请输入账号");
        } else if (user.getPassword() == null) {
            return R.error("请输入密码");
        } else {
            User user0 = userService.login(user);
            if (user0 == null) {
                return R.error("此账号不存在");
            } else if (!user0.getPassword().equals(user.getPassword())) {
                return R.error("密码输入错误");
            } else {
                request.getSession().setAttribute("admin", user0.getId());
                return R.success("登录成功");
            }
        }
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 用户登录
     * @email 1538520381@qq.com
     * @date 2023/12/11 15:58
     */
    @PostMapping("/userLogin")
    public R<String> login(HttpServletRequest request, @RequestBody LoginDTO loginDTO) {
        request.getSession().setAttribute("user", loginDTO.getOpenId());
        return R.success("登录成功");
    }

    /*
     * @author Persolute
     * @version 1.0
     * @description 退出登录
     * @email 1538520381@qq.com
     * @date 2023/12/9 17:48
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("admin");
        return R.success("退出登录成功");
    }
}
