package com.shuh.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.shuh.ui.holder.BaseHolder;
import com.shuh.ui.utils.PP;

import java.util.List;

/**
 * Created by Administrator on 2016/6/4 0004.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {

    protected Context context;
    protected List<T> list;

    protected void init(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

    protected View setHolder(Class<? extends BaseHolder> cls, View view, int resId) {
        if (view == null)
            view = setHolder(cls, view, LayoutInflater.from(context).inflate(resId, null));
        return view;
    }

    protected View setHolder(Class<? extends BaseHolder> cls, View view, View itemView) {
        try {
            if (view == null) {
                view = itemView;
                BaseHolder holder = cls.newInstance();
                holder.init(view);
                view.setTag(holder);
            }
        } catch (InstantiationException e) {
            PP.e("error", e);
        } catch (IllegalAccessException e) {
            PP.e("error", e);
        }
        return view;
    }
}
