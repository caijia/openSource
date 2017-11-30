package com.caijia.analysisopensource.ebusiness.commom.component.fragment;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * fragment懒加载,在ViewPager里面使用
 * 在ViewPager里面加载fragment时,当fragment可见时才进行数据加载,一次只加载一页数据
 * Note:
 * 不过不用这个类，也只在第一次的时候加载两页数据
 * Created by cai.jia on 2017/9/1 0001.
 */

public abstract class LazyFragment extends BaseWrapperFragment {

    private boolean isLoadNetworkData;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && getView() != null) {
            onVisible();
            if (!isLoadNetworkData) {
                isLoadNetworkData = true;
                onLoadNetworkData();
            }
        }
    }

    @CallSuper
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (getUserVisibleHint() && isVisible()) {
            onVisible();
            if (!isLoadNetworkData) {
                isLoadNetworkData = true;
                onLoadNetworkData();
            }
        }
    }

    /**
     * 在这个方法里面进行数据加载
     */
    protected void onLoadNetworkData(){
    }

    /**
     * 页面是可见状态
     */
    protected void onVisible(){
    }
}
