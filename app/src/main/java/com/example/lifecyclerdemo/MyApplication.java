package com.example.lifecyclerdemo;

import android.app.Application;

import androidx.lifecycle.ProcessLifecycleOwner;

import com.hjq.toast.ToastUtils;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.init(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(AppSwitchGroundObserver.Companion.getIntance());
    }
}
