package com.snail.bindicon.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.snail.bindicon.ui.BindView;
import com.snail.bindicon.util.ConfigurationUtil;

public class ScreenStateReceiver extends BroadcastReceiver {

    public static final String BIND_CHANGED_INTENT = "android.intent.bind.changed";
    private static final String TAG = ScreenStateReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (BIND_CHANGED_INTENT.equals(intent.getAction())) {
            boolean bindContact = ConfigurationUtil.getBindPreference().getBoolean(ConfigurationUtil.BIND_CONTACT, false);
            boolean bindDial = ConfigurationUtil.getBindPreference().getBoolean(ConfigurationUtil.BIND_DIAL, false);
            boolean bindSms = ConfigurationUtil.getBindPreference().getBoolean(ConfigurationUtil.BIND_SMS, false);
            BindView.showBindView(context, bindContact, bindDial, bindSms);
        }
    }
}
