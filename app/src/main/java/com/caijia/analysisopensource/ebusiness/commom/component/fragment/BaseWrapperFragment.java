package com.caijia.analysisopensource.ebusiness.commom.component.fragment;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.ButterKnife;

/**
 * Base里面不建议放公共布局等
 * Created by cai.jia on 2017/8/29 0029.
 */

public abstract class BaseWrapperFragment extends Fragment {

    @CallSuper
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(getLayoutId(), container, false);
        View interceptContentView = interceptContentView(view);
        //加一层布局,方便显示StateView
        if (isAddRootLayout() && getContext() != null) {
            FrameLayout frameLayout = new FrameLayout(getContext());
            frameLayout.addView(interceptContentView);
            ButterKnife.bind(this, frameLayout);
            return frameLayout;

        }else{
            ButterKnife.bind(this, interceptContentView);
            return interceptContentView;
        }
    }

    /**
     * 布局id
     *
     * @return
     */
    protected abstract @LayoutRes int getLayoutId();

    /**
     * 子类可以对布局拦截,做一些额外处理
     *
     * @param view
     * @return
     */
    protected View interceptContentView(View view) {
        return view;
    }

    /**
     * 是否添加一层父布局
     * @return
     */
    protected boolean isAddRootLayout() {
        return true;
    }
}
