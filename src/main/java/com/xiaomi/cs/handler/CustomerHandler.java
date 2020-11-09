package com.xiaomi.cs.handler;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaomi.cs.common.MessageTypeConstants;
import com.xiaomi.cs.message.MessageFactory;
import com.xiaomi.cs.pojo.entity.MySession;
import com.xiaomi.cs.pool.CustomerPool;
import com.xiaomi.cs.utils.MessageDispatcher;
import com.xiaomi.cs.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;


/**
 * @author l
 * @create 2020-11-08-19:24
 */
@Component
public class CustomerHandler extends TextWebSocketHandler {

    @Autowired
    private CustomerPool customerPool;

    @Autowired
    private MessageDispatcher messageDispatcher;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        messageDispatcher.addCustomer(session);
        TextMessage message =MessageFactory.createTextMessage(MessageTypeConstants.FIRST_CONNECTION);
        if (message != null) {
            session.sendMessage(message);
        }


    }

    @Override
    public void handleTextMessage(WebSocketSession session,
                                  TextMessage message) throws Exception {
        // TODO Auto-generated method stub
        String msg = message.getPayload();
        System.out.println("handlerText===========>" + msg);


           JSONObject result = null;
        Object parse = JSONArray.parse(msg);
        System.out.println(msg);

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