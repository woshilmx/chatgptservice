package com.lmx.project.service;

import com.lmx.project.MyApplication;
import com.lmx.project.model.entity.ChatModel;
import com.lmx.project.util.ChatGptUntil;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 用户服务测试
 *
 * @author lmx
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserServiceTest {
    @Resource
    private ChatGptUntil chatGptUntil;

    @Test
    public void testChatgpt() throws IOException {
        ChatModel chatModel = new ChatModel();
        chatModel.setRole("user");
        chatModel.setContent("2021年1月发生了什么");

        ArrayList<ChatModel> chatModels = new ArrayList<>();
        chatModels.add(chatModel);


//        chatGptUntil.getRespost(chatModels);
    }

//    @Resource
//    private UserService userService;
//
//    @Test
//    void testAddUser() {
//        User user = new User();
//        boolean result = userService.save(user);
//        System.out.println(user.getId());
//        Assertions.assertTrue(result);
//    }
//
//    @Test
//    void testUpdateUser() {
//        User user = new User();
//        boolean result = userService.updateById(user);
//        Assertions.assertTrue(result);
//    }
//
//    @Test
//    void testDeleteUser() {
//        boolean result = userService.removeById(1L);
//        Assertions.assertTrue(result);
//    }
//
//    @Test
//    void testGetUser() {
//        User user = userService.getById(1L);
//        Assertions.assertNotNull(user);
//    }
//
//    @Test
//    void userRegister() {
//        String userAccount = "lmx";
//        String userPassword = "";
//        String checkPassword = "123456";
//        try {
//            long result = userService.userRegister(userAccount, userPassword, checkPassword);
//            Assertions.assertEquals(-1, result);
//            userAccount = "yu";
//            result = userService.userRegister(userAccount, userPassword, checkPassword);
//            Assertions.assertEquals(-1, result);
//            userAccount = "lmx";
//            userPassword = "123456";
//            result = userService.userRegister(userAccount, userPassword, checkPassword);
//            Assertions.assertEquals(-1, result);
//            userAccount = "yu pi";
//            userPassword = "12345678";
//            result = userService.userRegister(userAccount, userPassword, checkPassword);
//            Assertions.assertEquals(-1, result);
//            checkPassword = "123456789";
//            result = userService.userRegister(userAccount, userPassword, checkPassword);
//            Assertions.assertEquals(-1, result);
//            userAccount = "doglmx";
//            checkPassword = "12345678";
//            result = userService.userRegister(userAccount, userPassword, checkPassword);
//            Assertions.assertEquals(-1, result);
//            userAccount = "lmx";
//            result = userService.userRegister(userAccount, userPassword, checkPassword);
//            Assertions.assertEquals(-1, result);
//        } catch (Exception e) {
//
//        }
//    }
}