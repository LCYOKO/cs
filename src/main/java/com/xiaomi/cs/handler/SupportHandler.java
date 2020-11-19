package com.xiaomi.cs.handler;


import com.alibaba.fastjson.JSONObject;
import com.xiaomi.cs.common.MessageTypeConstants;
import com.xiaomi.cs.message.MessageFactory;
import com.xiaomi.cs.message.SimpleMessage;
import com.xiaomi.cs.pojo.entity.MySession;
import com.xiaomi.cs.pool.SupportPool;
import com.xiaomi.cs.utils.MessageDispatcher;
import com.xiaomi.cs.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author l
 * @create 2020-11-08-19:25
 */
@Component
public class SupportHandler extends TextWebSocketHandler {
    @Autowired
    private SupportPool supportPool;
    @Autowired
    private MessageDispatcher messageDispatcher;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        MySession mySession = SessionUtil.generateSession(session.getUri().getQuery(), session.getId());
        supportPool.addSupport(session,mySession);
    }

    @Override
    public void handleTextMessage(WebSocketSession session,
                                  TextMessage message) throws Exception {
        // TODO Auto-generated method stub
        String msg = message.getPayload();

        SimpleMessage simpleMessage = JSONObject.parseObject(msg, SimpleMessage.class);
        if(simpleMessage.getType()==1){

        }
        messageDispatcher.sendMessage2Customer(MessageFactory.create2CustomerMessage(simpleMessage.getToUid(),simpleMessage.getToUsername(),
                simpleMessage.getFromUid(),simpleMessage.getFromUsername(),simpleMessage.getMsg(),MessageTypeConstants.TYPE_SEND_MESSAGE),
                simpleMessage.getToUid()
        );

    }


    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus status) throws Exception {
        // TODO Auto-generated method stub
        super.afterConnectionClosed(session, status);
        SimpleMessage message = JSONObject.parseObject(status.getReason(), SimpleMessage.class);
        messageDispatcher.removeSupport(session,message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);

       messageDispatcher.removeSupport(session,null);

    }
}
