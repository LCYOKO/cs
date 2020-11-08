package com.xiaomi.cs.controller;

import com.xiaomi.cs.common.CommonResponse;
import com.xiaomi.cs.common.ResponseConstants;
import com.xiaomi.cs.pojo.entity.User;
import com.xiaomi.cs.pool.CustomerPool;

import com.xiaomi.cs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author l
 * @create 2020-11-05-10:59
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CustomerPool customerPool;


    @GetMapping("/login")
    public CommonResponse login(User userDo, HttpServletRequest request){
        User user = userService.userLogin(userDo);
        if(user==null){
            return new CommonResponse(ResponseConstants.LOGIN_FAIL,ResponseConstants.LOGIN_FIAL,null);
        }
        request.getSession().setAttribute("user",user);
        if(user.getKefu()==1) {
          return   new CommonResponse(ResponseConstants.LOGIN_SUCCESS,ResponseConstants.LOGIN_SUCCESS_MGS,"/");

        }
        return new CommonResponse(ResponseConstants.LOGIN_SUCCESS,
                ResponseConstants.LOGIN_SUCCESS_MGS,"/user/index");
    }

    @GetMapping("/logout")
    public CommonResponse logout(HttpSession session){
        session.removeAttribute("user");
        return  new CommonResponse(0,null,null);
    }
}
