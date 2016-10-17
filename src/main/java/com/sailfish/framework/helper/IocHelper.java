package com.sailfish.framework.helper;

import com.sailfish.framework.annotation.Inject;
import com.sailfish.framework.util.ArrayUtil;
import com.sailfish.framework.util.CollectionUtil;
import com.sailfish.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by travis on 2016/10/12.
 * Ioc助手类
 * 步骤：
 *      1、通过BeanHelper获取所有的映射
 *      2、遍历映射
 *      3、分别取出Bean和Bean实例
 *      4、通过反射获取类中的成员变量
 *      5、遍历这些成员变量
 *      6、判断是否有inject注解
 *      7、如果有，就取出改bean实例，通过setField修改该成员变量的值
 */
public final class IocHelper {

    static {
        //获取所有的bean和bean实例的映射
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)){
            //遍历映射
            for (Map.Entry<Class<?>, Object> beanEntity : beanMap.entrySet()) {
                //从BeanMap中取出bean和bean实例的映射
                Class<?> beanClass = beanEntity.getKey();
                Object beanInstance = beanEntity.getValue();
                //获取bean类中定义的所有的成员变量
                Field[] fields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(fields)){
                    //遍历成员变量
                    for (Field field : fields) {
                        //判断当前bean field是否带有inject注解
                        if (field.isAnnotationPresent(Inject.class)){
                            //在bean map中获取bean field对应的实例
                            Class<?> beanFieldClass = field.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null){
                                //通过反射初始化beanfield的值
                                ReflectionUtil.setField(beanInstance, field, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
