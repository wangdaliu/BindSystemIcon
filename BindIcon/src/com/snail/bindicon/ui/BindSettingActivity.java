package com.snail.bindicon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import com.snail.bindicon.R;
import com.snail.bindicon.receiver.ScreenStateReceiver;
import com.snail.bindicon.util.ConfigurationUtil;

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

        bindContact.setChecked(ConfigurationUtil.getBindPreference().getBoolean(ConfigurationUtil.BIND_CONTACT, false));
        bindCall.setChecked(ConfigurationUtil.getBindPreference().getBoolean(ConfigurationUtil.BIND_DIAL, false));
        bindMms.setChecked(ConfigurationUtil.getBindPreference().getBoolean(ConfigurationUtil.BIND_SMS, false));
    }

    @Override
    public void onCheckedChanged(CompoundButton obj, boolean b) {
        switch (obj.getId()) {
            case R.id.bind_contact:
                ConfigurationUtil.getBindEditor().putBoolean(ConfigurationUtil.BIND_CONTACT, b).apply();
                break;
            case R.id.bind_call:
                ConfigurationUtil.getBindEditor().putBoolean(ConfigurationUtil.BIND_DIAL, b).apply();
                break;
            case R.id.bind_mms:
                ConfigurationUtil.getBindEditor().putBoolean(ConfigurationUtil.BIND_SMS, b).apply();
                break;
            default:
                break;
        }
        sendBindChangedReceiver();
    }

    private void sendBindChangedReceiver() {
        Intent intent = new Intent();
        intent.setAction(ScreenStateReceiver.BIND_CHANGED_INTENT);
        sendBroadcast(intent);
    }
}
