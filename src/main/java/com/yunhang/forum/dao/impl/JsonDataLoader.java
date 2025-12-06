package com.yunhang.forum.dao.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken; // **处理泛型擦除的关键类**
import com.yunhang.forum.dao.DataLoader;
import com.yunhang.forum.model.entity.Post;
import com.yunhang.forum.model.entity.User;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonDataLoader implements DataLoader {

  private static final String USER_FILE_PATH = "data/users.json";
  private static final String POST_FILE_PATH = "data/posts.json";

  private final Gson gson;

  public JsonDataLoader() {
    // 使用 GsonBuilder 初始化 Gson
    this.gson = new GsonBuilder().setPrettyPrinting().create();

    // 确保 data 文件夹和 json 文件存在
    initFile(USER_FILE_PATH);
    initFile(POST_FILE_PATH);
  }

  private void initFile(String pathStr) {
    Path path = Paths.get(pathStr);
    try {
      if (path.getParent() != null) {
        Files.createDirectories(path.getParent()); // 创建 data 文件夹
      }
      if (!Files.exists(path)) {
        Files.writeString(path, "[]"); // 如果文件不存在，创建空 JSON 列表 "[]"
        System.out.println("Initialized data file: " + pathStr);
      }
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to initialize data file: " + pathStr);
    }
  }

  @Override
  public List<User> loadUsers() {
    try (Reader reader = new FileReader(USER_FILE_PATH)) {
      // 使用 TypeToken 解决 List<User> 泛型问题
      Type userListType = new TypeToken<ArrayList<User>>() {
      }.getType();
      List<User> users = gson.fromJson(reader, userListType);

      return users != null ? users : new ArrayList<>();
    } catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @Override
  public boolean saveUsers(List<User> users) {
    try (Writer writer = new FileWriter(USER_FILE_PATH)) {
      gson.toJson(users, writer);
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public List<Post> loadPosts() {
    try (Reader reader = new FileReader(POST_FILE_PATH)) {
      Type postListType = new TypeToken<ArrayList<Post>>() {
      }.getType();
      List<Post> posts = gson.fromJson(reader, postListType);
      return posts != null ? posts : new ArrayList<>();
    } catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  @Override
  public boolean savePosts(List<Post> posts) {
    try (Writer writer = new FileWriter(POST_FILE_PATH)) {
      gson.toJson(posts, writer);
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }
}
