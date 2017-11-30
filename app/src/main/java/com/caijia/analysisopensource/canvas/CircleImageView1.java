/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.caijia.analysisopensource.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Private class created to work around issues with AnimationListeners being
 * called before the animation is actually complete and support shadows on older
 * platforms.
 */
public class CircleImageView1 extends AppCompatImageView {

    private static final int KEY_SHADOW_COLOR = 0x6E000000;
    private static final float X_OFFSET = 0f;
    private static final float Y_OFFSET = 1.75f;
    private static final float SHADOW_RADIUS = 3.5f;
    private int mShadowRadius;

    public CircleImageView1(Context context) {
        this(context,null);
    }

    public CircleImageView1(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleImageView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,Color.RED);
    }

    public void init(Context context, int color) {
        final float density = context.getResources().getDisplayMetrics().density;
        final int shadowYOffset = (int) (density * Y_OFFSET);
        final int shadowXOffset = (int) (density * X_OFFSET);

        mShadowRadius = (int) (density * SHADOW_RADIUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(0);
        }

        ShapeDrawable circle;
        OvalShape oval = new OvalShadow(mShadowRadius);
        circle = new ShapeDrawable(oval);
        setLayerType(View.LAYER_TYPE_SOFTWARE, circle.getPaint());
        circle.getPaint().setShadowLayer(mShadowRadius, shadowXOffset, shadowYOffset,
                KEY_SHADOW_COLOR);
        final int padding = mShadowRadius;
        // set padding so the inner image sits correctly within the shadow.
        setPadding(padding, padding, padding, padding);
        circle.getPaint().setColor(color);
        ViewCompat.setBackground(this, circle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth() + mShadowRadius * 2, getMeasuredHeight()
                + mShadowRadius * 2);
    }

    @Override
    public void setBackgroundColor(int color) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(color);
        }
    }

    private class OvalShadow extends OvalShape {
        private RadialGradient mRadialGradient;
        private Paint mShadowPaint;

        OvalShadow(int shadowRadius) {
            super();
            mShadowPaint = new Paint();
            mShadowRadius = shadowRadius;
            updateRadialGradient((int) rect().width());
        }

        @Override
        protected void onResize(float width, float height) {
            super.onResize(width, height);
            updateRadialGradient((int) width);
        }

        @Override
        public void draw(Canvas canvas, Paint paint) {
            final int viewWidth = CircleImageView1.this.getWidth();
            final int viewHeight = CircleImageView1.this.getHeight();
            canvas.drawCircle(viewWidth / 2, viewHeight / 2, viewWidth / 2 - mShadowRadius, paint);
        }

        private void updateRadialGradient(int diameter) {
//            mRadialGradient = new RadialGradient(diameter / 2, diameter / 2,
//                    mShadowRadius, new int[] { FILL_SHADOW_COLOR, Color.TRANSPARENT },
//                    null, Shader.TileMode.CLAMP);
//            mShadowPaint.setShader(mRadialGradient);
        }
    }
}
