package com.shuh.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016/4/29 0029.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews(savedInstanceState);
        initViews();
        initListener();
    }

    protected abstract void findViews(Bundle savedInstanceState);
    protected abstract void initViews();
    protected abstract void initListener();
}
