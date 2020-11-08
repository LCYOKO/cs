package com.xiaomi.cs.pool;

import org.springframework.stereotype.Component;
import org.springframework.web.server.WebSession;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
/**
 * @author l
 * @create 2020-11-06-15:26
 */
@Component
public class SupportPool {
    private HashMap<WebSocketSession,HashMap<Integer,WebSocketSession>> pool;
    @PostConstruct
    private  void init(){
         pool=new HashMap<>(16);
    }

    public Map<Integer,WebSocketSession>  getSupportList(WebSocketSession session){
        return  pool.getOrDefault(session,null);
    }

   public void addSupport(WebSocketSession session){
        pool.put(session,new HashMap<Integer,WebSocketSession>(16));
   }

   public void removeSupport(WebSocketSession session){

   }
}
