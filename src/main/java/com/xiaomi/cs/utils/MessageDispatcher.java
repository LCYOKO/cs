package com.xiaomi.cs.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaomi.cs.balance.Balance;
import com.xiaomi.cs.common.MessageTypeConstants;
import com.xiaomi.cs.message.MessageFactory;
import com.xiaomi.cs.message.SimpleMessage;
import com.xiaomi.cs.pojo.entity.MySession;
import com.xiaomi.cs.pool.CustomerPool;
import com.xiaomi.cs.pool.SupportPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * @author l
 * @create 2020-11-09-10:28
 */
@Component
public class MessageDispatcher {
  @Autowired
    private CustomerPool customerPool;
  @Autowired
    private SupportPool supportPool;
  @Autowired
    private Balance balance;
  public void addCustomer(WebSocketSession session) throws IOException {
      MySession mySession = SessionUtil.generateSession(session.getUri().getQuery(), session.getId());
      customerPool.addCustomer(session,mySession);
      session.sendMessage(new TextMessage(JSON.toJSON(new SimpleMessage(
              mySession.getUid(),mySession.getUsername(),-1,"小爱一号","你好 我是KF 机器人，有什么可以为你服务的",1
      )).toString()));
  }
  public void addSupport(WebSocketSession session){
      MySession mySession = SessionUtil.generateSession(session.getUri().getQuery(), session.getId());
      supportPool.addSupport(session,mySession);
  }

  public void sendMessage2Support(SimpleMessage simpleMessage,int support) throws IOException {
      WebSocketSession session = supportPool.getWebSocketSessionByUid(support);
      if(session!=null){
          session.sendMessage(new TextMessage(JSON.toJSON(simpleMessage).toString()));
      }
  }

  public void sendMessage2Customer(SimpleMessage simpleMessage,int customer) throws IOException {
      WebSocketSession session = customerPool.getWebSocektSessionByUid(customer);
      if(session!=null){
          session.sendMessage(new TextMessage(JSONObject.toJSON(simpleMessage).toString()));
      }
  }

  public void assginCustomer(SimpleMessage message) throws IOException {
      WebSocketSession session = customerPool.getWebSocektSessionByUid(message.getFromUid());
      synchronized (balance){
              WebSocketSession support = balance.doSelect(supportPool.getSupportList());
              if (support==null){
                 sendMessage2Customer(MessageFactory.create2CustomerMessage(message.getFromUid(), message.getFromUsername(),
                         -1, "小爱一号","暂时没有客服",1),message.getFromUid());
                  return ;
              }
              System.out.println(support+"123");
          MySession mySession = supportPool.getMySessionById(support.getId());
          supportPool.addCustomer(support,session);
          support.sendMessage(new TextMessage(JSONObject.toJSON(MessageFactory.create2CustomerMessage(mySession.getUid(),mySession.getUsername(),
                  message.getFromUid(),message.getFromUsername(),"",MessageTypeConstants.TYPE_ADD_USER)).toString()
                  ));
          customerPool.adjest(message.getFromUid());
         session.sendMessage(new TextMessage(
                 JSON.toJSON(MessageFactory.create2CustomerMessage(message.getFromUid(),message.getFromUsername(),
                         mySession.getUid(),mySession.getUsername(),"已为你转接客服"+mySession.getUsername(),1))
                         .toString()));
          }
  }

  public boolean isAssign(int id){
      return customerPool.customerAssigned(id);
  }



}
