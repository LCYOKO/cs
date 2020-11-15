package com.xiaomi.cs.controller;

import com.xiaomi.cs.common.CommonResponse;
import com.xiaomi.cs.pojo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author l
 * @create 2020-10-27-22:24
 */
@Controller
public class BaseController {
    private final String BASE_PREFIX="page/";

    @GetMapping("/toLogin")
    public String toLogin(){
        return  "page/login/login";
    }

    @GetMapping("/")
    public String toIndex1(){
        return "index";
    }
    @GetMapping("/index")
    public String doIndex2(){
        return "index";
    }
    @GetMapping("/page/main")
    public String toMain(){
        return "page/main";
    }
    @GetMapping("/user/index")
    public String toUserIdx(){
        return "user/index";
    }
    @GetMapping("/page")
    public String toSessionMain(String method){
        return  BASE_PREFIX+method;
    }
    @GetMapping("/user/chat")
    public String toUserChat(){
        return "user/chat";
    }



}
