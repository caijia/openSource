package com.caijia.analysisopensource.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by cai.jia on 2017/11/29.
 */

public class CircleImageView extends AppCompatImageView {

    private Paint paint;
    private Xfermode srcIn;
    private Bitmap bitmap;

    public CircleImageView(Context context) {
        this(context, null);
    }
    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);

        srcIn = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    private BitmapShader bitmapShader;

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();

        if (width == 0 || height == 0) {
            return;
        }

        if (bitmap == null) {
            return;
        }
        int radius = Math.min(width, height) / 2;

    }

    private Bitmap getBitmapFromDrawable() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        } catch (Exception e) {

        }
        return bitmap;
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        bitmap = getBitmapFromDrawable();
    }

    @Override
    public void setImageURI(@Nullable Uri uri) {
        super.setImageURI(uri);
        bitmap = getBitmapFromDrawable();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        bitmap = getBitmapFromDrawable();
    }
}
