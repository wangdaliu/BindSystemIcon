package com.snail.bindicon.util;

import android.content.SharedPreferences;
import com.snail.bindicon.BindApplication;

public class ConfigurationUtil {

    public static final String BIND = "bind";

    public static final String BIND_CONTACT = "bind_contact";
    public static final String BIND_DIAL = "bind_dial";
    public static final String BIND_SMS = "bind_sms";

    public static SharedPreferences.Editor getBindEditor() {
        SharedPreferences settings = getBindPreference();
        return settings.edit();
    }

    public static SharedPreferences getBindPreference() {
        return BindApplication.getInstance().getSharedPreferences(BIND, 0);
    }

}
