package org.philip.service;

import org.philip.pojo.User;


public interface UserService {
    // 根據用戶名查詢用戶
    User findByUserName(String username);

    //註冊
    void register(String username, String password);

    // 更新
    void update(User user);

    // 更新大頭貼
    void updateAvatar(String avatarUrl);
}
