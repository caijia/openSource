package com.caijia.analysisopensource.ebusiness.commom.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * use:
 * {@link #attach(Activity),#attach(Fragment),#attach(ViewGroup)}
 * {@link #showView(int),#hideAllView()}
 * <p>
 * custom state view:
 * {@link #setStateViewIds(int, int, int),#setStateViewId(int, int)}
 * Created by cai.jia on 2017/8/29 0029.
 */

public class StateView extends FrameLayout {

    public static final int STATE_LOADING = 1;
    public static final int STATE_RETRY = 2;
    public static final int STATE_EMPTY = 3;
    private View emptyView;
    private View loadingView;
    private View retryView;
    private int emptyViewId;
    private int loadingViewId;
    private int retryViewId;
    private OnRetryClickListener retryClickListener;
    private @IdRes int retryClickViewId;

    public StateView(@NonNull Context context) {
        this(context, null);
    }

    public StateView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateView(@NonNull Context context, @Nullable AttributeSet attrs,
                     @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public StateView(@NonNull Context context, @Nullable AttributeSet attrs,
                     @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private static ViewGroup.LayoutParams getLayoutParams(View view, int topMargin) {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.topMargin = topMargin;
        return params;
    }

    public static StateView attach(Activity activity, int topMargin) {
        ViewGroup parent = activity.findViewById(android.R.id.content);
        return attach(parent, topMargin);
    }

    public static StateView attach(Activity activity) {
        ViewGroup parent = activity.findViewById(android.R.id.content);
        return attach(parent, 0);
    }

    public static StateView attach(Fragment fragment, int topMargin) {
        View view = fragment.getView();
        if (view != null && view instanceof ViewGroup) {
            return attach((ViewGroup) view, topMargin);
        }
        return null;
    }

    public static StateView attach(Fragment fragment) {
        return attach(fragment, 0);
    }

    public static StateView attach(ViewGroup parent) {
        return attach(parent, 0);
    }

    /**
     * 将StateView加载到parent中
     * @param parent 推荐需要StateView的ViewGroup最好是FrameLayout,RelativeLayout,这样不会增加布局的层级
     * @param topMargin
     * @return
     */
    private static StateView attach(ViewGroup parent, int topMargin) {
        StateView stateView = new StateView(parent.getContext());
        if (parent instanceof FrameLayout || parent instanceof RelativeLayout) {
            ViewGroup.LayoutParams params = getLayoutParams(parent, topMargin);
            parent.addView(stateView, params);

        } else {
            ViewGroup parentContainer = (ViewGroup) parent.getParent();
            int groupIndex = parentContainer.indexOfChild(parent);
            parentContainer.removeView(parent);

            FrameLayout coverContainer = new FrameLayout(parent.getContext());
            ViewGroup.LayoutParams coverParams = parent.getLayoutParams();
            coverContainer.setLayoutParams(coverParams);
            coverContainer.addView(parent, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            parentContainer.addView(coverContainer, groupIndex, coverParams);
            ViewGroup.LayoutParams params = getLayoutParams(coverContainer, topMargin);
            coverContainer.addView(stateView, params);
        }
        return stateView;
    }

    public static int getActionBarAndStatusBarHeight(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return getStatusBarHeight(context) + dpToPx(context, 48);
        }else{
            return dpToPx(context, 48);
        }
    }

    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    private static int dpToPx(Context context, float dp) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics()));
    }

    private void init(Context context, AttributeSet attrs) {
        //default
//        emptyViewId = R.layout.common_stateview_empty;
//        loadingViewId = R.layout.common_stateview_loading;
//        retryViewId = R.layout.common_stateview_retry;
    }

    public void showView(@State int state) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        switch (state) {
            case STATE_LOADING: {
                if (loadingView == null) {
                    loadingView = inflater.inflate(loadingViewId, this, false);
                    loadingView.setClickable(true);
                    addView(loadingView);
                }
                setVisibility(loadingView, VISIBLE);
                break;
            }

            case STATE_RETRY: {
                if (retryView == null) {
                    retryView = inflater.inflate(retryViewId, this, false);
                    retryView.setClickable(true);
                    addView(retryView);
                    performRetryClick();
                }
                setVisibility(retryView, VISIBLE);
                break;
            }

            case STATE_EMPTY: {
                if (emptyView == null) {
                    emptyView = inflater.inflate(emptyViewId, this, false);
                    emptyView.setClickable(true);
                    addView(emptyView);
                }
                setVisibility(emptyView, VISIBLE);
                break;
            }
        }
    }

    private void setVisibility(View stateView, int visibility) {
        hideAllView();
        stateView.setVisibility(visibility);
    }

    public void hideAllView() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            view.setVisibility(GONE);
        }
    }

    public void setStateViewId(@State int state, @LayoutRes int stateViewId) {
        switch (state) {
            case STATE_LOADING: {
                loadingViewId = stateViewId;
                break;
            }

            case STATE_RETRY: {
                retryViewId = stateViewId;
                break;
            }

            case STATE_EMPTY: {
                emptyViewId = stateViewId;
                break;
            }
        }
    }

    public void setStateViewIds(@LayoutRes int emptyViewId, @LayoutRes int loadingViewId,
                                @LayoutRes int retryViewId) {
        this.emptyViewId = emptyViewId;
        this.loadingViewId = loadingViewId;
        this.retryViewId = retryViewId;
    }

    public void setRetryClickListener(final OnRetryClickListener retryClickListener) {
        setRetryClickListener(View.NO_ID, retryClickListener);
    }

    public void setRetryClickListener(@IdRes int retryClickViewId,
                                      final OnRetryClickListener retryClickListener) {
        this.retryClickViewId = retryClickViewId;
        this.retryClickListener = retryClickListener;
    }

    private void performRetryClick() {
        if (retryView == null) {
            return;
        }

        View clickView;

        if (retryClickViewId == View.NO_ID) {
            clickView = retryView;

        } else {
            View childRetryView = (View) retryView.getTag(retryClickViewId);
            if (childRetryView == null) {
                childRetryView = retryView.findViewById(retryClickViewId);
            }
            if (childRetryView != null) {
                retryView.setTag(retryClickViewId, childRetryView);
            }
            clickView = childRetryView == null ? retryView : childRetryView;
        }

        clickView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (retryClickListener != null) {
                    showView(STATE_LOADING);
                    retryClickListener.onRetryClickListener(v);
                }
            }
        });
    }

    public interface OnRetryClickListener {

        void onRetryClickListener(View v);
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STATE_LOADING, STATE_RETRY, STATE_EMPTY})
    public @interface State {

    }
}
