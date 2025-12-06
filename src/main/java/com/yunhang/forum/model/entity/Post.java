package com.yunhang.forum.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Post implements Serializable {
  private String id;           // 用于标识帖子 (如: pst_xxx)
  private String title;
  private String content;
  // 排序依赖 1: 帖子发布时间 (TimeSortStrategy 依赖)
  private LocalDateTime publishTime;
  // 排序依赖 2: 热度分数 (HotSortStrategy 依赖)
  private double hotScore;
}
