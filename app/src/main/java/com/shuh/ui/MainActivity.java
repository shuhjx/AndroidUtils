package com.shuh.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.shuh.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends BaseActivity{

    private FloatingActionButton fab;
    private Toolbar toolbar;
    private ListView listView;
    private ArrayList<HashMap<String, String>> list;

    @Override
    protected void findViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.listView);
    }
    private static final String NAME_KEY = "name_listView";
    private static final String CLASS_KEY = "class_listView";
    @Override
    protected void initViews() {
        list = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("自定义ViewPage", "com.shuh.ui.ViewPageActivity");
        hashMap.put("自定义开关", "com.shuh.ui.SwitchButtonActivity");
        hashMap.put("ActiveAndroid插件", "com.shuh.ui.ActiveAndroidActivity");

        Iterator mIterator = hashMap.entrySet().iterator();
        while (mIterator.hasNext()){
            Map.Entry entry = (Map.Entry) mIterator.next();
            HashMap<String, String> map = new HashMap<>();
            map.put(NAME_KEY, (String) entry.getKey());
            map.put(CLASS_KEY, (String) entry.getValue());
            list.add(map);
        }

        listView.setAdapter(new SimpleAdapter(this, list, android.R.layout.simple_list_item_1,
                new String[]{NAME_KEY}, new int[]{android.R.id.text1}));
    }

    @Override
    protected void initListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClassName(MainActivity.this, list.get(position).get(CLASS_KEY));
                startActivity(intent);
            }
        });
    }


}
