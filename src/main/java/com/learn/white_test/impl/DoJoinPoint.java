package com.learn.white_test.impl;

import com.alibaba.fastjson.JSON;
import com.learn.white_test.annoation.DoWhite;
import com.learn.white_test.config.PropertiesConfig;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: ZhangLei
 * @Date: 2021/05/30/22:39
 * @Description:
 * @Version: 1.0
 */
@Aspect
@Component
public class DoJoinPoint {

    private static final Logger log = LoggerFactory.getLogger(DoJoinPoint.class);

    @Autowired
    private PropertiesConfig propertiesConfig;

    @Pointcut("@annotation(com.learn.white_test.annoation.DoWhite)")
    public void aopPoint() {}

    @Around("aopPoint()")
    public Object doRouter(ProceedingJoinPoint jp) throws Throwable {

        Method method = getMethod(jp);
        DoWhite whiteList = method.getAnnotation(DoWhite.class);

        String keyValue = getFieldValue(whiteList.key(), jp.getArgs());

        log.info("middleware whitelist handler method:{} value:{}", method.getName(), keyValue);

        if (StringUtils.isBlank(keyValue)) {
            return jp.proceed();
        }

        String[] splits = propertiesConfig.getUsers().split(",");
        for (String str : splits) {
            if (StringUtils.equals(keyValue, str)) {
                return jp.proceed();
            }
        }

        /*拦截*/
        return returnObject(whiteList, method);

    }

    private Object returnObject(DoWhite doWhite, Method method) throws IllegalAccessException, InstantiationException {
        Class<?> returnType = method.getReturnType();
        String returnJson = doWhite.returnJson();
        if (StringUtils.isBlank(returnJson)) {
            return returnType.newInstance();
        }
        return JSON.parseObject(returnJson, returnType);
    }

    private Method getMethod(JoinPoint  jp) throws NoSuchMethodException {
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return jp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

    /**
     *
     * @param key  需要获取的值
     * @param args  方法参数
     * @return
     */
    private String getFieldValue(String key, Object[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
//        String fieldValue = "";
//        for (Object arg : args) {
//            fieldValue = BeanUtils.getProperty(arg, key);
//            if (StringUtils.isNotBlank(fieldValue)) {
//                break;
//            }
//        }
//        return fieldValue;

        String filedValue = null;
        for (Object arg : args) {
            try {
                if (null == filedValue || "".equals(filedValue)) {
                    filedValue = BeanUtils.getProperty(arg, key);
                } else {
                    break;
                }
            } catch (Exception e) {
                if (args.length == 1) {
                    return args[0].toString();
                }
            }
        }
        return filedValue;
    }

}
