package org.philip.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.philip.pojo.Result;
import org.philip.utils.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result<String> article(/*@RequestHeader(name = "Authorization") String token, HttpServletResponse response*/){
        /*// 驗證 token
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            return Result.success("所有文章數據");
        } catch (Exception e) {
            // http 響應狀態碼為 401
            response.setStatus(401);
            return Result.error("未登錄");
        }*/
        return Result.success("所有文章數據");
    }
}
