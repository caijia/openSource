package com.caijia.analysisopensource.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.caijia.analysisopensource.R;

/**
 * Created by cai.jia on 2017/11/30.
 */

public class XfermodeDemoView extends View{

    public XfermodeDemoView(Context context) {
        this(context,null);
    }

    public XfermodeDemoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    private Drawable shapeDrawable;

    public XfermodeDemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setAntiAlias(true);

        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.example);
        matrix = new Matrix();

        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        shapeDrawable = ContextCompat.getDrawable(context, R.drawable.shape_circle);
    }

    private Paint paint;
    private Xfermode xfermode ;
    private Bitmap bitmap;
    private Matrix matrix;
    private BitmapShader bitmapShader;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        createCircleImage(canvas);
    }

    private void createCircleImage1(Canvas canvas){
        int width = getWidth();
        int height = getHeight();
        paint.setShader(bitmapShader);
        float scale = width / bitmap.getWidth();
        matrix.postScale(scale, scale);
        bitmapShader.setLocalMatrix(matrix);
        canvas.drawCircle(width / 2,height / 2,width / 2,paint);
//        canvas.drawBitmap(drawableToBitmap(),0,0,paint);
    }

    private Bitmap drawableToBitmap() {
        int width = getWidth();
        int height = getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        shapeDrawable.setBounds(0, 0, width, height);
        shapeDrawable.draw(canvas);
        return bitmap;
    }

    private void createCircleImage(Canvas canvas){
        int width = getWidth();
        int height = getHeight();
        canvas.saveLayer(0, 0, width, height, null,Canvas.ALL_SAVE_FLAG);
        paint.setColor(Color.WHITE);
//        canvas.drawCircle(width / 2,height / 2,width / 2,paint);
        canvas.drawBitmap(drawableToBitmap(),0,0,paint);
        paint.setXfermode(xfermode);
        float scale = width / bitmap.getWidth();
        matrix.postScale(scale, scale);
        canvas.drawBitmap(bitmap,matrix,paint);
        canvas.restore();
        paint.setXfermode(null);
    }
}
