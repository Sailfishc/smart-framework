package com.sailfish.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by travis on 2016/10/16.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    /**
     * 注解
     * @return
     */
    Class<? extends Annotation> value();
}
