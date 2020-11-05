package com.xiaomi.cs.pojo.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author l
 * @create 2020-10-29-10:41
 */
@Data
public class User {
  private Integer id;
  private String miliao;
  private String userName;
  private String password;
  private Date  lastModifyTime;
  private Long createTime;
  private Long lastLoginTime;
  private Long updateTime;
}
