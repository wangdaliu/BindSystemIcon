package com.snail.bindicon.ui;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.snail.bindicon.BindApplication;

import java.util.HashSet;
import java.util.List;

public class BindHandlerThread extends HandlerThread implements Handler.Callback {

    private static final String TAG = BindHandlerThread.class.getSimpleName();
    private Handler mHandler;
    private static final int BIND_MESSAGE = 2013;

    public static final String BIND_DIAL_ACTION = "android.intent.action.DIAL";
    public static final String BIND_CONTACT_ACTION = "android.intent.action.CONTACT";
    public static final String BIND_SMS_ACTION = "android.intent.action.SMS";
    private Context mContext;

    private HashSet contactSet = new HashSet();
    private HashSet callSet = new HashSet();
    private HashSet smsSet = new HashSet();


    public BindHandlerThread(String param, Context context) {
        super(param);
        mContext = context;
    }

    public boolean operate() {
        if (this.mHandler == null) {
            return false;
        }
        this.mHandler.removeMessages(BIND_MESSAGE);
        ActivityManager activityManager = BindView.getActivityManager();
        List localList = activityManager.getRunningTasks(1);
        if ((localList != null) && (localList.size() > 0)) {
            mHandler.sendEmptyMessageDelayed(BIND_MESSAGE, 150L);
            mHandler.sendEmptyMessageDelayed(BIND_MESSAGE, 200L);
            mHandler.sendEmptyMessageDelayed(BIND_MESSAGE, 300L);
            mHandler.sendEmptyMessageDelayed(BIND_MESSAGE, 500L);
        }
        return true;
    }

    @Override
    protected void onLooperPrepared() {
        this.mHandler = new Handler(getLooper(), this);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (BindView.keyBack) {
            return true;
        }

        ActivityManager activityManager = BindView.getActivityManager();
        List localList = activityManager.getRunningTasks(1);

        if ((localList != null) && (localList.size() > 0)) {
            String className = ((ActivityManager.RunningTaskInfo) localList.get(0)).topActivity.getClassName();

            if (BindView.bindContact && contactSet.contains(className)) {
                startSnailIm(BIND_CONTACT_ACTION);
                return true;
            }

            if (BindView.bindCall && callSet.contains(className)) {
                startSnailIm(BIND_DIAL_ACTION);
                return true;
            }

            if (BindView.bindSms && smsSet.contains(className)) {
                startSnailIm(BIND_SMS_ACTION);
                return true;
            }
        }
        return false;
    }

    private void startSnailIm(String action) {
        Intent intent = new Intent();
        intent.setClass(mContext, MainActivity.class);
        intent.setAction(action);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    private void convertSet(Intent paramIntent, HashSet paramHashSet, boolean paramBoolean) {
        List<ResolveInfo> resolveInfos = BindApplication.getInstance().getPackageManager().queryIntentActivities(paramIntent, PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo resolveInfo : resolveInfos) {

            // Interpretation is the third party application
            if ((resolveInfo.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                continue;
            }
            if (!paramBoolean) {
                paramHashSet.add(resolveInfo.activityInfo.name);
                if ((resolveInfo.activityInfo.targetActivity != null) && (resolveInfo.activityInfo.targetActivity.length() > 1)) {
                    paramHashSet.add(resolveInfo.activityInfo.targetActivity);
                }
            }
        }
    }

    public void initHashSet() {
        // init callset
        Intent localIntent1 = new Intent();
        localIntent1.setAction("android.intent.action.CALL_BUTTON");
        convertSet(localIntent1, this.callSet, false);
        Intent localIntent2 = new Intent();
        localIntent2.setAction("android.intent.action.DIAL");
        convertSet(localIntent2, this.callSet, false);
        Intent localIntent3 = new Intent();
        localIntent3.setAction("android.intent.action.CALL");
        convertSet(localIntent3, this.callSet, false);

        // init callset
        Intent localIntent4 = new Intent();
        localIntent4.setAction("android.intent.action.VIEW");
        localIntent4.setType("vnd.android.cursor.dir/contact");
        convertSet(localIntent4, contactSet, false);
        Intent localIntent5 = new Intent("android.intent.action.VIEW");
        localIntent5.setType("vnd.android.cursor.dir/person");
        convertSet(localIntent5, contactSet, false);
//        contactSet.add("com.meizu.mzsnssyncservice.ui.SnsTabActivity");
//        contactSet.add("com.sec.android.app.contacts.PhoneBookTopMenuActivity");

        // init smsSet
        Intent localIntent6 = new Intent();
        localIntent6.setAction("android.intent.action.MAIN");
        localIntent6.setType("vnd.android.cursor.dir/mms");
        convertSet(localIntent6, smsSet, false);
        Intent localIntent7 = new Intent("android.intent.action.VIEW");
        localIntent7.setType("vnd.android-dir/mms-sms");
        convertSet(localIntent7, smsSet, false);

//        smsSet.add("com.android.mms");
//        smsSet.add("com.huawei.message");
//        smsSet.add("com.sonyericsson.conversations");
//        smsSet.add("com.motorola.blur.conversations");


    }


}
