package org.philip.controller;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.philip.pojo.Result;
import org.philip.pojo.User;
import org.philip.service.UserService;
import org.philip.utils.JwtUtil;
import org.philip.utils.Md5Util;
import org.philip.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$")String password){
        User u = userService.findByUserName(username);

        if(u == null){
            userService.register(username, password);
            return Result.success();
        }else{
            return Result.error("用戶名已被佔用");
        }

    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$")String password){
        // 根據用戶名查詢用戶
        User loginUser = userService.findByUserName(username);
        // 判斷用戶是否存在
        if(loginUser == null){
            return Result.error("用戶名不存在");
        }

        // 判斷密碼是否正確 loginUser 對象中的 password 是密文
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            // 登錄成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }

        return Result.error("密碼錯誤");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/){
        // 根據用戶名查詢用戶
        /*Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");*/

        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result<String> update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params){
        // 1. 校驗參數
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if(!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的參數");
        }

        // 原密碼是否正確
        // 調用 userService 根據用戶名拿到原密碼，再和old_pwd比對
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if(loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("原密碼填寫不正確");
        }

        // newPwd 和 rePwd是否一樣
        if (!rePwd.equals(newPwd)){
            return Result.error("兩次填寫的新密碼不一樣");
        }

        // 2. 調用 service 完成密碼更新
        userService.updatePwd(newPwd);
        return Result.success();
    }


}
