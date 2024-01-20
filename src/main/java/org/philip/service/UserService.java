package org.philip.service;

import org.philip.pojo.User;


public interface UserService {
    User findByUserName(String username);

    void register(String username, String password);
}
