package com.xiaomi.cs.balance;

import org.springframework.stereotype.Component;
import org.springframework.web.server.WebSession;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author l
 * @create 2020-11-09-10:37
 */
@Component
public class RandomBalance extends AbstractBalance {
    private final Random random=new Random();


    @Override
    public WebSocketSession select(List<WebSocketSession> sessions) {
        int n=sessions.size();
        return sessions.get(random.nextInt(n));
    }
}
