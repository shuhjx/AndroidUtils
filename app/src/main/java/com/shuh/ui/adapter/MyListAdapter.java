package com.shuh.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.shuh.ui.holder.BaseHolder;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/4 0004.
 */
public class MyListAdapter<T> extends MyBaseAdapter<T> {

    private Class<? extends BaseHolder> cls;
    private int resId = -1;
    private View itemView;
    public MyListAdapter(Context context, List<T> list, Class<? extends BaseHolder> cls, int resId){
        init(context, list);
        this.cls = cls;
        this.resId = resId;
    }

    public MyListAdapter(Context context, List<T> list, Class<? extends BaseHolder> cls, View itemView){
        init(context, list);
        this.cls = cls;
        this.itemView = itemView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(resId != -1){
            convertView = setHolder(cls, convertView, resId);
        }else if(itemView != null){
            convertView = setHolder(cls, convertView, itemView);
        }
        BaseHolder holder = (BaseHolder) convertView.getTag();
        holder.setUI(list.get(position));
        return convertView;
    }

}
