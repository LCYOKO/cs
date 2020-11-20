package com.xiaomi.cs.pool;

import com.xiaomi.cs.pojo.entity.MySession;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author l
 * @create 2020-11-06-15:26
 */
@Component
@Data
public class CustomerPool {
    private HashSet<Integer> unAssign;
    private HashSet<Integer> assigned;
    private HashSet<Integer>  underCare;
    private HashMap<String,MySession> sessionId2MySession;
    private HashMap<Integer,WebSocketSession> uid2WebSocketSession;
    private HashMap<Integer,String> c2s;
    private AtomicInteger custmoerCounter;
    @PostConstruct
    public void init(){
        sessionId2MySession=new HashMap<>(16);
        uid2WebSocketSession=new HashMap<>(16);
        unAssign=new HashSet<>();
        assigned=new HashSet<>();
        underCare=new HashSet<>();
        c2s=new HashMap<>(16);
        custmoerCounter=new AtomicInteger();
    }

    public void removeCustomer(WebSocketSession session){
        MySession mySession = sessionId2MySession.getOrDefault(session.getId(), null);
        if(mySession==null){
            return;
        }
        sessionId2MySession.remove(session.getId());
        uid2WebSocketSession.remove(mySession.getUid());
        underCare.remove(mySession.getUid());
        assigned.remove(mySession.getUid());
        unAssign.remove(mySession.getUid());
        c2s.remove(mySession.getUid());
        custmoerCounter.decrementAndGet();
    }

    public MySession getMySessionById(WebSocketSession session){
        return sessionId2MySession.getOrDefault(session.getId(),null);
    }
    public void addCustomer(WebSocketSession session,MySession mySession){
        sessionId2MySession.put(session.getId(),mySession);
        uid2WebSocketSession.put(mySession.getUid(),session);
        unAssign.add(mySession.getUid());
        custmoerCounter.incrementAndGet();
    }


    public String getUsernameBySessionId(String sessionId){
        MySession session = sessionId2MySession.getOrDefault(sessionId, null);
        if(session==null){
            return  null;
        }
       return session.getUsername();
    }

    public Integer getUidBySessionId(String sessionId){
        MySession session = sessionId2MySession.getOrDefault(sessionId, null);
        if(session==null){
            return  null;
        }
        return session.getUid();
    }

    public WebSocketSession getWebSocektSessionByUid(int uid){
       return uid2WebSocketSession.getOrDefault(uid,null);
    }
    public boolean customerAssigned(int id){
       return assigned.contains(id);
    }
    public void adjest(int id,String supportSessionId){
        unAssign.remove(id);
        assigned.add(id);
        c2s.put(id,supportSessionId);
    }
    public String getSupportSessionId(Integer uid){
       return c2s.get(uid);
    }
    public int getTotal(){
        return custmoerCounter.get();
    }
}
