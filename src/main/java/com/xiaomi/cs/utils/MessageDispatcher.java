package com.xiaomi.cs.utils;

import com.xiaomi.cs.balance.Balance;
import com.xiaomi.cs.message.MessageFactory;
import com.xiaomi.cs.message.SimpleMessage;
import com.xiaomi.cs.pojo.entity.MySession;
import com.xiaomi.cs.pool.CustomerPool;
import com.xiaomi.cs.pool.SupportPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

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
  public void addCustomer(WebSocketSession session){
      MySession mySession = SessionUtil.generateSession(session.getUri().getQuery(), session.getId());
      customerPool.addCustomer(session,mySession);
  }
  public void addSupport(WebSocketSession session){
      MySession mySession = SessionUtil.generateSession(session.getUri().getQuery(), session.getId());
      supportPool.addSupport(session,mySession);
  }

  public void sendMessage2Support(SimpleMessage simpleMessage,WebSocketSession support){

  }

  public void sendMessage2Customer(SimpleMessage simpleMessage,WebSocketSession customer){

  }

  public boolean assginCustomer(WebSocketSession session){
          synchronized (balance){
              WebSocketSession support = balance.doSelect(supportPool.getSupportList());
              if (support==null){
//                  sendMessage2Customer(MessageFactory.create2CustomerMessage(customer.getUid(), customer.getUsername(),
//                          -1, "BOT01","暂时没有客服",1),session);
                  return true;
              }
              supportPool.addCustomer(support,session);
              return true;
          }
  }

}
