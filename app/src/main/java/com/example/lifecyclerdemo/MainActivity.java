package com.example.lifecyclerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lifecyclerdemo.utils.JumpUtil;

//码牛 - 学习 Lifecycle
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static MutableLiveData<String> stringLiveData;
    private int i = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stringLiveData = new MutableLiveData<>();
        stringLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.e(TAG, "onChanged: "+s );
            }
        });
//        stringLiveData.observeForever(new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//
//            }
//        });

    }

    private void zithread() {
        stringLiveData.setValue("setLay");
        new Thread(){
            @Override
            public void run() {
                super.run();
                String s = "postLay";
                i++;
                stringLiveData.postValue(s+i);
//                stringLiveData.postValue(s+"-two");
//                stringLiveData.postValue(s+"-three");
            }
        }.start();
    }

    private void mainthread() {
        String s = "lay";
        i++;
        stringLiveData.setValue(s+i);
    }

    public void postValue(View view) {
        //子线程
        zithread();
//        mainthread();
    }

    public void jumpMain2(View view) {
        JumpUtil.overlay(this,Main2Activity.class);
    }
}