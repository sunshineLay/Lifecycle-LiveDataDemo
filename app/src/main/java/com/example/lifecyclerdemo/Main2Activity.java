package com.example.lifecyclerdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = Main2Activity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2_activity);
        Log.e(TAG, "onCreate: " );
//        MainActivity.stringLiveData.observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                Log.e(TAG, "onChanged-Main2: "+s );
//            }
//        });
        //由于这个内容是通过Lifecycle传递过来的。
        //所以这个内容会在ON_START之后执行。
        LiveDataBus.getInstance().with("msg",String.class,false)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Log.e(TAG, "onChanged: "+s);
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: " );
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " );
    }
}
