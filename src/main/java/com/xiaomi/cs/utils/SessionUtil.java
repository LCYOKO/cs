package com.xiaomi.cs.utils;

import com.xiaomi.cs.pojo.entity.MySession;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author l
 * @create 2020-11-09-9:50
 */
public class SessionUtil {

    public static String generateSessionId(WebSocketSession session){
        return null;
    }
    public static MySession generateSession(String query,String originSessionId){
        MySession session = new MySession();
        String[] params = query.split("&");
        for(String param:params){
            String[] keyValue = param.split("=");
            if("username".equals(keyValue[0])){
                session.setUsername(keyValue[1]);
            }
            else {
                session.setUid(Integer.parseInt(keyValue[1]));
            }

        }
        session.setSessionId(originSessionId);
        return  session;
    }
}
