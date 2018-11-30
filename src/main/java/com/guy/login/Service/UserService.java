package com.guy.login.Service;

import com.guy.login.domain.AppUser;

public interface UserService {
    AppUser findUserByEmail(String email);
    void saveUser(AppUser user);
}
