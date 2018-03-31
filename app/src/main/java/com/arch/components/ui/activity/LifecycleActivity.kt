package com.arch.components.ui.activity

import android.arch.lifecycle.*
import android.os.Bundle
import com.arch.components.R

class LifecycleActivity : BaseActivity() , LifecycleObserver{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)

        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun viewCreate(){
        showToast("ON_CREATE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun viewStart(){
        showToast("ON_START")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun viewResume(){
        showToast("ON_RESUME")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun viewPause(){
        showToast("ON_PAUSE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun viewStop(){
        showToast("ON_STOP")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun viewDestroy(){
        showToast("ON_DESTROY")
    }
}
