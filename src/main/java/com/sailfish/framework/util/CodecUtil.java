package com.sailfish.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by travis on 2016/10/12.
 * 编码与解码工具类
 */
public final class CodecUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);

    /**
     * 将url编码
     * @param source
     * @return
     */
    public static String encodeURL(String source){
        String target;
        try {
            target = URLEncoder.encode(source, "UTF-8");
        }catch (Exception e) {
            LOGGER.error("encode failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将url解码
     * @param source
     * @return
     */
    public static String decodeURL(String source){
        String target;
        try {
            target = URLDecoder.decode(source, "UTF-8");
        }catch (Exception e) {
            LOGGER.error("decode failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }
}
