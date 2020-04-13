package com.lsl.core.mybatis.type;

import java.lang.annotation.*;

/**
 * @Author: liushoulong
 * @Date: 2019/11/20 14:17
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Alias {
    String value();
}
