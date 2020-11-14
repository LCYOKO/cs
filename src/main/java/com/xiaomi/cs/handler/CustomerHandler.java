package com.xiaomi.cs.handler;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import com.xiaomi.cs.common.MessageTypeConstants;
import com.xiaomi.cs.message.MessageFactory;
import com.xiaomi.cs.message.SimpleMessage;
import com.xiaomi.cs.pojo.entity.MySession;
import com.xiaomi.cs.pool.CustomerPool;
import com.xiaomi.cs.utils.MessageDispatcher;
import com.xiaomi.cs.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import sun.java2d.pipe.SpanShapeRenderer;


/**
 * @author l
 * @create 2020-11-08-19:24
 */
@Component
public class CustomerHandler extends TextWebSocketHandler {


    @Autowired
    private RobotHandler robotHandler;
    @Autowired
    private MessageDispatcher messageDispatcher;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        messageDispatcher.addCustomer(session);


    }

    @Override
    public void handleTextMessage(WebSocketSession session,
                                  TextMessage message) throws Exception {
        // TODO Auto-generated method stub
        String msg = message.getPayload();

        SimpleMessage simpleMessage = JSONObject.parseObject(msg, SimpleMessage.class);
        if(simpleMessage.getMsg().contains("人工")){
            System.out.println(111);
            messageDispatcher.assginCustomer(simpleMessage);


        }
         else if(simpleMessage.getToUid()==-1) {
            String answer = robotHandler.getAnswer(simpleMessage.getMsg());
            messageDispatcher.sendMessage2Customer(MessageFactory.create2CustomerMessage(simpleMessage.getFromUid(),simpleMessage.getFromUsername(),
                    robotHandler.getId(),robotHandler.getBotName(),answer,MessageTypeConstants.TYPE_SEND_MESSAGE
            ), simpleMessage.getFromUid());


        }
         else{
            messageDispatcher.sendMessage2Support(MessageFactory.create2CustomerMessage(simpleMessage.getToUid(),simpleMessage.getToUsername(),
                    simpleMessage.getFromUid(),simpleMessage.getFromUsername(),simpleMessage.getMsg(),MessageTypeConstants.TYPE_SEND_MESSAGE),
                    simpleMessage.getToUid()
            );

        }
//        Object stamp = null;	//时间戳，用来标识返回结果
//        Pattern pattern = Pattern.compile("^\\{(\"\\w+\":\\S+,{0,1})+\\}$");
//        if(pattern.matcher(msg).matches()){
//            JSONObject json = JSONObject.fromObject(message.getPayload());
//            stamp = json.get("stamp");
//            result = WSDispatcher.dispatch(json, session);
//        }
//
//        String response = "";
//        if(result == null)	response = "404";
//        else{
//            result.put("stamp", stamp);
//            response = String.valueOf(result);
//        }
//        session.sendMessage(new TextMessage(response.getBytes(charset)));

    }


    /**
     *
     *
     *     出错删除session
     *
     *
     * @param session
     * @param status
     * @throws Exception
     */

    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus status) throws Exception {
        // TODO Auto-generated method stub
        super.afterConnectionClosed(session, status);

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);

    }

}