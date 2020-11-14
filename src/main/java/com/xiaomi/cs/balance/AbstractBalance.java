package com.xiaomi.cs.balance;

import org.springframework.web.socket.WebSocketSession;

import java.util.List;

/**
 * @author l
 * @create 2020-11-09-12:21
 */
public abstract class AbstractBalance implements Balance {
    @Override
    public WebSocketSession doSelect(List<WebSocketSession> sessions) {
        if(sessions.size()<=0) return null;
        return select(sessions);
    }

    public abstract WebSocketSession select(List<WebSocketSession> sessions);
}
