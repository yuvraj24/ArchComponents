package com.arch.components.ui.activity

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.OnLifecycleEvent
import android.os.Bundle
import com.arch.components.R

class LifecycleActivity : BaseActivity(), LifecycleObserver {

    /*
     * LifecycleRegistry can be used to mark the state of the view in stack.
     * It take a parameter of Lifecycle Owner.
     * */
    private val mLifecycleRegistry = LifecycleRegistry(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)

        /* Here getLifecycle() is a function of AppCompatActivity class which is used to get the
           instance of LifecycleRegistry class. So we can now subscribe to it for observing to any state change
           by below line of code.
         */
        lifecycle.addObserver(this)

        /**
         * Moves the Lifecycle to the given state and dispatches necessary events to the observers.
         * You can use this if you want to change state and execute some logic.
         * @param state new state
         */
        // mLifecycleRegistry.markState(Lifecycle.State.CREATED)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun viewCreate() {
        showToast("ON_CREATE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun viewStart() {
        showToast("ON_START")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun viewResume() {
        showToast("ON_RESUME")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun viewPause() {
        showToast("ON_PAUSE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun viewStop() {
        showToast("ON_STOP")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun viewDestroy() {
        showToast("ON_DESTROY")
    }
}
