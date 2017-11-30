package com.caijia.analysisopensource.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * 使用{@link android.graphics.BitmapShader}来实现不同形状的ImageView
 * 在预览界面可以看到不同形状的ImageView
 * Created by cai.jia on 2017/11/30.
 */

public class ShaderImageView extends AppCompatImageView {

    private Matrix shaderMatrix;
    private BitmapShader bitmapShader;
    private Paint paint;
    private Bitmap bitmap;

    public ShaderImageView(Context context) {
        this(context, null);
    }
    public ShaderImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public ShaderImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        shaderMatrix = new Matrix();
        paint = new Paint();
        paint.setAntiAlias(true);
        initialBitmapShader();
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        initialBitmapShader();
    }

    @Override
    public void setImageURI(@Nullable Uri uri) {
        super.setImageURI(uri);
        initialBitmapShader();
    }

    private void initialBitmapShader() {
        bitmap = getBitmapFromDrawable();
        if (bitmap != null && paint != null) {
            bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            paint.setShader(bitmapShader);
        }
    }

    private Bitmap getBitmapFromDrawable() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        int width = getWidth();
        int height = getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bitmapShader != null) {
            int width = getWidth();
            int height = getHeight();
            float scale = width / bitmap.getWidth();

            shaderMatrix.reset();
            shaderMatrix.postScale(scale, scale);
            bitmapShader.setLocalMatrix(shaderMatrix);

            canvas.drawCircle(width / 2,height / 2,width / 2,paint);
        }
    }
}
