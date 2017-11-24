package com.caijia.analysisopensource.core;

/**
 * Created by cai.jia on 2017/11/23.
 */

public class CacheControl {

    private static final String NO_CACHE = "no-cache";

    /**
     * 不使用缓存,当刷新时使用
     * @return
     */
    public static String network() {
        return NO_CACHE;
    }

    /**
     * 使用缓存，如果缓存没有过期
     * @param time 过期时间
     * @return
     */
    public static String mayCache(int time) {
        return "max-stale=" + time;
    }
}
