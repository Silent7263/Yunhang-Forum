package com.yunhang.forum.model.entity;

import lombok.Data;

import java.io.Serializable;

// Lombok @Data 自动生成 Getters, Setters, toString, equals, hashCode
@Data
public class User implements Serializable {
  private String id;        // 用于标识用户 (如: usr_xxx)
  private String nickname;
  private String passwordHash; // 用于持久化密码
}
