package com.xiaomi.cs.controller;

import com.sun.corba.se.impl.protocol.giopmsgheaders.RequestMessage;
import com.xiaomi.cs.pojo.CustomerMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * @author l
 * @create 2020-10-28-21:19
 */
@Controller
public class WebSocketController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/hello")
    public String hello(CustomerMessage message) {

        //System.out.println( requestMessage);
        System.out.println(message);
        return "收到";
    }
}
