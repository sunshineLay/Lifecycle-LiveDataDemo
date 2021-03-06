package com.example.lifecyclerdemo;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class NonStickyMutableLiveData<T> extends MutableLiveData {
    private boolean stickyFlag = false;//是否粘性，默认为非粘性
    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer observer) {
        super.observe(owner, observer);
        if(!stickyFlag){
            hook(observer);
            stickyFlag = true;
        }
    }
    // 利用反射，改变mLastVersion的值。
    // 使得observer.mLastVersion >= mVersion成立。
    private void hook(Observer<? super T> observer) {
        try {
            //得到变量 mObservers
            Class<LiveData> liveDataClass = LiveData.class;
            Field mObserversFild = liveDataClass.getDeclaredField("mObservers");
            mObserversFild.setAccessible(true);

            //通过变量得到对象
            Object mObserversObject = mObserversFild.get(this);
            //通过对象得到class对象
            Class<?> mObserversClass = mObserversObject.getClass();
            //得到方法get
            Method get = mObserversClass.getDeclaredMethod("get", Object.class);
            get.setAccessible(true);
            //执行方法get,拿到ObserverWrapper对象.
            //注意这里执行get方法的变成了对象mObserversObject，而不是class对象
            //方法执行的返回值结果是可以直接使用的Java对象
            Object invokeEntry = get.invoke(mObserversObject, observer);
            Object observerWraper = null;
            if(invokeEntry!=null && invokeEntry instanceof Map.Entry){
                observerWraper = ((Map.Entry) invokeEntry).getValue();
            }
            if(observerWraper == null){
                throw new NullPointerException("observerWraper is null!");
            }
            //拿到mLastVersion
            Class<?> superclass = observerWraper.getClass().getSuperclass();
            Field mLastVersion = superclass.getDeclaredField("mLastVersion");
            mLastVersion.setAccessible(true);
            //拿到mVersion
            Field mVersion = liveDataClass.getDeclaredField("mVersion");
            mVersion.setAccessible(true);
            //拿到mVersion值,得到对象和值都是用get
            Object mVersionValue = mVersion.get(this);
            mLastVersion.set(observerWraper,mVersionValue);


        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
