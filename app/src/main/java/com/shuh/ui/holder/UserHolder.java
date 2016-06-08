package com.shuh.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.shuh.ui.R;
import com.shuh.ui.model.Address;
import com.shuh.ui.model.BaseModel;
import com.shuh.ui.model.User;
import com.shuh.ui.utils.PP;

import java.util.List;

/**
 * Created by Administrator on 2016/6/4 0004.
 */
public class UserHolder extends BaseHolder {
    private TextView login, email, address, phone;

    @Override
    public void init(View view) {
        login = (TextView) view.findViewById(R.id.tv_login);
        email = (TextView) view.findViewById(R.id.tv_email);
        address = (TextView) view.findViewById(R.id.tv_address);
        phone = (TextView) view.findViewById(R.id.tv_phone);
    }

    @Override
    public void setUI(Object model) {
        User user = (User) model;
        login.setText(user.getLogin());
        email.setText(user.getEmail());
        List<Address> list = user.addresses();
        if(list == null || list.isEmpty()){
            address.setText("");
            phone.setText("");
        }else{
            Address addr = list.get(0);
            address.setText(addr.getAddr());
            phone.setText(addr.getPhone());
        }

    }
}
