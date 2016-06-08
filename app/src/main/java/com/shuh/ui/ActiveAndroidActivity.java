package com.shuh.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.activeandroid.query.Select;
import com.shuh.ui.adapter.MyListAdapter;
import com.shuh.ui.base.BaseActivity;
import com.shuh.ui.holder.UserHolder;
import com.shuh.ui.model.Address;
import com.shuh.ui.model.User;

import java.util.List;

/**
 * Created by Administrator on 2016/6/4 0004.
 */
public class ActiveAndroidActivity extends BaseActivity implements View.OnClickListener {

    private ListView listView;
    private Button btnAdd;
    private List<User> users;
    private MyListAdapter adapter;

    @Override
    protected void findViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_active);
        listView = (ListView) findViewById(R.id.list_view);
        btnAdd = (Button) findViewById(R.id.btn_add);
    }

    @Override
    protected void initViews() {
        users = new Select().all().from(User.class).execute();
        adapter = new MyListAdapter(this, users, UserHolder.class, R.layout.item_user);
        listView.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        case R.id.btn_add:
            User user = new User();
            user.setLogin("shuh" + users.size());
            user.setEmail("shuh_jx" + users.size() + "@163.com");
            user.setPassword("123456" + users.size());
            user.save();
            Address address = new Address();
            address.setAddr("AAAAAAAAAAAAAAAA" + users.size());
            address.setPhone("13154864" + users.size());
            address.setTag("TTTTTTTT" + users.size());
            address.setUser(user);
            address.save();

            users.add(user);
            adapter.notifyDataSetChanged();
            break;
        }
    }
}
