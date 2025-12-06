package com.yunhang.forum.dao;

import com.yunhang.forum.model.entity.Post;
import com.yunhang.forum.model.entity.User;

import java.util.List;

/**
 * 数据加载器接口 (DAO Interface)
 */
public interface DataLoader {

  // 读取所有用户
  List<User> loadUsers();

  // 保存所有用户
  boolean saveUsers(List<User> users);

  // 读取所有帖子
  List<Post> loadPosts();

  // 保存所有帖子
  boolean savePosts(List<Post> posts);
}
