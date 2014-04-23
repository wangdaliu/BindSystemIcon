package com.snail.bindicon.ui;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import com.snail.bind.BindConfig;
import com.snail.bind.BindManager;
import com.snail.bindicon.R;

public class BindSettingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    private Switch bindContact;
    private Switch bindCall;
    private Switch bindMms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binding_layout);

        bindContact = finder.find(R.id.bind_contact);
        bindCall = finder.find(R.id.bind_call);
        bindMms = finder.find(R.id.bind_mms);

        bindContact.setOnCheckedChangeListener(this);
        bindCall.setOnCheckedChangeListener(this);
        bindMms.setOnCheckedChangeListener(this);

        bindContact.setChecked(BindConfig.bindContact(BindSettingActivity.this));
        bindCall.setChecked(BindConfig.bindDial(BindSettingActivity.this));
        bindMms.setChecked(BindConfig.bindSms(BindSettingActivity.this));
    }

    @Override
    public void onCheckedChanged(CompoundButton obj, boolean b) {
        switch (obj.getId()) {
            case R.id.bind_contact:
                BindConfig.setBindContact(BindSettingActivity.this, b);
                break;
            case R.id.bind_call:
                BindConfig.setBindDial(BindSettingActivity.this, b);
                break;
            case R.id.bind_mms:
                BindConfig.setBindSms(BindSettingActivity.this, b);
                break;
            default:
                break;
        }
    }

}
