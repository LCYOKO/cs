package com.xiaomi.cs.balance;

import com.sun.javafx.collections.MappingChange;
import org.springframework.web.server.WebSession;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author l
 * @create 2020-11-09-10:37
 */
public interface Balance {


    WebSocketSession doSelect(List<WebSocketSession> sessions);
}
