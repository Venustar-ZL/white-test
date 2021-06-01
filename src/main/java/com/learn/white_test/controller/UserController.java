package com.learn.white_test.controller;

import com.learn.white_test.annoation.DoWhite;
import com.learn.white_test.bean.UserInfo;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ZhangLei
 * @Date: 2021/06/01/22:09
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/test")
public class UserController {

    @DoWhite(key = "userId", returnJson = "{\"code\":\"1111\",\"info\":\"非白名单可访问用户拦截！\"}")
    @GetMapping
    public UserInfo test(@RequestParam("userId") String userId) {
        System.out.println("get user info...");
        return new UserInfo();
    }

}
