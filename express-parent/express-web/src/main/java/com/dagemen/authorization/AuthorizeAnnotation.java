package com.dagemen.authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorizeAnnotation {
    /**
     * 是否需要鉴权,默认不需要
     */
    boolean isPermission() default false;

    /**
     * 是否需要登录，默认需要
     */
    boolean isLogin() default true;
}
