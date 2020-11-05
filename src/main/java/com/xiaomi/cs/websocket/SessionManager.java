package com.xiaomi.cs.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author l
 * @create 2020-10-28-20:29
 */
@Component
public class SessionManager {
    private  ConcurrentHashMap<String, WebSocketSession> SESSION_POOL ;
    private  int DEFAULT_SIZE=16;

    public SessionManager(int size){
        init(size);
    }

    public SessionManager(){
        init(DEFAULT_SIZE);
    }

    private void init(int size){
        SESSION_POOL=new ConcurrentHashMap<>(size);
    }

    /**
     * 添加 session
     *
     * @param key
     */
    public  void add(String key, WebSocketSession session) {

        SESSION_POOL.put(key, session);
    }

    /**
     * 删除 session,会返回删除的 session
     *
     * @param key
     * @return
     */
    public  WebSocketSession remove(String key) {
        // 删除 session
        return SESSION_POOL.remove(key);
    }

    /**
     * 删除并同步关闭连接
     *
     * @param key
     */
    public void removeAndClose(String key) {
        WebSocketSession session = remove(key);
        if (session != null) {
            try {
                // 关闭连接
                session.close();
            } catch (IOException e) {
                // todo: 关闭出现异常处理
                e.printStackTrace();
            }
        }
    }

    /**
     * 获得 session
     *
     * @param key
     * @return
     */
    public  WebSocketSession get(String key) {
        // 获得 session
        return SESSION_POOL.get(key);
    }
}
