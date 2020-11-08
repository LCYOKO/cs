package com.xiaomi.cs.message;

import lombok.Data;
import org.springframework.web.socket.WebSocketMessage;

/**
 * @author l
 * @create 2020-11-06-10:39
 */
@Data
public class SimpleMessage  {
   private int type;
   private String msg;
   private int uid;
   private String name;
}
