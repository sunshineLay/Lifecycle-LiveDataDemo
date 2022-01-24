package com.example.lifecyclerdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


public class JumpUtil {

    /**
     * 不带参数的跳转
     *
     * @param context
     * @param targetClazz
     */
    public static void overlay(Context context, Class<? extends Activity> targetClazz) {
        Intent mIntent = new Intent(context, targetClazz);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mIntent);
    }

    /**
     * 带参数的跳转
     *
     * @param context
     * @param targetClazz
     * @param bundle
     */
    public static void overlay(Context context, Class<? extends Activity> targetClazz, Bundle bundle) {
        Intent mIntent = new Intent(context, targetClazz);
        if (bundle != null) {
            mIntent.putExtras(bundle);
            mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        }
        context.startActivity(mIntent);
    }

    /**
     * @param context
     * @param targetClazz
     * @param bundle
     * @param flags
     */
    public static void overlay(Context context, Class<? extends Activity> targetClazz, Bundle bundle, Integer flags) {
        Intent mIntent = new Intent(context, targetClazz);
        if (bundle != null) {
            mIntent.putExtras(bundle);
        }
        if (flags != null) {
            mIntent.setFlags(flags);
        }
        context.startActivity(mIntent);
    }




    /**
     * @param context
     * @param targetClazz
     * @param requestCode
     * @param bundle
     */
    public static void startForResult(Activity context, Class<? extends Activity> targetClazz, int requestCode, Bundle bundle) {
        Intent mIntent = new Intent(context, targetClazz);
        if (bundle != null) {
            mIntent.putExtras(bundle);
        }
        context.startActivityForResult(mIntent, requestCode);
    }

    /**
     * @param fragment
     * @param targetClazz
     * @param requestCode
     * @param bundle
     */
    public static void startForResult(Fragment fragment, Class<? extends Activity> targetClazz, int requestCode, Bundle bundle) {
        Intent mIntent = new Intent(fragment.getActivity(), targetClazz);
        if (bundle != null) {
            mIntent.putExtras(bundle);
        }
        fragment.startActivityForResult(mIntent, requestCode);
    }
}
