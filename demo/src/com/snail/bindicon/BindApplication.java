package com.snail.bindicon;

import android.app.Application;
import com.snail.bind.BindManager;
import com.snail.bindicon.ui.MainActivity;

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
        BindManager.getInstance().initBindView(getApplicationContext(), MainActivity.class, MainActivity.class, MainActivity.class);
    }
}
