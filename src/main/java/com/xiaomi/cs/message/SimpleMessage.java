package com.xiaomi.cs.message;

import lombok.Data;


/**
 * @author l
 * @create 2020-11-06-10:39
 */
@Data
public class SimpleMessage  {
   private int type;
   private String msg;
   private int fromUid;
   private String fromUsername;
   private int toUid;
   private String toUsername;
   public SimpleMessage(){

   }
   public SimpleMessage(int to,String toName,int from,String fromName,String msg,int type){
      toUid=to;
      toUsername=toName;
      fromUid=from;
      fromUsername=fromName;
      this.msg=msg;
      this.type=type;
   }
}
