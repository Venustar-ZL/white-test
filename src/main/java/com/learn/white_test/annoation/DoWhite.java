package com.learn.white_test.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: ZhangLei
 * @Date: 2021/05/30/22:32
 * @Description:
 * @Version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoWhite {

    /*设置拦截的key*/
    String key() default "";

    String returnJson() default  "";

}
