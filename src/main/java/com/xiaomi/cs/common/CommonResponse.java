package com.xiaomi.cs.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author l
 * @create 2020-11-05-10:55
 */
@Data
public class CommonResponse  implements Serializable {
   private String msg;
   private Integer code;
   private Object data;
   public CommonResponse(int code,String msg,Object data){
       this.code=code;
       this.msg=msg;
       this.data=data;
   }
}
