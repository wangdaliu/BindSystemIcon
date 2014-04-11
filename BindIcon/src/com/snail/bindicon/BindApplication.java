package com.snail.bindicon;

import android.app.Application;
import com.snail.bindicon.ui.BindView;
import com.snail.bindicon.util.ConfigurationUtil;

public class BindApplication extends Application {

    private static BindApplication mApplication;


    public static BindApplication getInstance() {
        return mApplication;
    }

    public BindApplication() {
        BindApplication.mApplication = this;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        // initial
        boolean bindContact = ConfigurationUtil.getBindPreference().getBoolean(ConfigurationUtil.BIND_CONTACT, false);
        boolean bindDial = ConfigurationUtil.getBindPreference().getBoolean(ConfigurationUtil.BIND_DIAL, false);
        boolean bindSms = ConfigurationUtil.getBindPreference().getBoolean(ConfigurationUtil.BIND_SMS, false);
        BindView.showBindView(getApplicationContext(), bindContact, bindDial, bindSms);

    }
}
