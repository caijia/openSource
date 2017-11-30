package com.caijia.analysisopensource.ebusiness.commom.http;

import android.content.Context;

import com.caijia.analysisopensource.ebusiness.commom.util.NetStateUtil;

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
    public static String mayCache(Context context,int time) {
        boolean connected = NetStateUtil.isConnected(context);
        time = connected ? time : Integer.MAX_VALUE;
        return "max-stale=" + time;
    }
}
