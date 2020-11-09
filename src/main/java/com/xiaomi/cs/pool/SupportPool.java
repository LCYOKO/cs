package com.xiaomi.cs.pool;

import com.xiaomi.cs.pojo.entity.MySession;
import org.springframework.stereotype.Component;
import org.springframework.web.server.WebSession;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author l
 * @create 2020-11-06-15:26
 */
@Component
public class SupportPool {
    private Map<String,HashSet<WebSocketSession>> curServeCustomers;
    private Map<String,MySession>  sessionId2MySession;
    private Map<Integer,WebSocketSession> uid2WebSocketSession;
    private List<WebSocketSession> list;
    @PostConstruct
    private  void init(){
        curServeCustomers=new ConcurrentHashMap<>(16);
        sessionId2MySession=new ConcurrentHashMap<>(16);
        uid2WebSocketSession=new ConcurrentHashMap<>(16);
        list=new ArrayList<>();
    }

    public List<WebSocketSession> getSupportList(){
        return  list;
    }

   public void addSupport(WebSocketSession session,MySession mySession){
        curServeCustomers.put(session.getId(),new HashSet<WebSocketSession>());
        sessionId2MySession.put(session.getId(),mySession);
        uid2WebSocketSession.put(mySession.getUid(),session);

        list.add(session);
   }
   public boolean addCustomer(WebSocketSession support,WebSocketSession customer){
       HashSet<WebSocketSession> customers = curServeCustomers.getOrDefault(support, null);
       if(customers==null){
           return  false;
       }
       customers.add(customer);
       return  true;
   }
   public void removeSupport(WebSocketSession session){
       MySession mySession = sessionId2MySession.getOrDefault(session.getId(),null);
       if(mySession!=null){
           uid2WebSocketSession.remove(mySession.getUid());
           sessionId2MySession.remove(session.getId());
       }
       list.remove(session);
       curServeCustomers.remove(session.getId());
   }

   public boolean assignCustomer(WebSocketSession suuport,WebSocketSession customer){
       HashSet<WebSocketSession> customers = curServeCustomers.getOrDefault(suuport, null);
       if(customer==null){
           return false;
       }
       customers.add(customer);
       return  true;
   }

   public int getSupportNum(){
        return list.size();
   }

   public int getCustomerNumBySupport(String uid){
        return 0;
   }
}
