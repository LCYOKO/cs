package com.xiaomi.cs.message;

import com.alibaba.fastjson.JSONArray;
import com.xiaomi.cs.common.MessageTypeConstants;
import org.springframework.web.socket.TextMessage;

/**
 * @author l
 * @create 2020-11-08-19:31
 */
public class MessageFactory {
      private static  final String msg="你好 我是KF 机器人，有什么可以为你服务的";
    public static TextMessage createFirstTextMessage(int type){
        SimpleMessage message = new SimpleMessage();
        if(type==MessageTypeConstants.FIRST_CONNECTION){
                  message.setMsg(msg);
                  message.setType(MessageTypeConstants.TYPE_SEND_MESSAGE);
                  message.setFromUid(-1);
                  message.setFromUsername("小爱一号");
             return new TextMessage(JSONArray.toJSON(message).toString().getBytes());
        }
        return  null;
    }

    public  static SimpleMessage create2CustomerMessage(int to,String toName,int from ,String fromName,String msg,int type){
        SimpleMessage message = new SimpleMessage();
        message.setFromUid(from);
        message.setFromUsername(fromName);
        message.setToUid(to);
        message.setToUsername(toName);
        message.setMsg(msg);
        message.setType(type);
        return message;
    }


}
