package study.ji_xiao_yuan.filter;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import study.ji_xiao_yuan.common.BaseContext;
import study.ji_xiao_yuan.entity.result.R;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Persolute
 * @version 1.0
 * @description
 * @email 1538520381@qq.com
 * @date 2023/12/7 18:33
 */
@Slf4j
@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        String[] urls = new String[]{
                "/user/register",
                "/user/adminLogin",
                "/user/userLogin"
        };

        // 此请求无须拦截
        if (check(urls, requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 管理员已登录
        if (request.getSession().getAttribute("admin") != null) {
            BaseContext.setCurrentId(request.getSession().getAttribute("admin"));
            filterChain.doFilter(request, response);
            return;
        }

        // 用户已登录
        if (request.getSession().getAttribute("user") != null) {
            BaseContext.setCurrentId(request.getSession().getAttribute("user"));
            filterChain.doFilter(request, response);
            return;
        }

        // 未登录
        response.getWriter().write(JSON.toJSONString(R.error("未登录")));
    }

    // 路径匹配
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            if (PATH_MATCHER.match(url, requestURI)) {
                return true;
            }
        }
        return false;
    }
}
