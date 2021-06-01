package com.learn.white_test.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: ZhangLei
 * @Date: 2021/05/30/22:50
 * @Description:
 * @Version: 1.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "whitelist")
//@PropertySource(value = "classpath:application.yml", encoding = "UTF-8", factory = YamlPropertyLoaderFactory.class)
public class PropertiesConfig {

    private String users;



}
