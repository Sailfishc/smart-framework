package com.sailfish.framework;

import com.sailfish.framework.helper.*;
import com.sailfish.framework.util.ClassUtil;

/**
 * Created by travis on 2016/10/12.
 * 加载相应的class
 */
public final class HelpLoader {

    /**
     * aop要在ioc之前加载
     */
    public static void init(){
        Class<?> [] classList = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName(), true);
        }
    }
}
