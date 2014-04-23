package com.snail.bind;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 绑定设置
 *
 * @author liusz
 */
public class BindConfig {

    public static final String BIND = "bind";

    public static final String BIND_CONTACT = "bind_contact";
    public static final String BIND_DIAL = "bind_dial";
    public static final String BIND_SMS = "bind_sms";

    public static SharedPreferences.Editor getBindEditor(Context context) {
        SharedPreferences settings = getBindPreference(context);
        return settings.edit();
    }

    public static SharedPreferences getBindPreference(Context context) {
        return context.getSharedPreferences(BIND, 0);
    }


    /**
     * 获取是否绑定系统联系人按钮
     *
     * @param context
     * @return true ： 绑定成功， false : 绑定失败
     */
    public static boolean bindContact(Context context) {
        return getBindPreference(context).getBoolean(BIND_CONTACT, false);
    }

    /**
     * 获取是否绑定系统拨号按钮
     *
     * @param context
     * @return true ： 绑定成功， false : 绑定失败
     */
    public static boolean bindDial(Context context) {
        return getBindPreference(context).getBoolean(BIND_DIAL, false);
    }

    /**
     * 获取是否绑定系统短信按钮
     *
     * @param context
     * @return true ： 绑定成功， false : 绑定失败
     */
    public static boolean bindSms(Context context) {
        return getBindPreference(context).getBoolean(BIND_SMS, false);
    }


    /**
     * 设置是否绑定系统联系人按钮
     *
     * @param context
     * @param bool    true ： 绑定联系人， false : 取消绑定
     */
    public static void setBindContact(Context context, boolean bool) {
        getBindEditor(context).putBoolean(BIND_CONTACT, bool).apply();
        BindManager.getInstance().showBindView(context);
    }

    /**
     * 设置是否绑定系统拨号按钮
     *
     * @param context
     * @param bool    true ： 绑定拨号， false : 取消绑定
     */
    public static void setBindDial(Context context, boolean bool) {
        getBindEditor(context).putBoolean(BIND_DIAL, bool).apply();
        BindManager.getInstance().showBindView(context);
    }

    /**
     * 设置是否绑定系统短信按钮
     *
     * @param context
     * @param bool    true ： 绑定短信， false : 取消绑定
     */
    public static void setBindSms(Context context, boolean bool) {
        getBindEditor(context).putBoolean(BIND_SMS, bool).apply();
        BindManager.getInstance().showBindView(context);
    }

}
