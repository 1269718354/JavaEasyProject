package com.ustb.service;

import com.ustb.mapper.UserMapper;
import com.ustb.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    void setUserMapper(UserMapper userMapper);
}
