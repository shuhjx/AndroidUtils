package com.shuh.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.shuh.ui.base.BaseActivity;
import com.shuh.ui.widgets.MyViewPager;

/**
 * Created by Administrator on 2016/5/5 0005.
 */
public class ViewPageActivity extends BaseActivity {

    private MyViewPager viewPager;
    private RadioGroup radioGroup;
    private static final int[] IMAGES = new int[]{R.mipmap.a1,
            R.mipmap.a2, R.mipmap.a3, R.mipmap.a4, R.mipmap.a5, R.mipmap.a6};

    @Override
    protected void findViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_view_pager);
        viewPager = (MyViewPager) findViewById(R.id.viewPager);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
    }

    @Override
    protected void initViews() {
        for (int i=0,ii=IMAGES.length;i<ii;i++){
            ImageView view = new ImageView(this);
            view.setImageResource(IMAGES[i]);
            viewPager.addView(view);
        }
        View view = View.inflate(this, R.layout.view_test, null);
        viewPager.addView(view, 2);

        for (int i=0,ii=viewPager.getChildCount();i<ii;i++){
            RadioButton radio = new RadioButton(this);
            radio.setId(i);
            if(i == 0)
                radio.setChecked(true);
            radioGroup.addView(radio);
        }
    }

    @Override
    protected void initListener() {
        viewPager.setOnPageChangeListener(new MyViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                radioGroup.check(position);
            }
        });
    }
}
