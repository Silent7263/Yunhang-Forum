package com.yunhang.forum;

import com.yunhang.forum.dao.DataLoader;
import com.yunhang.forum.dao.impl.JsonDataLoader;
import com.yunhang.forum.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class DataPersistenceTest {

  private static final String TEST_USER_FILE = "data/users.json";

  @Test
  void testUserSaveAndLoadConsistency() {
    // 1. 准备数据
    DataLoader dataLoader = new JsonDataLoader();
    List<User> originalUsers = new ArrayList<>();

    // 创建一个模拟用户，确保 ID 和昵称有值
    User user1 = new User();
    user1.setId("usr_test_001");
    user1.setNickname("测试用户");
    user1.setPasswordHash("hashed_password");
    originalUsers.add(user1);

    // 2. 写入数据
    boolean saveResult = dataLoader.saveUsers(originalUsers);
    // 验证写入必须成功
    Assertions.assertTrue(saveResult, "保存用户数据应该成功");

    // 3. 重新读取数据 (模拟程序重启)
    DataLoader newDataLoader = new JsonDataLoader();
    List<User> loadedUsers = newDataLoader.loadUsers();

    // 4. 验证数据的一致性
    Assertions.assertEquals(1, loadedUsers.size(), "读取到的用户数量应为 1");

    // 验证 JSON 文件是否实际存在于项目根目录的 data 文件夹中
    Assertions.assertTrue(new File(TEST_USER_FILE).exists(), "JSON 文件必须存在");

    // 验证读取出的数据内容是否正确
    User loadedUser = loadedUsers.get(0);
    Assertions.assertEquals(user1.getId(), loadedUser.getId(), "ID 应该保持一致");
    Assertions.assertEquals(user1.getNickname(), loadedUser.getNickname(), "昵称应该保持一致");
  }
}
