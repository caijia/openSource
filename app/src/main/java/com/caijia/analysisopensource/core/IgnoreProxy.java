package com.caijia.analysisopensource.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 如果Repository中的方法标识了这个注解，表示不会被代理
 * Created by cai.jia on 2017/11/23.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreProxy {

}
