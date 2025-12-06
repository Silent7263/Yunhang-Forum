package com.yunhang.forum.service.strategy;

import com.yunhang.forum.model.entity.Post;

import java.util.List;

/**
 * 帖子搜索策略接口
 */
public interface PostSearchStrategy {
  List<Post> search(List<Post> source, String keyword);
}
