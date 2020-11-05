package com.xiaomi.cs.interceptor;


import com.xiaomi.cs.pojo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author l
 * @create 2020-10-28-20:41
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    private final String LOGIN_URL="/toLogin";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();

        User user=(User) request.getSession().getAttribute("user");
        response.sendRedirect(LOGIN_URL);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
