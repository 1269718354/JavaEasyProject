package com.ustb.service.impl;

import com.ustb.mapper.UserMapper;
import com.ustb.pojo.User;
import com.ustb.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void setUserMapper(UserMapper userMapper) {

    }
}
