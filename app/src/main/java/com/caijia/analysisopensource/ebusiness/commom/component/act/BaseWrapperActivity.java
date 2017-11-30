package com.caijia.analysisopensource.ebusiness.commom.component.act;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Base里面不建议放公共布局等
 * Created by cai.jia on 2017/9/1 0001.
 */

public abstract class BaseWrapperActivity extends AppCompatActivity {

    @Override
    public void setContentView(View view) {
        super.setContentView(interceptContentView(view));
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(interceptContentView(layoutResID));
        ButterKnife.bind(this);
    }

    /**
     * 子类可以对布局拦截,做一些额外处理
     *
     * @param layoutId
     * @return
     */
    protected View interceptContentView(int layoutId) {
        ViewGroup viewGroup = (ViewGroup) getWindow().getDecorView();
        return LayoutInflater.from(this).inflate(layoutId, viewGroup, false);
    }

    protected View interceptContentView(View view) {
        return view;
    }

}
