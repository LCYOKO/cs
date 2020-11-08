package com.xiaomi.cs.pool;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author l
 * @create 2020-11-06-15:26
 */
@Component
public class CustomerPool {
    private Set<WebSocketSession> assigned;
    private Set<WebSocketSession> unAssign;
    @PostConstruct
    public void init(){
        assigned=new HashSet<>();
        unAssign=new HashSet<>();
    }

    public void removeCustomer(WebSocketSession session){
        assigned.remove(session);
        unAssign.remove(session);
    }
    public void addCustomer(WebSocketSession session){
        unAssign.add(session);
    }
}
