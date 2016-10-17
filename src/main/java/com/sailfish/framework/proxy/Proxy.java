package com.sailfish.framework.proxy;

/**
 * 代理接口
 * Created by travis on 2016/10/16.
 */
public interface Proxy {

    /**
     * 执行链式代理
     * @param proxyChain
     * @return
     * @throws Throwable
     * 链式代理就是可以将多个代理通过一条链子穿起来，一个一个的去执行，执行顺序取决于添加到链子上的先后顺序
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}

