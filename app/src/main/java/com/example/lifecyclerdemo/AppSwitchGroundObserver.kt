package com.example.lifecyclerdemo

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.hjq.toast.ToastUtils

//线程安全的懒汉式单例
class AppSwitchGroundObserver private constructor():LifecycleEventObserver {
    companion object{
        private var instance:AppSwitchGroundObserver?=null
        get() {
            if(field==null){
                field = AppSwitchGroundObserver()
            }
            return field
        }
        @Synchronized
        fun getIntance():AppSwitchGroundObserver{
            return instance!!
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event){
            //应用处于前台
            Lifecycle.Event.ON_RESUME ->{
                ToastUtils.show("当前APP处于前台")
            }
            //应用处于后台
            Lifecycle.Event.ON_STOP ->{
                ToastUtils.show("当前APP处于后台")
            }
        }
    }
}