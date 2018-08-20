package com.guy.login.Service;

import com.guy.login.domain.User;

public interface UserService {
    User findUserByEmail(String email);
    void saveUser(User user);
}
