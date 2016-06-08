package com.shuh.ui.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.shuh.ui.utils.PP;

/**
 * Created by Administrator on 2016/4/29 0029.
 */
public class MyViewPager extends ViewGroup {

    private Scroller scroller;
    private GestureDetector mDetector;
    private OnPageChangeListener pageChangeListener;

    public MyViewPager(Context context) {
        super(context);
        init(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        scroller = new Scroller(context);
        mDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                PP.d("getScrollX: " + getScrollX());
                if(canScroll())
                    scrollBy((int) distanceX, 0);
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        });
    }

    public void setOnPageChangeListener(OnPageChangeListener listener){
        this.pageChangeListener = listener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i=0,ii=getChildCount();i<ii;i++){
            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private boolean canScroll(){
        int scrollX = getScrollX();
        return scrollX >= 0 && scrollX <= (getChildCount() - 1) * getWidth();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i=0,ii=getChildCount();i<ii;i++){
            getChildAt(i).layout(i*getWidth(), 0, (i+1)*getWidth(), getHeight());
        }
    }

    private int startX, startY;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDetector.onTouchEvent(event);// 将ACTION_DOWN传递给手势识别器, 避免事件丢失
                startX = (int) event.getX();
                startY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) (event.getX() - startX);
                int dy = (int) (event.getY() - startY);
                return Math.abs(dx) >  Math.abs(dy);
            default:
                break;
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);// 委托手势识别器处理
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                int position = (scrollX + getWidth()/2)/getWidth();
                if(position > getChildCount() - 1)
                    position = getChildCount() - 1;

                setCurrentItem(position);
                break;
        }
        return true;
    }

    public void setCurrentItem(int position){
        int dx = position*getWidth() - getScrollX();
        scroller.startScroll(getScrollX(), 0, dx, 0, Math.abs(dx));
        invalidate();
        if(pageChangeListener != null)
            pageChangeListener.onPageSelected(position);
    }

    @Override
    public void computeScroll() {
        if(scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), 0);
            invalidate();
        }
    }

    public interface OnPageChangeListener {
        public void onPageSelected(int position);
    }
}
