package com.shuh.ui.holder;

import android.view.View;

import com.shuh.ui.model.BaseModel;

/**
 * Created by Administrator on 2016/6/4 0004.
 */
public abstract class BaseHolder {
    public abstract void init(View view);
    public abstract void setUI(Object model);
}
