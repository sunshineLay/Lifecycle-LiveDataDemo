package com.example.lifecyclerdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

public class Main3Activity extends AppCompatActivity {
    //map
    private MutableLiveData<String> strLiveData = new MutableLiveData<>();
    private static final String TAG = Main3Activity.class.getSimpleName();
    //switchMap
    private MutableLiveData<String> str01LiveData = new MutableLiveData<>();
    private MutableLiveData<String> str02LiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> bool02LiveData = new MutableLiveData<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main3_activity);
        strLiveData.setValue("strLiveData");
        str01LiveData.setValue("str01LiveData");
        str02LiveData.setValue("str02LiveData");
        bool02LiveData.setValue(false);
    }

    public void mapValue(View view) {
        //写法一
        LiveData<Object> map = Transformations.map(strLiveData, new Function<String, Object>() {
            @Override
            public Object apply(String name) {
                return name + ":map";
            }
        });
        map.observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                Log.e(TAG, "onChanged: map - "+o.toString());
            }
        });
        //Lambda
//        Transformations.map(strLiveData,name ->{
//            return name + ":map";
//        });
    }

    public void switchMapValue(View view) {
        //写法一
        LiveData<String> switchMap = Transformations.switchMap(bool02LiveData, new Function<Boolean, LiveData<String>>() {
            @Override
            public LiveData<String> apply(Boolean input) {
                if (input) {
                    return str01LiveData;
                } else {
                    return str02LiveData;
                }
            }
        });
        switchMap.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.e(TAG, "onChanged: switchMap - "+s);
            }
        });
        //Lambda
//        LiveData switchMap = Transformations.switchMap(bool02LiveData, input -> {
//            if (input) {
//                return str01LiveData;
//            } else {
//                return str02LiveData;
//            }
//
//        });
    }
}
