package com.xiaomi.cs.pojo.entity;

import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

/**
 * @author l
 * @create 2020-11-09-9:28
 */
@Data
public class MySession {
   private String sessionId;
   private String username;
   private int uid;
   private WebSocketSession webSocketSession;
   private List<String> currentServeCustomers;
}
