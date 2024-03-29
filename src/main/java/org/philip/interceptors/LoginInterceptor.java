package org.philip.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.philip.pojo.Result;
import org.philip.utils.JwtUtil;
import org.philip.utils.ThreadLocalUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 令牌驗證
        String token = request.getHeader("Authorization");
        // 驗證 token
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            // 把業務數據儲存到 ThreadLocal 中
            ThreadLocalUtil.set(claims);
            // 放行
            return true;
        } catch (Exception e) {
            // http 響應狀態碼為 401
            response.setStatus(401);
            // 不放行
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清空 ThreadLocal 中的數據
        ThreadLocalUtil.remove();
    }
}
