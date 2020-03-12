package com.lgzf.service;

import com.lgzf.pojo.User;

/**
 * @auther lgzf
 * @data 2020-02-26 - 16:15
 **/
public interface UserService {

    User checkUser(String username, String password);
}
