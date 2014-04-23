package com.snail.bind;

import android.app.ActivityManager;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class BindView extends View {

	private static final String TAG = BindView.class.getSimpleName();
	private static BindView bindView = null;
	private static WindowManager windowManager = null;
	private static ActivityManager activityManager = null;
	private BindHandlerThread handlerThread;
	private Context mContext;
	private static boolean bindViewShown = false;

	public static boolean keyBack = false;

	public static boolean bindContact = false;
	public static boolean bindCall = false;
	public static boolean bindSms = false;

	public static ActivityManager getActivityManager() {
		return activityManager;
	}

	public BindView(Context context) {
		super(context);
		windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		this.mContext = context;
		handlerThread = new BindHandlerThread("mHandler", context);
        handlerThread.initHashSet();

		handlerThread.start();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (!keyBack) {
			handlerThread.operate();
		}
		return super.onTouchEvent(event);
	}


	public static void showBindView(Context context, boolean... bind) {
		if (null == bindView) {
			bindView = new BindView(context);
		}
		bindContact = bind[0];
		bindCall = bind[1];
		bindSms = bind[2];

		BindView localView;
		WindowManager.LayoutParams localLayoutParams;
		if (!bind[0] && !bind[1] && !bind[2] && bindViewShown) {
			windowManager.removeView(bindView);
			bindViewShown = false;
		} else {
			if ((bind[0] || bind[1] || bind[2]) && !bindViewShown) {
				localView = bindView;
				localView.setBackgroundColor(0);
				localLayoutParams = new WindowManager.LayoutParams();
				localLayoutParams.width = 1;
				localLayoutParams.height = 1;
				localLayoutParams.gravity = 51;
				localLayoutParams.x = 0;
				localLayoutParams.y = 0;
				localLayoutParams.format = 1;
				localLayoutParams.type = 2010;
				localLayoutParams.flags = 262152;
				windowManager.addView(localView, localLayoutParams);
				bindViewShown = true;
			}
		}
	}
}
