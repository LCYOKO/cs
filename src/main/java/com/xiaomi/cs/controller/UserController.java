package com.xiaomi.cs.controller;

import com.xiaomi.cs.common.CommonResponse;
import com.xiaomi.cs.pojo.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author l
 * @create 2020-11-05-10:59
 */
@RestController
public class UserController {

    @GetMapping("/login")
    public CommonResponse login(User user){
        return new CommonResponse(0,null,null);
    }

    @GetMapping("/logout")
    public CommonResponse logout(User user){
        return  new CommonResponse(0,null,null);
    }
}
