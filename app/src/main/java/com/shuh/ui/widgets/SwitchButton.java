package com.shuh.ui.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.shuh.ui.R;
import com.shuh.ui.utils.PP;

/**
 * Created by Administrator on 2016/4/29 0029.
 */
public class SwitchButton extends View {

    private Paint mPaint;
    private Bitmap bitmapBG;
    private Bitmap bitmapSlide;
    private boolean isOpen;
    private int MAX_LEFT;
    private int slideLeft;
    private boolean isClick;
    private OnCheckChangedListener listener;
    private static final String NAMESPACE = "http://schemas.android.com/apk/res-auto";

    public SwitchButton(Context context) {
        super(context);
        init(null);
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void setOnCheckChangedListener(OnCheckChangedListener listener){
        this.listener = listener;
    }

    private void init(AttributeSet attrs){
        mPaint = new Paint();
        bitmapBG = BitmapFactory.decodeResource(getResources(), R.mipmap.switch_background);
        int slideID;
        if(attrs != null){
            isOpen = attrs.getAttributeBooleanValue(NAMESPACE, "checked", false);
            slideID = attrs.getAttributeResourceValue(NAMESPACE, "slide", R.mipmap.slide_button);
        }else{
            slideID = R.mipmap.slide_button;
        }
        bitmapSlide = BitmapFactory.decodeResource(getResources(), slideID);
        MAX_LEFT = bitmapBG.getWidth() - bitmapSlide.getWidth();
        slideLeft = isOpen ? MAX_LEFT : 0;
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isClick){
                    isOpen = !isOpen;
                    slideLeft = isOpen ? MAX_LEFT : 0;
                    invalidate();
                    if(listener != null){
                        listener.onCheckChanged(SwitchButton.this, isOpen);
                    }
                }
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(bitmapBG.getWidth(), bitmapBG.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmapBG, 0, 0, mPaint);
        canvas.drawBitmap(bitmapSlide, slideLeft, 0, mPaint);
    }



    private int startX;
    private int moveX;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) (event.getX() - startX);
                moveX += Math.abs(dx);
                slideLeft += dx;
                if(slideLeft > MAX_LEFT)
                    slideLeft = MAX_LEFT;
                else if(slideLeft < 0)
                    slideLeft = 0;
                invalidate();
                startX = (int) event.getX();
                break;
            case MotionEvent.ACTION_UP:
                isClick = moveX <= 5;
                moveX = 0;
                if(!isClick){
                    boolean currentStatus = isOpen;
                    isOpen = slideLeft >= MAX_LEFT/2;
                    slideLeft = isOpen ? MAX_LEFT : 0;
                    invalidate();
                    if(currentStatus != isOpen && listener != null){
                        listener.onCheckChanged(SwitchButton.this, isOpen);
                    }
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    public interface OnCheckChangedListener {
        void onCheckChanged(View view, boolean isChecked);
    }
}
