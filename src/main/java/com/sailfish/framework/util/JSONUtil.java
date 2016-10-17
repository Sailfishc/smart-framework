package com.sailfish.framework.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by travis on 2016/10/12.
 * json工具类
 */
public final class JSONUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将pojo转为json
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJson(T obj){
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        }catch (Exception e) {
            LOGGER.error("convert pojo to json failure", e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * 将json转为pojo
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> type){
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json, type);
        }catch (Exception e){
            LOGGER.error("convert json to pojo failure", e);
            throw new RuntimeException(e);
        }
        return pojo;
    }
}
