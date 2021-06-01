package com.learn.white_test.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ZhangLei
 * @Date: 2021/06/01/22:20
 * @Description:
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private String code;
    private String info;

    private String name;
    private Integer age;
    private String address;

}
