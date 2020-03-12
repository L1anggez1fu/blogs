package com.lgzf.service.impl;

import com.lgzf.dao.UserDao;
import com.lgzf.pojo.User;
import com.lgzf.service.UserService;
import com.lgzf.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther lgzf
 * @data 2020-02-26 - 16:16
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.queryByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}