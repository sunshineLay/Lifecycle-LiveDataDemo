package com.example.lifecyclerdemo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

public class Test implements LifecycleEventObserver , DefaultLifecycleObserver {

    private static final String TAG;

    static {
        TAG = Test.class.getSimpleName();
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        switch (event){
            case ON_CREATE:
                Log.e(TAG, "onStateChanged: "+1);
                break;
            case ON_DESTROY:
                Log.e(TAG, "onStateChanged: "+2);
                break;
            default:
                break;
        }
    }


}
