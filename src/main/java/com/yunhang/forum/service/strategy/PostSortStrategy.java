package com.yunhang.forum.service.strategy;

import com.yunhang.forum.model.entity.Post;

import java.util.List;

/**
 * 帖子排序策略接口
 */
public interface PostSortStrategy {
  void sort(List<Post> posts);
}
