package com.sailfish.framework.bean;

import java.util.Map;

/**
 * Created by travis on 2016/10/12.
 * 返回视图对象
 */
public class View {

    /**
     * 视图路径
     */
    private String path;

    /**
     * 模型数据
     */
    private Map<String, Object> model;

    public View(String path) {
        this.path = path;
    }

    public View addModel(String path, Object value) {
        model.put(path, value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
