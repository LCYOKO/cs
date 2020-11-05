package com.xiaomi.cs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.cs.mapper.UserMapper;
import com.xiaomi.cs.pojo.entity.User;
import com.xiaomi.cs.service.UserService;

/**
 * @author l
 * @create 2020-11-05-11:08
 */
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Override
    public User selUserById(int id) {
        return this.getById(id);
    }

    @Override
    public User userLogin(User user) {
        return null;
    }
}
