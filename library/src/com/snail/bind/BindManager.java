package com.snail.bind;

import android.content.Context;
import android.content.Intent;

public class BindManager {

    private static BindManager bindManager = new BindManager();

    private Class contactClass, dialClass, smsClass;

    public Class getContactClass() {
        return contactClass;
    }

    public Class getDialClass() {
        return dialClass;
    }

    public Class getSmsClass() {
        return smsClass;
    }

    private BindManager() {
    }

    public static BindManager getInstance() {
        return bindManager;
    }

    /**
     * 在Application中调用，初始化BindView
     * @param context
     * @param contactClass 需要跳转联系人界面的class name
     * @param dialClass    需要跳转电话界面的class name
     * @param smsClass     需要跳转短信界面的class name
     */
    public void initBindView(Context context, Class contactClass, Class dialClass, Class smsClass) {
        this.contactClass = contactClass;
        this.dialClass = dialClass;
        this.smsClass = smsClass;
        showBindView(context);
    }

    /**
     * 显示BindView，在程序运行时以及设置绑定内容后调用
     * @param context
     */
    public void showBindView(Context context) {
        boolean bindContact = BindConfig.getBindPreference(context).getBoolean(BindConfig.BIND_CONTACT, false);
        boolean bindDial = BindConfig.getBindPreference(context).getBoolean(BindConfig.BIND_DIAL, false);
        boolean bindSms = BindConfig.getBindPreference(context).getBoolean(BindConfig.BIND_SMS, false);
        BindView.showBindView(context, bindContact, bindDial, bindSms);
    }

    /**
     * 点击返回键，需要调用该方法
     * @param context
     */
    public void onKeyBack(Context context){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        context.startActivity(intent);
    }
}
