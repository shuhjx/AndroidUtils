package com.shuh.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.shuh.ui.base.BaseActivity;
import com.shuh.ui.widgets.SwitchButton;

/**
 * Created by Administrator on 2016/5/5 0005.
 */
public class SwitchButtonActivity extends BaseActivity {
    private SwitchButton switchButton;
    @Override
    protected void findViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_switch_button);
        switchButton = (SwitchButton) findViewById(R.id.switchButton);
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initListener() {
        switchButton.setOnCheckChangedListener(new SwitchButton.OnCheckChangedListener() {
            @Override
            public void onCheckChanged(View view, boolean isChecked) {
                Toast.makeText(SwitchButtonActivity.this, "" + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
