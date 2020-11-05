package com.xiaomi.cs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaomi.cs.pojo.entity.User;

/**
 * @author l
 * @create 2020-11-05-11:08
 */
public interface UserService extends IService<User> {
    User selUserById(int id);
    User userLogin(User user);
}
