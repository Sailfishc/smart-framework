package com.sailfish.framework.helper;

import com.sailfish.framework.annotation.Action;
import com.sailfish.framework.bean.Handler;
import com.sailfish.framework.bean.Request;
import com.sailfish.framework.util.ArrayUtil;
import com.sailfish.framework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by travis on 2016/10/12.
 * 控制器助手类
 */
public final class ControllerHelper {

    /**
     * 用于存放请求和处理器的映射关系（ActionMap）
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {
        //获取所有的controller类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)){
            //遍历这些controller类
            for (Class<?> controllerClass : controllerClassSet) {
                //取出controller中定义的方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)){
                    //遍历controller中的method
                    for (Method method : methods) {
                        //判断方法上面是否有Action注解
                        if (method.isAnnotationPresent(Action.class)) {
                            //从action中 获取url映射
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            //验证url规则
                            if (mapping.matches("\\w+:/\\w*")) {
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                    //获取请求方法和请求路径
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);
                                    //初始化Action Map
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取Handler
     * @param requestMethod
     * @param requestPath
     * @return
     */
    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
