package com.xiaomi.cs.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sun.xml.internal.bind.v2.model.core.ID;
import lombok.Data;

import java.util.Date;

/**
 * @author l
 * @create 2020-11-15-17:24
 */
@Data
public class Knowledge {
  @TableId(type = IdType.AUTO,value ="id")
  private Integer id;
  private Integer categoryId;
  private String queryName;
  private String answer;
  private String keyword;
  private Date lastUpdateTime;

}
