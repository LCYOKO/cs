package com.xiaomi.cs.handler;

import org.springframework.stereotype.Component;

/**
 * @author l
 * @create 2020-11-09-16:24
 */
@Component
public class RobotHandler {
     private final String  robotName="小爱一号";
     private final int id=-1;
    public String getAnswer(String query){

        return "no answer";
    }

    public String getBotName(){
        return robotName;
    }
    public int getId(){
        return id;
    }

}
